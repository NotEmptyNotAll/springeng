package com.vshvet.firstrelease.Entity;

import javax.persistence.*;

@Entity
@Table(name = "engine_number", schema = "EngDB", catalog = "")
public class EngineNumber {
    private int id;
    private int number;
    private Engine engineByEngineFk;

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
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EngineNumber that = (EngineNumber) o;

        if (id != that.id) return false;
        if (number != that.number) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + number;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "engine_fk", referencedColumnName = "id", nullable = false)
    public Engine getEngineByEngineFk() {
        return engineByEngineFk;
    }

    public void setEngineByEngineFk(Engine engineByEngineFk) {
        this.engineByEngineFk = engineByEngineFk;
    }
}
