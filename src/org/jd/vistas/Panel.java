package org.jd.vistas;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jd.utilidades.PropiedadesPantalla;

public class Panel extends Stage {

    private static Panel instancia;
    private HBox hBoxBotones; // Contenedor de elementos en forma horizontal

    private Panel() {
    }

    public static Panel getInstancia() {
        if (instancia == null) {
            instancia = new Panel();
        }
        return instancia;
    }

    public VBox getPanel() {
        VBox vBox = new VBox(); // Contenedor principal
        hBoxBotones = new HBox(); // Contenerdor de los botones del menu
        VBox vBoxPaneles = new VBox(); // Contenedor de los paneles de cada menu

        double x = PropiedadesPantalla.getInstancia().getX(); // Tamaño de la ventana en el eje X
        double y = PropiedadesPantalla.getInstancia().getY(); // Tamaño de la ventan en el eje Y

        // Asignacion de tamaño a los contenedores
        vBox.setPrefSize(x, y);
        hBoxBotones.setPrefSize(x, y * 0.005);
        vBoxPaneles.setPrefSize(x, y * 0.995);
        vBoxPaneles.getChildren().add(VistaCliente.getInstancia().getVistaCliente());

        JFXButton btnEncabezado = new JFXButton("LLEGA RAPIDITO"); // Asignacion del nombre al boton
        btnEncabezado.setDisable(true); // Desactivar boton
        btnEncabezado.getStyleClass().addAll("headerNavButton", "panelButton"); // Diseños del panel de botones y de
                                                                                // boton
        btnEncabezado.setPrefSize(2 * x / 8, y); // Tamaño del boton
        btnEncabezado.setButtonType(JFXButton.ButtonType.FLAT); // Tipo de boton

        JFXButton btnClientes = new JFXButton("CLIENTES");
        selectButton(btnClientes); // Boton seleccionado al iniciar
        btnClientes.setId("btnClientes"); // Asignacion de id al boton
        btnClientes.getStyleClass().addAll("panelButton", "primaryButton");
        btnClientes.setPrefSize(x / 8, y);
        btnClientes.setButtonType(JFXButton.ButtonType.FLAT);
        btnClientes.setOnAction(event -> { // Evento clic y funcionalidad al realizarlo
            selectButton(btnClientes); // Asignacion de diseños al seleccionar el boton
            vBoxPaneles.getChildren().clear(); // Limpieza del panel para agregar los nuevos atributos
            vBoxPaneles.getChildren().add(VistaCliente.getInstancia().getVistaCliente());
        });

        JFXButton btnVehiculos = new JFXButton("VEHÍCULOS");
        btnVehiculos.setId("btnVehiculos");
        btnVehiculos.getStyleClass().addAll("panelButton", "primaryButton");
        btnVehiculos.setPrefSize(x / 8, y);
        btnVehiculos.setButtonType(JFXButton.ButtonType.FLAT);
        btnVehiculos.setOnAction(event -> {
            selectButton(btnVehiculos);
            vBoxPaneles.getChildren().clear();
            vBoxPaneles.getChildren().add(VistaVehiculo.getInstancia().getVistaVehiculo());
        });

        JFXButton btnConductores = new JFXButton("CONDUCTORES");
        btnConductores.setId("btnConductores");
        btnConductores.getStyleClass().addAll("panelButton", "primaryButton");
        btnConductores.setPrefSize(x / 8, y);
        btnConductores.setButtonType(JFXButton.ButtonType.FLAT);
        btnConductores.setOnAction(event -> {
            selectButton(btnConductores);
            vBoxPaneles.getChildren().clear();
            vBoxPaneles.getChildren().add(VistaConductor.getInstancia().getVistaConductor());
        });

        JFXButton btnViajes = new JFXButton("VIAJES");
        btnViajes.setId("btnViajes");
        btnViajes.getStyleClass().addAll("panelButton", "primaryButton");
        btnViajes.setPrefSize(x / 8, y);
        btnViajes.setButtonType(JFXButton.ButtonType.FLAT);
        btnViajes.setOnAction(event -> {
            selectButton(btnViajes);
            vBoxPaneles.getChildren().clear();
        });

        JFXButton btnRutas = new JFXButton("RUTAS");
        btnRutas.setId("btnRutas");
        btnRutas.getStyleClass().addAll("panelButton", "primaryButton");
        btnRutas.setPrefSize(x / 8, y);
        btnRutas.setButtonType(JFXButton.ButtonType.FLAT);
        btnRutas.setOnAction(event -> {
            selectButton(btnRutas);
            vBoxPaneles.getChildren().clear();
            vBoxPaneles.getChildren().add(VistaRuta.getInstancia().getVistaRuta());
        });

        JFXButton btnReportes = new JFXButton("REPORTES");
        btnReportes.setId("btnReportes");
        btnReportes.getStyleClass().addAll("panelButton", "primaryButton");
        btnReportes.setPrefSize(x / 8, y);
        btnReportes.setButtonType(JFXButton.ButtonType.FLAT);
        btnReportes.setOnAction(event -> {
            selectButton(btnReportes);
            vBoxPaneles.getChildren().clear();
        });

        hBoxBotones.getChildren().addAll(btnEncabezado, btnClientes, btnVehiculos, btnConductores, btnViajes, btnRutas,
                btnReportes); // Asignacion de botones al contenedor
        vBox.getChildren().addAll(hBoxBotones, vBoxPaneles); // Asignacion de los contenedores al panel principal
        return vBox;
    }

    // Metodo para quitar el estilo de boton seleccionado y asignarlo al boton
    // presionado
    private void selectButton(JFXButton jfxButton) {
        for (Node node : hBoxBotones.getChildren()) { // Se recorren todos los nodos del contenedor botones
            if (node instanceof JFXButton) { // Se verifica que el nodo sea una instancia del objeto JFXButton
                if (node.getId() != null) { // Se verifica que el boton contenga un id
                    node.getStyleClass().remove("selectedPanelNavButton"); // Se remueve el estilo de seleccion
                    node.getStyleClass().add("primaryButton"); // Se agrega el estilo incial del boton
                }
            }
        }
        jfxButton.getStyleClass().remove("primaryButton"); // Se remueve el estilo inicial al boton presionado
        jfxButton.getStyleClass().add("selectedPanelNavButton"); // Se agrega el estilo de seleccion al boton presionado
    }
}
