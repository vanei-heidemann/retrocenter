package br.com.javanei.retrocenter.common;

public class RetrocenterRuntimeException extends RuntimeException {
    public RetrocenterRuntimeException(String message) {
        super(message);
    }

    public RetrocenterRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetrocenterRuntimeException(Throwable cause) {
        super(cause);
    }

    public RetrocenterRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
