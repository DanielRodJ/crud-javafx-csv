package com.github.danielrodj.models.label;

public class PrintedLabel extends Label<PrintedLabel.Builder>{
    
    String partNumber;
    String labelType;
    int quantity;
    int stdPack;

    private PrintedLabel(Builder builder){
        super(builder);
        this.partNumber = builder.partNumber;
        this.labelType = builder.labelType;
        this.quantity = builder.quantity;
        this.stdPack = builder.stdPack;   
    }

    public String getPartNumber(){
        return partNumber;
    }
    
    public String getLabelType(){
        return labelType;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public int getStdPack(){
        return stdPack;
    }
    
    public static class Builder extends Label.Builder<Builder>{
        private String partNumber;
        private String labelType;
        private int quantity;
        private int stdPack;

        public Builder partNumber(String partNumber){
            this.partNumber = partNumber;
            return this;
        }

        public Builder labelType(String labelType){
            this.labelType = labelType;
            return this;
        }
        
        public Builder quantity(int quantity){
            this.quantity = quantity;
            return this;
        }

        public Builder stdPack(int stdPack){
            this.stdPack = stdPack;
            return this;
        }
        
        @Override
        protected Builder self(){
            return this;
        }

        public PrintedLabel build(){
            return new PrintedLabel(this);
        }
    }

}