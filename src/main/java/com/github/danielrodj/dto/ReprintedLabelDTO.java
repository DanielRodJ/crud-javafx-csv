package com.github.danielrodj.dto;

public class ReprintedLabelDTO extends LabelDTO {

    private String serial;

    public ReprintedLabelDTO() {
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return super.toString() + " ReprintedLabelDTO [serial=" + serial + "]";
    }

    public boolean isInvalid() {
        return this.getReasons().isBlank() ||
                this.getRequestDate().isBlank() ||
                this.getResponseDate().isBlank() ||
                this.getRequesterId() < 0 ||
                this.getCategoryId() < 0 ||
                this.getPrinterId() < 0 ||
                this.getSerial().isBlank();
    }

}
