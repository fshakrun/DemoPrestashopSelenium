# DemoPrestashopSelenium

В данном проекте на базе Java, Gradle, JUnit, Selenium, Allure реализована автоматизация нескольких тестовых сценариев проверяющих работоспособность функционала онлайн-магазина https://demo.prestashop.com/#/en/front.

### А именно, реализованы следующие проверки:

1. Доступности необходимого сайта ("1. Online Shop Availablility Test");
2. Заказа товара пользователем и оформление данного заказа (тест критического пути) ("2. Placing Order Test");
3. Ввода невалидных пользовательских данных и получение соответствующего уведомления ("3. Wrong Credential Authentification Failed Test");
4. Поиск товара и соответствие выдачи пользовательскому запросу ("4.Searching Items Relevant Result Check").
5. Восстановления пароля от пользовательского аккаунта ("5. Password Recovery Test").

+ Также была реализована проверка входа в аккаунт, используя валидные данные от вручную зарегистрированной учетной записи (" Valid Credential Authentification Passed Test").
Однако работоспособность этого теста гарантировать не представляется возможным, так как зарегистрированные мною на сайте аккаунты (используя домашний ip от российского провайдера) блокируются сразу же после выхода из них при последующем заходе спустя буквально несколько минут. Я оставил тест в соответствующем классе для ознакомления, но закомментировал его.

### Особенности реализованных тест-кейсов



### Структура проекта и необходимые улучшения
В соответствии с техническим заданием я начал реализовывать POM структуру (проект уже имеет отдельный класс для тестов и для страниц), но, к сожалению, мне не хватило времени на ее реализацию. Именно поэтому в текущей реализации элементы страниц и тесты вынесены в один файл. 
Также на настоящий момент не реализована возможность выбора браузера для автоматизированных UI тестов с помощью CLI-аргументов — все тесты осуществляются на Chrome.

### Запуск проекта

```./gradlew clean test```
```./gradlew allureServe```

 
