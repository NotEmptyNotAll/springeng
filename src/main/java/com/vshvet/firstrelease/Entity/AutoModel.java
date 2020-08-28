package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "auto_model", schema = ConstValue.SCHEMA_NAME )
public class AutoModel {
    private int id;
    private String modelName;
    private Date date;
    private Collection<AutomobileEngine> automobileEnginesById;
    private Integer status_fk;
    private Status status;

    public AutoModel(int id, String modelName, Integer status_fk) {
        this.id = id;
        this.modelName = modelName;
        this.status_fk = status_fk;
    }

    public AutoModel(AutoModel autoModel) {
        this(autoModel.getId(),
                autoModel.getModelName(),
                autoModel.getStatus_fk());
    }
    @Basic
    @Column(name = "status_fk", insertable = false, updatable = false)
    public Integer getStatus_fk() {
        return status_fk;
    }

    public void setStatus_fk(Integer status_fk) {
        this.status_fk = status_fk;
    }

    public AutoModel() {
    }

    public AutoModel(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="id_auto_model_id")
    @SequenceGenerator(name="id_auto_model_id", initialValue=193)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "model_name", nullable = false, length = 64)
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

        AutoModel autoModel = (AutoModel) o;

        if (id != autoModel.id) return false;
        if (modelName != null ? !modelName.equals(autoModel.modelName) : autoModel.modelName != null) return false;
        if (date != null ? !date.equals(autoModel.date) : autoModel.date != null) return false;

        return true;
    }

    @ManyToOne
    @JoinColumn(name = "status_fk", referencedColumnName = "id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (modelName != null ? modelName.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "autoModelByAutoModelFk")
    public Collection<AutomobileEngine> getAutomobileEnginesById() {
        return automobileEnginesById;
    }

    public void setAutomobileEnginesById(Collection<AutomobileEngine> automobileEnginesById) {
        this.automobileEnginesById = automobileEnginesById;
    }
}
