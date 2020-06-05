package com.zhou.springbootfreemarker.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.zhou.springbootfreemarker.entity.JavaClassInfo;
import com.zhou.springbootfreemarker.entity.JavaColumnInfo;
import com.zhou.springbootfreemarker.entity.TableInfo;
import com.zhou.springbootfreemarker.service.TableInfoService;
import com.zhou.springbootfreemarker.utils.MyFileVisitor;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/10 5:23 下午
 */
@RestController
@RequestMapping("/tableInfo")
public class TableInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableInfoController.class);

    private static final String BASE_CODE_PATH = "code";

    private static final String ENTITY_TEMPLATE_NAME = "entity.java.ftl";

    private static final String MAPPER_JAVA_TEMPLATE_NAME = "mapper.java.ftl";

    private static final String MAPPER_XML_TEMPLATE_NAME = "mapper.xml.ftl";

    private static final String SERVICE_TEMPLATE_NAME = "service.java.ftl";

    private static final String IMPL_TEMPLATE_NAME = "impl.java.ftl";

    private static final String JAVA_SUFFIX = ".java";

    private static final String MAPPER_JAVA_SUFFIX = "Mapper.java";

    private static final String MAPPER_XML_SUFFIX = "Mapper.xml";

    private static final String SERVICE_SUFFIX = "Service.java";

    private static final String IMPL_SUFFIX = "ServiceImpl.java";

    @Resource
    private TableInfoService tableInfoService;

    @GetMapping("/exportCode")
    public Object exportCode(@RequestParam(value = "packageName") String packageName, HttpServletResponse response){
        OutputStream out = null;
        BufferedInputStream in = null;
        File zip = null;
        try {
            List<TableInfo> tableInfos = this.tableInfoService.showAllTableInfo();
            Map<String, List<TableInfo>> tableInfosMap = tableInfos.stream().collect(Collectors.groupingBy(TableInfo::getTableName));
            Set<String> tableNames = tableInfosMap.keySet();
            List<JavaClassInfo> classInfoList = tableNames.stream().map(tableName -> {
                JavaClassInfo javaClassInfo = new JavaClassInfo();
                javaClassInfo.setClassName(StringUtils.capitalize(StrUtil.toCamelCase(tableName)));
                javaClassInfo.setTableName(tableName);
                List<TableInfo> tables = tableInfosMap.get(tableName);
                javaClassInfo.setComment(tables.get(0).getTableComment());
                javaClassInfo.setImportBigDecimal(false);
                javaClassInfo.setImportDate(false);
                javaClassInfo.setIncludePk(false);
                //设置字段信息
                List<JavaColumnInfo> columnInfoList = tables.stream().map(tableInfo -> {
                    JavaColumnInfo javaColumnInfo = new JavaColumnInfo();
                    javaColumnInfo.setColumnName(tableInfo.getColumnName());
                    if (tableInfo.getColumnKey()){
                        javaColumnInfo.setIsPk(true);
                    }else {
                        javaColumnInfo.setIsPk(false);
                    }
                    if(javaColumnInfo.getIsPk()){
                        javaClassInfo.setIncludePk(true);
                    }
                    javaColumnInfo.setJavaProperties(StrUtil.toCamelCase(tableInfo.getColumnName()));
                    javaColumnInfo.setComment(tableInfo.getColumnComment());
                    String dataType = tableInfo.getDataType();
                    if (dataType.equals("varchar") || dataType.equals("text")
                            || dataType.equals("char") || dataType.equals("tinytext")
                            || dataType.equals("mediumtext") || dataType.equals("longtext")) {
                        javaColumnInfo.setJavaType("String");
                    }
                    if (dataType.equals("date") || dataType.equals("datetime") || dataType.equals("timestamp")) {
                        javaColumnInfo.setJavaType("Date");
                        javaClassInfo.setImportDate(true);
                    }
                    if (dataType.equals("int") || dataType.equals("tinyint")
                            || dataType.equals("smallint") || dataType.equals("mediumint")
                            || dataType.equals("integer") || dataType.equals("bigint")) {
                        javaColumnInfo.setJavaType("Integer");
                    }
                    if (dataType.equals("float")){
                        javaColumnInfo.setJavaType("Float");
                    }
                    if (dataType.equals("bit")){
                        javaColumnInfo.setJavaType("Boolean");
                    }
                    if (dataType.equals("double")) {
                        javaColumnInfo.setJavaType("Double");
                    }
                    if (dataType.equals("decimal")){
                        javaColumnInfo.setJavaType("BigDecimal");
                        javaClassInfo.setImportBigDecimal(true);
                    }
                    return javaColumnInfo;
                }).collect(Collectors.toList());
                javaClassInfo.setJavaColumnInfos(columnInfoList);
                return javaClassInfo;
            }).collect(Collectors.toList());

            String entityPath = BASE_CODE_PATH + File.separator + "entity";
            createCode(entityPath, JAVA_SUFFIX, ENTITY_TEMPLATE_NAME, classInfoList, packageName);
            LOGGER.info("==========>entity write success<==========");

            String mapperJavaPath = BASE_CODE_PATH + File.separator + "mapper";
            createCode(mapperJavaPath, MAPPER_JAVA_SUFFIX, MAPPER_JAVA_TEMPLATE_NAME, classInfoList, packageName);
            LOGGER.info("==========>mapper java write success<==========");

            String mapperXmlPath = BASE_CODE_PATH + File.separator + "xml";
            createCode(mapperXmlPath, MAPPER_XML_SUFFIX, MAPPER_XML_TEMPLATE_NAME, classInfoList, packageName);
            LOGGER.info("==========>mapper xml write success<==========");

            String servicePath = BASE_CODE_PATH + File.separator + "service";
            createCode(servicePath, SERVICE_SUFFIX, SERVICE_TEMPLATE_NAME, classInfoList, packageName);
            LOGGER.info("==========>service write success<==========");

            String implPath = BASE_CODE_PATH + File.separator + "impl";
            createCode(implPath, IMPL_SUFFIX, IMPL_TEMPLATE_NAME, classInfoList, packageName);
            LOGGER.info("==========>impl write success<==========");

            zip = ZipUtil.zip(Paths.get(BASE_CODE_PATH).toFile());
            Files.walkFileTree(Paths.get(BASE_CODE_PATH), new MyFileVisitor());
            out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + zip.getName());
            in = new BufferedInputStream(new FileInputStream(zip));
            byte[] buff = new byte[1024];
            int len;
            while ((len = in.read(buff)) != -1){
                out.write(buff, 0, len);
            }
            out.flush();
            return "SUCCESS";
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            try {
                if (in != null){
                    in.close();
                }
                if (out != null){
                    out.close();
                }
                if (zip != null){
                    Files.deleteIfExists(zip.toPath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createCode(String path, String suffix, String templateName, List<JavaClassInfo> classInfoList, String packageName) throws IOException, TemplateException {
        Path dir = Paths.get(path);
        if (!Files.exists(dir)){
            Files.createDirectories(dir);
        }
        Writer writer = null;
        for (JavaClassInfo javaClassInfo : classInfoList) {
            javaClassInfo.setPackageName(packageName);
            writer = new FileWriter(dir + File.separator + javaClassInfo.getClassName() + suffix);
            Configuration configuration = new Configuration(Configuration.getVersion());
            configuration.setDirectoryForTemplateLoading(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "templates"));
            configuration.setDefaultEncoding("UTF-8");
            Template template = configuration.getTemplate(templateName);
            template.process(javaClassInfo, writer);
        }
        if (writer != null){
            writer.close();
        }
    }
}
