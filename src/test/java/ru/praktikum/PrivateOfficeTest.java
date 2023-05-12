package ru.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.api.User;
import ru.praktikum.api.UserClient;
import ru.praktikum.config.WebDriverConfig;
import ru.praktikum.pages.LoginPage;
import ru.praktikum.pages.MainPage;
import ru.praktikum.pages.PrivateOfficePage;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.praktikum.config.AppConfig.*;

public class PrivateOfficeTest {

    private WebDriver driver;
    private UserClient userClient;
    private String token;

    @Before
    public void setUp() {
        User user = new User().userGenerator();
        userClient = new UserClient();
        ValidatableResponse response = userClient.createUser(user);
        token = response.extract().path("accessToken");
        driver = WebDriverConfig.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
    }

    @After
    public void tearDown() {
        driver.quit();
        ValidatableResponse responseDeleteUser = userClient.deleteUser(token);
        responseDeleteUser.statusCode(SC_ACCEPTED).and().assertThat().body("message", equalTo("User successfully removed"));
    }

    @Test
    @DisplayName("Переход по клику на Личный кабинет")
    public void linkGoToPrivateOfficeTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPrivateOfficeLink();
        PrivateOfficePage privateOfficePage = new PrivateOfficePage(driver);
        Assert.assertTrue(privateOfficePage.logoutButtonIsDisplayed());
    }

    @Test
    @DisplayName("Переход по клику на Конструктор из Личного кабинета")
    public void linkConstructorFromPrivateOfficeTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPrivateOfficeLink();
        PrivateOfficePage privateOfficePage = new PrivateOfficePage(driver);
        privateOfficePage.clickConstructorLink();
        Assert.assertEquals("Url не соответствует mainPage", MAIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход по клику на лого из Личного кабинета")
    public void linkLogoFromPrivateOfficeTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPrivateOfficeLink();
        PrivateOfficePage privateOfficePage = new PrivateOfficePage(driver);
        privateOfficePage.clickLogoStellarBurger();
        Assert.assertEquals("Url не соответствует mainPage", MAIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Выход по кнопке Выйти в личном кабинете")
    public void logoutFromPrivateOffice() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPrivateOfficeLink();
        PrivateOfficePage privateOfficePage = new PrivateOfficePage(driver);
        privateOfficePage.clickLogoutButton();
        Assert.assertEquals("Url не соответствует loginPage", LOGIN_URL, driver.getCurrentUrl());
    }
}
