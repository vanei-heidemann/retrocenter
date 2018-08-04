package br.com.javanei.retrocenter.common;

public class Identified<T> {
    private Long id;
    private T value;

    public Identified() {
    }

    public Identified(Long id) {
        this.id = id;
    }

    public Identified(Long id, T value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
