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
import ru.praktikum.pages.ForgotPasswordPage;
import ru.praktikum.pages.MainPage;
import ru.praktikum.pages.LoginPage;
import ru.praktikum.pages.RegistrationPage;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.praktikum.config.AppConfig.*;

public class LoginTest {

    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private String token;

    @Before
    public void setUp() {
        user = new User().userGenerator();
        userClient = new UserClient();
        ValidatableResponse response = userClient.createUser(user);
        token = response.extract().path("accessToken");
    }

    @After
    public void tearDown() {
        ValidatableResponse responseDeleteUser = userClient.deleteUser(token);
        responseDeleteUser.statusCode(SC_ACCEPTED).and().assertThat().body("message", equalTo("User successfully removed"));
        driver.quit();
    }

    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт на главной")
    public void loginOnMainPageTest() {
        driver = WebDriverConfig.get(MAIN_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        Assert.assertTrue(mainPage.isDisplayedCheckoutButton());
    }

    @Test
    @DisplayName("Вход через кнопку Личный кабинет")
    public void loginOnButtonPrivateOfficeTest() {
        driver = WebDriverConfig.get(MAIN_URL);
        MainPage mainPage =new MainPage(driver);
        mainPage.clickPrivateOfficeLink();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        Assert.assertTrue(mainPage.isDisplayedCheckoutButton());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginLinkOnRegistrationPageTest() {
        driver = WebDriverConfig.get(REGISTRATION_URL);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginLink();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isDisplayedCheckoutButton());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginLinkOnForgotPasswordPageTest() {
        driver = WebDriverConfig.get(FORGOT_PASSWORD_URL);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isDisplayedCheckoutButton());
    }
}
