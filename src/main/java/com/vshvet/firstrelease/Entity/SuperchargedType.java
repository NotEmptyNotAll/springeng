package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "supercharged_type", schema = ConstValue.SCHEMA_NAME)
public class SuperchargedType {
    private int id;
    private String nameType;
    private String mark;
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

    public SuperchargedType() {
    }

    public SuperchargedType(SuperchargedType superchargedType) {
        this(superchargedType.getId(),
                superchargedType.getNameType(),
                superchargedType.getMark(),
                superchargedType.getStatus_fk());
    }

    public SuperchargedType(int id, String nameType, String mark, Integer status_fk) {
        this.id = id;
        this.nameType = nameType;
        this.mark = mark;
        this.status_fk = status_fk;
    }

    public SuperchargedType(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_supercharged_type_seq")
    @SequenceGenerator(name = "id_supercharged_type_seq", initialValue = 4)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "mark", nullable = false)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "name_type", nullable = false, length = 64)
    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    @Basic
    @Column(name = "date", nullable = false)
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

        SuperchargedType that = (SuperchargedType) o;

        if (id != that.id) return false;
        if (nameType != null ? !nameType.equals(that.nameType) : that.nameType != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameType != null ? nameType.hashCode() : 0);
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


    @OneToMany(mappedBy = "superchargedTypeBySuperchargedTypeFk")
    public Collection<Engine> getEnginesById() {
        return enginesById;
    }

    public void setEnginesById(Collection<Engine> enginesById) {
        this.enginesById = enginesById;
    }
}
