package com.wu.common;

import com.wu.Spider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @program: sprider
 * @description: 自定义driver
 * @author: Wu
 * @create: 2020-03-28 20:35
 **/


public class CustomerDriver {

    public static WebDriver getWebDriver(String url) {
        //设置webdriver路径
        System.setProperty("webdriver.chrome.driver", Spider.class.getClassLoader().getResource("chromedriver").getPath());
        //创建webdriver
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(url);
        return webDriver;
    }
}
