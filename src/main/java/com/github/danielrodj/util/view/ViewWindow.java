package com.github.danielrodj.util.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewWindow {

    private final Stage stage;
    private final FXMLResource fxmlResource;

    public ViewWindow(TypeWindow typeWindow, Stage stage) {
        this.stage = stage;
        this.fxmlResource = FXMLViewLoader.getFXMLResource(typeWindow.getfilePath());
        setupView(typeWindow);
        setupStage(typeWindow.getSize());
    }

    private void setupView(TypeWindow typeWindow) {
        Parent root = fxmlResource.getView();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(typeWindow.getTitle());
    }

    private void setupStage(Size size) {
        stage.setMaxWidth(size.getMaxWidth());
        stage.setMaxHeight(size.getMaxHeight());
        stage.setWidth(size.getPrefWidth());
        stage.setHeight(size.getPrefHeight());
        stage.setMinWidth(size.getMinWidth());
        stage.setMinHeight(size.getMinHeight());
    }

    public void show() {
        stage.show();
    }

    public void close() {
        stage.close();
    }

    public FXMLResource getFxmlResource() {
        return fxmlResource;
    }

    public Object getController(){
        return getFxmlResource().getController();
    }

}