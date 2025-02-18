package com.github.danielrodj.interfaces;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import com.github.danielrodj.dto.LabelDTO;
import com.github.danielrodj.models.label.Label;

public interface LabelService<T extends Label<?>> extends BasicService<T> {

    Integer getRequesterIdByLabelId(Integer id);
    
    Integer getPrinterIdByLabelId(Integer id);

    Integer getCategoryIdByLabelId(Integer id);

    Integer getOriginalEditorIdByLabelId(Integer id);

    Integer getLastEditorIdByLabelId(Integer id);

    String getReasonsByLabelId(Integer id);

    String getRequestDateByLabelId(Integer id);

    String getResponseDateByLabelId(Integer id);

    LocalDate getParseRequestDateByLabelId(Integer id) throws ParseException;

    LocalDate getParseResponseDateByLabelId(Integer id) throws ParseException;

    Integer deleteLabelByLabelId(Integer id);

    LabelDTO getDTO(Integer id);

    void insertDTO(LabelDTO dto);
 
    void updateDTO(LabelDTO dto);

    List<Label<?>> getAllLabels();
    
}
