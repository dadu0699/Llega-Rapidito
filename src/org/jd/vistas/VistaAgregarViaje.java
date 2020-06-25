package org.jd.vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jd.estructuras.ListaAdyacencia;
import org.jd.estructuras.ListaCircular;
import org.jd.estructuras.ListaDoble;
import org.jd.estructuras.TablaDijkstra;
import org.jd.estructuras.TablaHash;
import org.jd.estructuras.Vertice;
import org.jd.modelos.Cliente;
import org.jd.modelos.Conductor;
import org.jd.modelos.Ruta;
import org.jd.modelos.Vehiculo;
import org.jd.modelos.Viaje;
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
        gridPane.add(txtTitulo, 0, 6);

        ObservableList obsOrigen = FXCollections.observableArrayList(ListaAdyacencia.getInstancia().obtenerLugares());
        ObservableList<Vertice> origenL = obsOrigen;
        JFXComboBox<Vertice> cbOrigen = new JFXComboBox<>(origenL);
        cbOrigen.setPromptText("ORIGEN");
        cbOrigen.setLabelFloat(true);
        cbOrigen.setPrefWidth(x);
        gridPane.add(cbOrigen, 0, 7);

        ObservableList obsDestino = FXCollections.observableArrayList(ListaAdyacencia.getInstancia().obtenerLugares());
        ObservableList<Vertice> destinoL = obsDestino;
        JFXComboBox<Vertice> cbDestino = new JFXComboBox<>(destinoL);
        cbDestino.setPromptText("DESTINO");
        cbDestino.setLabelFloat(true);
        cbDestino.setPrefWidth(x);
        gridPane.add(cbDestino, 0, 8);

        ObservableList obsClientes = FXCollections.observableArrayList(TablaHash.getInstancia().obtenerDatos());
        ObservableList<Cliente> clientesL = obsClientes;
        JFXComboBox<Cliente> cbCliente = new JFXComboBox<>(clientesL);
        cbCliente.setPromptText("CLIENTE");
        cbCliente.setLabelFloat(true);
        cbCliente.setPrefWidth(x);
        gridPane.add(cbCliente, 0, 9);

        ObservableList obsConductor = FXCollections.observableArrayList(ListaCircular.getInstancia().obtenerDatos());
        ObservableList<Conductor> conductorL = obsConductor;
        JFXComboBox<Conductor> cbConductor = new JFXComboBox<>(conductorL);
        cbConductor.setPromptText("CONDUCTOR");
        cbConductor.setLabelFloat(true);
        cbConductor.setPrefWidth(x);
        gridPane.add(cbConductor, 0, 10);

        /*
        ObservableList obsVehiculo = FXCollections.observableArrayList(ArbolB.getInstancia().obtenerDatos());
        ObservableList<String> vehiculoL = obsVehiculo;
        JFXComboBox<String> cbVehiculo = new JFXComboBox<>(vehiculoL);
        cbVehiculo.setPromptText("VEHICULO");
        cbVehiculo.setLabelFloat(true);
        cbVehiculo.setPrefWidth(x);
        gridPane.add(cbVehiculo, 0, 8);
         */
        JFXButton buttonAdd = new JFXButton("AGREGAR");
        buttonAdd.getStyleClass().addAll("customButton", "primaryButton");
        buttonAdd.setButtonType(JFXButton.ButtonType.FLAT);
        buttonAdd.setPrefSize(x, y * 0.04);
        buttonAdd.setOnAction(event -> {
            if (cbOrigen.getSelectionModel().getSelectedItem() == null
                    || cbDestino.getSelectionModel().getSelectedItem() == null
                    || cbCliente.getSelectionModel().getSelectedItem() == null
                    || cbConductor.getSelectionModel().getSelectedItem() == null) { //falta agregar comprobacion de cbVehiculo
                Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "UNO O MÁS DATOS SON INCORRECTOS");
            } else {
                Vehiculo temporal = new Vehiculo("AS213", "Mercedes", "AMG", "2020", "Verde", "Q.200.00", "Mecánica");
                Ruta ruta = null;
                
                TablaDijkstra tb = new TablaDijkstra();
                tb.recorrer(cbOrigen.getSelectionModel().getSelectedItem(), cbDestino.getSelectionModel().getSelectedItem());
                
                ListaDoble.getInstancia().agregar(new Viaje(cbOrigen.getSelectionModel().getSelectedItem(),
                        cbDestino.getSelectionModel().getSelectedItem(),
                        cbCliente.getSelectionModel().getSelectedItem(),
                        cbConductor.getSelectionModel().getSelectedItem(), temporal, ruta));
                VistaViaje.getInstancia().reiniciarHBox();
                Alerta.getInstancia().mostrarNotificacion("VIAJE", "REGISTRO REALIZADO EXITOSAMENTE");
            }
        });
        gridPane.add(buttonAdd, 0, 11);
        return gridPane;
    }
}
