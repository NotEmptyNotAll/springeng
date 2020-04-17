package com.vshvet.firstrelease.Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "supercharged_type", schema = "EngDB", catalog = "")
public class SuperchargedType {
    private int id;
    private String nameType;
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
    @Column(name = "name_type", nullable = false, length = 64)
    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuperchargedType that = (SuperchargedType) o;

        if (id != that.id) return false;
        if (nameType != null ? !nameType.equals(that.nameType) : that.nameType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameType != null ? nameType.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "superchargedTypeBySuperchargedTypeFk")
    public Collection<Engine> getEnginesById() {
        return enginesById;
    }

    public void setEnginesById(Collection<Engine> enginesById) {
        this.enginesById = enginesById;
    }
}
