module com.github.danielrodj {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;
    requires java.logging; 

    // Exporta el paquete principal si es necesario para otros módulos
    exports com.github.danielrodj;

    // Permite acceso reflexivo al paquete de controladores para JavaFX
    opens com.github.danielrodj.controllers to javafx.fxml;
}