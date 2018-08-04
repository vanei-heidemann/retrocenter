package br.com.javanei.retrocenter.common;

public class UnknownAttributeException extends RetrocenterRuntimeException {
    public UnknownAttributeException(String attr) {
        super("Unknown Attribute: " + attr);
    }
}
