package com.github.danielrodj.interfaces;

import java.util.List;

import com.github.danielrodj.models.label.PrintedLabel;

public interface PrintedLabelService{

    String getPartNumberByLabelId(Integer id);

    String getTypeByLabelId(Integer id);

    Integer getQuantityByLabelId(Integer id);

    Integer getStandarPackByLabelId(Integer id);

    List<PrintedLabel> getAllPrintedLabels();
    
}
