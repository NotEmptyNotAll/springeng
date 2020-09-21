package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "automobile_engine", schema = ConstValue.SCHEMA_NAME)
public class AutomobileEngine {
    private int id;
    private int autoManufactureFk;
    private int autoModelFk;
    private Integer engineFk;
    private Integer releaseYearFrom;
    private Integer releaseYearBy;
    private Integer elemId;
    private Date date;
    private AutoManufacture autoManufactureByAutoManufactureFk;
    private AutoModel autoModelByAutoModelFk;
    private Engine engineByEngineFk;
    private List<Parameters> parametersList ;
    private Elements elementsByElemId;
    private Collection<EngineNumber> engineNumbersById;
    private Integer status_fk;
    private String years;
    private Status status;

    public AutomobileEngine(int id,
                            int autoManufactureFk,
                            int autoModelFk,
                            Integer engineFk,
                            Integer releaseYearFrom, Integer releaseYearBy, Integer status_fk) {
        this.id = id;
        this.autoManufactureFk = autoManufactureFk;
        this.autoModelFk = autoModelFk;
        this.engineFk = engineFk;
        this.engineByEngineFk=new Engine(engineFk);
        this.autoModelByAutoModelFk=new AutoModel(autoModelFk);
        this.autoManufactureByAutoManufactureFk=new AutoManufacture(autoManufactureFk);
        this.releaseYearFrom = releaseYearFrom;
        this.releaseYearBy = releaseYearBy;
        this.status_fk = status_fk;
    }

    public AutomobileEngine(AutomobileEngine automobileEngine) {
        this(automobileEngine.getId(),
                automobileEngine.getAutoManufactureFk(),
                automobileEngine.getAutoModelFk(),
                automobileEngine.getEngineFk(),
                automobileEngine.getReleaseYearFrom(),
                automobileEngine.getReleaseYearBy(),
                automobileEngine.getStatus_fk());
    }

    @Basic
    @Column(name = "status_fk", insertable = false, updatable = false)
    public Integer getStatus_fk() {
        return status_fk;
    }

    public void setStatus_fk(Integer status_fk) {
        this.status_fk = status_fk;
    }

    public AutomobileEngine() {
    }

    public AutomobileEngine(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "years", insertable = false, updatable = false, nullable = true)
    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    @Basic
    @Column(name = "auto_manufacture_fk", insertable = false, updatable = false, nullable = true)
    public int getAutoManufactureFk() {
        return autoManufactureFk;
    }

    public void setAutoManufactureFk(int autoManufactureFk) {
        this.autoManufactureFk = autoManufactureFk;
    }

    @Basic
    @Column(name = "auto_model_fk", insertable = false, updatable = false, nullable = true)
    public int getAutoModelFk() {
        return autoModelFk;
    }

    public void setAutoModelFk(int autoModelFk) {
        this.autoModelFk = autoModelFk;
    }

    @Basic
    @Column(name = "engine_fk", insertable = false, updatable = false, nullable = true)
    public Integer getEngineFk() {
        return engineFk;
    }

    public void setEngineFk(Integer engineFk) {
        this.engineFk = engineFk;
    }

    @Basic
    @Column(name = "release_year_from", nullable = true)
    public Integer getReleaseYearFrom() {
        return releaseYearFrom;
    }

    public void setReleaseYearFrom(Integer releaseYearFrom) {
        this.releaseYearFrom = releaseYearFrom;
    }

    @Basic
    @Column(name = "release_year_by", nullable = true)
    public Integer getReleaseYearBy() {
        return releaseYearBy;
    }

    public void setReleaseYearBy(Integer releaseYearBy) {
        this.releaseYearBy = releaseYearBy;
    }

    @Basic
    @Column(name = "elem_id", insertable = false, updatable = false, nullable = true)
    public Integer getElemId() {
        return elemId;
    }

    public void setElemId(Integer elemId) {
        this.elemId = elemId;
    }

    @Basic
    @Column(name = "date", nullable = true)
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

        AutomobileEngine that = (AutomobileEngine) o;

        if (id != that.id) return false;
        if (autoManufactureFk != that.autoManufactureFk) return false;
        if (autoModelFk != that.autoModelFk) return false;
        if (engineFk != null ? !engineFk.equals(that.engineFk) : that.engineFk != null) return false;
        if (releaseYearFrom != null ? !releaseYearFrom.equals(that.releaseYearFrom) : that.releaseYearFrom != null)
            return false;
        if (releaseYearBy != null ? !releaseYearBy.equals(that.releaseYearBy) : that.releaseYearBy != null)
            return false;
        if (elemId != null ? !elemId.equals(that.elemId) : that.elemId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + autoManufactureFk;
        result = 31 * result + autoModelFk;
        result = 31 * result + (engineFk != null ? engineFk.hashCode() : 0);
        result = 31 * result + (releaseYearFrom != null ? releaseYearFrom.hashCode() : 0);
        result = 31 * result + (releaseYearBy != null ? releaseYearBy.hashCode() : 0);
        result = 31 * result + (elemId != null ? elemId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
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

    @OneToMany(mappedBy = "automobileEngine")
    public List<Parameters> getParametersList() {
        return parametersList;
    }

    public void setParametersList(List<Parameters> parametersList) {
        this.parametersList = parametersList;
    }


    @ManyToOne
    @JoinColumn(name = "engine_fk", referencedColumnName = "id")
    public Engine getEngineByEngineFk() {
        return engineByEngineFk;
    }

    public void setEngineByEngineFk(Engine engineByEngineFk) {
        this.engineByEngineFk = engineByEngineFk;
    }

    @ManyToOne
    @JoinColumn(name = "elem_id", referencedColumnName = "elemID")
    public Elements getElementsByElemId() {
        return elementsByElemId;
    }

    public void setElementsByElemId(Elements elementsByElemId) {
        this.elementsByElemId = elementsByElemId;
    }

    @OneToMany(mappedBy = "engineByEngineFk")
    public Collection<EngineNumber> getEngineNumbersById() {
        return engineNumbersById;
    }

    public void setEngineNumbersById(Collection<EngineNumber> engineNumbersById) {
        this.engineNumbersById = engineNumbersById;
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
