package br.com.javanei.retrocenter.common;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PaginatedResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean hasNext = false;
    private boolean hasPrev = false;
    private List<T> items = new LinkedList();

    public PaginatedResult() {
    }

    public PaginatedResult(boolean hasPrev, boolean hasNext) {
        this.hasPrev = hasPrev;
        this.hasNext = hasNext;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(Boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void add(T value) {
        this.items.add(value);
    }
}
