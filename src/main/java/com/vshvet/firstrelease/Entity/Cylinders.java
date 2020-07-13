package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "cylinders", schema = ConstValue.SCHEMA_NAME )
public class Cylinders {
    private int id;
    private String typeName;
    private Date date;
    private Collection<Engine> enginesById;
    private Integer status_fk;
    private Status status;

    @Basic
    @Column(name = "status_fk", insertable = false, updatable = false)
    public Integer getStatus_fk() {
        return status_fk;
    }

    public void setStatus_fk(Integer status_fk) {
        this.status_fk = status_fk;
    }

    public Cylinders(int id, String typeName, Integer status_fk) {
        this.id = id;
        this.typeName = typeName;
        this.status_fk = status_fk;
    }

    public Cylinders(Cylinders cylinders) {
    this(cylinders.getId(),
            cylinders.getTypeName(),
            cylinders.getStatus_fk());
    }

    public Cylinders() {
    }

    public Cylinders(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="id_cylinders_seq")
    @SequenceGenerator(name="id__id_cylinders_seqseq", initialValue=4)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_name", nullable = false, length = 64)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cylinders cylinders = (Cylinders) o;

        if (id != cylinders.id) return false;
        if (typeName != null ? !typeName.equals(cylinders.typeName) : cylinders.typeName != null) return false;
        if (date != null ? !date.equals(cylinders.date) : cylinders.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "status_fk", referencedColumnName = "id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "cylindersByCylindersPlacementFk")
    public Collection<Engine> getEnginesById() {
        return enginesById;
    }

    public void setEnginesById(Collection<Engine> enginesById) {
        this.enginesById = enginesById;
    }
}
