package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginLink = By.xpath("//a[text()=\"Войти\"]");

    public void clickLoginLink() {driver.findElement(loginLink).click();}
}
