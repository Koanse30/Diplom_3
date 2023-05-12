package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.praktikum.config.AppConfig.MAIN_URL;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver=driver;
    }

    private final By emailField = By.xpath("//input[@name=\"name\"]");
    private final By passwordField = By.xpath("//input[@name=\"Пароль\"]");
    private final By loginButton = By.xpath("//button[text()=\"Войти\"]");

    public void setEmail(String email) {driver.findElement(emailField).sendKeys(email);}
    public void setPassword(String password) {driver.findElement(passwordField).sendKeys(password);}
    public void clickLoginButton() {driver.findElement(loginButton).click();}

    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
        new WebDriverWait(driver, 3).until(ExpectedConditions.urlToBe(MAIN_URL));
    }
}
