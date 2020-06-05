package com.zhou.springbootwithmybatisplus;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.springbootwithmybatisplus.entity.LearnUntiCode;
import com.zhou.springbootwithmybatisplus.entity.LearnVersionsVolume;
import com.zhou.springbootwithmybatisplus.entity.Menu;
import com.zhou.springbootwithmybatisplus.entity.Person;
import com.zhou.springbootwithmybatisplus.mapper.LearnUntiCodeMapper;
import com.zhou.springbootwithmybatisplus.mapper.LearnVersionsVolumeMapper;
import com.zhou.springbootwithmybatisplus.mapper.MenuMapper;
import com.zhou.springbootwithmybatisplus.mapper.PersonMapper;
import com.zhou.springbootwithmybatisplus.utils.ExcelReadUtils;
import com.zhou.springbootwithmybatisplus.utils.IDUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootwithmybatisplusApplicationTests {

    @Resource
    private PersonMapper personMapper;

    @Resource
    private LearnVersionsVolumeMapper learnVersionsVolumeMapper;

    @Resource
    private LearnUntiCodeMapper learnUntiCodeMapper;

    @Resource
    private MenuMapper menuMapper;

    @Test
    public void testSelect() {
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
        wrapper.eq("age", 20).eq("name", "张三");
        Person person = this.personMapper.selectOne(wrapper);
        System.out.println(person);
    }

    @Test
    public void testReadExcel() {
        String dir = "/Users/zhoufuqiang/文件/三好/555.版本分册表格区(上线版)/快乐英语/";

        String[] params = {"wordSpell", "wordSort"};
        String verVolCode;
        Map<String, String> map = new HashMap<>();


        map.put("3上","156058023254775185");

        map.put("3下","156058024015357082");

        map.put("4上","156058024865866155");

        map.put("4下","156058025661791345");

        map.put("5上","156058026421761488");

        map.put("5下","156058027220249935");

        map.put("6上","156058028145067272");

        map.put("6下","156058028959365543");

        File file = new File(dir);
        String[] list = file.list();
        for (int f = 0; f < list.length; f++) {
            String fileName = list[f];
            String name = fileName.substring(0, fileName.lastIndexOf("."));
            String volumeCode = map.get(name);
            Workbook workbook = ExcelReadUtils.readExcel(dir + fileName);
            System.out.println(name + "<==================>" +workbook.getNumberOfSheets());
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                String sheetName = sheet.getSheetName();
//                System.out.println(name + "<==================>" + sheetName);
                verVolCode = IDUtils.getId();
                LearnVersionsVolume learnVersionsVolume = LearnVersionsVolume.builder().build();
                learnVersionsVolume.setCreateTime(new Date());
                learnVersionsVolume.setVerVolCode(verVolCode);
                learnVersionsVolume.setParentCode(volumeCode);
                learnVersionsVolume.setStatus(1);
                learnVersionsVolume.setType(3);
                learnVersionsVolume.setVolumeName(sheetName);
                this.learnVersionsVolumeMapper.insert(learnVersionsVolume);

                List<LearnUntiCode> learnUntiCodes = ExcelReadUtils.getListBean(LearnUntiCode.class, workbook, 1, i, params);
                for (LearnUntiCode learnUntiCode : learnUntiCodes) {
                    if (StringUtils.isEmpty(learnUntiCode.getWordSpell())) {
                        continue;
                    }
                    learnUntiCode.setIsExperience(2);
                    learnUntiCode.setCreateTime(new Date());
                    learnUntiCode.setStatus(1);
                    learnUntiCode.setVerVolCode(verVolCode);
                    this.learnUntiCodeMapper.insert(learnUntiCode);
                }
            }
        }
    }

    @Test
    public void getLeafOrganizationInfosTest(){
        List<Menu> leafOrganizationInfos = getLeafOrganizationInfos("0");
        System.out.println(JSON.toJSONString(leafOrganizationInfos, true));
    }

    @Test
    public void remove(){
        int del = del("1001", 0);
        System.out.println(del);
    }

    private List<Menu> getLeafOrganizationInfos(String id){
        List<Menu> menuList = this.menuMapper.selectList(new QueryWrapper<Menu>().eq("parent_id", id).orderByAsc("num"));
        List<Menu> menus = new ArrayList<>();
        for (Menu menu : menuList) {
            Menu newMenu = new Menu();
            newMenu.setId(menu.getId());
            newMenu.setName(menu.getName());
            newMenu.setParentId(menu.getParentId());
            newMenu.setMenus(getLeafOrganizationInfos(menu.getId()));
            menus.add(newMenu);
        }
        return menus;
    }

    private int del(String id, int flag){
        List<Menu> menuList = this.menuMapper.selectList(new QueryWrapper<Menu>().eq("parent_id", id));
        for (Menu menu : menuList) {
            /*Integer count = this.menuMapper.selectCount(new QueryWrapper<Menu>().eq("parent_id", id));
            flag += this.menuMapper.deleteById(menu.getId());
            if (count > 0){
                del(menu.getId(), flag);
            }*/
            flag ++;
            del(menu.getId(), flag);
        }
        flag += this.menuMapper.deleteById(id);
        return flag;
    }

}
