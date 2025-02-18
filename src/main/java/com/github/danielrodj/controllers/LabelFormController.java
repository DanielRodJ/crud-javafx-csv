package com.github.danielrodj.controllers;

import java.time.LocalDate;

import com.github.danielrodj.dto.LabelDTO;
import com.github.danielrodj.dto.PrintedLabelDTO;
import com.github.danielrodj.dto.ReprintedLabelDTO;
import com.github.danielrodj.models.label.LabelType;
import com.github.danielrodj.util.ComboBoxItem;
import com.github.danielrodj.util.ControlUtils;
import com.github.danielrodj.util.GServices;
import com.github.danielrodj.util.GUIUtils;
import com.github.danielrodj.util.Global;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LabelFormController {

    private LabelType labelType;
    private LabelDTO labelDTO;
    private boolean isUpdate;

    @FXML
    private TextField txtFLabelId;
    @FXML
    private ComboBox<ComboBoxItem> cBoxRequester, cBoxPrinter, cBoxCategory;
    @FXML
    private DatePicker dpRequestDate, dpResponseDate;
    @FXML
    private TextArea txtADescription;
    @FXML
    private Button btnContextual;
    @FXML
    private TextField txtFPartNumber, txtFSerial;
    @FXML
    private ComboBox<ComboBoxItem> cBoxLabelPhase;
    @FXML
    private Spinner<Integer> spnQuantity, spnStdPack;
    @FXML
    private VBox updateSectionContainer, pLSectionContainer, rLSectionContainer;
    @FXML
    private StackPane addDataTitleContainer;

    @FXML
    private javafx.scene.control.Label lblOriginalEditorName, lblLastEditorName;

    public void manualInitialize(LabelType labelType, LabelDTO labelDTO, boolean isUpdate) {

        // Instance of shared methods
        this.labelType = labelType;
        this.isUpdate = isUpdate;
        this.labelDTO = labelDTO;
        initializeDefaultComponents();
        setupActionContext(isUpdate);
        setupLabelContext(labelType, labelDTO);
        initializeBinds(labelType);

        if (labelType == LabelType.PRINTED_LABEL) {
            initializePLComponents();
        }

        // Instance of update-exclusive methods
        if (isUpdate) {
            loadLabelUserData(labelDTO);
            loadRecordDataIntoForm(labelType, labelDTO);
            txtFLabelId.setText(String.valueOf(labelDTO.getLabelId()));
        } else {
            txtFLabelId.setText(String.valueOf(getNewestId(labelType)));
        }

    }

    // - - - - - - - - METHODS TO HIDE OR SHOW SECTIONS ACCORDING TO ADD / UPDATE.

    private void setupActionContext(boolean isUpdate) {
        updateSectionContainer.setVisible(isUpdate);
        updateSectionContainer.setManaged(isUpdate);
        addDataTitleContainer.setVisible(!isUpdate);
        addDataTitleContainer.setManaged(!isUpdate);
        btnContextual.setText(isUpdate ? "Update Label" : "Add Label");
    }

    private void setupLabelContext(LabelType labelType, LabelDTO labelDTO) {
        pLSectionContainer.setVisible(labelType == LabelType.PRINTED_LABEL);
        pLSectionContainer.setManaged(labelType == LabelType.PRINTED_LABEL);
        rLSectionContainer.setVisible(labelType == LabelType.REPRINTED_LABEL);
        rLSectionContainer.setManaged(labelType == LabelType.REPRINTED_LABEL);
    }

    // - - - - - - - - METHODS TO HIDE OR SHOW SECTIONS ACCORDING TO ADD / UPDATE.

    @FXML
    private void handleContextualAction() {
        if (isUpdate) {
            updateRecord(labelType, labelDTO);
        } else {
            addNewRecord(labelType, labelDTO);
        }
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void addNewRecord(LabelType labelType, LabelDTO labelDTO) {
        labelDTO = (labelType == LabelType.PRINTED_LABEL) ? getPLValues(labelDTO) : getRLValues(labelDTO);
        labelDTO.setOriginalEditorId(Global.activeUser.getUserId());
        labelDTO.setLastEditorId(Global.activeUser.getUserId());

        if (labelType == LabelType.PRINTED_LABEL) {
            GServices.pLabelService.insertDTO(labelDTO);
            GUIUtils.showConfirmationDialog("Printed Label Created", "The record has been successfully created.");
            closeWindow();
        }

        if (labelType == LabelType.REPRINTED_LABEL) {
            GServices.rLabelService.insertDTO(labelDTO);
            GUIUtils.showConfirmationDialog("Reprinted Label Created", "The record has been successfully created.");
            closeWindow();
        }
    }

    private void updateRecord(LabelType labelType, LabelDTO labelDTO) {
        labelDTO = (labelType == LabelType.PRINTED_LABEL) ? getPLValues(labelDTO) : getRLValues(labelDTO);
        labelDTO.setLastEditorId(Global.activeUser.getUserId());

        if (labelType == LabelType.PRINTED_LABEL) {
            PrintedLabelDTO printedLabelDTO = (PrintedLabelDTO) labelDTO;
            GServices.pLabelService.updateDTO(printedLabelDTO);
            GUIUtils.showConfirmationDialog("Printed Label Updated", "The record has been successfully updated.");
            closeWindow();
        }

        if (labelType == LabelType.REPRINTED_LABEL) {
            ReprintedLabelDTO reprintedLabelDTO = (ReprintedLabelDTO) labelDTO;
            GServices.rLabelService.updateDTO(reprintedLabelDTO);
            GUIUtils.showConfirmationDialog("Reprinted Label Updated", "The record has been successfully updated.");
            closeWindow();
        }
    }

    // - - - - - - - - - - - - - - - - - - METHODS TO LOAD EXISTING INFORMATION

    private void loadRecordDataIntoForm(LabelType labelType, LabelDTO labelDTO) {
        int requesterId = labelDTO.getRequesterId();
        int categoryId = labelDTO.getCategoryId();
        int printerId = labelDTO.getPrinterId();

        cBoxRequester.getSelectionModel()
                .select(new ComboBoxItem(requesterId, GServices.requesterService.getFullNameByRequesterId(requesterId)));
        cBoxPrinter.getSelectionModel()
                .select(new ComboBoxItem(printerId, GServices.printerService.getPrinterNameByPrinterId(printerId)));
        cBoxCategory.getSelectionModel()
                .select(new ComboBoxItem(categoryId, GServices.categoryService.getCategoryNameByCategoryId(categoryId)));

        txtADescription.setText(labelDTO.getReasons());

        if (labelType == LabelType.PRINTED_LABEL) {
            PrintedLabelDTO printedLabelDTO = (PrintedLabelDTO) labelDTO;
            txtFPartNumber.setText(printedLabelDTO.getPartNumber());
            cBoxLabelPhase.getSelectionModel().select(0);
            spnQuantity.getValueFactory().setValue(printedLabelDTO.getQuantity());
            spnStdPack.getValueFactory().setValue(printedLabelDTO.getStdPack());
        }

        if (labelType == LabelType.REPRINTED_LABEL) {
            ReprintedLabelDTO reprintedLabelDTO = (ReprintedLabelDTO) labelDTO;
            txtFSerial.setText(reprintedLabelDTO.getSerial());
        }

    }

    private void loadLabelUserData(LabelDTO labelDTO) {
        int originalEditorId = labelDTO.getOriginalEditorId();
        int lastEditorId = labelDTO.getLastEditorId();
        String originalEditorName = GServices.userService.getUsernameByUserId(originalEditorId);
        String lastEditorName = GServices.userService.getUsernameByUserId(lastEditorId);

        lblOriginalEditorName.setText(originalEditorName);
        lblLastEditorName.setText(lastEditorName);
    }

    // - - - - - - - - - - - - - - - - - - METHODS TO LOAD EXISTING INFORMATION

    // - - - - - - - - - - - - - - - - - - METHOD FOR INITIALIZATION OF BINDS

    private void initializeBinds(LabelType labelType) {
        BooleanBinding cBoxCategoryValid = createComboBoxBooleanBinding(cBoxCategory);
        BooleanBinding cBoxRequesterValid = createComboBoxBooleanBinding(cBoxRequester);
        BooleanBinding cBoxPrinterValid = createComboBoxBooleanBinding(cBoxPrinter);

        BooleanBinding buttonValid = cBoxCategoryValid.and(cBoxRequesterValid).and(cBoxPrinterValid);

        if (labelType == LabelType.PRINTED_LABEL) {
            BooleanBinding txtFPartNumberValid = createTextBooleanBinding(txtFPartNumber);
            BooleanBinding cBoxPhaseValid = createComboBoxBooleanBinding(cBoxLabelPhase);
            BooleanBinding spnQuantityValid = createSpinnerBooleanBinding(spnQuantity);
            BooleanBinding spnStdPackValid = createSpinnerBooleanBinding(spnStdPack);
            buttonValid = buttonValid
                    .and(txtFPartNumberValid)
                    .and(cBoxPhaseValid)
                    .and(spnQuantityValid)
                    .and(spnStdPackValid);
        }

        if (labelType == LabelType.REPRINTED_LABEL) {
            BooleanBinding txtFSerialValid = createTextBooleanBinding(txtFSerial);
            buttonValid = buttonValid.and(txtFSerialValid);
        }

        btnContextual.disableProperty().bind(buttonValid.not());
    }

    private BooleanBinding createTextBooleanBinding(TextField textField) {
        return Bindings.createBooleanBinding(
                () -> !textField.getText().isEmpty(), textField.textProperty());
    }

    private BooleanBinding createComboBoxBooleanBinding(ComboBox<ComboBoxItem> comboBox) {
        return Bindings.createBooleanBinding(
                () -> (comboBox.getValue() != null && comboBox.getValue().getId() != -1), comboBox.valueProperty());
    }

    private BooleanBinding createSpinnerBooleanBinding(Spinner<Integer> spinner) {
        return Bindings.createBooleanBinding(
                () -> spinner.getValue() != null && spinner.getValue() != 0, spinner.valueProperty());
    }

    // - - - - - - - - - - - - - - - - - - METHOD FOR INITIALIZATION OF BINDS

    // - - - - - - - - - - - METHODS RELATED TO THE RETRIEVAL OF VALUES IN FIELDS

    private PrintedLabelDTO getPLValues(LabelDTO labelDTO) {
        PrintedLabelDTO dto = new PrintedLabelDTO();
        dto.setLabelId(Integer.parseInt(txtFLabelId.getText()));
        dto.setRequesterId(cBoxRequester.getSelectionModel().getSelectedItem().getId());
        dto.setPrinterId(cBoxPrinter.getSelectionModel().getSelectedItem().getId());
        dto.setCategoryId(cBoxCategory.getSelectionModel().getSelectedItem().getId());
        dto.setRequestDate(dpRequestDate.getValue().toString());
        dto.setResponseDate(dpResponseDate.getValue().toString());
        dto.setOriginalEditorId(labelDTO.getOriginalEditorId());
        dto.setLastEditorId(labelDTO.getLastEditorId());
        dto.setReasons(txtADescription.getText());
        dto.setPartNumber(txtFPartNumber.getText());
        dto.setLabelType(cBoxLabelPhase.getSelectionModel().getSelectedItem().getName());
        dto.setQuantity(spnQuantity.getValue());
        dto.setStdPack(spnStdPack.getValue());
        return dto;
    }

    private ReprintedLabelDTO getRLValues(LabelDTO labelDTO) {
        ReprintedLabelDTO dto = new ReprintedLabelDTO();
        dto.setLabelId(Integer.parseInt(txtFLabelId.getText()));
        dto.setRequesterId(cBoxRequester.getSelectionModel().getSelectedItem().getId());
        dto.setPrinterId(cBoxPrinter.getSelectionModel().getSelectedItem().getId());
        dto.setCategoryId(cBoxCategory.getSelectionModel().getSelectedItem().getId());
        dto.setRequestDate(dpRequestDate.getValue().toString());
        dto.setResponseDate(dpResponseDate.getValue().toString());
        dto.setOriginalEditorId(labelDTO.getOriginalEditorId());
        dto.setLastEditorId(labelDTO.getLastEditorId());
        dto.setReasons(txtADescription.getText());
        dto.setSerial(txtFSerial.getText());
        return dto;
    }

    // - - - - - - - - - - - METHODS RELATED TO THE RETRIEVAL OF VALUES IN FIELDS

    // - - - - - - - - - - - METHODS RELATED TO THE INITIALIZATION OF COMPONENTS.

    private void initializeDefaultComponents() {
        ControlUtils.populateComboBox(GServices.requesterService.getAll(), cBoxRequester,
                entry -> new ComboBoxItem(entry.getKey(),
                        entry.getValue().getFirstName() + " " + entry.getValue().getLastName()),
                "The map is empty or null!");
        ControlUtils.populateComboBox(GServices.categoryService.getAll(), cBoxCategory,
                entry -> new ComboBoxItem(entry.getKey(), entry.getValue().getCategoryName()),
                "The map is empty or null!");
        ControlUtils.populateComboBox(GServices.printerService.getAll(), cBoxPrinter,
                entry -> new ComboBoxItem(entry.getKey(), entry.getValue().getPrinterName()),
                "The map is empty or null!");

        dpRequestDate.setValue(LocalDate.now());
        dpResponseDate.setValue(LocalDate.now());
    }

    private void initializePLComponents() {
        ControlUtils.populateComboBox(GServices.phaseService.getAll(), cBoxLabelPhase,
                entry -> new ComboBoxItem(entry.getKey(), entry.getValue().getPhaseName()),
                "The map is empty or null!");

        spnQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100));
        spnStdPack.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100));
    }

    // - - - - - - - - - - - METHODS RELATED TO THE INITIALIZATION OF COMPONENTS.

    private int getNewestId(LabelType labelType) {
        return labelType == LabelType.PRINTED_LABEL ? GServices.pLabelService.getNewestId() : GServices.rLabelService.getNewestId();
    }

    private void closeWindow() {
        Stage stage = (Stage) txtFLabelId.getScene().getWindow();
        stage.close();
    }

}