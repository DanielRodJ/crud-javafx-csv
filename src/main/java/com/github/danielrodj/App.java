package com.github.danielrodj;

import com.github.danielrodj.util.view.TypeWindow;
import com.github.danielrodj.util.view.ViewWindow;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        ViewWindow loginWindow = new ViewWindow(TypeWindow.LOGIN, primaryStage);
        loginWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
