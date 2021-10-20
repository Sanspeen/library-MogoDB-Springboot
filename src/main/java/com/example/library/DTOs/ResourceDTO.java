package com.example.library.DTOs;

import java.time.LocalDate;

public class ResourceDTO {

    private String id;
    private String name;
    private Integer avaibleResources;
    private Integer lendedResources;
    private String kind;
    private String thematic;
    private LocalDate localDate;

    public ResourceDTO() {
    }

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
