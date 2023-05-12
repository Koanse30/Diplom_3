package ru.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.config.WebDriverConfig;
import ru.praktikum.pages.MainPage;

import static ru.praktikum.config.AppConfig.MAIN_URL;

public class ConstructorTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverConfig.get(MAIN_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    public void goToBunSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceSection();
        mainPage.clickBunSection();
        Assert.assertTrue(mainPage.isSelectedBun());
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void goToSauceSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceSection();
        Assert.assertTrue(mainPage.isSelectedSauce());
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void goToFillingSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingSection();
        Assert.assertTrue(mainPage.isSelectedFilling());
    }
}
