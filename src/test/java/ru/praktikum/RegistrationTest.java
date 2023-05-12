package ru.praktikum;

import io.qameta.allure.junit4.DisplayName;
import ru.praktikum.api.User;
import ru.praktikum.api.UserClient;
import ru.praktikum.config.WebDriverConfig;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.pages.RegistrationPage;


import static ru.praktikum.api.UserCreds.credsFrom;
import static ru.praktikum.config.AppConfig.LOGIN_URL;
import static ru.praktikum.config.AppConfig.REGISTRATION_URL;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegistrationTest {

    private WebDriver driver;
    private User user;
    private UserClient userClient;

    @Before
    public void setUp() {
        user = new User().userGenerator();
        userClient = new UserClient();
        driver = WebDriverConfig.get(REGISTRATION_URL);
    }

    @After
    public void tearDown() {
        if(driver.getCurrentUrl().equals(LOGIN_URL)) {
            ValidatableResponse response = userClient.loginUser(credsFrom(user));
            String token = response.extract().path("accessToken");
            ValidatableResponse responseDeleteUser = userClient.deleteUser(token);
            responseDeleteUser.statusCode(SC_ACCEPTED).and().assertThat().body("message", equalTo("User successfully removed"));
        }
        driver.quit();
    }

    @Test
    @DisplayName("Успешная регистрация.")
    public void registrationSuccessTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        Assert.assertEquals("Url не соответствует loginPage" , LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Отображение ошибки при вводе некорректного пароля при регистрации.")
    public void registrationWithIncorrectPasswordTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setName(user.getName());
        registrationPage.setEmail(user.getEmail());
        registrationPage.setPassword("qwe12");
        registrationPage.clickButtonRegister();
        Assert.assertTrue(registrationPage.passwordErrorMessage());
    }
}
