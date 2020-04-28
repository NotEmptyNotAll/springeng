package com.vshvet.firstrelease.Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "auto_model", schema = "EngDB", catalog = "")
public class AutoModel {
    private int id;
    private String modelName;
    private Collection<Automobile> automobilesById;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoModel autoModel = (AutoModel) o;

        if (id != autoModel.id) return false;
        if (modelName != null ? !modelName.equals(autoModel.modelName) : autoModel.modelName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (modelName != null ? modelName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "autoModelByAutoModelFk")
    public Collection<Automobile> getAutomobilesById() {
        return automobilesById;
    }

    public void setAutomobilesById(Collection<Automobile> automobilesById) {
        this.automobilesById = automobilesById;
    }
}
