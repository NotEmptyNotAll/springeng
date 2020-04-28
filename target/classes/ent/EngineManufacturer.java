package com.vshvet.firstrelease.Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "engine_manufacturer", schema = "EngDB", catalog = "")
public class EngineManufacturer {
    private int id;
    private String nameManufacturer;
    private Collection<Engine> enginesById;

    @Id
    @Column(name = "id", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EngineManufacturer that = (EngineManufacturer) o;

        if (id != that.id) return false;
        if (nameManufacturer != null ? !nameManufacturer.equals(that.nameManufacturer) : that.nameManufacturer != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameManufacturer != null ? nameManufacturer.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "engineManufacturerByEngineManufacturerFk")
    public Collection<Engine> getEnginesById() {
        return enginesById;
    }

    public void setEnginesById(Collection<Engine> enginesById) {
        this.enginesById = enginesById;
    }
}
