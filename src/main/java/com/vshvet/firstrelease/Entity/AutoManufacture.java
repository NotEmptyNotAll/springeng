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

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
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
}
