package org.example.tbanktranslate.exception;

public class TranslateServerAccessException extends Exception {

    public TranslateServerAccessException() {
        super("Ошибка доступа к ресурсу перевода.");
    }
}
