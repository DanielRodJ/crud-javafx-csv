package com.github.danielrodj.util.view;

import javafx.scene.Parent;

/**
 * Represents a JavaFX FXML resource, encapsulating both the view (UI component)
 * and its associated controller. This class is useful for managing FXML-based
 * views and their controllers in a structured way.
 */

public class FXMLResource {

    // The root node of the FXML view (UI component)
    private final Parent view;

    // The controller associated with the FXML view
    private final Object controller;

    /**
     * Constructs an FXMLResource object with the specified view and controller.
     *
     * @param view       The root node of the FXML view.
     * @param controller The controller associated with the FXML view.
     */
    public FXMLResource(Parent view, Object controller) {
        this.view = view;
        this.controller = controller;
    }

    /**
     * Returns the root node of the FXML view.
     *
     * @return The Parent node representing the FXML view.
     */
    public Parent getView() {
        return view;
    }

    /**
     * Returns the controller associated with the FXML view.
     *
     * @return The controller object associated with the FXML view.
     */
    public Object getController() {
        return controller;
    }
}