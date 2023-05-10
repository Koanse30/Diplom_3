package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver=driver;
    }

    private final By loginButton = By.xpath("//button[text()=\"Войти в аккаунт\"]");
    private final By privateOfficeLink = By.xpath("//nav//a//p[text()=\"Личный Кабинет\"]");
    private final By bunSectionTab = By.xpath("//div[@style=\"display: flex;\"]//div[1]");
    private final By sauceSectionTab = By.xpath("//div[@style=\"display: flex;\"]//div[2]");
    private final By fillingSectionTab = By.xpath("//div[@style=\"display: flex;\"]//div[3]");
    private final By checkoutButton = By.xpath("//button[text()=\"Оформить заказ\"]");

    public void clickLoginButton() {driver.findElement(loginButton).click();}
    public void clickPrivateOfficeLink() {driver.findElement(privateOfficeLink).click();}
    public void clickBunSection() {driver.findElement(bunSectionTab).click();}
    public void clickSauceSection() {driver.findElement(sauceSectionTab).click();}
    public void clickFillingSection() {driver.findElement(fillingSectionTab).click();}

    public boolean isSelectedBun() {
        return driver.findElement(bunSectionTab).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }
    public boolean isSelectedSauce() {
        return driver.findElement(sauceSectionTab).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }
    public boolean isSelectedFilling() {
        return driver.findElement(fillingSectionTab).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    public boolean isDisplayedCheckoutButton() {
        return driver.findElement(checkoutButton).isDisplayed();
    }
}
