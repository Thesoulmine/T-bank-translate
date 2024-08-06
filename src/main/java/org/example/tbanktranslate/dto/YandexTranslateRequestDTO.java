package org.example.tbanktranslate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YandexTranslateRequestDTO {

    private String[] texts;

    private String sourceLanguageCode;

    private String targetLanguageCode;
}
