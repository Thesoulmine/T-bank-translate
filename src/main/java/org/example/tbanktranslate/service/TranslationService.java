package org.example.tbanktranslate.service;

import org.example.tbanktranslate.exception.TranslateClientException;
import org.example.tbanktranslate.model.Translation;

public interface TranslationService {
    Translation translateAndSave(Translation translation) throws TranslateClientException;
}
