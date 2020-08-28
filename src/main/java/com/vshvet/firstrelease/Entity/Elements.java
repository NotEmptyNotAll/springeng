package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "elements", schema = ConstValue.SCHEMA_NAME)
public class Elements implements Comparable<Elements> {
    private int elemId;
    private Integer paramNameFk;
    private Integer parentId;
    private String color;
    private Date date;
    private Collection<AutomobileEngine> automobileEnginesByElemId;
    private ParameterNames parameterNamesByParamNameFk;
    private Elements parentElements;
    private List<Parameters> parametersByElemId;
    private List<Elements> childElements;
    private List<FileStorage> fileStorages;
    private Integer sortNumber;

    private Status status;

    @Basic
    @Column(name = "sort_number", insertable = false, updatable = false, nullable = true)
    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer status_fk) {
        this.sortNumber = status_fk;
    }


    public Elements(int elemId) {
        this.elemId = elemId;
    }

    public Elements() {
    }

    public Elements(Elements elements) {
        this(elements.getElemId(),
                elements.getParamNameFk(),
                elements.getParentId(),
                elements.getSortNumber());
    }

    public Elements(int elemId,
                    Integer paramNameFk,
                    Integer parentId, Integer status_fk) {
        this.elemId = elemId;
        this.paramNameFk = paramNameFk;
        this.parentId = parentId;
        this.sortNumber = status_fk;
    }

    public Elements(int elemId, Integer paramNameFk, Integer parentId, String color, Date date, Collection<AutomobileEngine> automobileEnginesByElemId, ParameterNames parameterNamesByParamNameFk, Elements parentElements, List<Parameters> parametersByElemId, List<Elements> childElements, List<FileStorage> fileStorages, Integer sortNumber, Status status) {
        this.elemId = elemId;
        this.paramNameFk = paramNameFk;
        this.parentId = parentId;
        this.color = color;
        this.date = date;
        this.automobileEnginesByElemId = automobileEnginesByElemId;
        this.parameterNamesByParamNameFk = parameterNamesByParamNameFk;
        this.parentElements = parentElements;
        this.parametersByElemId = parametersByElemId;
        this.childElements = childElements;
        this.fileStorages = fileStorages;
        this.sortNumber = sortNumber;
        this.status = status;
    }

    @Id
    @Column(name = "elemID", nullable = false)
    public int getElemId() {
        return elemId;
    }

    public void setElemId(int elemId) {
        this.elemId = elemId;
    }


    @Basic
    @Column(name = "color", insertable = false, updatable = false, nullable = true)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "param_name_fk", insertable = false, updatable = false, nullable = true)
    public Integer getParamNameFk() {
        return paramNameFk;
    }

    public void setParamNameFk(Integer paramNameFk) {
        this.paramNameFk = paramNameFk;
    }

    @Basic
    @Column(name = "parent_id", nullable = true, insertable = false, updatable = false)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
        Elements elements = (Elements) o;
        return elemId == elements.elemId &&
                Objects.equals(paramNameFk, elements.paramNameFk) &&
                Objects.equals(parentId, elements.parentId) &&
                Objects.equals(color, elements.color) &&
                Objects.equals(date, elements.date) &&
                Objects.equals(automobileEnginesByElemId, elements.automobileEnginesByElemId) &&
                Objects.equals(parameterNamesByParamNameFk, elements.parameterNamesByParamNameFk) &&
                Objects.equals(parentElements, elements.parentElements) &&
                Objects.equals(parametersByElemId, elements.parametersByElemId) &&
                Objects.equals(childElements, elements.childElements) &&
                Objects.equals(fileStorages, elements.fileStorages) &&
                Objects.equals(sortNumber, elements.sortNumber) &&
                Objects.equals(status, elements.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elemId, paramNameFk, parentId, color, date, automobileEnginesByElemId, parameterNamesByParamNameFk, parentElements, parametersByElemId, childElements, fileStorages, sortNumber, status);
    }

    @OneToMany(mappedBy = "elementsByElemId")
    public Collection<AutomobileEngine> getAutomobileEnginesByElemId() {
        return automobileEnginesByElemId;
    }

    public void setAutomobileEnginesByElemId(Collection<AutomobileEngine> automobileEnginesByElemId) {
        this.automobileEnginesByElemId = automobileEnginesByElemId;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "elemID")
    public Elements getParentElements() {
        return parentElements;
    }

    public void setParentElements(Elements parentElements) {
        this.parentElements = parentElements;
    }

    @ManyToOne
    @JoinColumn(name = "param_name_fk", referencedColumnName = "id")
    public ParameterNames getParameterNamesByParamNameFk() {
        return parameterNamesByParamNameFk;
    }

    public void setParameterNamesByParamNameFk(ParameterNames parameterNamesByParamNameFk) {
        this.parameterNamesByParamNameFk = parameterNamesByParamNameFk;
    }

    @OneToMany(mappedBy = "parentElements")
    public List<Elements> getChildElements() {
        return childElements;
    }

    public void setChildElements(List<Elements> childElements) {
        this.childElements = childElements;
    }

    @OneToMany(mappedBy = "elementsByElemFk")
    public List<Parameters> getParametersByElemId() {
        return parametersByElemId;
    }

    public void setParametersByElemId(List<Parameters> parametersByElemId) {
        this.parametersByElemId = parametersByElemId;
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
    public int compareTo(Elements o) {
        return parameterNamesByParamNameFk.getName().compareTo(o.getParameterNamesByParamNameFk().getName());
    }

    @OneToMany(mappedBy = "elements")
    public List<FileStorage> getFileStorages() {
        return fileStorages;
    }

    public void setFileStorages(List<FileStorage> fileStorages) {
        this.fileStorages = fileStorages;
    }
    /*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "elem_param",
            joinColumns = @JoinColumn(name = "elem_id"),
            inverseJoinColumns = @JoinColumn(name = "param_id"))
    public Set<Parameters> getParameters() {
        return parameters;
    }

    public void setParameters(Set<Parameters> parameters) {
        this.parameters = parameters;
    }
*/
}
