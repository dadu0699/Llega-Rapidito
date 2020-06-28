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

public class VistaModificarVehiculo extends Stage {

    private static VistaModificarVehiculo instancia;

    private VistaModificarVehiculo() {
    }

    static VistaModificarVehiculo getInstancia() {
        if (instancia == null) {
            instancia = new VistaModificarVehiculo();
        }
        return instancia;
    }

    public GridPane getGridPane(Vehiculo vehiculo) {
        GridPane gridPane = new GridPane();

        double x = PropiedadesPantalla.getInstancia().getX();
        double y = PropiedadesPantalla.getInstancia().getY();

        gridPane.setVgap(25);
        gridPane.setHgap(5);
        gridPane.setPadding(new Insets(20, 20, 20, 10));
        // gridPane.setGridLinesVisible(true);

        Text txtTitulo = new Text("MODIFICAR");
        txtTitulo.getStyleClass().add("textTitle");
        txtTitulo.setFont(new Font(25));
        gridPane.add(txtTitulo, 0, 4, 2, 1);

        JFXTextField jFTPlaca = new JFXTextField(vehiculo.getPlaca());
        jFTPlaca.setPromptText("PLACA");
        jFTPlaca.setLabelFloat(true);
        jFTPlaca.setEditable(false);
        jFTPlaca.setPrefWidth(x);
        gridPane.add(jFTPlaca, 0, 5, 2, 1);

        JFXTextField jFTMarca = new JFXTextField(vehiculo.getMarca());
        jFTMarca.setPromptText("MARCA");
        jFTMarca.setLabelFloat(true);
        jFTMarca.setPrefWidth(x);
        gridPane.add(jFTMarca, 0, 6, 2, 1);

        JFXTextField jFTModelo = new JFXTextField(vehiculo.getModelo());
        jFTModelo.setPromptText("MODELO");
        jFTModelo.setLabelFloat(true);
        jFTModelo.setPrefWidth(x);
        gridPane.add(jFTModelo, 0, 7, 2, 1);

        JFXTextField jFTAnio = new JFXTextField(vehiculo.getAnio());
        jFTAnio.setPromptText("AÑO");
        jFTAnio.setLabelFloat(true);
        jFTAnio.setPrefWidth(x);
        gridPane.add(jFTAnio, 0, 8, 2, 1);

        JFXTextField jFTColor = new JFXTextField(vehiculo.getColor());
        jFTColor.setPromptText("COLOR");
        jFTColor.setLabelFloat(true);
        jFTColor.setPrefWidth(x);
        gridPane.add(jFTColor, 0, 9, 2, 1);

        JFXTextField jFTPrecio = new JFXTextField(vehiculo.getPrecio());
        jFTPrecio.setPromptText("PRECIO");
        jFTPrecio.setLabelFloat(true);
        jFTPrecio.setPrefWidth(x);
        gridPane.add(jFTPrecio, 0, 10, 2, 1);

        String[] transmisiones = {"Automatica", "Mecanica"};
        ObservableList obsTransmision = FXCollections.observableArrayList(transmisiones);
        ObservableList<String> informacion = obsTransmision;
        JFXComboBox<String> cbTransmision = new JFXComboBox<>(informacion);
        cbTransmision.setPromptText("TRANSMISIÓN");
        cbTransmision.getSelectionModel().select(vehiculo.getTransmision());
        cbTransmision.setLabelFloat(true);
        cbTransmision.setPrefWidth(x);
        gridPane.add(cbTransmision, 0, 11, 2, 1);

        JFXButton btnModificar = new JFXButton("MODIFICAR");
        btnModificar.getStyleClass().addAll("customButton", "primaryButton");
        btnModificar.setButtonType(JFXButton.ButtonType.FLAT);
        btnModificar.setPrefSize(x, y * 0.04);
        btnModificar.setOnAction(event -> {
            if (jFTPlaca.getText().length() == 0
                    || jFTMarca.getText().length() == 0
                    || jFTModelo.getText().length() == 0
                    || jFTAnio.getText().length() == 0
                    || jFTColor.getText().length() == 0
                    || jFTPrecio.getText().length() == 0
                    || cbTransmision.getSelectionModel().getSelectedItem() == null) {
                Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "UNO O MÁS DATOS SON INCORRECTOS");
            } else {
                vehiculo.setMarca(jFTMarca.getText().trim());
                vehiculo.setModelo(jFTModelo.getText().trim());
                vehiculo.setAnio(jFTAnio.getText().trim());
                vehiculo.setColor(jFTColor.getText().trim());
                vehiculo.setPrecio(jFTPrecio.getText().trim());
                vehiculo.setTransmision(cbTransmision.getSelectionModel().getSelectedItem().trim());

                if (vehiculo.getPlaca().equalsIgnoreCase(jFTPlaca.getText().trim())) {
                    Alerta.getInstancia().mostrarNotificacion("VEHICULOS", "VEHICULO ACTUALIZADO EXITOSAMENTE");
                } else {
                    if (ArbolB.getInstancia().buscar(jFTPlaca.getText().trim()) == null) {
                        // ArbolB.getInstancia().eliminar(vehiculo.jFTPlaca());
                        vehiculo.setPlaca(jFTPlaca.getText().trim());
                        ArbolB.getInstancia().insertar(vehiculo);
                        Alerta.getInstancia().mostrarNotificacion("VEHICULOS", "VEHICULO ACTUALIZADO EXITOSAMENTE");
                    } else {
                        Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "ERROR AL MODIFICAR DPI");
                    }
                }
                VistaVehiculo.getInstancia().actualizarItemsTabla();
            }
        });
        gridPane.add(btnModificar, 0, 12);

        JFXButton btnCancelar = new JFXButton("CANCELAR");
        btnCancelar.getStyleClass().addAll("customButton", "dangerButton");
        btnCancelar.setButtonType(JFXButton.ButtonType.FLAT);
        btnCancelar.setPrefSize(x, y * 0.04);
        btnCancelar.setOnAction(event -> VistaVehiculo.getInstancia().reiniciarHBox());
        gridPane.add(btnCancelar, 1, 12);

        return gridPane;
    }
}
