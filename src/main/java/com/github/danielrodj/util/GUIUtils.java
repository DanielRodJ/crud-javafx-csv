package com.github.danielrodj.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class GUIUtils {

    // Constants for common texts
    private static final String DEFAULT_ERROR_TITLE = "Error";
    private static final String DEFAULT_CONFIRMATION_TITLE = "Confirmation";
    private static final String DEFAULT_CANCEL_OPTION = "Cancel";

    /**
     * Displays a customizable error dialog.
     *
     * @param title   The title of the dialog.
     * @param header  The header text (can be null).
     * @param content The content message.
     */
    public static void showError(String title, String header, String content) {
        showDialog(AlertType.ERROR, title, header, content);
    }

    /**
     * Displays an error dialog with a title and message.
     *
     * @param title   The title of the dialog.
     * @param message The content message.
     */
    public static void showErrorDialog(String title, String message) {
        showDialog(AlertType.ERROR, title, null, message);
    }

    /**
     * Displays a confirmation dialog with a title and message.
     *
     * @param title   The title of the dialog.
     * @param message The content message.
     * @return True if the user clicks OK, otherwise false.
     */
    public static boolean showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title != null ? title : DEFAULT_CONFIRMATION_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    /**
     * Displays a three-option dialog with custom options.
     *
     * @param title   The title of the dialog.
     * @param header  The header text (can be null).
     * @param content The content message.
     * @param options The custom options to display (must have at least 2 options).
     * @return The selected option or "Cancel" if the dialog is closed.
     */
    public static String showThreeOptionDialog(String title, String header, String content, String[] options) {
        if (options == null || options.length < 2) {
            throw new IllegalArgumentException("At least two options are required.");
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        ButtonType option1 = new ButtonType(options[0]);
        ButtonType option2 = new ButtonType(options[1]);

        alert.getButtonTypes().setAll(option1, option2);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == option1) {
                return options[0];
            } else if (result.get() == option2) {
                return options[1];
            }
        }
        return null;
    }

    /**
     * A generic method to display a dialog.
     *
     * @param type    The type of dialog (e.g., ERROR, CONFIRMATION).
     * @param title   The title of the dialog.
     * @param header  The header text (can be null).
     * @param content The content message.
     */
    private static void showDialog(AlertType type, String title, String header, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(type);
            alert.setTitle(title != null ? title : DEFAULT_ERROR_TITLE);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }
}