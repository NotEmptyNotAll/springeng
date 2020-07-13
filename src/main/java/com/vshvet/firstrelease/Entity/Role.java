package com.vshvet.firstrelease.Entity;


import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;


@Entity
@Table(name = "roles", schema = ConstValue.SCHEMA_NAME)
public class Role {

    private Integer id;

    private ERole name;

    public Role(Integer id) {
        this.id = id;
    }

    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                name == role.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}