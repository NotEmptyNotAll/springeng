package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "engine_manufacturer", schema = ConstValue.SCHEMA_NAME)
public class EngineManufacturer {

    private int id;
    private String nameManufacturer;
    private Date date;
    private Collection<Engine> enginesById;
    private Integer status_fk;
    private Status status;

    public EngineManufacturer(int id, String nameManufacturer, Integer status_fk) {
        this.id = id;
        this.nameManufacturer = nameManufacturer;
        this.status_fk = status_fk;
    }

    public EngineManufacturer(EngineManufacturer engineManufacturer) {
        this(engineManufacturer.getId(),
                engineManufacturer.getNameManufacturer(),
                engineManufacturer.getStatus_fk());
    }


    @Basic
    @Column(name = "status_fk", insertable = false, updatable = false)
    public Integer getStatus_fk() {
        return status_fk;
    }

    public void setStatus_fk(Integer status_fk) {
        this.status_fk = status_fk;
    }


    public EngineManufacturer() {
    }

    public EngineManufacturer(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_seq")
    @SequenceGenerator(name = "id_seq", initialValue = 4)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_manufacturer", nullable = false, length = 64)
    public String getNameManufacturer() {
        return nameManufacturer;
    }

    public void setNameManufacturer(String nameManufacturer) {
        this.nameManufacturer = nameManufacturer;
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

        EngineManufacturer that = (EngineManufacturer) o;

        if (id != that.id) return false;
        if (nameManufacturer != null ? !nameManufacturer.equals(that.nameManufacturer) : that.nameManufacturer != null)
            return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameManufacturer != null ? nameManufacturer.hashCode() : 0);
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

    @OneToMany(mappedBy = "engineManufacturerByEngineManufacturerFk")
    public Collection<Engine> getEnginesById() {
        return enginesById;
    }

    public void setEnginesById(Collection<Engine> enginesById) {
        this.enginesById = enginesById;
    }
}
