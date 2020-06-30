package org.jd.vistas;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jd.estructuras.TablaCodificacion;
import org.jd.modelos.Reporte;
import org.jd.utilidades.ManejoDeArchivos;
import org.jd.utilidades.PropiedadesPantalla;
import org.jd.utilidades.Reportes;

public class VistaTops extends Stage {

    private static VistaTops instancia;
    private HBox hBox;
    private GridPane gridPane;
    private TableView tableView;
    private ObservableList observableList;

    public VistaTops() {
    }

    public static VistaTops getInstancia() {
        if (instancia == null) {
            instancia = new VistaTops();
        }
        return instancia;
    }

    public HBox getVistaTops() {
        hBox = new HBox();
        gridPane = new GridPane();

        double x = PropiedadesPantalla.getInstancia().getX();
        double y = PropiedadesPantalla.getInstancia().getY();

        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 10, 20, 20));
        gridPane.setMinWidth(x / 2);
        gridPane.setPrefSize(x, y);
        hBox.setPrefSize(x, y);

        Text txtTitulo = new Text("TOPS");
        txtTitulo.getStyleClass().add("textTitle");
        txtTitulo.setFont(new Font(30));
        gridPane.add(txtTitulo, 0, 0);

        HBox hBoxBotonesTop = new HBox();
        JFXButton btnTopViajesLg = new JFXButton("TOP 10 VIAJES LARGOS");
        btnTopViajesLg.getStyleClass().addAll("customButton", "dangerButton");
        btnTopViajesLg.setButtonType(JFXButton.ButtonType.FLAT);
        btnTopViajesLg.setPrefSize(x, y);
        btnTopViajesLg.setOnAction(event -> {
            if (observableList != null) {
                observableList.clear();
            }
            observableList = FXCollections.observableArrayList(Reportes.getInstancia().topViajesLargos());
            tableView.setItems(observableList);
        });

        JFXButton btnTopClientes = new JFXButton("TOP 10 CLIENTES");
        btnTopClientes.getStyleClass().addAll("customButton", "dangerButton");
        btnTopClientes.setButtonType(JFXButton.ButtonType.FLAT);
        btnTopClientes.setPrefSize(x, y);
        btnTopClientes.setOnAction(event -> {
            if (observableList != null) {
                observableList.clear();
            }
            observableList = FXCollections.observableArrayList(Reportes.getInstancia().topClientes());
            tableView.setItems(observableList);
        });

        JFXButton btnTopConductores = new JFXButton("TOP 10 CONDUCTORES");
        btnTopConductores.getStyleClass().addAll("customButton", "dangerButton");
        btnTopConductores.setButtonType(JFXButton.ButtonType.FLAT);
        btnTopConductores.setPrefSize(x, y);
        btnTopConductores.setOnAction(event -> {
            if (observableList != null) {
                observableList.clear();
            }
            observableList = FXCollections.observableArrayList(Reportes.getInstancia().topConductores());
            tableView.setItems(observableList);
        });

        JFXButton btnTopVehiculos = new JFXButton("TOP 10 VEHICULOS");
        btnTopVehiculos.getStyleClass().addAll("customButton", "dangerButton");
        btnTopVehiculos.setButtonType(JFXButton.ButtonType.FLAT);
        btnTopVehiculos.setPrefSize(x, y);
        btnTopVehiculos.setOnAction(event -> {
            if (observableList != null) {
                observableList.clear();
            }
            observableList = FXCollections.observableArrayList(Reportes.getInstancia().topVehiculos());
            tableView.setItems(observableList);
        });

        hBoxBotonesTop.getChildren().addAll(btnTopViajesLg, btnTopClientes,
                btnTopConductores, btnTopVehiculos);
        hBoxBotonesTop.setPrefSize(x, y / 40);
        hBoxBotonesTop.setMargin(btnTopViajesLg, new Insets(0, 5, 0, 0));
        hBoxBotonesTop.setMargin(btnTopClientes, new Insets(0, 5, 0, 0));
        hBoxBotonesTop.setMargin(btnTopConductores, new Insets(0, 5, 0, 0));
        gridPane.add(hBoxBotonesTop, 0, 1);

        HBox hBoxDecodificar = new HBox();
        JFXButton btnCargarTabla = new JFXButton("CARGAR TABLA DE CODIFICACION");
        btnCargarTabla.getStyleClass().addAll("customButton", "dangerButton");
        btnCargarTabla.setButtonType(JFXButton.ButtonType.FLAT);
        btnCargarTabla.setPrefSize(x, y);
        btnCargarTabla.setOnAction(event -> {
            ManejoDeArchivos.getInstancia().subirArchivo("Tabla de codificacion", "*.edd");
            TablaCodificacion.getInstancia().agregarArchivo(ManejoDeArchivos.getInstancia().leerArchivo());
            Alerta.getInstancia().mostrarNotificacion("HUFFMAN", "TABLA DE CODIFICACION CARGADA");
        });

        JFXButton btnCargarArchivo = new JFXButton("DESCOMPRIMIR ARCHIVO");
        btnCargarArchivo.getStyleClass().addAll("customButton", "dangerButton");
        btnCargarArchivo.setButtonType(JFXButton.ButtonType.FLAT);
        btnCargarArchivo.setPrefSize(x, y);
        btnCargarArchivo.setOnAction(event -> {
            ManejoDeArchivos.getInstancia().subirArchivo("Tabla de codificacion", "*.edd");
            Reportes.getInstancia().descromprimir(ManejoDeArchivos.getInstancia().leerArchivo());
        });

        hBoxDecodificar.getChildren().addAll(btnCargarTabla, btnCargarArchivo);
        hBoxDecodificar.setPrefSize(x, y / 40);
        hBoxDecodificar.setMargin(btnCargarTabla, new Insets(0, 5, 0, 0));
        gridPane.add(hBoxDecodificar, 0, 2);

        TableColumn<Reporte, Integer> cantidad = new TableColumn<>("Cantidad");
        cantidad.setPrefWidth(x / 10);
        cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        TableColumn<Reporte, String> colInformacion = new TableColumn<>("INFORMACION");
        colInformacion.setPrefWidth(9 * x / 10);
        colInformacion.setCellValueFactory(new PropertyValueFactory<>("informacion"));

        tableView = new TableView<>(observableList);
        tableView.getColumns().addAll(cantidad, colInformacion);
        tableView.setPrefHeight(y * 0.95);
        gridPane.add(tableView, 0, 3);

        hBox.getChildren().add(gridPane);
        return hBox;
    }
}
