# Diplom_3
## Перед запуском тестов
В классе (src/main/java/ru/praktikum/config/WebDriverConfig.java) перед запуском тестов необходимо указать пути к веб драйверам и бинарному файлу Яндекс браузера :

CHROME_DRIVER_PATH - путь к файлу драйвера Google Chrome браузера (Хромдрайвера нужной версии)

YANDEX_DRIVER_PATH - путь к файлу драйвера Яндекс браузера (Хромдрайвера нужной версии)

YANDEX_BROWSER_PATH - путь к исполняемому файлу Яндекс браузера в системе

## Запуск тестов в терминале
Запустить тесты в Google Chrome:

mvn clean test -Dbrowser=chrome

Запустить тесты в Яндекс браузере:

mvn clean test -Dbrowser=yandex

## Просмотр отчёта

mvn allure:serve