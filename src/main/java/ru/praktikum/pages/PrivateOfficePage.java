package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.praktikum.config.AppConfig.LOGIN_URL;
import static ru.praktikum.config.AppConfig.MAIN_URL;

public class PrivateOfficePage {

    WebDriver driver;

    public PrivateOfficePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By constructorLink = By.xpath("//nav//ul//li[1]//a");
    private final By logoStellarBurger = By.xpath("//div[@class=\"AppHeader_header__logo__2D0X2\"]");
    private final By logoutButton = By.xpath("//button[text()=\"Выход\"]");

    public boolean logoutButtonIsDisplayed() {return driver.findElement(logoutButton).isDisplayed();}

    public void clickConstructorLink() {
        driver.findElement(constructorLink).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.urlToBe(MAIN_URL));}
    public void clickLogoStellarBurger() {
        driver.findElement(logoStellarBurger).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.urlToBe(MAIN_URL));}
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.urlToBe(LOGIN_URL));}
}
