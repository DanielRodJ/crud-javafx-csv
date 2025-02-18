package com.github.danielrodj.models;

public class LabelPhase {

    Integer id;
    String acronym;
    String name;
    String description;

    public LabelPhase() {
    }

    public LabelPhase(Integer id, String acronym, String name, String description) {
        this.id = id;
        this.acronym = acronym;
        this.name = name;
        this.description = description;
    }

    public Integer getPhaseId() {
        return id;
    }

    public void setPhaseId(Integer id) {
        this.id = id;
    }

    public String getPhaseAcronym() {
        return acronym;
    }

    public void setPhaseAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getPhaseName() {
        return name;
    }

    public void setPhaseName(String name) {
        this.name = name;
    }

    public String getPhaseDescription() {
        return description;
    }

    public void setPhaseDescription(String description) {
        this.description = description;
    }

}
