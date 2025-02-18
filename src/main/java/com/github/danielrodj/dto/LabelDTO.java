package com.github.danielrodj.dto;

public class LabelDTO {

    private Integer labelId;
    private String reasons;
    private String requestDate;
    private String responseDate;
    private Integer requesterId;
    private Integer categoryId;
    private Integer printerId;
    private Integer originalEditorId;
    private Integer lastEditorId;

    public LabelDTO() {

    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public Integer getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Integer requesterId) {
        this.requesterId = requesterId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Integer printerId) {
        this.printerId = printerId;
    }

    public Integer getOriginalEditorId() {
        return originalEditorId;
    }

    public void setOriginalEditorId(Integer originalEditorId) {
        this.originalEditorId = originalEditorId;
    }

    public Integer getLastEditorId() {
        return lastEditorId;
    }

    public void setLastEditorId(Integer lastEditorId) {
        this.lastEditorId = lastEditorId;
    }

    @Override
    public String toString() {
        return "LabelDTO [labelId=" + labelId + ", reasons=" + reasons + ", requestDate=" + requestDate
                + ", responseDate=" + responseDate + ", requesterId=" + requesterId + ", categoryId=" + categoryId
                + ", printerId=" + printerId + ", originalEditorId=" + originalEditorId + ", lastEditorId="
                + lastEditorId + "]";
    }

}
