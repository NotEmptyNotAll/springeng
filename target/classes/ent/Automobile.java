package com.vshvet.firstrelease.Entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Automobile {
    private int id;
    private Date editionYear;
    private Date editionYearBefore;
    private AutoManufacture autoManufactureByAutoManufactureFk;
    private AutoModel autoModelByAutoModelFk;
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
    @Column(name = "edition_year", nullable = true)
    public Date getEditionYear() {
        return editionYear;
    }

    public void setEditionYear(Date editionYear) {
        this.editionYear = editionYear;
    }

    @Basic
    @Column(name = "edition_year_before", nullable = true)
    public Date getEditionYearBefore() {
        return editionYearBefore;
    }

    public void setEditionYearBefore(Date editionYearBefore) {
        this.editionYearBefore = editionYearBefore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Automobile that = (Automobile) o;

        if (id != that.id) return false;
        if (editionYear != null ? !editionYear.equals(that.editionYear) : that.editionYear != null) return false;
        if (editionYearBefore != null ? !editionYearBefore.equals(that.editionYearBefore) : that.editionYearBefore != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (editionYear != null ? editionYear.hashCode() : 0);
        result = 31 * result + (editionYearBefore != null ? editionYearBefore.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "auto_manufacture_fk", referencedColumnName = "id", nullable = false)
    public AutoManufacture getAutoManufactureByAutoManufactureFk() {
        return autoManufactureByAutoManufactureFk;
    }

    public void setAutoManufactureByAutoManufactureFk(AutoManufacture autoManufactureByAutoManufactureFk) {
        this.autoManufactureByAutoManufactureFk = autoManufactureByAutoManufactureFk;
    }

    @ManyToOne
    @JoinColumn(name = "auto_model_fk", referencedColumnName = "id", nullable = false)
    public AutoModel getAutoModelByAutoModelFk() {
        return autoModelByAutoModelFk;
    }

    public void setAutoModelByAutoModelFk(AutoModel autoModelByAutoModelFk) {
        this.autoModelByAutoModelFk = autoModelByAutoModelFk;
    }

    @ManyToOne
    @JoinColumn(name = "engine_fk", referencedColumnName = "id")
    public Engine getEngineByEngineFk() {
        return engineByEngineFk;
    }

    public void setEngineByEngineFk(Engine engineByEngineFk) {
        this.engineByEngineFk = engineByEngineFk;
    }
}
