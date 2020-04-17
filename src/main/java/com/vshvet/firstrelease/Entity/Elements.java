package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "elements", schema = ConstValue.SCHEMA_NAME)
public class Elements {
    private int elemId;
    private Integer paramNameFk;
    private Integer parentId;
    private Date date;
    private Collection<AutomobileEngine> automobileEnginesByElemId;
    private ParameterNames parameterNamesByParamNameFk;
    private Elements parentElements;
    private List<Parameters> parametersByElemId;
    private List<Elements> childElements;


    @Id
    @Column(name = "elemID", nullable = false)
    public int getElemId() {
        return elemId;
    }

    public void setElemId(int elemId) {
        this.elemId = elemId;
    }

    @Basic
    @Column(name = "param_name_fk",insertable = false,updatable = false,nullable = true)
    public Integer getParamNameFk() {
        return paramNameFk;
    }

    public void setParamNameFk(Integer paramNameFk) {
        this.paramNameFk = paramNameFk;
    }

    @Basic
    @Column(name = "parent_id", nullable = true,insertable = false,updatable = false)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

        Elements elements = (Elements) o;

        if (elemId != elements.elemId) return false;
        if (paramNameFk != null ? !paramNameFk.equals(elements.paramNameFk) : elements.paramNameFk != null)
            return false;
        if (parentId != null ? !parentId.equals(elements.parentId) : elements.parentId != null) return false;
        if (date != null ? !date.equals(elements.date) : elements.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = elemId;
        result = 31 * result + (paramNameFk != null ? paramNameFk.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
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

}
