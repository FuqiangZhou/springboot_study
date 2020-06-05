package com.zhou.springbootredis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhou.springbootredis.bean.RedisTestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void addRedis(){
        this.redisTemplate.opsForValue().set("aaa", "vvv");
    }

    @Test
    public void sendCommand() {
        for (int i = 0; i < 20; i++){
            OptData optData = new OptData();
            optData.setOpt("c ");
            optData.setDevicePort(i);
            String cmd = JSON.toJSONString(optData);
            this.redisTemplate.opsForList().rightPush("testObject", cmd);
        }
    }

    @Test
    public void getListFormRedis(){
        List<Object> testObject = this.redisTemplate.opsForList().range("testObject", 0, -1);
        assert testObject != null;
        for (Object o : testObject){
            String cmd = (String) o;
            OptData optData = JSON.parseObject(cmd, new TypeReference<OptData>() {
            });
            System.out.println(optData);
        }
    }

    @Test
    public void getServerValue(){
        Object obj = this.redisTemplate.opsForValue().get("SERVER:server");
        System.out.println(obj != null ? (int) obj : "未获取到Object");
    }

    @Test
    public void recoverValue(){
        RedisTestBean redisTestBean = new RedisTestBean();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        redisTestBean.setList(list);
        redisTestBean.setCode("1001");
        redisTestBean.setName("test");
        this.redisTemplate.opsForValue().set("test", redisTestBean);
    }

    @Test
    public void updateValue(){
        Object test = this.redisTemplate.opsForValue().get("test");
        RedisTestBean redisTestBean = (RedisTestBean) test;
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        redisTestBean.setList(list);
        redisTestBean.setCode("1002");
        redisTestBean.setName("test1");
        this.redisTemplate.opsForValue().set("test", redisTestBean);
    }

    @Test
    public void addToRedis(){
        boolean flag = true;
        while (flag){
            System.out.println("开始写入redis => " + System.currentTimeMillis());
            List<Object> test = redisTemplate.opsForList().range("testDate",0, -1);
            DateUtils.DateInterval comparisonInterval = new DateUtils.DateInterval();
            comparisonInterval.setInterval(DateUtils.stringToDate("2019-08-08 09:00", "yyyy-MM-dd HH:mm"), DateUtils.stringToDate("2019-08-08 23:00", "yyyy-MM-dd HH:mm"));
            if (test != null && test.size() > 0){
                System.out.println(test);
                for (Object dateInterval : test){
                    boolean isCross = DateUtils.isCross((DateUtils.DateInterval) dateInterval, comparisonInterval);
                    if (isCross){
                        System.out.println("存在交叉时间===>写入失败");
                        flag = false;
                        break;
                    }
                }
                if (!flag){
                    continue;
                }
                System.out.println("写入redis成功 => " + System.currentTimeMillis());
                redisTemplate.opsForList().rightPush("testDate", comparisonInterval);
            }else {
                System.out.println("写入redis成功 ==> " + System.currentTimeMillis());
                redisTemplate.opsForList().rightPush("testDate", comparisonInterval);
            }
        }
        System.out.println("程序结束");
    }

}
