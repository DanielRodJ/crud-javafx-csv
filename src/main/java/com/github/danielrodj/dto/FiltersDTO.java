package com.github.danielrodj.dto;

public class FiltersDTO {
    private Integer labelId;
    private Integer categoryId;
    private Integer requesterId;
    private Integer printerId;
    private String startDate;
    private String endDate;

    public FiltersDTO() {
        
    }

    public FiltersDTO(Integer labelId, Integer categoryId, Integer requesterId, Integer printerId, String startDate,
            String endDate) {
        this.labelId = labelId;
        this.categoryId = categoryId;
        this.requesterId = requesterId;
        this.printerId = printerId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Integer requesterId) {
        this.requesterId = requesterId;
    }

    public Integer getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Integer printerId) {
        this.printerId = printerId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "FiltersDTO [labelId=" + labelId + ", categoryId=" + categoryId + ", requesterId=" + requesterId
                + ", printerId=" + printerId + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }

}
