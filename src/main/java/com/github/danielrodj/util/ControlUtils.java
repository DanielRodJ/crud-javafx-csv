package com.github.danielrodj.util;

import java.util.Map;
import java.util.function.Function;

import com.github.danielrodj.models.label.Label;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class ControlUtils {

    public static <K, V> void populateComboBox(Map<K, V> map, ComboBox<ComboBoxItem> comboBox,
            Function<Map.Entry<K, V>, ComboBoxItem> mapper,
            String emptyMessage) {
                
        if (map != null && !map.isEmpty()) {
            ComboBoxItem def = new ComboBoxItem(-1, "Select an option");
            comboBox.getItems().add(def);
            comboBox.setValue(def);
            for (Map.Entry<K, V> entry : map.entrySet()) {
                comboBox.getItems().add(mapper.apply(entry));
            }
        } else {
            ComboBoxItem def = new ComboBoxItem(-2, "Add new values");
            comboBox.getItems().add(def);
            comboBox.setValue(def);
            System.out.println(emptyMessage);
        }
    }

    public static void initializeTableListenerWidth(TableView<Label<?>> tableView) {
        Platform.runLater(() -> adjustColumnWidths(tableView));

        tableView.getItems().addListener((ListChangeListener<Label<?>>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                    adjustColumnWidths(tableView);
                }
            }
        });
    }

    private static void adjustColumnWidths(TableView<?> tableView) {
        tableView.getColumns().forEach(column -> {
            Text t = new Text(column.getText());
            double max = t.getLayoutBounds().getWidth() + 40; // Header Width
            for (int i = 0; i < tableView.getItems().size(); i++) {
                if (column.getCellData(i) != null) {
                    t = new Text(column.getCellData(i).toString());
                    double calcWidth = t.getLayoutBounds().getWidth() + 10; // Data Width
                    if (calcWidth > max) {
                        max = calcWidth;
                    }
                }
            }
            column.setPrefWidth(max);
        });
    }
    
}
