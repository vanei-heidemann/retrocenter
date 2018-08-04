package br.com.javanei.retrocenter.catalog.tosec.flags;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class TOSECPublisherFlag implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public TOSECPublisherFlag(String name) {
        this.name = name;
    }

    public TOSECPublisherFlag() {
    }

    public static List<TOSECPublisherFlag> parsePublishers(String publishers) {
        List<TOSECPublisherFlag> l = new LinkedList<>();
        if (!publishers.trim().equals("-")) {
            StringTokenizer st = new StringTokenizer(publishers, " - ");
            while (st.hasMoreTokens()) {
                l.add(new TOSECPublisherFlag(st.nextToken().trim()));
            }
        }
        return l;
    }

    public static String toString(List<TOSECPublisherFlag> publishers) {
        if (publishers.isEmpty()) {
            return "-";
        }
        if (publishers.size() == 1) {
            return publishers.get(0).getName();
        }
        StringBuilder sb = new StringBuilder();
        for (TOSECPublisherFlag p : publishers) {
            if (sb.length() > 0) {
                sb.append(" - ");
            }
            sb.append(p.getName());
        }
        return sb.toString();
    }

    public static List<String> toStringList(List<TOSECPublisherFlag> publishers) {
        List<String> l = new LinkedList<>();
        for (TOSECPublisherFlag p : publishers) {
            l.add(p.getName());
        }
        return l;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
