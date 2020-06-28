package org.jd.vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jd.estructuras.ArbolB;
import org.jd.modelos.Vehiculo;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaAgregarVehiculo extends Stage {

    private static VistaAgregarVehiculo instancia;

    private VistaAgregarVehiculo() {
    }

    public static VistaAgregarVehiculo getInstancia() {
        if (instancia == null) {
            instancia = new VistaAgregarVehiculo();
        }
        return instancia;
    }

    public GridPane getFormulario() {
        GridPane gridPane = new GridPane();

        double x = PropiedadesPantalla.getInstancia().getX();
        double y = PropiedadesPantalla.getInstancia().getY();

        gridPane.setVgap(25);
        gridPane.setHgap(5);
        gridPane.setPadding(new Insets(20, 20, 20, 10));
        // gridPane.setGridLinesVisible(true);

        Text txtTitulo = new Text("AGREGAR");
        txtTitulo.getStyleClass().add("textTitle");
        txtTitulo.setFont(new Font(25));
        gridPane.add(txtTitulo, 0, 4);

        JFXTextField jFTPlaca = new JFXTextField();
        jFTPlaca.setPromptText("PLACA");
        jFTPlaca.setLabelFloat(true);
        jFTPlaca.setPrefWidth(x);
        gridPane.add(jFTPlaca, 0, 5);

        JFXTextField jFTMarca = new JFXTextField();
        jFTMarca.setPromptText("MARCA");
        jFTMarca.setLabelFloat(true);
        jFTMarca.setPrefWidth(x);
        gridPane.add(jFTMarca, 0, 6);

        JFXTextField jFTModelo = new JFXTextField();
        jFTModelo.setPromptText("MODELO");
        jFTModelo.setLabelFloat(true);
        jFTModelo.setPrefWidth(x);
        gridPane.add(jFTModelo, 0, 7);

        JFXTextField jFTAnio = new JFXTextField();
        jFTAnio.setPromptText("AÑO");
        jFTAnio.setLabelFloat(true);
        jFTAnio.setPrefWidth(x);
        gridPane.add(jFTAnio, 0, 8);

        JFXTextField jFTColor = new JFXTextField();
        jFTColor.setPromptText("COLOR");
        jFTColor.setLabelFloat(true);
        jFTColor.setPrefWidth(x);
        gridPane.add(jFTColor, 0, 9);

        JFXTextField jFTPrecio = new JFXTextField();
        jFTPrecio.setPromptText("PRECIO");
        jFTPrecio.setLabelFloat(true);
        jFTPrecio.setPrefWidth(x);
        gridPane.add(jFTPrecio, 0, 10);

        String[] transmisiones = {"Automatica", "Mecanica"};
        ObservableList obsTransmision = FXCollections.observableArrayList(transmisiones);
        ObservableList<String> informacion = obsTransmision;
        JFXComboBox<String> cbTransmision = new JFXComboBox<>(informacion);
        cbTransmision.setPromptText("TRANSMISIÓN");
        cbTransmision.setLabelFloat(true);
        cbTransmision.setPrefWidth(x);
        gridPane.add(cbTransmision, 0, 11);

        JFXButton buttonAdd = new JFXButton("AGREGAR");
        buttonAdd.getStyleClass().addAll("customButton", "primaryButton");
        buttonAdd.setButtonType(JFXButton.ButtonType.FLAT);
        buttonAdd.setPrefSize(x, y * 0.04);
        buttonAdd.setOnAction(event -> {
            if (jFTPlaca.getText().length() == 0
                    || jFTMarca.getText().length() == 0
                    || jFTModelo.getText().length() == 0
                    || jFTAnio.getText().length() == 0
                    || jFTColor.getText().length() == 0
                    || jFTPrecio.getText().length() == 0
                    || cbTransmision.getSelectionModel().getSelectedItem() == null) {
                Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "UNO O MÁS DATOS SON INCORRECTOS");
            } else {
                boolean vehiculo = ArbolB.getInstancia().insertar(
                        new Vehiculo(jFTPlaca.getText(), jFTMarca.getText(),
                                jFTModelo.getText(), jFTAnio.getText(),
                                jFTColor.getText(), jFTPrecio.getText(),
                                cbTransmision.getSelectionModel().getSelectedItem()));
                if (!vehiculo) {
                    Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "EL VEHICULO FUE REGISTRADO PREVIAMENTE");
                } else {
                    // VistaCliente.getInstancia().actualizarItemsTabla();
                    VistaVehiculo.getInstancia().reiniciarHBox();
                    Alerta.getInstancia().mostrarNotificacion("VEHICULO", "REGISTRO REALIZADO EXITOSAMENTE");
                }
            }
        });
        gridPane.add(buttonAdd, 0, 12);
        return gridPane;
    }
}
