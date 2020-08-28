package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "parameter_names", schema = ConstValue.SCHEMA_NAME)
public class ParameterNames {
    private int id;
    private String name;
    private String fullName;
    private Date dateCreate;
    private Boolean treeRoot;
    private Collection<Elements> elementsById;
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

    public ParameterNames() {
    }

    public ParameterNames(int id, String name, String fullName, Boolean treeRoot, Integer status_fk) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.treeRoot = treeRoot;
        this.status_fk = status_fk;
    }

    public ParameterNames(ParameterNames parameterNames) {
        this(parameterNames.getId(),
                parameterNames.getName(),
                parameterNames.getFullName(),
                parameterNames.getTreeRoot(),
                parameterNames.getStatus_fk());
    }

    public ParameterNames(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tree_root", length = 64)
    public Boolean getTreeRoot() {
        return treeRoot;
    }

    public void setTreeRoot(Boolean treeRoot) {
        this.treeRoot = treeRoot;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "full_name", nullable = true, length = 64)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "date_create", nullable = true)
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date date) {
        this.dateCreate = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParameterNames that = (ParameterNames) o;
        return id == that.id &&
                name.equals(that.name) &&
                fullName.equals(that.fullName) &&
                dateCreate.equals(that.dateCreate) &&
                treeRoot.equals(that.treeRoot) &&
                elementsById.equals(that.elementsById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullName, dateCreate, treeRoot, elementsById);
    }

    @ManyToOne
    @JoinColumn(name = "status_fk", referencedColumnName = "id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "parameterNamesByParamNameFk")
    public Collection<Elements> getElementsById() {
        return elementsById;
    }

    public void setElementsById(Collection<Elements> elementsById) {
        this.elementsById = elementsById;
    }
}
