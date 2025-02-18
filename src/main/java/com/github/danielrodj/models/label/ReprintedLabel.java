package com.github.danielrodj.models.label;

public class ReprintedLabel extends Label<ReprintedLabel.Builder>{

    String serialNumber;

    private ReprintedLabel(Builder builder){
        super(builder);
        this.serialNumber = builder.serialNumber;
    }
    
    public String getSerialNumber(){
        return serialNumber;
    }

    public static class Builder extends Label.Builder<Builder>{

        private String serialNumber;
        
        public Builder serialNumber(String serialNumber){
            this.serialNumber = serialNumber;
            return this;
        }

        @Override
        protected Builder self(){
            return this;
        }

        public ReprintedLabel build(){
            return new ReprintedLabel(this);
        }

    }
}