package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

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
    private Integer status_fk;
    private Status status;

    public Engine(int id, String engineType, Integer engineManufacturerFk, int cylindersPlacementFk, int fuelTypeFk, int superchargedTypeFk, Integer cylindersNumber, Integer flapNumber, Double pistonDiameter, Integer pistonStroke, int engineCapacity, String powerKwt, Integer horsepower, Double degreeCompression, Integer releaseYearFrom, Integer releaseYearBy,Integer status_fk, Status status) {
        this.id = id;
        this.engineType = engineType;
        this.engineManufacturerFk = engineManufacturerFk;
        this.cylindersPlacementFk = cylindersPlacementFk;
        this.fuelTypeFk = fuelTypeFk;
        this.superchargedTypeFk = superchargedTypeFk;
        this.cylindersNumber = cylindersNumber;
        this.flapNumber = flapNumber;
        this.pistonDiameter = pistonDiameter;
        this.pistonStroke = pistonStroke;
        this.engineCapacity = engineCapacity;
        this.powerKwt = powerKwt;
        this.horsepower = horsepower;
        this.degreeCompression = degreeCompression;
        this.releaseYearFrom = releaseYearFrom;
        this.releaseYearBy = releaseYearBy;
        this.status_fk = status_fk;
        this.status = status;
    }

    public Engine(Engine engine) {
        this(engine.getId(),
                engine.getEngineType(),
                engine.getEngineManufacturerFk(),
                engine.getCylindersPlacementFk(),
                engine.getFuelTypeFk(),
                engine.getSuperchargedTypeFk(),
                engine.getCylindersNumber(),
                engine.getFlapNumber(),
                engine.getPistonDiameter(),
                engine.getPistonStroke(),
                engine.getEngineCapacity(),
                engine.getPowerKwt(),
                engine.getHorsepower(),
                engine.getDegreeCompression(),
                engine.getReleaseYearFrom(),
                engine.getReleaseYearBy(),
                engine.getStatus_fk(),
                engine.getStatus());
    }


    @Basic
    @Column(name = "status_fk", insertable = false, updatable = false)
    public Integer getStatus_fk() {
        return status_fk;
    }

    public void setStatus_fk(Integer status_fk) {
        this.status_fk = status_fk;
    }

    public Engine(int id) {
        this.id = id;
    }

    public Engine() {
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_engine_seq")
    @SequenceGenerator(name = "id_engine_seq", initialValue = 85)
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
    @Column(name = "engine_manufacturer_fk", insertable = false, updatable = false, nullable = true)
    public Integer getEngineManufacturerFk() {
        return engineManufacturerFk;
    }

    public void setEngineManufacturerFk(Integer engineManufacturerFk) {
        this.engineManufacturerFk = engineManufacturerFk;
    }

    @Basic
    @Column(name = "cylinders_placement_fk", insertable = false, updatable = false, nullable = true)
    public int getCylindersPlacementFk() {
        return cylindersPlacementFk;
    }

    public void setCylindersPlacementFk(int cylindersPlacementFk) {
        this.cylindersPlacementFk = cylindersPlacementFk;
    }

    @Basic
    @Column(name = "fuel_type_fk", insertable = false, updatable = false, nullable = true)
    public int getFuelTypeFk() {
        return fuelTypeFk;
    }

    public void setFuelTypeFk(int fuelTypeFk) {
        this.fuelTypeFk = fuelTypeFk;
    }

    @Basic
    @Column(name = "supercharged_type_fk", insertable = false, updatable = false, nullable = true)
    public int getSuperchargedTypeFk() {
        return superchargedTypeFk;
    }

    public void setSuperchargedTypeFk(int superchargedTypeFk) {
        this.superchargedTypeFk = superchargedTypeFk;
    }

    @Basic
    @Column(name = "cylinders_number",  nullable = true)
    public Integer getCylindersNumber() {
        return cylindersNumber;
    }

    public void setCylindersNumber(Integer cylindersNumber) {
        this.cylindersNumber = cylindersNumber;
    }

    @Basic
    @Column(name = "flap_number", nullable = true)
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
        return id == engine.id &&
                cylindersPlacementFk == engine.cylindersPlacementFk &&
                fuelTypeFk == engine.fuelTypeFk &&
                superchargedTypeFk == engine.superchargedTypeFk &&
                engineCapacity == engine.engineCapacity &&
                Objects.equals(engineType, engine.engineType) &&
                Objects.equals(engineManufacturerFk, engine.engineManufacturerFk) &&
                Objects.equals(cylindersNumber, engine.cylindersNumber) &&
                Objects.equals(flapNumber, engine.flapNumber) &&
                Objects.equals(pistonDiameter, engine.pistonDiameter) &&
                Objects.equals(pistonStroke, engine.pistonStroke) &&
                Objects.equals(powerKwt, engine.powerKwt) &&
                Objects.equals(horsepower, engine.horsepower) &&
                Objects.equals(degreeCompression, engine.degreeCompression) &&
                Objects.equals(releaseYearFrom, engine.releaseYearFrom) &&
                Objects.equals(releaseYearBy, engine.releaseYearBy) &&
                Objects.equals(date, engine.date) &&
                Objects.equals(automobileEnginesById, engine.automobileEnginesById) &&
                Objects.equals(engineManufacturerByEngineManufacturerFk, engine.engineManufacturerByEngineManufacturerFk) &&
                Objects.equals(cylindersByCylindersPlacementFk, engine.cylindersByCylindersPlacementFk) &&
                Objects.equals(fuelTypeByFuelTypeFk, engine.fuelTypeByFuelTypeFk) &&
                Objects.equals(superchargedTypeBySuperchargedTypeFk, engine.superchargedTypeBySuperchargedTypeFk) &&
                Objects.equals(engineNumbersById, engine.engineNumbersById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, engineType, engineManufacturerFk, cylindersPlacementFk, fuelTypeFk, superchargedTypeFk, cylindersNumber, flapNumber, pistonDiameter, pistonStroke, engineCapacity, powerKwt, horsepower, degreeCompression, releaseYearFrom, releaseYearBy, date, automobileEnginesById, engineManufacturerByEngineManufacturerFk, cylindersByCylindersPlacementFk, fuelTypeByFuelTypeFk, superchargedTypeBySuperchargedTypeFk, engineNumbersById);
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
    @JoinColumn(name = "status_fk", referencedColumnName = "id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
