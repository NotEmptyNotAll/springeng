package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
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
    private Language language;
    private Integer languageFk;
    private Integer defaultParamNameFk;
    private ParameterNames defaultParamName;
    private List<ParameterNames> parameterNamesList;


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

    public ParameterNames(int id, String name, String fullName, Date dateCreate, Boolean treeRoot, Collection<Elements> elementsById, Integer status_fk, Status status, Language language, Integer language_fk) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.dateCreate = dateCreate;
        this.treeRoot = treeRoot;
        this.elementsById = elementsById;
        this.status_fk = status_fk;
        this.status = status;
        this.language = language;
        this.languageFk = language_fk;
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

    @Basic
    @Column(name = "language_fk", insertable = false, updatable = false, nullable = true)
    public Integer getLanguageFk() {
        return languageFk;
    }

    public void setLanguageFk(Integer languageFk) {
        this.languageFk = languageFk;
    }

    @Basic
    @Column(name = "default_param_name_fk", nullable = true, insertable = false, updatable = false)
    public Integer getDefaultParamNameFk() {
        return defaultParamNameFk;
    }


    public void setDefaultParamNameFk(Integer defaultParamNameFk) {
        this.defaultParamNameFk = defaultParamNameFk;
    }

    @ManyToOne
    @JoinColumn(name = "default_param_name_fk", referencedColumnName = "id")
    public ParameterNames getDefaultParamName() {
        return defaultParamName;
    }

    public void setDefaultParamName(ParameterNames defaultParamName) {
        this.defaultParamName = defaultParamName;
    }

    @ManyToOne
    @JoinColumn(name = "languageFk", referencedColumnName = "id", nullable = false)
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @OneToMany(mappedBy = "defaultParamNameFk")
    public List<ParameterNames> getParameterNamesList() {
        return parameterNamesList;
    }

    public void setParameterNamesList(List<ParameterNames> parameterNamesList) {
        this.parameterNamesList = parameterNamesList;
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
