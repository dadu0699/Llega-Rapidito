package org.jd.vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jd.modelos.Vehiculo;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaVehiculo extends Stage {

    private static VistaVehiculo instancia;
    private HBox hBoxPaneles;
    private VBox vBoxCRUD;
    private TableView tableView;
    private ObservableList observableList;

    private VistaVehiculo() {
    }

    public static VistaVehiculo getInstancia() {
        if (instancia == null) {
            instancia = new VistaVehiculo();
        }
        return instancia;
    }

    public void reiniciarHBox() {
        hBoxPaneles.getChildren().clear();
        vBoxCRUD.getChildren().clear();
        vBoxCRUD.getChildren().add(VistaAgregarVehiculo.getInstancia().getFormulario());
        hBoxPaneles.getChildren().addAll(getTablaVehiculo(), vBoxCRUD);
    }

    private void actualizarObsList() {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Vehiculo("AS213", "Mercedes", "AMG", "2020", "Verde", "Q.200.00", "Mecánica"));
        vehiculos.add(new Vehiculo("QEW43", "BMW", "M3", "1980", "Negro", "Q.125.00", "Mecánica"));
        // vehiculos = CategoryController.getInstancia().getBooks();

        if (observableList != null) {
            observableList.clear();
        }
        observableList = FXCollections.observableArrayList(vehiculos);
    }

    public void actualizarItemsTabla() {
        actualizarObsList();
        tableView.setItems(observableList);
    }

    // Lista de busqueda
    private void actualizarObsList(String search) {
    }

    public void actualizarItemsTabla(String search) {
        actualizarObsList(search);
        tableView.setItems(observableList);
    }

    public VBox getVistaVehiculo() {
        VBox vBox = new VBox();
        GridPane gridPaneTitulo = new GridPane();

        double x = PropiedadesPantalla.getInstancia().getX();
        double y = PropiedadesPantalla.getInstancia().getY();

        gridPaneTitulo.setVgap(10);
        gridPaneTitulo.setPadding(new Insets(20));
        gridPaneTitulo.setPrefSize(x, y * 0.005);
        vBox.setPrefSize(x, y);

        Text txtTitulo = new Text("VEHICULOS");
        txtTitulo.getStyleClass().add("textTitle");
        txtTitulo.setFont(new Font(30));
        gridPaneTitulo.add(txtTitulo, 0, 0);
        gridPaneTitulo.setPadding(new Insets(10, 20, 2.5, 20));

        hBoxPaneles = new HBox();
        hBoxPaneles.setPrefSize(x, y * 0.995);

        vBoxCRUD = new VBox();
        vBoxCRUD.setPrefSize(x * 0.30, y * 0.995);
        vBoxCRUD.getChildren().add(VistaAgregarVehiculo.getInstancia().getFormulario());

        hBoxPaneles.getChildren().addAll(getTablaVehiculo(), vBoxCRUD);
        vBox.getChildren().addAll(gridPaneTitulo, hBoxPaneles);
        return vBox;
    }

    private GridPane getTablaVehiculo() {
        GridPane gridPane = new GridPane();

        double x = PropiedadesPantalla.getInstancia().getX();
        double y = PropiedadesPantalla.getInstancia().getY();

        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(5, 10, 20, 20));
        gridPane.setPrefSize(x * 0.70, y);

        JFXTextField txtBuscar = new JFXTextField();
        txtBuscar.setPromptText("BUSCAR");
        txtBuscar.setPrefSize(x, y * 0.005);
        txtBuscar.textProperty().addListener((ObservableValue<? extends String> observable,
                String oldValue, String newValue) -> {
            actualizarItemsTabla(txtBuscar.getText().trim());
        });
        gridPane.add(txtBuscar, 0, 0);

        HBox hBoxBotones = new HBox();
        JFXButton btnArchivo = new JFXButton("ARCHIVO");
        btnArchivo.getStyleClass().addAll("customButton", "primaryButton");
        btnArchivo.setButtonType(JFXButton.ButtonType.FLAT);
        btnArchivo.setPrefSize(x, y);
        btnArchivo.setOnAction(event -> {
        });

        JFXButton btnAgregar = new JFXButton("AGREGAR");
        btnAgregar.getStyleClass().addAll("customButton", "primaryButton");
        btnAgregar.setButtonType(JFXButton.ButtonType.FLAT);
        btnAgregar.setPrefSize(x, y);
        btnAgregar.setOnAction(event -> {
            vBoxCRUD.getChildren().clear();
            vBoxCRUD.getChildren().addAll(VistaAgregarVehiculo.getInstancia().getFormulario());
        });

        JFXButton btnModificar = new JFXButton("MODIFICAR");
        btnModificar.getStyleClass().addAll("customButton", "warningButton");
        btnModificar.setButtonType(JFXButton.ButtonType.FLAT);
        btnModificar.setPrefSize(x, y);
        btnModificar.setOnAction(event -> {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                vBoxCRUD.getChildren().remove(0);
                vBoxCRUD.getChildren().add(0, VistaModificarVehiculo.getInstancia().getGridPane(
                        (Vehiculo) tableView.getSelectionModel().getSelectedItem()));
            } else {
                reiniciarHBox();
            }
        });

        JFXButton btnEliminar = new JFXButton("ELIMINAR");
        btnEliminar.getStyleClass().addAll("customButton", "dangerButton");
        btnEliminar.setButtonType(JFXButton.ButtonType.FLAT);
        btnEliminar.setPrefSize(x, y);
        btnEliminar.setOnAction(event -> {
            Vehiculo vehiculo = (Vehiculo) tableView.getSelectionModel().getSelectedItem();
            if (vehiculo != null) {
                reiniciarHBox();
                // ELIMINAR Vehiculo (METODO)
                VistaVehiculo.this.actualizarItemsTabla();
                Alerta.getInstancia().mostrarNotificacion("VEHICULOS", "VEHICULO ELIMINADO EXITOSAMENTE");
            }
        });

        hBoxBotones.getChildren().addAll(btnArchivo, btnAgregar, btnModificar, btnEliminar);
        hBoxBotones.setPrefSize(x, y * 0.005);
        hBoxBotones.setMargin(btnArchivo, new Insets(0, 5, 0, 0));
        hBoxBotones.setMargin(btnAgregar, new Insets(0, 5, 0, 0));
        hBoxBotones.setMargin(btnModificar, new Insets(0, 5, 0, 0));
        gridPane.add(hBoxBotones, 0, 1);

        TableColumn<Vehiculo, String> colPlaca = new TableColumn<>("PLACA");
        colPlaca.setPrefWidth(x / 10);
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));

        TableColumn<Vehiculo, String> colMarca = new TableColumn<>("MARCA");
        colMarca.setPrefWidth(x / 10);
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));

        TableColumn<Vehiculo, String> colModelo = new TableColumn<>("MODELO");
        colModelo.setPrefWidth(x / 10);
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<Vehiculo, String> colAnio = new TableColumn<>("AÑO");
        colAnio.setPrefWidth(x / 10);
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));

        TableColumn<Vehiculo, String> colColor = new TableColumn<>("COLOR");
        colColor.setPrefWidth(x / 10);
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));

        TableColumn<Vehiculo, String> colPrecio = new TableColumn<>("PRECIO");
        colPrecio.setPrefWidth(x / 10);
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<Vehiculo, String> colTransmision = new TableColumn<>("TRANSMISIÓN");
        colTransmision.setPrefWidth(x / 10);
        colTransmision.setCellValueFactory(new PropertyValueFactory<>("transmision"));

        actualizarObsList();
        tableView = new TableView<>(observableList);
        tableView.getColumns().addAll(colPlaca, colMarca, colModelo, colAnio, colColor, colPrecio, colTransmision);
        tableView.setOnMouseClicked(event -> {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                vBoxCRUD.getChildren().remove(0);
                vBoxCRUD.getChildren().add(0, VistaMostrarVehiculo.getInstancia().getGridPane(
                        (Vehiculo) tableView.getSelectionModel().getSelectedItem()));
            }
        });
        tableView.setPrefSize(x, y * 0.995);
        gridPane.add(tableView, 0, 2);
        
        return gridPane;
    }
}
