package com.github.danielrodj.repositories;

import com.github.danielrodj.database.BasicRepository;
import com.github.danielrodj.models.IssueCategory;

public class CategoryRepositoryCSV extends BasicRepository<IssueCategory> {

    private static final String CSV_FILE = "src/main/resources/com/github/danielrodj/csv/Categories.csv";
    private static final String HEADER = "ID;Category Name;Description";

    public CategoryRepositoryCSV() {
        super(CSV_FILE, HEADER);
    }

    @Override
    protected int getId(IssueCategory category) {
        return category.getCategoryId();
    }

    @Override
    protected IssueCategory parse(String[] values) {
        return new IssueCategory(Integer.parseInt(values[0]), values[1], values[2]);
    }

    @Override
    protected String format(IssueCategory category) {
        return String.join(";", new String[] {
                String.valueOf(category.getCategoryId()),
                category.getCategoryName(),
                category.getCategoryDescription()
        });
    }

}