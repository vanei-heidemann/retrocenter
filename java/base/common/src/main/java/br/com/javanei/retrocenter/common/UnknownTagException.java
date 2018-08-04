package br.com.javanei.retrocenter.common;

public class UnknownTagException extends RetrocenterRuntimeException {
    public UnknownTagException(String tag) {
        super("Unknown Tag: " + tag);
    }
}
