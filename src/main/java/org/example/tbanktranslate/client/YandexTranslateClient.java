package org.example.tbanktranslate.client;

import org.example.tbanktranslate.dto.YandexTranslateRequestDTO;
import org.example.tbanktranslate.dto.YandexTranslateResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.NoSuchElementException;

@Component
public class YandexTranslateClient implements TranslateClient {

    @Value("${yandex.translate.client.api.key}")
    private String apiKey;

    @Override
    public String translate(String sourceText,
                            String sourceLanguageCode,
                            String targetLanguageCode) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Api-Key " + apiKey);

        HttpEntity<YandexTranslateRequestDTO> request = new HttpEntity<>(
                new YandexTranslateRequestDTO(
                        new String[]{sourceText},
                        sourceLanguageCode,
                        targetLanguageCode),
                headers);

        ResponseEntity<YandexTranslateResponseDTO> response = restTemplate.postForEntity(
                "https://translate.api.cloud.yandex.net/translate/v2/translate",
                request,
                YandexTranslateResponseDTO.class);

        return response.getBody().getTranslations().get(0).getText();
    }
}
