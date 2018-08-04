package br.com.javanei.retrocenter.common;

public class PlatformNotFoundException extends RetrocenterException {
    public PlatformNotFoundException(Object id) {
        super("Platform [" + id + "] not found");
    }
}
