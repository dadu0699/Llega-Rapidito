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
import org.jd.estructuras.ListaCircular;
import org.jd.estructuras.TablaHash;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaAgregarViaje extends Stage {

    private static VistaAgregarViaje instancia;

    private VistaAgregarViaje() {
    }

    public static VistaAgregarViaje getInstancia() {
        if (instancia == null) {
            instancia = new VistaAgregarViaje();
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
        gridPane.add(txtTitulo, 0, 3);

        JFXTextField jFTOrigen = new JFXTextField();
        jFTOrigen.setPromptText("ORIGEN");
        jFTOrigen.setLabelFloat(true);
        jFTOrigen.setPrefWidth(x);
        gridPane.add(jFTOrigen, 0, 4);

        JFXTextField jFTDestino = new JFXTextField();
        jFTDestino.setPromptText("DESTINO");
        jFTDestino.setLabelFloat(true);
        jFTDestino.setPrefWidth(x);
        gridPane.add(jFTDestino, 0, 5);

        JFXTextField jFTFecha = new JFXTextField();
        jFTFecha.setPromptText("FECHA");
        jFTFecha.setLabelFloat(true);
        jFTFecha.setPrefWidth(x);
        gridPane.add(jFTFecha, 0, 6);

        JFXTextField jFTHora = new JFXTextField();
        jFTHora.setPromptText("HORA");
        jFTHora.setLabelFloat(true);
        jFTHora.setPrefWidth(x);
        gridPane.add(jFTHora, 0, 7);

        ObservableList obsClientes = FXCollections.observableArrayList(TablaHash.getInstancia().obtenerDatos());
        ObservableList<String> clientesL = obsClientes;
        JFXComboBox<String> cbCliente = new JFXComboBox<>(clientesL);
        cbCliente.setPromptText("CLIENTE");
        cbCliente.setLabelFloat(true);
        cbCliente.setPrefWidth(x);
        gridPane.add(cbCliente, 0, 8);

        ObservableList obsConductor = FXCollections.observableArrayList(ListaCircular.getInstancia().obtenerDatos());
        ObservableList<String> conductorL = obsConductor;
        JFXComboBox<String> cbConductor = new JFXComboBox<>(conductorL);
        cbConductor.setPromptText("CONDUCTOR");
        cbConductor.setLabelFloat(true);
        cbConductor.setPrefWidth(x);
        gridPane.add(cbConductor, 0, 9);

        /*
        ObservableList obsVehiculo = FXCollections.observableArrayList(ArbolB.getInstancia().obtenerDatos());
        ObservableList<String> vehiculoL = obsVehiculo;
        JFXComboBox<String> cbVehiculo = new JFXComboBox<>(vehiculoL);
        cbVehiculo.setPromptText("VEHICULO");
        cbVehiculo.setLabelFloat(true);
        cbVehiculo.setPrefWidth(x);
        gridPane.add(cbVehiculo, 0, 10);
         */
        JFXTextField jFTVehiculo = new JFXTextField();
        jFTVehiculo.setPromptText("VEHICULO");
        jFTVehiculo.setLabelFloat(true);
        jFTVehiculo.setPrefWidth(x);
        jFTVehiculo.setVisible(false);
        gridPane.add(jFTVehiculo, 0, 10);

        JFXButton buttonAdd = new JFXButton("AGREGAR");
        buttonAdd.getStyleClass().addAll("customButton", "primaryButton");
        buttonAdd.setButtonType(JFXButton.ButtonType.FLAT);
        buttonAdd.setPrefSize(x, y * 0.04);
        buttonAdd.setOnAction(event -> {
            if (jFTOrigen.getText().length() == 0
                    || jFTDestino.getText().length() == 0
                    || jFTFecha.getText().length() == 0
                    || jFTHora.getText().length() == 0) {
                Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "UNO O MÁS DATOS SON INCORRECTOS");
            } else {
            }
        });
        gridPane.add(buttonAdd, 0, 11);
        return gridPane;
    }
}
