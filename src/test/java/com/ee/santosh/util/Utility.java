package com.ee.santosh.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Utility {

    public void waitForJS(WebDriver driver, String jsCode, String expectedValue) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(int i=0; i < 10; i++) {
            String sText =  js.executeScript(jsCode).toString();
            if(sText.equals(expectedValue)) {
                break;
            }
            System.out.println(sText);
            Thread.sleep(1000);
        }
    }

    public String runJS(WebDriver driver, String jsCode) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String sText = js.executeScript(jsCode).toString();
        return sText;
    }
}

