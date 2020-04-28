package com.vshvet.firstrelease.Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "auto_manufacture", schema = "EngDB", catalog = "")
public class AutoManufacture {
    private int id;
    private String manufactureName;
    private Collection<Automobile> automobilesById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoManufacture that = (AutoManufacture) o;

        if (id != that.id) return false;
        if (manufactureName != null ? !manufactureName.equals(that.manufactureName) : that.manufactureName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (manufactureName != null ? manufactureName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "autoManufactureByAutoManufactureFk")
    public Collection<Automobile> getAutomobilesById() {
        return automobilesById;
    }

    public void setAutomobilesById(Collection<Automobile> automobilesById) {
        this.automobilesById = automobilesById;
    }
}
