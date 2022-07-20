package com.cydeo.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {

    //This method will accept int (in seconds) and executes Thread.sleep for given duration
    public static void sleep(int second){
        second *= 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {

        }
    }

    /*TC #2: Create utility method (Day7)
    1. Create a new class called BrowserUtils
    2. Create a method to make Task1 logic re-usable
    3. When method is called, it should switch window and verify title.
    Method info:
    • Name: switchWindowAndVerify
    • Return type: void
    • Arg1: WebDriver
    • Arg2: String expectedInUrl
    • Arg3: String expectedTitle*/

    /*I do not want to create the objects of this class. So it is static
    This method accepts 3 arguments
        Arg1- webDriver
        Arg2- expectedInURL: to verify if the URL contains given String
                If condition matches, will break loop.
        Arg3- expectedInTitle: to compared against actualTitle
     */
    public static void switchWindowAndVerify(String expectedInURL, String expectedInTitle){

        Set<String> allWindowsHandles = Driver.getDriver().getWindowHandles();

        for (String each : allWindowsHandles) {

            Driver.getDriver().switchTo().window(each);
            System.out.println("Current URL: " + Driver.getDriver().getCurrentUrl());

            if (Driver.getDriver().getCurrentUrl().contains(expectedInURL)){
                break;
            }
        }

        //5. Assert: Title contains “Etsy”
        //driver.switchTo().window("Etsy");
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedInTitle));

    }


    //This method accepts a String expectedTitle and asserts if it is true
    public static void verifyTitle(String expectedTitle){
        Assert.assertEquals(Driver.getDriver().getTitle(), expectedTitle);
    }

    //Creating a utility method for ExplicitWait, so we d npt have to repeat the lines
    public static void waitForInvisibilityOf(WebElement webElement){

        Driver.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.invisibilityOf(webElement));

    }

    /**
     * This method will accept a String as expected value and verify actual URL CONTAINS the value
     * @param expectedTitle
     */
    public static void verifyUrlContains(String expectedTitle){
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(expectedTitle));
    }


    /**
     * This method will accept a dropdown as a WebElement
     * and return all the options' text in a List of String
     * @param dropdownElement
     * @return
     */
    public static List<String> dropdownOptionsAsString(WebElement dropdownElement){
        //System.out.println(expectedMonths); //[January, February, March, April, May, June, July, August, September, October, November, December]
        Select select = new Select(dropdownElement);

        //List of all Actual month <options> as a String
        List<WebElement> actualOptionsAsWebElement = select.getOptions();

        //List of all Actual month <options> as a WebElement
        List<String> actualOptionsAsString = new ArrayList<>();

        for (WebElement each : actualOptionsAsWebElement){
            actualOptionsAsString.add(each.getText());
        }

        return actualOptionsAsString;
    }

    /**
     * This method will accept a group of radioButtons as a List of WebElement
     * It eill loop through the List, and click to the radio button with provided attributeValue
     * @param radioButtons
     * @param attributeValue
     */
    public static void clickRadioButton(List<WebElement> radioButtons, String attributeValue){

        for (WebElement each : radioButtons) {
            if(each.getAttribute("value").equalsIgnoreCase(attributeValue)){
                each.click();
            }
        }
    }
}
