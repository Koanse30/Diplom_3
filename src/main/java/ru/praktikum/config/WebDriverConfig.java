package ru.praktikum.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class WebDriverConfig {

    /*
    Переменные окружения, прописанные в системе:
    CHROME_DRIVER_PATH - путь к файлу драйвера Google Chrome браузера (Хромдрайвера нужной версии)
    YANDEX_DRIVER_PATH - путь к файлу драйвера Яндекс браузера (Хромдрайвера нужной версии)
    YANDEX_BROWSER_PATH - путь к исполняемому файлу Яндекс браузера в системе
     */
    public static final long WAIT_SECONDS_TIMEOUT = 10;

    private static final String CHROME_DRIVER_PATH = "C:/WebDriver/bin/chromedriver.exe";
    private static final String YANDEX_DRIVER_PATH = "C:/WebDriver/bin/chromedriver-yandex.exe";
    private static final String YANDEX_BROWSER_PATH = "C:/Users/KAS/AppData/Local/Yandex/YandexBrowser/Application/browser.exe";

    public static WebDriver get(String url) {
        String browser = System.getProperty("browser");
        if (browser == null) {
            return createChromeDriver(url);
        }

        switch (browser) {
            case "chrome":
                  return createChromeDriver(url);
            case "yandex":
                  return createYandexDriver(url);
            default: throw new  RuntimeException("Browser " + browser + " not exist");
        }
    }

    private static WebDriver createChromeDriver(String url) {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(WAIT_SECONDS_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(url);
        return driver;
    }

    private static WebDriver createYandexDriver(String url) {
        System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.setBinary(YANDEX_BROWSER_PATH);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(WAIT_SECONDS_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(url);
        return driver;
    }
}
