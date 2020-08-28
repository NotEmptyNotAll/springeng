package com.vshvet.firstrelease.Entity;

import com.vshvet.firstrelease.ConstValue;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "file_storage", schema = ConstValue.SCHEMA_NAME)
public class FileStorage {
    private int id;
    private Elements elements;
    private AutomobileEngine automobileEngine;
    private String fileUrl;
    private Date date;

    public FileStorage() {
    }

    public FileStorage(int id) {
        this.id = id;
    }

    public FileStorage(Elements elements,
                       AutomobileEngine automobileEngine,
                       String fileUrl) {
        this.elements = elements;
        this.automobileEngine = automobileEngine;
        this.fileUrl = fileUrl;
        this.date=new Date(new java.util.Date().getTime());
    }

    public FileStorage(int id, String fileUrl) {
        this.id = id;
        this.fileUrl = fileUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_file_storage_seq")
    @SequenceGenerator(name = "id_file_storage_seq", initialValue = 1)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Basic
    @Column(name = "file_url", nullable = false, length = 124)
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }


    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "elements_fk", referencedColumnName = "elemID", nullable = false)
    public Elements getElements() {
        return elements;
    }

    public void setElements(Elements elements) {
        this.elements = elements;
    }

    @ManyToOne
    @JoinColumn(name = "automobile_engine_fk", referencedColumnName = "id", nullable = false)
    public AutomobileEngine getAutomobileEngine() {
        return automobileEngine;
    }

    public void setAutomobileEngine(AutomobileEngine automobileEngine) {
        this.automobileEngine = automobileEngine;
    }
}
