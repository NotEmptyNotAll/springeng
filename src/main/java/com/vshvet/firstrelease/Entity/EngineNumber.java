package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "engine_number", schema = ConstValue.SCHEMA_NAME)
public class EngineNumber {
    private int id;
    private String number;
    private int engineFk;
    private Date date;
    private AutomobileEngine engineByEngineFk;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "number", nullable = false)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "engine_fk", insertable = false,updatable = false,nullable = false)
    public int getEngineFk() {
        return engineFk;
    }

    public void setEngineFk(int engineFk) {
        this.engineFk = engineFk;
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

        EngineNumber that = (EngineNumber) o;

        if (id != that.id) return false;
        if (number != that.number) return false;
        if (engineFk != that.engineFk) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + number.hashCode();
        result = 31 * result + engineFk;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "engine_fk", referencedColumnName = "id", nullable = false)
    public AutomobileEngine getEngineByEngineFk() {
        return this.engineByEngineFk;
    }

    public void setEngineByEngineFk(AutomobileEngine engineByEngineFk) {
        this.engineByEngineFk = engineByEngineFk;
    }
}
