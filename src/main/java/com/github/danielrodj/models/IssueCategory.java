package com.github.danielrodj.models;

/**
 *
 * @author Angel Daniel Rodríguez Juárez
 */
public class IssueCategory {

    private int categoryId;
    private String categoryName;
    private String categoryDescription;

    public IssueCategory() {
    }

    public IssueCategory(int categoryId, String categoryName, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public IssueCategory(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
    
    public int getCategoryId(){
        return categoryId;
    }
    
    public String getCategoryName(){
        return categoryName;
    }
    
    public String getCategoryDescription(){
        return categoryDescription;
    }

}
