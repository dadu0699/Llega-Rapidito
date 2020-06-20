package org;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jd.vistas.Panel;

public class LlegaRapidito extends Application {
    private static Stage stage;
    private static VBox root;
    private static JFXDecorator decorator;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        root = new VBox(); // Contenedor de elementos en forma vertical
        decorator = new JFXDecorator(stage, root, false, false, true); // Asignacion propiedades de la barra de menus (pantalla completa, maximizar, minimizar)
        Scene scene = new Scene(decorator, 480, 270); // Asignacion de la barra de menus y tamaño de ventana

        scene.getStylesheets().add("/org/jd/assets/stylesheets/style.css"); // Asignacion de la hoja de estilos o diseño de la aplicacion
        stage.setTitle("LLEGA RAPIDITO"); // Titulo de la ventana
        stage.setResizable(false); // Redimensionar
        stage.setScene(scene); // Asignacion de propiedades a la ventana principal
        stage.setMaximized(true); // Ventana princiapl maximizada al iniciar
        stage.centerOnScreen(); // Ventana principal centrada

        root.getChildren().addAll(Panel.getInstancia().getPanel()); // Se agrega al contenedor el panel principal
        stage.show(); // Mostrar ventana principal
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static VBox getRoot() {
        return root;
    }
}
