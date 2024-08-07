package org.example.tbanktranslate.service;

import org.example.tbanktranslate.exception.YandexClientException;
import org.example.tbanktranslate.model.Translation;

public interface TranslationService {
    Translation translateAndSave(Translation translation) throws YandexClientException;
}
