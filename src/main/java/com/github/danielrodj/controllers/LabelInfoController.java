package com.github.danielrodj.controllers;

import com.github.danielrodj.dto.LabelDTO;
import com.github.danielrodj.dto.PrintedLabelDTO;
import com.github.danielrodj.dto.ReprintedLabelDTO;
import com.github.danielrodj.models.label.LabelType;
import com.github.danielrodj.util.GServices;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class LabelInfoController {

    private final StringProperty txtIdentifier = new SimpleStringProperty();
    private final StringProperty txtLabelPhase = new SimpleStringProperty();
    private final StringProperty txtStdPack = new SimpleStringProperty();
    private final StringProperty txtQuantity = new SimpleStringProperty();
    private final StringProperty txtRequester = new SimpleStringProperty();
    private final StringProperty txtRequestDate = new SimpleStringProperty();
    private final StringProperty txtResponseDate = new SimpleStringProperty();
    private final StringProperty txtPrinter = new SimpleStringProperty();
    private final StringProperty txtCategory = new SimpleStringProperty();

    @FXML // Labels with Variants.
    private Label lblIdentifier, lblLabelPhase, lblStdPack, lblQuantity;

    @FXML // Shared Labels.
    private Label lblRequester, lblRequestDate, lblResponseDate, lblPrinter, lblCategory;

    @FXML // Shared TextArea
    private TextArea txtADescription;

    @FXML
    private void initialize() {
        lblIdentifier.setText("Identifier : ");
        lblLabelPhase.setText("Label Phase : ");
        lblStdPack.setText("Standar Pack : ");
        lblQuantity.setText("Quantity : ");
        lblRequester.setText("Requester : ");
        lblRequestDate.setText("Request Date : ");
        lblResponseDate.setText("Response Date : ");
        lblPrinter.setText("Printer Name : ");
        lblCategory.setText("Issue Category : ");
        txtADescription.setWrapText(true);
        bindLabels();
    }

    private void bindLabels() {
        lblIdentifier.textProperty().bind(txtIdentifier);
        lblLabelPhase.textProperty().bind(txtLabelPhase);
        lblStdPack.textProperty().bind(txtStdPack);
        lblQuantity.textProperty().bind(txtQuantity);
        lblRequester.textProperty().bind(txtRequester);
        lblRequestDate.textProperty().bind(txtRequestDate);
        lblResponseDate.textProperty().bind(txtResponseDate);
        lblPrinter.textProperty().bind(txtPrinter);
        lblCategory.textProperty().bind(txtCategory);
    }

    public void manualInitialize(LabelType labelType, LabelDTO labelDTO) {
        if (labelType == LabelType.PRINTED_LABEL) {
            PrintedLabelDTO printedLabelDTO = (PrintedLabelDTO) labelDTO;
            txtIdentifier.set("Part Number : " + printedLabelDTO.getPartNumber());
            txtLabelPhase.set("Label Phase : " + printedLabelDTO.getLabelType());
            txtStdPack.set("Standar Pack : " + printedLabelDTO.getStdPack());
            txtQuantity.set("Quantity : " + printedLabelDTO.getQuantity());
        } else {
            ReprintedLabelDTO reprintedLabelDTO = (ReprintedLabelDTO) labelDTO;
            txtIdentifier.set("Serial Number : " + reprintedLabelDTO.getSerial());
            txtLabelPhase.set("");
            txtStdPack.set("");
            txtQuantity.set("");
        }

        executeSharedLogic(labelDTO);
    }

    private void executeSharedLogic(LabelDTO labelDTO) {
        txtRequester.set("Requester : " + getRequesterFullName(labelDTO.getRequesterId()));
        txtRequestDate.set("Request Date : " + labelDTO.getRequestDate());
        txtResponseDate.set("Response Date : " + labelDTO.getResponseDate());
        txtPrinter.set("Printer Name : " + getPrinterName(labelDTO.getPrinterId()));
        txtCategory.set("Issue Category : " + getCategoryName(labelDTO.getCategoryId()));
        txtADescription.setText(labelDTO.getReasons());
    }

    private String getRequesterFullName(int requesterId) {
        return GServices.requesterService.getFullNameByRequesterId(requesterId);
    }

    private String getPrinterName(int printerId) {
        return GServices.printerService.getPrinterNameByPrinterId(printerId);
    }

    private String getCategoryName(int categoryId) {
        return GServices.categoryService.getCategoryNameByCategoryId(categoryId);
    }

}