package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "measurement_units", schema = ConstValue.SCHEMA_NAME)
public class MeasurementUnits {
    private int id;
    private String shortNameM;
    private String fullNameM;
    private String shortNameA;
    private Integer fullNameA;
    private Date date;
    private Collection<Parameters> parametersById;
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

    public MeasurementUnits(int id) {
        this.id = id;
    }

    public MeasurementUnits() {
    }

    public MeasurementUnits(MeasurementUnits measurementUnits) {
        this(measurementUnits.getId(),
                measurementUnits.getShortNameM(),
                measurementUnits.getFullNameM(),
                measurementUnits.getShortNameA(),
                measurementUnits.getFullNameA(),
                measurementUnits.getStatus_fk());
    }

    public MeasurementUnits(int id, String shortNameM, String fullNameM, String shortNameA, Integer fullNameA, Integer status_fk) {
        this.id = id;
        this.shortNameM = shortNameM;
        this.fullNameM = fullNameM;
        this.shortNameA = shortNameA;
        this.fullNameA = fullNameA;
        this.status_fk = status_fk;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_measurement_units_seq")
    @SequenceGenerator(name = "id_measurement_units_seq", initialValue = 7)
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

        MeasurementUnits that = (MeasurementUnits) o;

        if (id != that.id) return false;
        if (shortNameM != null ? !shortNameM.equals(that.shortNameM) : that.shortNameM != null) return false;
        if (fullNameM != null ? !fullNameM.equals(that.fullNameM) : that.fullNameM != null) return false;
        if (shortNameA != null ? !shortNameA.equals(that.shortNameA) : that.shortNameA != null) return false;
        if (fullNameA != null ? !fullNameA.equals(that.fullNameA) : that.fullNameA != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (shortNameM != null ? shortNameM.hashCode() : 0);
        result = 31 * result + (fullNameM != null ? fullNameM.hashCode() : 0);
        result = 31 * result + (shortNameA != null ? shortNameA.hashCode() : 0);
        result = 31 * result + (fullNameA != null ? fullNameA.hashCode() : 0);
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

    @OneToMany(mappedBy = "measurementUnitsByMeasurementUnitsFk")
    public Collection<Parameters> getParametersById() {
        return parametersById;
    }

    public void setParametersById(Collection<Parameters> parametersById) {
        this.parametersById = parametersById;
    }
}
