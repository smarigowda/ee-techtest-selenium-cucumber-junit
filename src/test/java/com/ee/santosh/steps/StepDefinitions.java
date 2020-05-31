package com.ee.santosh.steps;

import cucumber.api.java.en.Given;

public class StepDefinitions {
    @Given("^User open the application$")
    public void user_opens_the_application() {
        System.out.println("User opens the application");
    }
}
