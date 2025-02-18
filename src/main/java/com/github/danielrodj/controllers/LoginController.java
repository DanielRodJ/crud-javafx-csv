package com.github.danielrodj.controllers;

import com.github.danielrodj.models.User;
import com.github.danielrodj.services.UserServiceImplementation;
import com.github.danielrodj.util.GUIUtils;
import com.github.danielrodj.util.Global;
import com.github.danielrodj.util.view.TypeWindow;
import com.github.danielrodj.util.view.ViewWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class LoginController {

    private static final String ERROR_TITLE = "Error";
    private static final String INVALID_CREDENTIALS_HEADER = "Authentication Failed";
    private static final String INVALID_CREDENTIALS_CONTENT = "The username or password you entered is incorrect. Please try again.";

    private final UserServiceImplementation userService = new UserServiceImplementation();

    @FXML
    private TextField txtFUsername, txtFPassword;

    @FXML
    private Label lnkForgotPassword, lnkNewAccount;

    @FXML
    private Button btnLogin;

    @FXML
    private void initialize() {
        btnLogin.disableProperty()
                .bind(txtFUsername.textProperty().isEmpty().or(txtFPassword.textProperty().isEmpty()));
    }

    @FXML
    private void handleLoginAction(ActionEvent event) {

        if (!authenticateUser()) {
            return;
        }

        navigateToRecordViewer(event);
    }

    private boolean authenticateUser() {

        String username = txtFUsername.getText();
        String password = txtFPassword.getText();

        Optional<User> userOptional = userService.verifyUser(username, password);

        if (!userOptional.isPresent()) {
            GUIUtils.showError(ERROR_TITLE, INVALID_CREDENTIALS_HEADER, INVALID_CREDENTIALS_CONTENT);
            cleanFields();
            return false;
        }

        Global.activeUser = userOptional.get();
        return true;
    }

    private void navigateToRecordViewer(ActionEvent event) {
        ViewWindow viewerWindow = new ViewWindow(TypeWindow.RECORD_VIEWER, new Stage());
        viewerWindow.show();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void cleanFields() {
        txtFUsername.clear();
        txtFPassword.clear();
        txtFUsername.requestFocus();
    }

}