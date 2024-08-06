package org.example.tbanktranslate.dto;

import lombok.Data;

import java.util.List;

@Data
public class YandexTranslateResponseDTO {

    private List<Translation> translations;

    @Data
    public static class Translation {

        private String text;

        private String detectedLanguageCode;
    }
}
