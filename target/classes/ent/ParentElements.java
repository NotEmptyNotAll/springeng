package com.vshvet.firstrelease.Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "parent_elements", schema = "EngDB", catalog = "")
public class ParentElements {
    private int elemId;
    private String shortName;
    private String fullName;
    private Integer parentId;
    private Collection<Parameters> parametersByElemId;

    @Id
    @Column(name = "elemID", nullable = false)
    public int getElemId() {
        return elemId;
    }

    public void setElemId(int elemId) {
        this.elemId = elemId;
    }

    @Basic
    @Column(name = "short_name", nullable = true, length = 30)
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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
    @Column(name = "parentId", nullable = true)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParentElements that = (ParentElements) o;

        if (elemId != that.elemId) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = elemId;
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "parentElementsByElemFk")
    public Collection<Parameters> getParametersByElemId() {
        return parametersByElemId;
    }

    public void setParametersByElemId(Collection<Parameters> parametersByElemId) {
        this.parametersByElemId = parametersByElemId;
    }
}
