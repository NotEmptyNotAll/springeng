package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;

@Entity
@Table(name = "status", schema = ConstValue.SCHEMA_NAME)
public class Status {
    private Integer id;
    private String status;

    public Status() {
    }

    public Status(Integer id) {
        this.id = id;
    }

    public Status(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    @Id
    @Column(name = "id", nullable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_status_seq")
    @SequenceGenerator(name = "id_status_seq", initialValue = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status", insertable = false, updatable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
