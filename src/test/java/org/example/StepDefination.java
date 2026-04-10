package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.time.Duration;

public class StepDefination {
    WebDriver driver;
    WebDriverWait wait;

    @Given("I am on the OrangeHRM login page")
    public void i_am_on_the_login_page() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/");
        // Wait for page to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
    }

    @And("I click the login button")
    public void i_click_the_login_button() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
    }

    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        // Wait for URL to change to dashboard or look for dashboard-specific elements
        wait.until(ExpectedConditions.urlContains("dashboard"));
        // Verify we're on dashboard by checking for a unique element
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        driver.quit();
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        // Wait for error message - could be in different locations
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Invalid') or contains(text(), 'failed') or @role='alert']")));
        Assert.assertTrue(driver.findElements(By.xpath("//*[contains(text(), 'Invalid') or contains(text(), 'failed') or @role='alert']")).size() > 0);
        driver.quit();
    }

    @Given("I navigate to {string}")
    public void i_navigate_to_url(String url) {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(url);
    }

    @Then("I should see the login page")
    public void i_should_see_the_login_page() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        Assert.assertTrue(driver.findElements(By.name("username")).size() > 0);
        driver.quit();
    }
}