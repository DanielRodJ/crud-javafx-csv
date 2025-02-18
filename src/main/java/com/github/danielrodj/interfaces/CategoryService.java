package com.github.danielrodj.interfaces;

import com.github.danielrodj.models.IssueCategory;

public interface CategoryService extends BasicService<IssueCategory> {

    String getCategoryNameByCategoryId(Integer id);

}
