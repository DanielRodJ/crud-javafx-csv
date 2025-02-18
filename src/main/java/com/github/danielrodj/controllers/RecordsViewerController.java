package com.github.danielrodj.controllers;

import java.time.LocalDate;

import com.github.danielrodj.dto.FiltersDTO;
import com.github.danielrodj.dto.LabelDTO;
import com.github.danielrodj.dto.PrintedLabelDTO;
import com.github.danielrodj.dto.ReprintedLabelDTO;
import com.github.danielrodj.models.label.Label;
import com.github.danielrodj.models.label.LabelType;
import com.github.danielrodj.models.label.PrintedLabel;
import com.github.danielrodj.services.AbstractLabelService;
import com.github.danielrodj.util.ComboBoxItem;
import com.github.danielrodj.util.ControlUtils;
import com.github.danielrodj.util.GUIUtils;
import com.github.danielrodj.util.Global;
import com.github.danielrodj.util.view.TypeWindow;
import com.github.danielrodj.util.view.ViewWindow;
import com.github.danielrodj.util.GServices;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RecordsViewerController {

    private ObservableList<Label<?>> lastList;

    private String selectedType;
    private Integer selectedIndex, selectedLabelId;

    @FXML
    private TableView<Label<?>> labelRecords;

    @FXML
    private TableColumn<Label<?>, Integer> colId;

    @FXML
    private TableColumn<Label<?>, String> colType, colRequester, colCategory, colPrinter, colRequestDate,
            colResponseDate, colOriginalEditor, colLastEditor;

    @FXML
    private DatePicker dpStart, dpEnd;

    @FXML
    private Button btnDeleteRecord, btnUpdateRecord, btnAddRecord, btnDisplayRecord, btnApply;

    @FXML
    private TextField txtFLabelId, txtFPartNumber, txtFSerial;

    @FXML
    private ComboBox<ComboBoxItem> cBoxCategory, cBoxPrinter, cBoxRequester, cBoxLabelPhase, cBoxDisplay;

    @FXML
    private CheckBox chkBLabelId, chkBCategory, chkBRequester, chkBPrinter, chkBDate, chkBPartNumber, chkBPhase,
            chkBSerial, chkBAll;

    @FXML
    private javafx.scene.control.Label lblSelectedId, lblUsername;

    @FXML
    public void initialize() {
        initializeComponents();
        initializeTableColumns();
        loadAllLabelsInTable();
        setupRowSelectionListener();
        initializeListeners();
        initializeBinds();
        ControlUtils.initializeTableListenerWidth(labelRecords);
    }

    @FXML
    private void handleDisplayRecordAction() {
        LabelType labelType = mapSelectedTypeToLabelType(selectedType);
        ViewWindow labelInfoWindow = new ViewWindow(TypeWindow.LABEL_INFO, getApplicationModal());
        LabelInfoController controller = (LabelInfoController) labelInfoWindow.getController();
        LabelDTO labelDTO = createLabelDTO(labelType, selectedLabelId, true);
        controller.manualInitialize(labelType, labelDTO);
        labelInfoWindow.show();
    }

    @FXML
    private void handleAddRecordAction() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        String option = GUIUtils.showThreeOptionDialog(
                "Label Type",
                null,
                "Select an option",
                new String[] { "Printed Label", "Reprinted Label" });

        LabelType labelType = mapSelectedTypeToLabelType(option);
        LabelDTO labelDTO = createLabelDTO(labelType, null, false);
        ViewWindow registerLabelWindow = new ViewWindow(TypeWindow.LABEL_FORM, getApplicationModal());
        LabelFormController controller = (LabelFormController) registerLabelWindow.getController();
        controller.manualInitialize(labelType, labelDTO, false);
        registerLabelWindow.show();
    }

    @FXML
    private void handleUpdateRecordAction() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        LabelType labelType = mapSelectedTypeToLabelType(selectedType);
        LabelDTO labelDTO = createLabelDTO(labelType, selectedLabelId, true);
        ViewWindow updateLabelWindow = new ViewWindow(TypeWindow.LABEL_FORM, getApplicationModal());
        LabelFormController controller = (LabelFormController) updateLabelWindow.getController();
        controller.manualInitialize(labelType, labelDTO, true);
        updateLabelWindow.show();
    }

    public void reloadTable(){
        populateTable(lastList);
    }

    @FXML
    private void handleDeleteRecordAction() {
        AbstractLabelService<?> labelService = selectedType.equals("Printed Label")
                ? GServices.pLabelService
                : GServices.rLabelService;

        boolean confirmed = GUIUtils.showConfirmationDialog("Delete Record",
                "Are you sure you want to delete this record?");

        if (!confirmed) {
            return;
        }

        int result = labelService.deleteLabelByLabelId(selectedLabelId);

        if (result == 1) {
            labelRecords.getItems().removeIf(label -> Integer.valueOf(label.getLabelId()).equals(selectedLabelId));
            labelRecords.getSelectionModel().clearSelection();
            rowSelected(false);
        } else {
            GUIUtils.showError("Deletion Failed", null, "Could not delete the selected label.");
        }
    }

    @FXML
    private void handleApplyAction() {
        FiltersDTO filters = setSelectedFilters(
                txtFLabelId.getText(),
                cBoxCategory.getSelectionModel().getSelectedItem().getId(),
                cBoxRequester.getSelectionModel().getSelectedItem().getId(),
                cBoxPrinter.getSelectionModel().getSelectedItem().getId(),
                dpStart.getValue().toString(),
                dpEnd.getValue().toString());

        ObservableList<Label<?>> observableList = loadFilteredLabelsInTable(filters);

        populateTable(observableList);
        chkBAll.setSelected(false);
    }

    @FXML
    private void handleDefaultAction() {
        ObservableList<Label<?>> observableListLabels = FXCollections
                .observableArrayList(GServices.pLabelService.getAllLabels());
        populateTable(observableListLabels);
        chkBAll.setSelected(false);
        cBoxLabelPhase.getSelectionModel().select(1);

    }

    @FXML
    private void handleExportDataAction() {

    }

    private Stage getApplicationModal() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }

    private LabelType mapSelectedTypeToLabelType(String selectedType) {
        switch (selectedType) {
            case "Printed Label":
                return LabelType.PRINTED_LABEL;
            case "Reprinted Label":
                return LabelType.REPRINTED_LABEL;
            default:
                throw new IllegalArgumentException("Unsupported label type: " + selectedType);
        }
    }

    private LabelDTO createLabelDTO(LabelType labelType, Integer selectedLabelId, boolean load) {
        switch (labelType) {
            case PRINTED_LABEL:
                return load ? GServices.pLabelService.getDTO(selectedLabelId) : new PrintedLabelDTO();
            case REPRINTED_LABEL:
                return load ? GServices.rLabelService.getDTO(selectedLabelId) : new ReprintedLabelDTO();
            default:
                throw new IllegalArgumentException("Unsupported label type: " + labelType);
        }
    }

    private void initializeComponents() {
        lblUsername.setText(Global.activeUser.getUsername());
        initializeCombobox();
        initializeDatePicker();
    }

    private void initializeTableColumns() {

        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(
                cellData.getValue().getLabelId()).asObject());

        colRequester.setCellValueFactory(cellData -> new SimpleStringProperty(
                GServices.requesterService
                        .getFullNameByRequesterId(cellData.getValue().getRequesterId())));

        colCategory.setCellValueFactory(cellData -> new SimpleStringProperty(
                GServices.categoryService
                        .getCategoryNameByCategoryId(cellData.getValue().getCategoryId())));

        colPrinter.setCellValueFactory(cellData -> new SimpleStringProperty(
                GServices.printerService
                        .getPrinterNameByPrinterId(cellData.getValue().getPrinterId())));

        colRequestDate.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getRequestDate()));

        colResponseDate.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getResponseDate()));

        colType.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue() instanceof PrintedLabel ? "Printed Label" : "Reprinted Label"));

        colOriginalEditor.setCellValueFactory(cellData -> new SimpleStringProperty(
                GServices.userService
                        .getUsernameByUserId(cellData.getValue().getOriginalEditorId())));

        colLastEditor.setCellValueFactory(cellData -> new SimpleStringProperty(
                GServices.userService.getUsernameByUserId(cellData.getValue().getLastEditorId())));
    }

    private void populateTable(ObservableList<Label<?>> listLabels) {
        if (listLabels != null && !listLabels.isEmpty()) {
            labelRecords.getItems().setAll(listLabels);
        } else {
            labelRecords.getItems().clear();
        }
        lastList = listLabels;
    }

    private void setupRowSelectionListener() {
        labelRecords.getSelectionModel().selectedItemProperty()
                .addListener((_, _, newValue) -> {
                    if (newValue != null) {
                        rowSelected(true);
                        lblSelectedId.setText("The Selected Label ID is : " + selectedLabelId);
                    } else {
                        rowSelected(false);
                        lblSelectedId.setText("The Selected Label ID is : No Label Selected");
                    }
                });
    }

    private void rowSelected(boolean isSelected) {
        this.selectedIndex = isSelected ? labelRecords.getSelectionModel().getSelectedIndex() : null;
        this.selectedType = isSelected ? colType.getCellData(selectedIndex) : null;
        this.selectedLabelId = isSelected ? labelRecords.getItems().get(selectedIndex).getLabelId() : null;
    }

    // - - - - - - - - - - - - - - - - - - METHODS RELATED TO THE FILTERING FUNCTION

    private FiltersDTO setSelectedFilters(String labelId, Integer categoryId, Integer requesterId,
            Integer printerId, String startDate, String endDate) {
        FiltersDTO filtersDTO = new FiltersDTO();
        filtersDTO.setCategoryId(chkBCategory.isSelected() ? categoryId : null);
        filtersDTO.setRequesterId(chkBRequester.isSelected() ? requesterId : null);
        filtersDTO.setPrinterId(chkBPrinter.isSelected() ? printerId : null);
        filtersDTO.setStartDate(chkBDate.isSelected() ? startDate : null);
        filtersDTO.setEndDate(chkBDate.isSelected() ? endDate : null);
        return filtersDTO;
    }

    private ObservableList<Label<?>> loadFilteredLabelsInTable(FiltersDTO filters) {
        ObservableList<Label<?>> observableListLabels = FXCollections
                .observableArrayList(GServices.pLabelService.getAllLabelsByRequester(
                        filters.getCategoryId(),
                        filters.getRequesterId(),
                        filters.getPrinterId(),
                        filters.getStartDate(),
                        filters.getEndDate()));
        return observableListLabels;
    }

    // - - - - - - - - - - - - - - - - - - METHODS RELATED TO THE FILTERING FUNCTION

    // - - - - - - - - - - - - - - - - - - METHOD FOR INITIALIZATION OF BINDS

    private void initializeBinds() {

        BooleanBinding isRowSelected = Bindings
                .isNotNull(labelRecords.getSelectionModel().selectedItemProperty());

        btnDeleteRecord.disableProperty().bind(isRowSelected.not());
        btnUpdateRecord.disableProperty().bind(isRowSelected.not());
        btnAddRecord.disableProperty().bind(isRowSelected);
        btnDisplayRecord.disableProperty().bind(isRowSelected.not());

        // Bindings to enable the field corresponding to each checkbox
        txtFLabelId.disableProperty().bind(chkBLabelId.selectedProperty().not());
        cBoxCategory.disableProperty().bind(chkBCategory.selectedProperty().not());
        cBoxRequester.disableProperty().bind(chkBRequester.selectedProperty().not());
        cBoxPrinter.disableProperty().bind(chkBPrinter.selectedProperty().not());
        dpStart.disableProperty().bind(chkBDate.selectedProperty().not());
        dpEnd.disableProperty().bind(chkBDate.selectedProperty().not());
        txtFPartNumber.disableProperty().bind(chkBPartNumber.selectedProperty().not());
        cBoxLabelPhase.disableProperty().bind(chkBPhase.selectedProperty().not());
        txtFSerial.disableProperty().bind(chkBSerial.selectedProperty().not());

        // Bindings to verify if the fields meet established conditions.
        BooleanBinding txtFLabelIdValid = createTextBooleanBinding(txtFLabelId, chkBLabelId);
        BooleanBinding cBoxCategoryValid = createComboBoxBooleanBinding(cBoxCategory, chkBCategory);
        BooleanBinding cBoxRequesterValid = createComboBoxBooleanBinding(cBoxRequester, chkBRequester);
        BooleanBinding cBoxPrinterValid = createComboBoxBooleanBinding(cBoxPrinter, chkBPrinter);
        BooleanBinding txtFPartNumberValid = createTextBooleanBinding(txtFPartNumber, chkBPartNumber);
        BooleanBinding cBoxPhaseValid = createComboBoxBooleanBinding(cBoxLabelPhase, chkBPhase);
        BooleanBinding txtFSerialValid = createTextBooleanBinding(txtFSerial, chkBPhase);

        // Bindings to verify that at least one checkbox is selected.
        BooleanBinding atLeastOneCheckboxSelected = chkBLabelId.selectedProperty()
                .or(chkBCategory.selectedProperty())
                .or(chkBRequester.selectedProperty())
                .or(chkBPrinter.selectedProperty())
                .or(chkBDate.selectedProperty())
                .or(chkBPartNumber.selectedProperty())
                .or(chkBPhase.selectedProperty())
                .or(chkBSerial.selectedProperty());

        // Binding to verify that the conditions to enable the apply button are met.
        BooleanBinding btnApplyDisabled = atLeastOneCheckboxSelected
                .and(txtFLabelIdValid)
                .and(cBoxCategoryValid)
                .and(cBoxRequesterValid)
                .and(cBoxPrinterValid)
                .and(txtFPartNumberValid)
                .and(cBoxPhaseValid)
                .and(txtFSerialValid);

        btnApply.disableProperty().bind(btnApplyDisabled.not());

        BooleanBinding isPrintedLabels = Bindings.createBooleanBinding(
                () -> {
                    ComboBoxItem selectedItem = cBoxDisplay.getValue();
                    return selectedItem != null && selectedItem.getId() == 2;
                }, cBoxDisplay.valueProperty());

        BooleanBinding isReprintedLabels = Bindings.createBooleanBinding(
                () -> {
                    ComboBoxItem selectedItem = cBoxDisplay.getValue();
                    return selectedItem != null && selectedItem.getId() == 3;
                }, cBoxDisplay.valueProperty());

        chkBPartNumber.disableProperty().bind(isPrintedLabels.not());
        chkBPhase.disableProperty().bind(isPrintedLabels.not());
        chkBSerial.disableProperty().bind(isReprintedLabels.not());
    }

    private BooleanBinding createTextBooleanBinding(TextField textField, CheckBox checkBox) {
        return Bindings.createBooleanBinding(
                () -> !textField.getText().isEmpty() || !checkBox.isSelected(),
                checkBox.selectedProperty(), textField.textProperty());
    }

    private BooleanBinding createComboBoxBooleanBinding(ComboBox<ComboBoxItem> comboBox, CheckBox checkBox) {
        return Bindings.createBooleanBinding(
                () -> (comboBox.getValue() != null && comboBox.getValue().getId() != -1)
                        || !checkBox.isSelected(),
                checkBox.selectedProperty(), comboBox.valueProperty());
    }

    // - - - - - - - - - - - - - - - - - - METHOD FOR INITIALIZATION OF BINDS

    // - - - - - - - - - - - METHODS FOR THE INITIALIZATION OF LISTENERS

    private void initializeListeners() {
        initializeFieldCleanerListener(txtFLabelId, chkBLabelId);
        initializeFieldCleanerListener(txtFPartNumber, chkBPartNumber);
        initializeFieldCleanerListener(txtFSerial, chkBSerial);
        initializeFieldCleanerListener(cBoxCategory, chkBCategory);
        initializeFieldCleanerListener(cBoxRequester, chkBRequester);
        initializeFieldCleanerListener(cBoxPrinter, chkBPrinter);
        initializeFieldCleanerListener(cBoxLabelPhase, chkBPhase);

        chkBAll.selectedProperty().addListener((_, _, _) -> {
            chkBLabelId.setSelected(chkBAll.isSelected());
            chkBCategory.setSelected(chkBAll.isSelected());
            chkBRequester.setSelected(chkBAll.isSelected());
            chkBPrinter.setSelected(chkBAll.isSelected());
            chkBDate.setSelected(chkBAll.isSelected());
            if (!chkBPartNumber.isDisable()) {
                chkBPartNumber.setSelected(chkBAll.isSelected());
            }
            if (!chkBPhase.isDisable()) {
                chkBPhase.setSelected(chkBAll.isSelected());
            }
            if (!chkBSerial.isDisable()) {
                chkBSerial.setSelected(chkBAll.isSelected());
            }
        });

        cBoxDisplay.valueProperty().addListener((_, _, newValue) -> {
            if (newValue != null && newValue instanceof ComboBoxItem) {
                ComboBoxItem selectedItem = (ComboBoxItem) newValue;
                if (selectedItem.getId() == 1) {
                    loadAllLabelsInTable();
                } else if (selectedItem.getId() == 2) {
                    loadAllPrintedLabelsInTable();
                } else if (selectedItem.getId() == 3) {
                    loadAllReprintedLabelsInTable();
                }
            }
        });

    }

    private void initializeFieldCleanerListener(Control control, CheckBox checkBox) {
        checkBox.selectedProperty().addListener((_, _, newValue) -> {
            if (!newValue) {
                if (control instanceof TextInputControl) {
                    ((TextInputControl) control).clear();
                } else if (control instanceof ComboBox) {
                    ((ComboBox<?>) control).getSelectionModel().select(0);
                }
            }
        });
    }

    // - - - - - - - - - - - METHODS FOR THE INITIALIZATION OF LISTENERS

    // - - - - - - - - - - - METHODS RELATED TO THE INITIALIZATION OF COMPONENTS.

    private void initializeCombobox() {
        cBoxDisplay.getItems().setAll(
                new ComboBoxItem(1, "Labels"),
                new ComboBoxItem(2, "Printed Labels"),
                new ComboBoxItem(3, "Reprinted Labels"));

        ControlUtils.populateComboBox(
                GServices.phaseService.getAll(),
                cBoxLabelPhase,
                entry -> new ComboBoxItem(entry.getKey(), entry.getValue().getPhaseName()),
                "The map is empty or null!");

        ControlUtils.populateComboBox(
                GServices.requesterService.getAll(),
                cBoxRequester,
                entry -> new ComboBoxItem(entry.getKey(),
                        entry.getValue().getFirstName() + " " + entry.getValue().getLastName()),
                "The map is empty or null!");

        ControlUtils.populateComboBox(
                GServices.categoryService.getAll(),
                cBoxCategory,
                entry -> new ComboBoxItem(entry.getKey(), entry.getValue().getCategoryName()),
                "The map is empty or null!");

        ControlUtils.populateComboBox(
                GServices.printerService.getAll(),
                cBoxPrinter,
                entry -> new ComboBoxItem(entry.getKey(), entry.getValue().getPrinterName()),
                "The map is empty or null!");
    }

    private void initializeDatePicker() {
        dpStart.setValue(LocalDate.now());
        dpEnd.setValue(LocalDate.now());
    }

    // - - - - - - - - - - - METHODS RELATED TO THE INITIALIZATION OF COMPONENTS.

    // - - - - - - - - - - - DEFAULT METHODS FOR LOADING CONTENT INTO TABLES.

    private void loadAllLabelsInTable() {
        ObservableList<Label<?>> observableListLabels = FXCollections
                .observableArrayList(GServices.pLabelService.getAllLabels());
        populateTable(observableListLabels);
        chkBAll.setSelected(false);
    }

    private void loadAllPrintedLabelsInTable() {
        ObservableList<Label<?>> observableListLabels = FXCollections
                .observableArrayList(GServices.pLabelService.getAllPrintedLabels());
        populateTable(observableListLabels);
        chkBAll.setSelected(false);
    }

    private void loadAllReprintedLabelsInTable() {
        ObservableList<Label<?>> observableListLabels = FXCollections
                .observableArrayList(GServices.rLabelService.getAllReprintedLabels());
        populateTable(observableListLabels);
        chkBAll.setSelected(false);
    }

    // - - - - - - - - - - - DEFAULT METHODS FOR LOADING CONTENT INTO TABLES.

}