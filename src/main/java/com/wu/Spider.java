package com.wu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @program: sprider
 * @description: 爬虫测试
 * @author: Wu
 * @create: 2020-03-20 22:24
 **/


public class Spider {
    public static void main(String[] args) {
        //设置webdriver路径
        System.setProperty("webdriver.chrome.driver",Spider.class.getClassLoader().getResource("chromedriver").getPath());
        //创建webdriver
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.baidu.com/s?wd=sprider&rsv_spt=1&rsv_iqid=0xa3e67638000ebaae&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=1&rsv_dl=tb&oq=chromedriver_mac64&inputT=4561&rsv_t=ce2aJh%2Fzvm7VTDbiJZQuE7G7nzOT7lyQEqHDOOZlgh57eBX65IXEoJIh81BIbD6KEXzs&rsv_pq=d9cff49d000ea775&rsv_sug3=11&rsv_sug1=8&rsv_sug7=100&rsv_sug2=0&rsv_sug4=4561");
    }
}
