package com.zhou.simplehellostarter;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/20 5:43 下午
 */
public class SimpleHelloService {

    private String hello;

    private String hi;

    private SimpleHelloProperties.Test test;

    public SimpleHelloService(String hello, String hi, SimpleHelloProperties.Test test){
        this.hello = hello;
        this.hi = hi;
        this.test = test;
    }

    public String splice(String arg) {
        return this.hello + ", " + arg + "\n" + this.hi + ", " + arg + "\n" + this.test.getType();
    }
}
