package com.github.danielrodj.dto;

public class LabelUserData {

    Integer originalEditorId;
    String originalEditorName;
    Integer lastEditorId;
    String lastEditorName;
    String dateCreated;
    String dateUpdated;

    public LabelUserData() {
    }

    public Integer getOriginalEditorId() {
        return originalEditorId;
    }

    public void setOriginalEditorId(Integer originalEditorId) {
        this.originalEditorId = originalEditorId;
    }

    public String getOriginalEditorName() {
        return originalEditorName;
    }

    public void setOriginalEditorName(String originalEditorName) {
        this.originalEditorName = originalEditorName;
    }

    public Integer getLastEditorId() {
        return lastEditorId;
    }

    public void setLastEditorId(Integer lastEditorId) {
        this.lastEditorId = lastEditorId;
    }

    public String getLastEditorName() {
        return lastEditorName;
    }

    public void setLastEditorName(String lastEditorName) {
        this.lastEditorName = lastEditorName;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

}
