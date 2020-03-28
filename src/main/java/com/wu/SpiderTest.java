package com.wu;

import com.wu.common.CustomerDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @program: sprider
 * @description: 抓取4399官网
 * @author: Wu
 * @create: 2020-03-28 20:18
 **/


public class SpiderTest {
    public static void main(String[] args) {
//        WebDriver webDriver = CustomerDriver.getWebDriver("http://game.4399sy.com/site2018/news/gonggao?website_id=8");
        WebDriver webDriver = CustomerDriver.getWebDriver("http://qjzjsy.4399sy.com/news/");
        clickOption(webDriver, "公告");
        extractJobsByPagination(webDriver);
    }

    private static void extractJobsByPagination(WebDriver webDriver) {
        List<WebElement> jobItemListElement = webDriver.findElements(By.className("u-hd"));
        for (WebElement jobItem : jobItemListElement) {
            String href = jobItem.getAttribute("href");
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("window.open('" + href + "')");
            String currentHandle = webDriver.getWindowHandle();
            Set<String> handles = webDriver.getWindowHandles();
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (String s : handles) {
                if (s.equals(currentHandle))
                    continue;
                else {
                    webDriver.switchTo().window(s);
                    extractPageInfo(webDriver);
                    webDriver.close();
                    webDriver.switchTo().window(currentHandle);
                    webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                }
            }
        }
        //获取下一页按钮
        WebElement pagerNextBtn = webDriver.findElement(By.className("pages_link--next"));
        if (pagerNextBtn == null) {
            return;
        }
        if (!pagerNextBtn.getAttribute("href").contains("javascript:void(0);")) {
            pagerNextBtn.click();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            extractJobsByPagination(webDriver);
        }
    }

    private static void clickOption(WebDriver webDriver, String chooseTitle) {
        //找到工作经验那一行的span
        WebElement chooseElement = webDriver.findElement(By.xpath("//div[@class='m-tabs']//a[contains(@class,u-btn) and contains(text(),'" + chooseTitle + "')]"));
        //模拟点击事件
        chooseElement.click();
    }

    private static void extractPageInfo(WebDriver webDriver) {
        String title = webDriver.findElement(By.xpath("//h2[@class='u-title']")).getText();
        System.out.println(title);
    }
}
