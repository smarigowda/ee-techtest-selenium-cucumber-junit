package com.ee.santosh.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:cucumber/homepage.feature" ,
        glue = "com.ee.santosh.steps",
        plugin ={"pretty" , "html:htmlreport"}
)
public class TestRunner {
}