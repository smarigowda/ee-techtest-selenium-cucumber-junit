package com.ee.santosh.steps;

import com.ee.santosh.pageobjects.HomePage;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import cucumber.api.junit.Cucumber;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
public class StepDefinitions {

    // TO DO: Move the url into properties file.
    private final String url = "http://hotel-test.equalexperts.io/";

    // TO DO:
    // Move the initialization of driver into another class
    // And update this class to inherit from it.
    private WebDriver driver;
    private HomePage homePage;

    @Given("^User opens the application$")
    public void user_opens_the_application() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        homePage = new HomePage(driver);
        homePage.open(url);
    }

    @When("^User inputs (.+) (.+) (.+) (.+) (.+) and (.+)")
    public void user_provides_all_the_details_with_deposit(String firstName, String lastName, String totalPrice, String checkoutDate, String checkinDate, String depositPaid) throws Exception {
        homePage
                .setCheckoutDate(checkoutDate)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setTotalPrice(totalPrice)
                .setDeposit(depositPaid)
                .setCheckinDate(checkinDate);
    }

    @When("^User Saves the booking$")
    public void user_saves_the_booking() {
        homePage.saveBooking();
    }

    @Then("^User sees one booking saved successfully$")
    public void user_sees_one_booking_saved_successfully() throws Exception {
        homePage.verifyOrderCountToBe(1);
    }

    @Then("^User sees the booking successfully deleted$")
    public void user_sees_the_booking_successfully_deleted() throws Exception {
        homePage.verifyOrderCountToBe(0);
    }

    @Then("^User deletes the booking$")
    public void user_deletes_the_booking() {
        homePage.deleteOrder();
    }

    @Given("^User Deletes all the bookings$")
    public void user_deletes_all_the_orders() throws InterruptedException {
        homePage.deleteAllOrders();
    }

    @After
    public void endOfScenario() {
        driver.close();
    }
}
