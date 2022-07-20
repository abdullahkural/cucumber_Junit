package com.cydeo.step_definitions;

//In the class we will be able to pass pre&post conditions to each scenario and each step

import com.cydeo.utilities.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    //@Before (order = 0) //imported from "io.cucumber.java" not from junit
    public void setupScenario(){
        System.out.println("====Setting up browser using cucumber @Before");}


    //@Before (value = "@login", order = 2) //imported from "io.cucumber.java" not from junit
    public void setupScenarioForLogins(){
        System.out.println("====this will only apply to scenarios @login tag");}


   // @Before (value = "@db", order = 1) //imported from "io.cucumber.java" not from junit
    public void setupScenarioForDatabaseScenarios(){
        System.out.println("====this will only apply to scenarios @db tag");}


    @After //imported from "io.cucumber.java" not from junit
    public void teardownScenario(Scenario scenario){
        //System.out.println("====Closing browser using cucumber @After");
        //System.out.println("====Scenario ended / Take screenshot if failed!");

        //scenario.isFailed() --> if scenario fails this method will return TRUE boolean value
        if(scenario.isFailed()){
            //in order to implement getScreenshotAs() method of TakesScreenshot interface, we downcast
            byte [] screenshot= ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        Driver.closeDriver();
    }


   // @BeforeStep
    public void setupStep(){
        System.out.println("-----------> applying set up @BeforeStep");}


   // @AfterStep
    public void afterStep(){
        System.out.println("-----------> applying teardown using @AfterStep");}

}

