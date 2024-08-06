package org.example.tbanktranslate.client;

public interface TranslateClient {

    String translate(String sourceText, String sourceLanguageCode, String targetLanguageCode);
}
