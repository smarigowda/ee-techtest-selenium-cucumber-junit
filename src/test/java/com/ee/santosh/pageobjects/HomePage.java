package com.ee.santosh.pageobjects;

import com.ee.santosh.util.Utility;
import com.jayway.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    private String headingSelector = ".jumbotron";
    private String checkinDateSelector = "#checkin";
    private String checkoutDateSelector = "#checkout";
    private String firstNameSelector = "#firstname";
    private String surnameSelector = "#lastname";
    private String totalpriceSelector = "#totalprice";
    private String depositpaidSelector = "#depositpaid";
    private String saveButtonSelector = "input[value=' Save ']";
    private String deleteButtonSelector = "input[value=Delete]";
    private Utility util = new Utility();
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 120000);
    }

    public HomePage deleteOrder() {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(this.deleteButtonSelector)));
            element.click();
        } catch (Exception e) {
            System.out.println("Order not found. Not to worry.");
        }
        return this;
    }

    public HomePage deleteAllOrders() throws InterruptedException {

        // Send a request to http://hotel-test.equalexperts.io/booking
        // If the response is an empty array then there are no bookings to be deleted
        // If the response is not an empty array then there are some bookings.
        //
        // Application is taking some time to display the bookings on the screen,
        // Wait until the orders are displayed only if there are any history of bookings.

//        String response = RestAssured
//                .given()
//                .when().get("http://hotel-test.equalexperts.io/booking")
//                .then()
//                .extract()
//                .response()
//                .asString();

        String response = "[]";

        if(!response.equals("[]")) {
            System.out.println("There are some previous bookings, so wait until the orders are displayed on the home screen");

            // Wait until the orders are displayed on the screen
            wait.until((WebDriver driver) -> {
                List<WebElement> el = driver.findElements(By.cssSelector(this.deleteButtonSelector));
                return el.size() > 0;
            });

            List<WebElement> el = driver.findElements(By.cssSelector(this.deleteButtonSelector));

            for ( WebElement e : el ) {
                e.click();
            }
        }
        return this;
    }

    public HomePage verifyOrderCountToBe(int expectedCount) throws Exception {
        int i = 0;
        int totalAttempts = 10;
        int actualCount = 0;
        do {
            try {
                List<WebElement> el = driver.findElements(By.cssSelector(this.deleteButtonSelector));
                actualCount = el.size();
                if(actualCount == expectedCount) {
                    return this;
                }
            } catch (Exception e) {
                System.out.println("retrying method ...");
            }
            Thread.sleep(500);
        } while(++i < totalAttempts);
        Assert.assertEquals(actualCount, expectedCount);
        if( i == totalAttempts) {
            throw new Exception("verifyOrderCount failed...!");
        }
        return this;
    }

    private boolean isOrderCreatedSuccessfully() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(this.deleteButtonSelector)));
        } catch (Exception e) {
            System.out.println("Unable to save the order. Please check the log");
            return false;
        }
        return true;
    }

    private void waitUntilSave() {
        // demo to show we can use a lambda function and also JS code can be run from within the lambda function.
        wait.until((WebDriver driver) -> {
            WebElement saveButton = driver.findElement(By.cssSelector(this.saveButtonSelector));
            saveButton.click();
            String firstname = util.runJS(driver, "return document.getElementById('firstname').value;");
            return firstname.equals("");
        });
    }

    public HomePage open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(this.headingSelector)));
        return this;
    }

    public HomePage setCheckinDate(String checkinDate) {
        WebElement dateBox = driver.findElement(By.cssSelector(this.checkinDateSelector));
        dateBox.sendKeys(checkinDate);
        dateBox.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.attributeToBe(dateBox, "value", checkinDate));
        return this;
    }

    public HomePage setCheckoutDate(String checkoutDate) throws InterruptedException {
        WebElement dateBox = driver.findElement(By.cssSelector(this.checkoutDateSelector));
        dateBox.sendKeys(checkoutDate);
        // Demo to show that we can run a JS code from inside the test
        util.waitForJS(driver, "return document.getElementById('checkout').value;", checkoutDate);
        dateBox.sendKeys(Keys.ENTER);
        return this;
    }

    public HomePage setFirstName(String firstName) {
        WebElement element = driver.findElement(By.cssSelector(this.firstNameSelector));
        element.sendKeys(firstName);
        wait.until(ExpectedConditions.attributeToBe(element, "value", firstName));
        return this;
    }

    public HomePage setLastName(String lastName) {
        WebElement element = driver.findElement(By.cssSelector(this.surnameSelector));
        element.sendKeys(lastName);
        wait.until(ExpectedConditions.attributeToBe(element, "value", lastName));
        return this;
    }

    public HomePage setTotalPrice(String  totalPrice) {
        WebElement element = driver.findElement(By.cssSelector(this.totalpriceSelector));
        element.sendKeys(totalPrice);
        wait.until(ExpectedConditions.attributeToBe(element, "value", totalPrice));
        return this;
    }

    public HomePage setDeposit(String isPaid) {
        Select depositSelector = new Select(driver.findElement(By.cssSelector(this.depositpaidSelector)));
        depositSelector.selectByVisibleText(isPaid);

        // Demo to show that a lambda function can be used inside wait.until
        wait.until((WebDriver dr1) -> {
            String selectedOption = depositSelector.getFirstSelectedOption().getText();
            return selectedOption.equals(isPaid);
        });
        return this;
    }

    public HomePage saveBooking() {
        this.waitUntilSave();
        boolean isOrderCreated = this.isOrderCreatedSuccessfully();
        Assert.assertEquals(isOrderCreated, true);
        return this;
    }
}
