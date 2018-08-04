package br.com.javanei.retrocenter.common;

public class DuplicatedItemException extends RetrocenterRuntimeException {
    public DuplicatedItemException(String item) {
        super("Duplicated item: " + item);
    }
}
