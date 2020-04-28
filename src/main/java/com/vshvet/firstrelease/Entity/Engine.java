package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "engine", schema = ConstValue.SCHEMA_NAME)
public class Engine {
    private int id;
    private String engineType;
    private Integer engineManufacturerFk;
    private int cylindersPlacementFk;
    private int fuelTypeFk;
    private int superchargedTypeFk;
    private Integer cylindersNumber;
    private Integer flapNumber;
    private Double pistonDiameter;
    private Integer pistonStroke;
    private int engineCapacity;
    private String powerKwt;
    private Integer horsepower;
    private Double degreeCompression;
    private Integer releaseYearFrom;
    private Integer releaseYearBy;
    private Date date;
    private Collection<AutomobileEngine> automobileEnginesById;
    private EngineManufacturer engineManufacturerByEngineManufacturerFk;
    private Cylinders cylindersByCylindersPlacementFk;
    private FuelType fuelTypeByFuelTypeFk;
    private SuperchargedType superchargedTypeBySuperchargedTypeFk;
    private Collection<EngineNumber> engineNumbersById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "engine_type", nullable = false, length = 64)
    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Basic
    @Column(name = "engine_manufacturer_fk",insertable = false,updatable = false, nullable = true)
    public Integer getEngineManufacturerFk() {
        return engineManufacturerFk;
    }

    public void setEngineManufacturerFk(Integer engineManufacturerFk) {
        this.engineManufacturerFk = engineManufacturerFk;
    }

    @Basic
    @Column(name = "cylinders_placement_fk",insertable = false,updatable = false, nullable = false)
    public int getCylindersPlacementFk() {
        return cylindersPlacementFk;
    }

    public void setCylindersPlacementFk(int cylindersPlacementFk) {
        this.cylindersPlacementFk = cylindersPlacementFk;
    }

    @Basic
    @Column(name = "fuel_type_fk",insertable = false,updatable = false, nullable = false)
    public int getFuelTypeFk() {
        return fuelTypeFk;
    }

    public void setFuelTypeFk(int fuelTypeFk) {
        this.fuelTypeFk = fuelTypeFk;
    }

    @Basic
    @Column(name = "supercharged_type_fk",insertable = false,updatable = false, nullable = false)
    public int getSuperchargedTypeFk() {
        return superchargedTypeFk;
    }

    public void setSuperchargedTypeFk(int superchargedTypeFk) {
        this.superchargedTypeFk = superchargedTypeFk;
    }

    @Basic
    @Column(name = "cylinders_number", insertable = false,updatable = false,nullable = true)
    public Integer getCylindersNumber() {
        return cylindersNumber;
    }

    public void setCylindersNumber(Integer cylindersNumber) {
        this.cylindersNumber = cylindersNumber;
    }

    @Basic
    @Column(name = "flap_number", insertable = false,updatable = false,nullable = true)
    public Integer getFlapNumber() {
        return flapNumber;
    }

    public void setFlapNumber(Integer flapNumber) {
        this.flapNumber = flapNumber;
    }

    @Basic
    @Column(name = "piston_diameter", nullable = false)
    public Double getPistonDiameter() {
        return pistonDiameter;
    }

    public void setPistonDiameter(Double pistonDiameter) {
        this.pistonDiameter = pistonDiameter;
    }

    @Basic
    @Column(name = "piston_stroke", nullable = true)
    public Integer getPistonStroke() {
        return pistonStroke;
    }

    public void setPistonStroke(Integer pistonStroke) {
        this.pistonStroke = pistonStroke;
    }

    @Basic
    @Column(name = "engine_capacity", nullable = false)
    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Basic
    @Column(name = "power_kwt", nullable = true)
    public String getPowerKwt() {
        return powerKwt;
    }

    public void setPowerKwt(String powerKwt) {
        this.powerKwt = powerKwt;
    }

    @Basic
    @Column(name = "horsepower", nullable = true)
    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    @Basic
    @Column(name = "degree_compression", nullable = true)
    public Double getDegreeCompression() {
        return degreeCompression;
    }

    public void setDegreeCompression(Double degreeCompression) {
        this.degreeCompression = degreeCompression;
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

        Engine engine = (Engine) o;

        if (id != engine.id) return false;
        if (cylindersPlacementFk != engine.cylindersPlacementFk) return false;
        if (fuelTypeFk != engine.fuelTypeFk) return false;
        if (superchargedTypeFk != engine.superchargedTypeFk) return false;
        if (pistonDiameter != engine.pistonDiameter) return false;
        if (engineCapacity != engine.engineCapacity) return false;
        if (engineType != null ? !engineType.equals(engine.engineType) : engine.engineType != null) return false;
        if (engineManufacturerFk != null ? !engineManufacturerFk.equals(engine.engineManufacturerFk) : engine.engineManufacturerFk != null)
            return false;
        if (cylindersNumber != null ? !cylindersNumber.equals(engine.cylindersNumber) : engine.cylindersNumber != null)
            return false;
        if (flapNumber != null ? !flapNumber.equals(engine.flapNumber) : engine.flapNumber != null) return false;
        if (pistonStroke != null ? !pistonStroke.equals(engine.pistonStroke) : engine.pistonStroke != null)
            return false;
        if (powerKwt != null ? !powerKwt.equals(engine.powerKwt) : engine.powerKwt != null) return false;
        if (horsepower != null ? !horsepower.equals(engine.horsepower) : engine.horsepower != null) return false;
        if (degreeCompression != null ? !degreeCompression.equals(engine.degreeCompression) : engine.degreeCompression != null)
            return false;
        if (releaseYearFrom != null ? !releaseYearFrom.equals(engine.releaseYearFrom) : engine.releaseYearFrom != null)
            return false;
        if (releaseYearBy != null ? !releaseYearBy.equals(engine.releaseYearBy) : engine.releaseYearBy != null)
            return false;
        if (date != null ? !date.equals(engine.date) : engine.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (engineType != null ? engineType.hashCode() : 0);
        result = 31 * result + (engineManufacturerFk != null ? engineManufacturerFk.hashCode() : 0);
        result = 31 * result + cylindersPlacementFk;
        result = 31 * result + fuelTypeFk;
        result = 31 * result + superchargedTypeFk;
        result = 31 * result + (cylindersNumber != null ? cylindersNumber.hashCode() : 0);
        result = 31 * result + (flapNumber != null ? flapNumber.hashCode() : 0);
        result = 31 * result + pistonDiameter.hashCode();
        result = 31 * result + (pistonStroke != null ? pistonStroke.hashCode() : 0);
        result = 31 * result + engineCapacity;
        result = 31 * result + (powerKwt != null ? powerKwt.hashCode() : 0);
        result = 31 * result + (horsepower != null ? horsepower.hashCode() : 0);
        result = 31 * result + (degreeCompression != null ? degreeCompression.hashCode() : 0);
        result = 31 * result + (releaseYearFrom != null ? releaseYearFrom.hashCode() : 0);
        result = 31 * result + (releaseYearBy != null ? releaseYearBy.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "engineByEngineFk")
    public Collection<AutomobileEngine> getAutomobileEnginesById() {
        return automobileEnginesById;
    }

    public void setAutomobileEnginesById(Collection<AutomobileEngine> automobileEnginesById) {
        this.automobileEnginesById = automobileEnginesById;
    }

    @ManyToOne
    @JoinColumn(name = "engine_manufacturer_fk", referencedColumnName = "id")
    public EngineManufacturer getEngineManufacturerByEngineManufacturerFk() {
        return engineManufacturerByEngineManufacturerFk;
    }

    public void setEngineManufacturerByEngineManufacturerFk(EngineManufacturer engineManufacturerByEngineManufacturerFk) {
        this.engineManufacturerByEngineManufacturerFk = engineManufacturerByEngineManufacturerFk;
    }

    @ManyToOne
    @JoinColumn(name = "cylinders_placement_fk", referencedColumnName = "id", nullable = false)
    public Cylinders getCylindersByCylindersPlacementFk() {
        return cylindersByCylindersPlacementFk;
    }

    public void setCylindersByCylindersPlacementFk(Cylinders cylindersByCylindersPlacementFk) {
        this.cylindersByCylindersPlacementFk = cylindersByCylindersPlacementFk;
    }

    @ManyToOne
    @JoinColumn(name = "fuel_type_fk", referencedColumnName = "id", nullable = false)
    public FuelType getFuelTypeByFuelTypeFk() {
        return fuelTypeByFuelTypeFk;
    }

    public void setFuelTypeByFuelTypeFk(FuelType fuelTypeByFuelTypeFk) {
        this.fuelTypeByFuelTypeFk = fuelTypeByFuelTypeFk;
    }

    @ManyToOne
    @JoinColumn(name = "supercharged_type_fk", referencedColumnName = "id", nullable = false)
    public SuperchargedType getSuperchargedTypeBySuperchargedTypeFk() {
        return superchargedTypeBySuperchargedTypeFk;
    }

    public void setSuperchargedTypeBySuperchargedTypeFk(SuperchargedType superchargedTypeBySuperchargedTypeFk) {
        this.superchargedTypeBySuperchargedTypeFk = superchargedTypeBySuperchargedTypeFk;
    }

    @OneToMany(mappedBy = "engineByEngineFk")
    public Collection<EngineNumber> getEngineNumbersById() {
        return engineNumbersById;
    }

    public void setEngineNumbersById(Collection<EngineNumber> engineNumbersById) {
        this.engineNumbersById = engineNumbersById;
    }
}
