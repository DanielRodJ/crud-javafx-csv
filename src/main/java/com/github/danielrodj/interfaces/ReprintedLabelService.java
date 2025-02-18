package com.github.danielrodj.interfaces;

import java.util.List;

import com.github.danielrodj.models.label.ReprintedLabel;

public interface ReprintedLabelService extends LabelService<ReprintedLabel>{

    String getSerialNumberByLabelId(Integer id);

    List<ReprintedLabel> getAllReprintedLabels();

}