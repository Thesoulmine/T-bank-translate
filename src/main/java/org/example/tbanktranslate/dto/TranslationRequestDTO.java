package org.example.tbanktranslate.dto;

import lombok.Data;

@Data
public class TranslationRequestDTO {

    private String sourceText;

    private String sourceLanguageCode;

    private String targetLanguageCode;
}
