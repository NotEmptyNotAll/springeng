package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "fuel_type", schema = ConstValue.SCHEMA_NAME)
public class FuelType {
    private int id;
    private String nameType;
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

    public FuelType(int id) {
        this.id = id;
    }

    public FuelType() {
    }

    public FuelType(int id, String nameType, Integer status_fk) {
        this.id = id;
        this.nameType = nameType;
        this.status_fk = status_fk;
    }

    public FuelType(FuelType fuelType) {
        this(fuelType.getId(),
                fuelType.getNameType(),
                fuelType.getStatus_fk());
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_fuel_type_seq")
    @SequenceGenerator(name = "id_fuel_type_seq", initialValue = 4)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        FuelType fuelType = (FuelType) o;

        if (id != fuelType.id) return false;
        if (nameType != null ? !nameType.equals(fuelType.nameType) : fuelType.nameType != null) return false;
        if (date != null ? !date.equals(fuelType.date) : fuelType.date != null) return false;

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

    @OneToMany(mappedBy = "fuelTypeByFuelTypeFk")
    public Collection<Engine> getEnginesById() {
        return enginesById;
    }

    public void setEnginesById(Collection<Engine> enginesById) {
        this.enginesById = enginesById;
    }
}
