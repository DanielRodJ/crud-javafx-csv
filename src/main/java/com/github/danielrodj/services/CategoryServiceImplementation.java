package com.github.danielrodj.services;

import java.util.Map;

import com.github.danielrodj.interfaces.CategoryService;
import com.github.danielrodj.models.IssueCategory;
import com.github.danielrodj.repositories.CategoryRepositoryCSV;

public class CategoryServiceImplementation implements CategoryService {

    CategoryRepositoryCSV categoryRepositoryCSV;

    public CategoryServiceImplementation(){
        this.categoryRepositoryCSV = new CategoryRepositoryCSV();
    }

    @Override
    public IssueCategory get(int id) {
        return categoryRepositoryCSV.get(id);
    }

    @Override
    public Map<Integer, IssueCategory> getAll() {
        return categoryRepositoryCSV.getAll();
    }

    @Override
    public int insert(IssueCategory issueCategory) {
        return categoryRepositoryCSV.insert(issueCategory);
    }

    @Override
    public int update(IssueCategory issueCategory) {
        return categoryRepositoryCSV.update(issueCategory);
    }

    @Override
    public int delete(IssueCategory issueCategory) {
        return categoryRepositoryCSV.delete(issueCategory);
    }

    @Override
    public String getCategoryNameByCategoryId(Integer id) {
        return get(id).getCategoryName();
    }

}
