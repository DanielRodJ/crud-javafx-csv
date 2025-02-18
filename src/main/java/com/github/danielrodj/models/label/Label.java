package com.github.danielrodj.models.label;

public abstract class Label<T extends Label.Builder<T>> {

    private final int labelId;
    private final String reasons;
    private final String requestDate;
    private final String requestTime;
    private final String responseDate;
    private final String responseTime;

    private final int requesterId;
    private final int categoryId;
    private final int printerId;
    private final int originalEditorId;
    private final int lastEditorId;

    protected Label(Builder<T> builder) {
        this.labelId = builder.labelId;
        this.reasons = builder.reasons;
        this.requestDate = builder.requestDate;
        this.requestTime = builder.requestTime;
        this.responseDate = builder.responseDate;
        this.responseTime = builder.responseTime;

        this.requesterId = builder.requesterId;
        this.categoryId = builder.categoryId;
        this.printerId = builder.printerId;
        this.originalEditorId = builder.originalEditorId;
        this.lastEditorId = builder.lastEditorId;
    }

    public int getLabelId() {
        return labelId;
    }
    
    public String getReasons() {
        return reasons;
    }
    
    public String getRequestDate() {
        return requestDate;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public int getRequesterId() {
        return requesterId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getPrinterId() {
        return printerId;
    }

    public int getOriginalEditorId(){
        return originalEditorId;
    }
    
    public int getLastEditorId(){
        return lastEditorId;
    }

    public static abstract class Builder<T extends Builder<T>> {

        private int labelId;
        private String reasons;
        private String requestDate;
        private String requestTime;
        private String responseDate;
        private String responseTime;

        private int requesterId;
        private int categoryId;
        private int printerId;
        private int originalEditorId;
        private int lastEditorId;

        public T labelId(int labelId) {
            this.labelId = labelId;
            return self();
        }

        public T reasons(String reasons) {
            this.reasons = reasons;
            return self();
        }

        public T requestDate(String requestDate) {
            this.requestDate = requestDate;
            return self();
        }

        public T requestTime(String requestTime) {
            this.requestTime = requestTime;
            return self();
        }

        public T responseDate(String responseDate) {
            this.responseDate = responseDate;
            return self();
        }

        public T responseTime(String responseTime) {
            this.responseTime = responseTime;
            return self();
        }

        public T requesterId(int requesterId) {
            this.requesterId = requesterId;
            return self();
        }

        public T categoryId(int categoryId) {
            this.categoryId = categoryId;
            return self();
        }

        public T printerId(int printerId) {
            this.printerId = printerId;
            return self();
        }
        
        public T originalEditorId(int originalEditorId) {
            this.originalEditorId = originalEditorId;
            return self();
        }
        
        public T lastEditorId(int lastEditorId) {
            this.lastEditorId = lastEditorId;
            return self();
        }
        
        protected abstract T self();
    }
}
