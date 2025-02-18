package com.github.danielrodj.util.view;

import com.github.danielrodj.controllers.ErrorFXMLController;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class FXMLResourceError extends FXMLResource {

    public FXMLResourceError() {
        super(createErrorView(), new ErrorFXMLController());
    }

    private static VBox createErrorView() {
        VBox root = new VBox();
        root.setSpacing(10); // Espacio entre nodos
        root.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #F8D7DA;");

        // Crear el Label para el mensaje de error
        Label errorMessage = new Label("Ocurrió un error inesperado.");
        errorMessage.setStyle("-fx-text-fill: #721C24; -fx-font-size: 16px;");

        // Crear el Button para cerrar la ventana
        Button closeButton = new Button("Cerrar");
        closeButton.setStyle("-fx-background-color: #F5C6CB; -fx-text-fill: #721C24;");
        root.getChildren().addAll(errorMessage, closeButton);
        return root;
    }
}
