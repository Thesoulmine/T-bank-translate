# T-bank Translate

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
