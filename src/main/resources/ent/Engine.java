package com.vshvet.firstrelease.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Engine {
    private int id;
    private int pistonDiameter;
    private Integer pistonStroke;
    private int engineCapacity;
    private String powerKWt;
    private String horsepower;
    private Integer degreeCompression;
    private Date produceYear;
    private EngineManufacturer engineManufacturerByEngineManufacturerFk;
    private String engineTypeByEngineTypeFk;
    private FuelType fuelTypeByFuelTypeFk;
    private TypeOfCylinderSize typeOfCylinderSizeByTypeOfCylinderSizeFk;
    private SuperchargedType superchargedTypeBySuperchargedTypeFk;
    private Collection<Automobile> automobilesById;
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
    @Column(name = "engine_type", nullable = false)
    public String getEngineTypeByEngineTypeFk() {
        return engineTypeByEngineTypeFk;
    }

    public void setEngineTypeByEngineTypeFk(String engineTypeByEngineTypeFk) {
        this.engineTypeByEngineTypeFk = engineTypeByEngineTypeFk;
    }

    @Basic
    @Column(name = "piston_diameter", nullable = false)
    public int getPistonDiameter() {
        return pistonDiameter;
    }

    public void setPistonDiameter(int pistonDiameter) {
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
    @Column(name = "power_kWt", nullable = true, length = 33)
    public String getPowerKWt() {
        return powerKWt;
    }

    public void setPowerKWt(String powerKWt) {
        this.powerKWt = powerKWt;
    }

    @Basic
    @Column(name = "horsepower", nullable = true, length = 33)
    public String getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(String horsepower) {
        this.horsepower = horsepower;
    }

    @Basic
    @Column(name = "degree_compression", nullable = true)
    public Integer getDegreeCompression() {
        return degreeCompression;
    }

    public void setDegreeCompression(Integer degreeCompression) {
        this.degreeCompression = degreeCompression;
    }

    @Basic
    @Column(name = "produce_year", nullable = true)
    public Date getProduceYear() {
        return produceYear;
    }

    public void setProduceYear(Date produceYear) {
        this.produceYear = produceYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Engine engine = (Engine) o;

        if (id != engine.id) return false;
        if (pistonDiameter != engine.pistonDiameter) return false;
        if (engineCapacity != engine.engineCapacity) return false;
        if (pistonStroke != null ? !pistonStroke.equals(engine.pistonStroke) : engine.pistonStroke != null)
            return false;
        if (powerKWt != null ? !powerKWt.equals(engine.powerKWt) : engine.powerKWt != null) return false;
        if (horsepower != null ? !horsepower.equals(engine.horsepower) : engine.horsepower != null) return false;
        if (degreeCompression != null ? !degreeCompression.equals(engine.degreeCompression) : engine.degreeCompression != null)
            return false;
        if (produceYear != null ? !produceYear.equals(engine.produceYear) : engine.produceYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + pistonDiameter;
        result = 31 * result + (pistonStroke != null ? pistonStroke.hashCode() : 0);
        result = 31 * result + engineCapacity;
        result = 31 * result + (powerKWt != null ? powerKWt.hashCode() : 0);
        result = 31 * result + (horsepower != null ? horsepower.hashCode() : 0);
        result = 31 * result + (degreeCompression != null ? degreeCompression.hashCode() : 0);
        result = 31 * result + (produceYear != null ? produceYear.hashCode() : 0);
        return result;
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
    @JoinColumn(name = "fuel_type_fk", referencedColumnName = "id", nullable = false)
    public FuelType getFuelTypeByFuelTypeFk() {
        return fuelTypeByFuelTypeFk;
    }

    public void setFuelTypeByFuelTypeFk(FuelType fuelTypeByFuelTypeFk) {
        this.fuelTypeByFuelTypeFk = fuelTypeByFuelTypeFk;
    }

    @ManyToOne
    @JoinColumn(name = "type_of_cylinder_size_fk", referencedColumnName = "id", nullable = false)
    public TypeOfCylinderSize getTypeOfCylinderSizeByTypeOfCylinderSizeFk() {
        return typeOfCylinderSizeByTypeOfCylinderSizeFk;
    }

    public void setTypeOfCylinderSizeByTypeOfCylinderSizeFk(TypeOfCylinderSize typeOfCylinderSizeByTypeOfCylinderSizeFk) {
        this.typeOfCylinderSizeByTypeOfCylinderSizeFk = typeOfCylinderSizeByTypeOfCylinderSizeFk;
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
    public Collection<Automobile> getAutomobilesById() {
        return automobilesById;
    }

    public void setAutomobilesById(Collection<Automobile> automobilesById) {
        this.automobilesById = automobilesById;
    }

    @OneToMany(mappedBy = "engineByEngineFk")
    public Collection<EngineNumber> getEngineNumbersById() {
        return engineNumbersById;
    }

    public void setEngineNumbersById(Collection<EngineNumber> engineNumbersById) {
        this.engineNumbersById = engineNumbersById;
    }
}
