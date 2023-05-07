package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.praktikum.config.AppConfig.LOGIN_URL;

public class RegistrationPage {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By nameField = By.xpath("//fieldset[1]//input[@name=\"name\"]");
    private final By emailField = By.xpath("//fieldset[2]//input[@name=\"name\"]");
    private final By passwordField = By.xpath("//input[@name=\"Пароль\"]");
    private final By incorrectPasswordText = By.xpath("//p[text()=\"Некорректный пароль\"]");
    private final By buttonRegister = By.xpath("//button[text()=\"Зарегистрироваться\"]");
    private final By loginLink = By.xpath("//a[text()=\"Войти\"]");

    public void setName(String name) {driver.findElement(nameField).sendKeys(name);}
    public void setEmail(String email) {driver.findElement(emailField).sendKeys(email);}
    public void setPassword(String password) {driver.findElement(passwordField).sendKeys(password);}
    public void clickButtonRegister() {driver.findElement(buttonRegister).click();}
    public void clickLoginLink() {driver.findElement(loginLink).click();}
    public boolean passwordErrorMessage() {return driver.findElement(incorrectPasswordText).isDisplayed();}

    public void registration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickButtonRegister();
        new WebDriverWait(driver, 3).until(ExpectedConditions.urlToBe(LOGIN_URL));
    }
}
