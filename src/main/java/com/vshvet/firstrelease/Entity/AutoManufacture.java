package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "auto_manufacture", schema = ConstValue.SCHEMA_NAME)
public class AutoManufacture {
    private int id;
    private String manufactureName;
    private Date date;
    private Collection<AutomobileEngine> automobileEnginesById;
    private Integer status_fk;
    private Status status;

    public AutoManufacture() {
    }

    public AutoManufacture(int id) {
        this.id = id;
    }

    public AutoManufacture(int id, String manufactureName, Integer status_fk) {
        this.id = id;
        this.manufactureName = manufactureName;
        this.status_fk = status_fk;
    }

    public AutoManufacture(AutoManufacture autoManufacture) {
        this(autoManufacture.getId(),
                autoManufacture.getManufactureName(),
                autoManufacture.getStatus_fk());
    }

    @Basic
    @Column(name = "status_fk", insertable = false, updatable = false)
    public Integer getStatus_fk() {
        return status_fk;
    }

    public void setStatus_fk(Integer status_fk) {
        this.status_fk = status_fk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_auto_manufacture_seq")
    @SequenceGenerator(name = "id_auto_manufacture_seq", initialValue = 20)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_auto_manufacture_seq")
    @SequenceGenerator(name = "id_auto_manufacture_seq", initialValue = 4)
    @Column(name = "manufacture_name", nullable = false, length = 64)
    public String getManufactureName() {
        return manufactureName;
    }

    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
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

        AutoManufacture that = (AutoManufacture) o;

        if (id != that.id) return false;
        if (manufactureName != null ? !manufactureName.equals(that.manufactureName) : that.manufactureName != null)
            return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (manufactureName != null ? manufactureName.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "autoManufactureByAutoManufactureFk")
    public Collection<AutomobileEngine> getAutomobileEnginesById() {
        return automobileEnginesById;
    }

    public void setAutomobileEnginesById(Collection<AutomobileEngine> automobileEnginesById) {
        this.automobileEnginesById = automobileEnginesById;
    }

    @ManyToOne
    @JoinColumn(name = "status_fk", referencedColumnName = "id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
