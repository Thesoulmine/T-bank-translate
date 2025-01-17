# T-bank Translate

## Как запустить:

### Вариант 1: docker-compose.yml

1. Скачайте проект с GitHub

2. Запустите приложение с помощью Docker Compose: ```docker-compose up```

### Вариант 2: spring-boot

1. Скачайте проект с GitHub

2. Откройте файл src/main/resources/application.properties и настройте параметры подключения к вашей postgresql базе данных:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/имя_вашей_бд
spring.datasource.username=ваше_имя_пользователя
spring.datasource.password=ваш_пароль
```
  
3. Запустите приложение в терминале с помощью команды: ```./mvnw spring-boot:run``` 

## Как пользоваться:

### Вариант 1: Использование Swagger UI

1. Зайдите на [Swagger UI](http://localhost:8080/swagger-ui/index.html#/translation-controller/translate).
2. Нажмите **Try it out**.
3. Заполните тело запроса.
4. Нажмите **Execute**.
5. В ответе получите переведенный текст.

### Вариант 2: Использование Postman

1. Отправьте POST-запрос на `http://localhost:8080/api/translate` с заполненным телом.

#### Пример тела запроса:

```json
{
  "sourceText": "string",
  "sourceLanguageCode": "string",
  "targetLanguageCode": "string"
}
```
#### Поля запроса

| Поле                 | Тип     | Описание                                                                 |
|----------------------|---------|--------------------------------------------------------------------------|
| `sourceText`         | string  | Текст, который нужно перевести                                           |
| `sourceLanguageCode` | string  | Код языка исходного текста (например, "en" для английского, "ru" для русского) |
| `targetLanguageCode` | string  | Код языка, на который нужно перевести текст (например, "en" для английского, "ru" для русского)  |

#### Пример тела ответа:

```json
{
  "targetText": "string"
}
```
#### Поля ответа

| Поле                 | Тип     | Описание                                                                 |
|----------------------|---------|--------------------------------------------------------------------------|
| `targetText`         | string  | Полученный перевод текста                                                |

#### Пример тела ошибки:

```json
{
  "message": "string"
}
```
#### Поля ошибки

| Поле                 | Тип     | Описание                                                                 |
|----------------------|---------|--------------------------------------------------------------------------|
| `message`         | string  | Сообщение ошибки                                             |
