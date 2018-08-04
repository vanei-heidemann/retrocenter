package br.com.javanei.retrocenter.common;

public abstract class RetrocenterException extends Exception {
    public RetrocenterException(String message) {
        super(message);
    }

    public RetrocenterException(Throwable cause) {
        super(cause);
    }

    public RetrocenterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
