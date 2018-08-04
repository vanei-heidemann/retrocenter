package br.com.javanei.retrocenter.platform.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PLATFORM_ALT_NAME", indexes = {
        @Index(name = "PLATFORM_ALT_NAME_0001", unique = true, columnList = "PLATFORM_ID,ALTERNATE_NAME")
})
public class PlatformAltNameEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLATFORM_ALT_NAME_ID", nullable = false)
    private Long id;

    @Column(name = "ALTERNATE_NAME", length = 255, nullable = false, unique = true)
    private String alternateName;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PLATFORM_ID")
    private PlatformEntity platform;

    public PlatformAltNameEntity() {
    }

    public PlatformAltNameEntity(String alternateName) {
        this.alternateName = alternateName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlternateName() {
        return alternateName;
    }

    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName;
    }

    public PlatformEntity getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEntity platform) {
        this.platform = platform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlatformAltNameEntity that = (PlatformAltNameEntity) o;

        return alternateName.equals(that.alternateName);
    }

    @Override
    public int hashCode() {
        return alternateName.hashCode();
    }
}
