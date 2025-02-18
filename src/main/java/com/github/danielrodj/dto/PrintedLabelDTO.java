package com.github.danielrodj.dto;

public class PrintedLabelDTO extends LabelDTO {

    private String partNumber;
    private String labelType;
    private Integer quantity;
    private Integer stdPack;

    public PrintedLabelDTO() {
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStdPack() {
        return stdPack;
    }

    public void setStdPack(Integer stdPack) {
        this.stdPack = stdPack;
    }

    @Override
    public String toString() {
        return super.toString() + " PrintedLabelDTO [partNumber=" + partNumber + ", labelType=" + labelType + ", quantity=" + quantity
                + ", stdPack=" + stdPack + "]";
    }

    public boolean isInvalid() {
        return this.getReasons().isBlank() ||
                this.getRequestDate().isBlank() ||
                this.getResponseDate().isBlank() ||
                this.getRequesterId() < 0 ||
                this.getCategoryId() < 0 ||
                this.getPrinterId() < 0 ||
                this.getPartNumber().isBlank() ||
                this.getLabelType().equals("Select an option") ||
                this.getQuantity() <= 0 ||
                this.getStdPack() <= 0;
    }

}
