package com.ee.santosh.steps;

import com.ee.santosh.pageobjects.HomePage;
import com.ee.santosh.pojo.classes.Data;
import com.ee.santosh.pojo.classes.DataItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import cucumber.api.junit.Cucumber;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(Cucumber.class)
public class StepDefinitions {

    private final String url = "http://hotel-test.equalexperts.io/";
    private final String jsonDataFile = "src/test/resources/data/data.json";
    private WebDriver driver;
    private HomePage homePage;
    private Data data;

    @Given("^User opens the application$")
    public void user_opens_the_application() throws IOException, InterruptedException {

        System.out.println("User Opens the Application");
        data = new Data();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fileData = new String(Files.readAllBytes(Paths.get(jsonDataFile)));
        data = gson.fromJson(fileData, Data.class);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        homePage = new HomePage(driver);
        homePage.open(url);
    }

    @When("^User provides all the details with deposit true$")
    public void user_provides_all_the_details_with_deposit_true() throws Exception {
        System.out.println("User provides all the details with deposit true");
        DataItem dataItem = data.getTest_1().getData().get(0);
        homePage
                .setCheckoutDate(dataItem.getCheckoutDate())
                .setFirstName(dataItem.getFirstname())
                .setLastName(dataItem.getLastname())
                .setTotalPrice(dataItem.getTotalPrice())
                .setDeposit(dataItem.getDeposit())
                .setCheckinDate(dataItem.getCheckinDate());
    }

    @When("^User Saves the booking$")
    public void user_saves_the_booking() {
        System.out.println("User Saves the booking");
        homePage.saveBooking();
    }

    @Then("^User sees one booking saved successfully$")
    public void user_sees_one_booking_saved_successfully() throws Exception {
        System.out.println("User sees one booking saved successfully");
        homePage.verifyOrderCountToBe(1);
    }

    @Then("^User sees the booking successfully deleted$")
    public void user_sees_the_booking_successfully_deleted() throws Exception {
        System.out.println("User sees the booking successfully deleted");
        homePage.verifyOrderCountToBe(0);
    }

    @Then("^User deletes the booking$")
    public void user_deletes_the_booking() {
        System.out.println("User deleted the booking");
        homePage.deleteOrder();
    }

    @Given("^User Deletes all the bookings$")
    public void user_deletes_all_the_orders() throws InterruptedException {
        System.out.println("User deletes all the orders");
        homePage.deleteAllOrders();
    }

    @After
    public void endOfScenario() {
        driver.close();
    }
}
