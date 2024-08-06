package org.example.tbanktranslate.model;

import lombok.Data;

@Data
public class Translation {

    private Long id;

    private String ipAddress;

    private String sourceText;

    private String targetText;

    private String sourceLanguageCode;

    private String targetLanguageCode;
}
