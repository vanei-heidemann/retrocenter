package br.com.javanei.retrocenter.catalog.nointro.flags;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class NoIntroDevStatus implements Serializable {
    private final NoIntroDevStatusEnum status;
    private final String complement;

    public NoIntroDevStatus(NoIntroDevStatusEnum status, String complement) {
        this.status = status;
        this.complement = complement;
    }

    public static List<NoIntroDevStatus> parseTag(String tag) {
        List<NoIntroDevStatus> result = new LinkedList<>();
        String[] ss = tag.split(",");
        for (String s : ss) {
            if (s.trim().startsWith("Proto")) {
                // Identifica se é um Proto
                result.add(new NoIntroDevStatus(NoIntroDevStatusEnum.Proto, s.trim().length() > 5 ? s.trim().substring(5) : null));
            } else if (s.trim().equals("Demo")) {
                // Identifica se é um Demo
                result.add(new NoIntroDevStatus(NoIntroDevStatusEnum.Demo, null));
            } else if (s.trim().startsWith("Beta")) {
                // Identifica se é um Beta
                result.add(new NoIntroDevStatus(NoIntroDevStatusEnum.Beta, s.trim().length() > 4 ? s.trim().substring(4) : null));
            } else if (s.trim().equals("Promo")) {
                // Identifica se se é um Promo
                result.add(new NoIntroDevStatus(NoIntroDevStatusEnum.Promo, null));
            } else if (s.trim().equals("Unl")) {
                // Identifica se é um jogo não licenciado
                result.add(new NoIntroDevStatus(NoIntroDevStatusEnum.Unl, null));
            } else if (s.trim().equals("Sample")) {
                // Identifica se se é um Sample
                result.add(new NoIntroDevStatus(NoIntroDevStatusEnum.Sample, null));
            } else if (s.trim().equals("Preview")) {
                // Identifica se é um Preview
                result.add(new NoIntroDevStatus(NoIntroDevStatusEnum.Preview, null));
            } else {
                return null;
            }
        }
        return result;
    }

    public NoIntroDevStatusEnum getStatus() {
        return status;
    }

    public String getComplement() {
        return complement;
    }

    @Override
    public String toString() {
        return this.status.name() + (this.complement != null ? this.complement : "");
    }
}
