package org.example.tbanktranslate.client;

import java.io.IOException;

public interface TranslateClient {

    String translate(String sourceText, String sourceLanguageCode, String targetLanguageCode);
}
