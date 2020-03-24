package com.wu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * @program: sprider
 * @description: 爬虫测试
 * @author: Wu
 * @create: 2020-03-20 22:24
 **/


public class Spider {
    /**
    * @Description: testDemo测试的是拉勾网
    * @Param:
    * @return:
    * @Date: 2020/3/21
    */
    public static void main(String[] args) {
        //设置webdriver路径
        System.setProperty("webdriver.chrome.driver",Spider.class.getClassLoader().getResource("chromedriver").getPath());
        //创建webdriver
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.lagou.com/zhaopin/Java/?labelWords=label");

        //时间2020-3-21，拉钩网打开页面会提示恭喜被红包砸中，自动化关闭该页面
        WebElement noticeElement = webDriver.findElement(By.xpath("//div[@class='body-box']//div[@class='body-btn']"));
        if (noticeElement != null) {
            noticeElement.click();
        }

        clickOption(webDriver, "工作经验", "应届毕业生");
        clickOption(webDriver, "学历要求", "本科");
//        clickOption(webDriver, "融资阶段", "天使轮");
        clickOption(webDriver, "融资阶段", "不限");
        clickOption(webDriver, "公司规模", "不限");
        clickOption(webDriver, "行业领域", "移动互联网");

        //解析页面元素
        extractJobsByPagination(webDriver);

    }

    private static void extractJobsByPagination(WebDriver webDriver) {
        List<WebElement> jobItemListElement = webDriver.findElements(By.className("con_list_item"));
        for (WebElement jobItem : jobItemListElement) {
            String companyName = jobItem.findElement(By.className("company")).findElement(By.className("company_name")).getText();
            String money = jobItem.findElement(By.className("position")).findElement(By.className("money")).getText();
            System.out.println("公司名："+companyName+"；薪资："+money);
        }
        //获取下一页按钮
        
        WebElement pagerNextBtn = webDriver.findElement(By.className("pager_next"));
        if (pagerNextBtn == null) {
            return;
        }
        if (!pagerNextBtn.getAttribute("class").contains("pager_next_disable")) {
            pagerNextBtn.click();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            extractJobsByPagination(webDriver);
        }
    }

    private static void clickOption(WebDriver webDriver, String chooseTitle, String optionTitle) {
        //找到工作经验那一行的span
        WebElement chooseElement = webDriver.findElement(By.xpath("//li[@class='multi-chosen']//span[contains(text(),'" + chooseTitle + "')]"));
        //根据工作经验span找到对应的a标签
        WebElement optionElement = chooseElement.findElement(By.xpath("../a[contains(text(),'" + optionTitle + "')]"));
        //模拟点击事件
        optionElement.click();
    }
}
