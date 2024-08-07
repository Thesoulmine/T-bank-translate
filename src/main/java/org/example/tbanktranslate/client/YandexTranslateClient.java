package org.example.tbanktranslate.client;

import org.example.tbanktranslate.dto.YandexTranslateRequestDTO;
import org.example.tbanktranslate.dto.YandexTranslateResponseDTO;
import org.example.tbanktranslate.exception.UnsupportedLanguageException;
import org.example.tbanktranslate.exception.RequestLimitExceededException;
import org.example.tbanktranslate.exception.TranslateServerAccessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class YandexTranslateClient implements TranslateClient {

    @Value("${yandex.translate.client.api.key}")
    private String apiKey;

    @Override
    public String translate(String sourceText,
                            String sourceLanguageCode,
                            String targetLanguageCode)
            throws TranslateServerAccessException, UnsupportedLanguageException, RequestLimitExceededException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createTranslateHeaders();
        HttpEntity<YandexTranslateRequestDTO> request =
                createTranslateRequest(sourceText, sourceLanguageCode, targetLanguageCode, headers);

        try {
            ResponseEntity<YandexTranslateResponseDTO> response = restTemplate.postForEntity(
                    "https://translate.api.cloud.yandex.net/translate/v2/translate",
                    request,
                    YandexTranslateResponseDTO.class);

            return response.getBody().getTranslations().get(0).getText();
        } catch (HttpStatusCodeException exception) {
            int statusCode = exception.getStatusCode().value();

            switch (statusCode) {
                case 400 -> throw new UnsupportedLanguageException();
                case 429 -> throw new RequestLimitExceededException();
                default -> throw new TranslateServerAccessException();
            }
        }
    }

    private HttpHeaders createTranslateHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Api-Key " + apiKey);
        return headers;
    }

    private HttpEntity<YandexTranslateRequestDTO> createTranslateRequest(String sourceText, String sourceLanguageCode, String targetLanguageCode, HttpHeaders headers) {
        YandexTranslateRequestDTO requestDTO = new YandexTranslateRequestDTO(
                new String[]{sourceText},
                sourceLanguageCode,
                targetLanguageCode);
        return new HttpEntity<>(requestDTO, headers);
    }
}
