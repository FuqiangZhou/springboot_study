package com.zhou.springbootwithmybatisplus.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReadUtils {

    private static final String XLS = "xls";

    private static final String XLSX = "xlsx";


    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (filePath.endsWith(XLS)) {
                wb = new HSSFWorkbook(is);
            } else if (filePath.endsWith(XLSX)) {
                wb = new XSSFWorkbook(is);
            } else {
                throw new RuntimeException("文件格式错误");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue;
        if (cell != null) {
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:
                    cellValue = cell.getNumericCellValue();
                    break;
                case FORMULA:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = cell.getDateCellValue();
                    } else {
                        cellValue = cell.getNumericCellValue();
                    }
                    break;
                case STRING:
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                default:
                    cellValue = "";
                    break;
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

    public static List<Map<String, Object>> getList(Workbook wb, int startRow, int sheetNum, String[] columns) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (wb != null) {
            Sheet sheet = wb.getSheetAt(sheetNum);
            int maxRowNum = sheet.getPhysicalNumberOfRows();
            for (int i = startRow; i < maxRowNum; i++) {
                Map<String, Object> map = new LinkedHashMap<>();
                Row row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < columns.length; j++) {
                        map.put(columns[j], ExcelReadUtils.getCellFormatValue(row.getCell(j)));
                    }
                } else {
                    continue;
                }
                list.add(map);
            }
        }
        return list;
    }

    @SuppressWarnings({"unchecked", "RedundantArrayCreation"})
    public static <T> List<T> getListBean(Class<T> clazz, Workbook wb, int startRow, int sheetNum, String[] columns) {
        List<T> list = new ArrayList<>();
        try {
            List<Map<String, Object>> mapList = getList(wb, startRow, sheetNum, columns);
            Field[] fields = clazz.getDeclaredFields();
            for (Map<String, Object> map : mapList) {
                T t = clazz.newInstance();
                for (String column : columns) {
                    Class modelFieldClass = String.class;
                    for (Field field : fields) {
                        if (column.equals(field.getName())) {
                            modelFieldClass = field.getType();
                            break;
                        }
                    }
                    Object obj = map.get(column);
                    String str = String.valueOf(obj).trim();
                    if (StringUtils.isNotEmpty(str)) {
                        if (modelFieldClass.isAssignableFrom(Integer.class)) {
                            obj = (int) Double.parseDouble(str);
                        } else if (modelFieldClass.isAssignableFrom(Double.class)) {
                            obj = Double.parseDouble(str);
                        } else if (modelFieldClass.isAssignableFrom(Timestamp.class)) {
                            Timestamp timestamp = (Timestamp) obj;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            obj = sdf.format(timestamp);
                        } else if (modelFieldClass.isAssignableFrom(java.util.Date.class)) {
                            java.util.Date date = (java.util.Date) obj;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            obj = sdf.format(date);
                        } else if (modelFieldClass.isAssignableFrom(java.sql.Date.class)) {
                            java.sql.Date date = (java.sql.Date) obj;
                            Timestamp timestamp = new Timestamp(date.getTime());
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            obj = sdf.format(timestamp);
                        } else if (modelFieldClass.isAssignableFrom(String.class)) {
                            if (obj instanceof Double) {
                                int i = (int) Double.parseDouble(str);
                                obj = String.valueOf(i);
                            }
                        }
                        Method setModel = clazz.getDeclaredMethod("set" + captureName(column), new Class[]{modelFieldClass});
                        setModel.invoke(t, new Object[]{obj});
                    }
                }
                list.add(t);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

}
