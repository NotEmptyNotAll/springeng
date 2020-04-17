package com.vshvet.firstrelease.Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "measurement_units", schema = "EngDB", catalog = "")
public class MeasurementUnits {
    private int id;
    private String shortNameM;
    private String fullNameM;
    private String shortNameA;
    private Integer fullNameA;
    private Collection<Parameters> parametersById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "short_name_M", nullable = true, length = 30)
    public String getShortNameM() {
        return shortNameM;
    }

    public void setShortNameM(String shortNameM) {
        this.shortNameM = shortNameM;
    }

    @Basic
    @Column(name = "full_name_M", nullable = true, length = 64)
    public String getFullNameM() {
        return fullNameM;
    }

    public void setFullNameM(String fullNameM) {
        this.fullNameM = fullNameM;
    }

    @Basic
    @Column(name = "short_name_A", nullable = true, length = 64)
    public String getShortNameA() {
        return shortNameA;
    }

    public void setShortNameA(String shortNameA) {
        this.shortNameA = shortNameA;
    }

    @Basic
    @Column(name = "full_name_A", nullable = true)
    public Integer getFullNameA() {
        return fullNameA;
    }

    public void setFullNameA(Integer fullNameA) {
        this.fullNameA = fullNameA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeasurementUnits that = (MeasurementUnits) o;

        if (id != that.id) return false;
        if (shortNameM != null ? !shortNameM.equals(that.shortNameM) : that.shortNameM != null) return false;
        if (fullNameM != null ? !fullNameM.equals(that.fullNameM) : that.fullNameM != null) return false;
        if (shortNameA != null ? !shortNameA.equals(that.shortNameA) : that.shortNameA != null) return false;
        if (fullNameA != null ? !fullNameA.equals(that.fullNameA) : that.fullNameA != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (shortNameM != null ? shortNameM.hashCode() : 0);
        result = 31 * result + (fullNameM != null ? fullNameM.hashCode() : 0);
        result = 31 * result + (shortNameA != null ? shortNameA.hashCode() : 0);
        result = 31 * result + (fullNameA != null ? fullNameA.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "measurementUnitsByMeasurementUnitsFk")
    public Collection<Parameters> getParametersById() {
        return parametersById;
    }

    public void setParametersById(Collection<Parameters> parametersById) {
        this.parametersById = parametersById;
    }
}
