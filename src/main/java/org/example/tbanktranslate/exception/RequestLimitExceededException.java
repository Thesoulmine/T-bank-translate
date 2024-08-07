package org.example.tbanktranslate.exception;

public class RequestLimitExceededException extends Exception {

    public RequestLimitExceededException() {
        super("Превышен лимит сообщений в секунду.");
    }
}
