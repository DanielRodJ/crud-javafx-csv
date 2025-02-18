package com.github.danielrodj.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.danielrodj.dto.LabelDTO;
import com.github.danielrodj.dto.ReprintedLabelDTO;
import com.github.danielrodj.interfaces.ReprintedLabelService;
import com.github.danielrodj.models.label.ReprintedLabel;
import com.github.danielrodj.repositories.RLabelRepositoryCSV;

public class RLabelServiceImplementation extends AbstractLabelService<ReprintedLabel> implements ReprintedLabelService {

    public RLabelServiceImplementation() {
        super(new RLabelRepositoryCSV());
    }

    @Override
    public String getSerialNumberByLabelId(Integer id) {
        return get(id).getSerialNumber();
    }

    @Override
    public List<ReprintedLabel> getAllReprintedLabels() {
        List<ReprintedLabel> listLabels = new ArrayList<>();
        Map<Integer, ReprintedLabel> reprintedLabels = getAll();
        listLabels.addAll(reprintedLabels.values());
        return listLabels;
    }

    @Override
    public ReprintedLabelDTO getDTO(Integer id) {
        return buildReprintedLabelDTO(get(id));
    }

    @Override
    public void insertDTO(LabelDTO dto) {
        ReprintedLabel reprintedLabel = buildReprintedLabel((ReprintedLabelDTO) dto);
        insert(reprintedLabel);
    }

    @Override
    public void updateDTO(LabelDTO dto) {
        ReprintedLabel reprintedLabel = buildReprintedLabel((ReprintedLabelDTO) dto);
        update(reprintedLabel);
    }

    /* public void addNewReprintedLabelRecord(ReprintedLabelDTO reprintedLabelDTO) {
        ReprintedLabel reprintedLabel = buildReprintedLabel(reprintedLabelDTO);
        insert(reprintedLabel);
    }

    public void updateReprintedLabelRecord(ReprintedLabelDTO reprintedLabelDTO) {
        ReprintedLabel reprintedLabel = buildReprintedLabel(reprintedLabelDTO);
        update(reprintedLabel);
    } */

    private ReprintedLabel buildReprintedLabel(ReprintedLabelDTO reprintedLabelDTO) {
        return new ReprintedLabel.Builder()
                .labelId(reprintedLabelDTO.getLabelId())
                .reasons(reprintedLabelDTO.getReasons())
                .requestDate(reprintedLabelDTO.getRequestDate())
                .responseDate(reprintedLabelDTO.getResponseDate())
                .requesterId(reprintedLabelDTO.getRequesterId())
                .categoryId(reprintedLabelDTO.getCategoryId())
                .printerId(reprintedLabelDTO.getPrinterId())
                .originalEditorId(reprintedLabelDTO.getOriginalEditorId())
                .lastEditorId(reprintedLabelDTO.getLastEditorId())
                .serialNumber(reprintedLabelDTO.getSerial())
                .build();
    }

    private ReprintedLabelDTO buildReprintedLabelDTO(ReprintedLabel reprintedLabel) {
        ReprintedLabelDTO reprintedLabelDTO = new ReprintedLabelDTO();
        reprintedLabelDTO.setLabelId(reprintedLabel.getLabelId());
        reprintedLabelDTO.setReasons(reprintedLabel.getReasons());
        reprintedLabelDTO.setRequestDate(reprintedLabel.getRequestDate());
        reprintedLabelDTO.setResponseDate(reprintedLabel.getResponseDate());
        reprintedLabelDTO.setRequesterId(reprintedLabel.getRequesterId());
        reprintedLabelDTO.setCategoryId(reprintedLabel.getCategoryId());
        reprintedLabelDTO.setPrinterId(reprintedLabel.getPrinterId());
        reprintedLabelDTO.setOriginalEditorId(reprintedLabel.getOriginalEditorId());
        reprintedLabelDTO.setLastEditorId(reprintedLabel.getLastEditorId());
        reprintedLabelDTO.setSerial(reprintedLabel.getSerialNumber());
        return reprintedLabelDTO;
    }

}
