package org.example.tbanktranslate.client;

import org.example.tbanktranslate.exception.UnsupportedLanguageException;
import org.example.tbanktranslate.exception.RequestLimitExceededException;
import org.example.tbanktranslate.exception.TranslateServerAccessException;

public interface TranslateClient {

    String translate(String sourceText, String sourceLanguageCode, String targetLanguageCode)
            throws TranslateServerAccessException, UnsupportedLanguageException, RequestLimitExceededException;
}
