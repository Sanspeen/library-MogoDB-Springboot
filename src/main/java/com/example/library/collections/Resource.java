package com.example.library.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Resource {
    @Id
    private String id;
    private String name;
    private Integer avaibleResources;
    private Integer lendedResources;
    private String kind;
    private String thematic;
    private LocalDate localDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvaibleResources() {
        return avaibleResources;
    }

    public void setAvaibleResources(Integer avaibleResources) {
        this.avaibleResources = avaibleResources;
    }

    public Integer getLendedResources() {
        return lendedResources;
    }

    public void setLendedResources(Integer lendedResources) {
        this.lendedResources = lendedResources;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getThematic() {
        return thematic;
    }

    public void setThematic(String thematic) {
        this.thematic = thematic;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
