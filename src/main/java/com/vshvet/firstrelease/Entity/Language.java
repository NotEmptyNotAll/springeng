package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "language",
        schema = ConstValue.SCHEMA_NAME)
public class Language {

    private String name;

    private Integer id;

    private List<ParameterNames> parameterNames;

    public Language() {
    }

    public Language(Integer id) {
        this.id = id;
    }

    public Language(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_language_seq")
    @SequenceGenerator(name = "id_language_seq", initialValue = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "languageFk")
    public List<ParameterNames> getParameterNames() {
        return parameterNames;
    }

    public void setParameterNames(List<ParameterNames> parameterNames) {
        this.parameterNames = parameterNames;
    }
}
