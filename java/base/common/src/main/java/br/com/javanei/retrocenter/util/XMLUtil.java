package br.com.javanei.retrocenter.util;

public final class XMLUtil {
    private XMLUtil() {
    }

    public static void appendXMLAttributeIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null) {
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
        }
    }

    public static void appendXMLTagIfNotNull(StringBuilder sb, String name, Object value, int ident) {
        if (value != null) {
            for (int i = 0; i < ident; i++) {
                sb.append("\t");
            }
            sb.append("<").append(name).append(">").append(value).append("</").append(name).append(">\n");
        }
    }
}
