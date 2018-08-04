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
 * alter table lbox_datafile_region drop foreign key FKmfjtb1i3scyfw0htyctcfn75i;
 * alter table lbox_datafile_region drop foreign key FKs6c62mmi3erm8he0blt904ibx;
 * alter table lbox_datafile_region drop key FKs6c62mmi3erm8he0blt904ibx;
 * alter table lbox_datafile_region drop key FKmfjtb1i3scyfw0htyctcfn75i;
 * alter table lbox_datafile_region change datafile_id lbox_datafile_id bigint(20) NOT NULL;
 * <p>
 * alter table lbox_datafile_region drop foreign key FKd6ifmlenn7lf9p98l7f5wwthc;
 * alter table lbox_datafile_region drop foreign key FKs6c62mmi3erm8he0blt904ibx;
 * alter table lbox_datafile_region drop key UKl39cvwserrxfuj54sue3gfq44;
 * alter table lbox_datafile_region drop key FKd6ifmlenn7lf9p98l7f5wwthc;
 * alter table lbox_datafile_region change region_id lbox_region_id bigint(20) NOT NULL;
 */
@Entity
@Table(name = "LBOX_DATAFILE_REGION",
        uniqueConstraints = @UniqueConstraint(columnNames = {"LBOX_DATAFILE_ID", "LBOX_REGION_ID"}))
public class LBoxDatafileRegionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DATAFILE_REGION_ID", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_DATAFILE_ID")
    private LBoxDatafileEntity datafile;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_REGION_ID")
    private LBoxRegionEntity region;

    public LBoxDatafileRegionEntity() {
    }

    public LBoxDatafileRegionEntity(LBoxDatafileEntity datafile, LBoxRegionEntity region) {
        this.datafile = datafile;
        this.region = region;
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

    public LBoxRegionEntity getRegion() {
        return region;
    }

    public void setRegion(LBoxRegionEntity region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxDatafileRegionEntity that = (LBoxDatafileRegionEntity) o;

        if (datafile != null ? !datafile.equals(that.datafile) : that.datafile != null) return false;
        return region != null ? region.equals(that.region) : that.region == null;
    }

    @Override
    public int hashCode() {
        int result = datafile != null ? datafile.hashCode() : 0;
        result = 31 * result + (region != null ? region.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LBoxDatafileRegionEntity{" +
                "id=" + id +
                ", region=" + region +
                '}';
    }
}
