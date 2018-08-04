package br.com.javanei.retrocenter.gamedb.launchbox.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * alter table lbox_datafile_platform drop foreign key FK1bc9blx1j1k6xywsson5ig5da;
 * alter table lbox_datafile_platform drop foreign key FKomvprt73aq43qxx4i708513ql;
 * alter table lbox_datafile_platform drop key UK98k6txy2ejuwbwegmih9c0gfu;
 * alter table lbox_datafile_platform drop key FK1bc9blx1j1k6xywsson5ig5da;
 * alter table lbox_datafile_platform change datafile_id lbox_datafile_id bigint(20) NOT NULL;
 * <p>
 * alter table lbox_datafile_platform change platform_id lbox_platform_id bigint(20) NOT NULL;
 * <p>
 * alter table lbox_datafile_platform drop foreign key FK4a2bq4kpilh0umgk6jbxce6o7;
 * alter table lbox_datafile_platform drop key FK4a2bq4kpilh0umgk6jbxce6o7;
 * alter table lbox_datafile_platform drop foreign key FKomvprt73aq43qxx4i708513ql;
 * alter table lbox_datafile_platform drop index UK1b1fqmhhm9m7krsf80e9v8pv4;
 */
@Entity
@Table(name = "LBOX_DATAFILE_PLATFORM",
        uniqueConstraints = @UniqueConstraint(columnNames = {"LBOX_DATAFILE_ID", "LBOX_PLATFORM_ID"}))
public class LBoxDatafilePlatformEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DATAFILE_PLATFORM_ID", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_DATAFILE_ID")
    private LBoxDatafileEntity datafile;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_PLATFORM_ID")
    private LBoxPlatformEntity platform;

    public LBoxDatafilePlatformEntity() {
    }

    public LBoxDatafilePlatformEntity(LBoxDatafileEntity datafile, LBoxPlatformEntity platform) {
        this.datafile = datafile;
        this.platform = platform;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LBoxDatafileEntity getDatafile() {
        return datafile;
    }

    public void setDatafile(LBoxDatafileEntity datafile) {
        this.datafile = datafile;
    }

    public LBoxPlatformEntity getPlatform() {
        return platform;
    }

    public void setPlatform(LBoxPlatformEntity platform) {
        this.platform = platform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxDatafilePlatformEntity that = (LBoxDatafilePlatformEntity) o;

        if (datafile != null ? !datafile.equals(that.datafile) : that.datafile != null) return false;
        return platform != null ? platform.equals(that.platform) : that.platform == null;
    }

    @Override
    public int hashCode() {
        int result = datafile != null ? datafile.hashCode() : 0;
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LBoxDatafilePlatformEntity{" +
                "id=" + id +
                ", platform=" + platform +
                '}';
    }
}
