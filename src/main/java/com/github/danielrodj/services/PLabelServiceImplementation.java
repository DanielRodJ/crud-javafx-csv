package com.github.danielrodj.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.danielrodj.dto.LabelDTO;
import com.github.danielrodj.dto.PrintedLabelDTO;
import com.github.danielrodj.interfaces.PrintedLabelService;
import com.github.danielrodj.models.label.PrintedLabel;
import com.github.danielrodj.repositories.PLabelRepositoryCSV;

public class PLabelServiceImplementation extends AbstractLabelService<PrintedLabel> implements PrintedLabelService {

    public PLabelServiceImplementation() {
        super(new PLabelRepositoryCSV());
    }

    @Override
    public String getPartNumberByLabelId(Integer id) {
        return get(id).getPartNumber();
    }

    @Override
    public Integer getQuantityByLabelId(Integer id) {
        return get(id).getQuantity();
    }

    @Override
    public Integer getStandarPackByLabelId(Integer id) {
        return get(id).getStdPack();
    }

    @Override
    public String getTypeByLabelId(Integer id) {
        return get(id).getLabelType();
    }

    @Override
    public List<PrintedLabel> getAllPrintedLabels() {
        List<PrintedLabel> listLabels = new ArrayList<>();
        Map<Integer, PrintedLabel> printedLabels = getAll();
        listLabels.addAll(printedLabels.values());
        return listLabels;
    }

    @Override
    public PrintedLabelDTO getDTO(Integer id) {
        return buildPrintedLabelDTO(get(id));
    }

    @Override
    public void insertDTO(LabelDTO dto) {
        PrintedLabel printedLabel = buildPrintedLabel((PrintedLabelDTO) dto);
        insert(printedLabel);
    }

    @Override
    public void updateDTO(LabelDTO dto) {
        PrintedLabel printedLabel = buildPrintedLabel((PrintedLabelDTO) dto);
        update(printedLabel);
    }

    private PrintedLabel buildPrintedLabel(PrintedLabelDTO printedLabelDTO) {
        return new PrintedLabel.Builder()
                .labelId(printedLabelDTO.getLabelId())
                .reasons(printedLabelDTO.getReasons())
                .requestDate(printedLabelDTO.getRequestDate())
                .responseDate(printedLabelDTO.getResponseDate())
                .requesterId(printedLabelDTO.getRequesterId())
                .categoryId(printedLabelDTO.getCategoryId())
                .printerId(printedLabelDTO.getPrinterId())
                .originalEditorId(printedLabelDTO.getOriginalEditorId())
                .lastEditorId(printedLabelDTO.getLastEditorId())
                .partNumber(printedLabelDTO.getPartNumber())
                .labelType(printedLabelDTO.getLabelType())
                .quantity(printedLabelDTO.getQuantity())
                .stdPack(printedLabelDTO.getStdPack())
                .build();
    }

    private PrintedLabelDTO buildPrintedLabelDTO(PrintedLabel printedLabel) {
        PrintedLabelDTO printedLabelDTO = new PrintedLabelDTO();
        printedLabelDTO.setLabelId(printedLabel.getLabelId());
        printedLabelDTO.setReasons(printedLabel.getReasons());
        printedLabelDTO.setRequestDate(printedLabel.getRequestDate());
        printedLabelDTO.setResponseDate(printedLabel.getResponseDate());
        printedLabelDTO.setRequesterId(printedLabel.getRequesterId());
        printedLabelDTO.setCategoryId(printedLabel.getCategoryId());
        printedLabelDTO.setPrinterId(printedLabel.getPrinterId());
        printedLabelDTO.setOriginalEditorId(printedLabel.getOriginalEditorId());
        printedLabelDTO.setLastEditorId(printedLabel.getLastEditorId());
        printedLabelDTO.setPartNumber(printedLabel.getPartNumber());
        printedLabelDTO.setLabelType(printedLabel.getLabelType());
        printedLabelDTO.setQuantity(printedLabel.getQuantity());
        printedLabelDTO.setStdPack(printedLabel.getStdPack());
        return printedLabelDTO;
    }

}
