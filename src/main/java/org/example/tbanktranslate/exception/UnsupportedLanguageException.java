package org.example.tbanktranslate.exception;

public class UnsupportedLanguageException extends Exception {

    public UnsupportedLanguageException() {
        super("Данные языки не поддерживаются.");
    }
}
