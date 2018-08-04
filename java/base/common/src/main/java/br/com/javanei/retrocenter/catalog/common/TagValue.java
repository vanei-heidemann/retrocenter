package br.com.javanei.retrocenter.catalog.common;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TagValue implements Serializable {
    private static final long serialVersionUID = 1L;
    private String value;
    private char[] separators;

    private TagValue(String value) {
        this.value = value;
    }

    private TagValue(String value, char[] separators) {
        this.value = value;
        this.separators = separators;
    }

    public static List<TagValue> parseNameInTags(String name) {
        List<TagValue> r = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        char[] separators = null;
        for (char c : name.toCharArray()) {
            switch (c) {
                case '(':
                    if (separators != null) {
                        sb.append(c);
                    } else {
                        if (sb.toString().trim().length() > 0) {
                            r.add(new TagValue(sb.toString().trim()));
                        }
                        separators = new char[]{'(', ')'};
                        sb = new StringBuilder();
                    }
                    break;
                case ')':
                    if (separators != null && separators[1] != ')') {
                        sb.append(c);
                    } else {
                        if (sb.toString().trim().length() > 0) {
                            r.add(new TagValue(sb.toString().trim(), separators));
                        }
                        sb = new StringBuilder();
                        separators = null;
                    }
                    break;
                case '[':
                    if (separators != null) {
                        sb.append(c);
                    } else {
                        if (sb.toString().trim().length() > 0) {
                            r.add(new TagValue(sb.toString().trim()));
                        }
                        separators = new char[]{'[', ']'};
                        sb = new StringBuilder();
                    }
                    break;
                case ']':
                    if (separators != null && separators[1] != ']') {
                        sb.append(c);
                    } else {
                        if (sb.toString().trim().length() > 0) {
                            r.add(new TagValue(sb.toString().trim(), separators));
                        }
                        sb = new StringBuilder();
                        separators = null;
                    }
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        if (sb.toString().trim().length() > 0)
            r.add(new TagValue(sb.toString().trim(), separators));
        return r;
    }

    public String getValue() {
        return this.value;
    }

    public char getStartSeparator() {
        return this.separators != null ? this.separators[0] : null;
    }

    public char getEndSeparator() {
        return this.separators != null ? this.separators[1] : null;
    }

    @Override
    public String toString() {
        if (this.separators == null) return this.value;
        StringBuilder sb = new StringBuilder();
        sb.append(separators[0]).append(this.value).append(separators[1]);
        return sb.toString();
    }
}
