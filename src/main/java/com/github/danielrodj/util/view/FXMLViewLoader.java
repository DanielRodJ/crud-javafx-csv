package com.github.danielrodj.util.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * Utility class for loading JavaFX FXML views and their controllers.
 * Provides methods to load FXML files, replace or add views to containers, and
 * handle errors.
 *
 * <p>
 * Example usage:
 * 
 * <pre>
 * Parent view = FXMLViewLoader.getNewView("/path/to/view.fxml");
 * Pane container = ...;
 * FXMLViewLoader.replaceAllView(container, view);
 * </pre>
 */
public final class FXMLViewLoader {

    private static final Logger logger = Logger.getLogger(FXMLViewLoader.class.getName());

    private FXMLViewLoader() {
        // Private constructor to prevent instantiation
    }

    /**
     * Loads a new FXML view from the specified path and returns its root node.
     *
     * @param fxmlPath The path to the FXML file relative to the classpath.
     * @return The root node of the loaded FXML view.
     * @throws IllegalStateException If the FXML resource cannot be loaded.
     */
    public static Parent getNewView(String fxmlPath) {
        return getFXMLResource(fxmlPath).getView();
    }

    /**
     * Replaces all children of the specified container with the given view.
     *
     * @param container The container (e.g., Pane) whose children will be replaced.
     * @param view      The view to set as the container's children.
     */
    public static void replaceAllView(Pane container, Parent view) {
        container.getChildren().setAll(view);
    }

    /**
     * Adds the given view to the specified container without removing existing
     * children.
     *
     * @param container The container (e.g., Pane) to which the view will be added.
     * @param view      The view to add to the container.
     */
    public static void addToTheActualView(Pane container, Parent view) {
        container.getChildren().add(view);
    }

    /**
     * Loads an FXML resource (view and controller) from the specified path.
     *
     * @param fxmlPath The path to the FXML file relative to the classpath.
     * @return An Optional containing the FXMLResource (view and controller) if
     *         successful, or empty if an error occurs.
     * @throws IllegalArgumentException If the FXML path is null or empty.
     */
    public static FXMLResource getFXMLResource(String fxmlPath) {
        URL resource = FXMLViewLoader.class.getResource(fxmlPath);
        
        if (resource == null) {
            logger.severe("FXML file not found: " + fxmlPath);
            return new FXMLResourceError();
        }

        try {

            FXMLLoader loader = new FXMLLoader(resource);
            Parent view = loader.load();
            Object controller = loader.getController();
            return new FXMLResource(view, controller);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading FXML file: " + fxmlPath, e);
            return new FXMLResourceError();
        }
    }
}