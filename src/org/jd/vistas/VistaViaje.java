package org.jd.vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jd.estructuras.ListaDoble;
import org.jd.modelos.Viaje;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaViaje extends Stage {

    private static VistaViaje instancia;
    private HBox hBoxPaneles;
    private VBox vBoxCRUD;
    private TableView tableView;
    private ObservableList observableList;

    private VistaViaje() {
    }

    public static VistaViaje getInstancia() {
        if (instancia == null) {
            instancia = new VistaViaje();
        }
        return instancia;
    }

    public void reiniciarHBox() {
        hBoxPaneles.getChildren().clear();
        vBoxCRUD.getChildren().clear();
        vBoxCRUD.getChildren().add(VistaAgregarViaje.getInstancia().getFormulario());
        hBoxPaneles.getChildren().addAll(getTablaViaje(), vBoxCRUD);
    }

    private void actualizarObsList() {
        if (observableList != null) {
            observableList.clear();
        }
        observableList = FXCollections.observableArrayList(ListaDoble.getInstancia().obtenerDatos());
    }

    public void actualizarItemsTabla() {
        actualizarObsList();
        tableView.setItems(observableList);
    }

    // Lista de busqueda
    private void actualizarObsList(String buscar) {
        observableList = FXCollections.observableArrayList(ListaDoble.getInstancia().buscarViaje(buscar));
    }

    public void actualizarItemsTabla(String search) {
        actualizarObsList(search);
        tableView.setItems(observableList);
    }

    public VBox getVistaViaje() {
        VBox vBox = new VBox();
        GridPane gridPaneTitulo = new GridPane();

        double x = PropiedadesPantalla.getInstancia().getX();
        double y = PropiedadesPantalla.getInstancia().getY();

        gridPaneTitulo.setVgap(10);
        gridPaneTitulo.setPadding(new Insets(20));
        gridPaneTitulo.setPrefSize(x, y * 0.005);
        vBox.setPrefSize(x, y);

        Text txtTitulo = new Text("VIAJES");
        txtTitulo.getStyleClass().add("textTitle");
        txtTitulo.setFont(new Font(30));
        gridPaneTitulo.add(txtTitulo, 0, 0);
        gridPaneTitulo.setPadding(new Insets(10, 20, 2.5, 20));

        hBoxPaneles = new HBox();
        hBoxPaneles.setPrefSize(x, y * 0.995);

        vBoxCRUD = new VBox();
        vBoxCRUD.setPrefSize(x * 0.30, y * 0.995);
        vBoxCRUD.setAlignment(Pos.CENTER);
        vBoxCRUD.getChildren().add(VistaAgregarViaje.getInstancia().getFormulario());

        hBoxPaneles.getChildren().addAll(getTablaViaje(), vBoxCRUD);
        vBox.getChildren().addAll(gridPaneTitulo, hBoxPaneles);
        return vBox;
    }

    private GridPane getTablaViaje() {
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

        JFXButton btnAgregar = new JFXButton("AGREGAR");
        btnAgregar.getStyleClass().addAll("customButton", "primaryButton");
        btnAgregar.setButtonType(JFXButton.ButtonType.FLAT);
        btnAgregar.setPrefSize(x, y);
        btnAgregar.setOnAction(event -> {
            vBoxCRUD.getChildren().clear();
            vBoxCRUD.getChildren().addAll(VistaAgregarViaje.getInstancia().getFormulario());
        });

        JFXButton btnModificar = new JFXButton("MODIFICAR");
        btnModificar.getStyleClass().addAll("customButton", "warningButton");
        btnModificar.setButtonType(JFXButton.ButtonType.FLAT);
        btnModificar.setPrefSize(x, y);
        btnModificar.setDisable(true);
        btnModificar.setOnAction(event -> {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                vBoxCRUD.getChildren().remove(0);
                //   vBoxCRUD.getChildren().add(0, VistaModificarViaje.getInstancia().getGridPane((Viaje) tableView.getSelectionModel().getSelectedItem()));
            } else {
                reiniciarHBox();
            }
        });

        JFXButton btnEliminar = new JFXButton("ELIMINAR");
        btnEliminar.getStyleClass().addAll("customButton", "dangerButton");
        btnEliminar.setButtonType(JFXButton.ButtonType.FLAT);
        btnEliminar.setPrefSize(x, y);
        btnEliminar.setDisable(true);
        btnEliminar.setOnAction(event -> {
            Viaje viaje = (Viaje) tableView.getSelectionModel().getSelectedItem();
            if (viaje != null) {
                //    ListaDoble.getInstancia().eliminar(viaje.getId());
                reiniciarHBox();
                VistaViaje.this.actualizarItemsTabla();
                Alerta.getInstancia().mostrarNotificacion("VIAJES", "VIAJE ELIMINADO EXITOSAMENTE");
            }
        });

        hBoxBotones.getChildren().addAll(btnAgregar, btnModificar, btnEliminar);
        hBoxBotones.setPrefSize(x, y * 0.005);
        hBoxBotones.setMargin(btnAgregar, new Insets(0, 5, 0, 0));
        hBoxBotones.setMargin(btnModificar, new Insets(0, 5, 0, 0));
        gridPane.add(hBoxBotones, 0, 1);

        TableColumn<Viaje, String> colOrigen = new TableColumn<>("ORIGEN");
        colOrigen.setPrefWidth(x / 10);
        colOrigen.setCellValueFactory(new PropertyValueFactory<>("origen"));

        TableColumn<Viaje, String> colDestino = new TableColumn<>("DESTINO");
        colDestino.setPrefWidth(x / 10);
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));

        TableColumn<Viaje, String> colFechaHora = new TableColumn<>("FECHA Y HORA");
        colFechaHora.setPrefWidth(x / 10);
        colFechaHora.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<Viaje, String> colCliente = new TableColumn<>("CLIENTE");
        colCliente.setPrefWidth(x / 10);
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        TableColumn<Viaje, String> colConductor = new TableColumn<>("CONDUCTOR");
        colConductor.setPrefWidth(x / 10);
        colConductor.setCellValueFactory(new PropertyValueFactory<>("conductor"));

        TableColumn<Viaje, String> colVehiculo = new TableColumn<>("VEHICULO");
        colVehiculo.setPrefWidth(x / 10);
        colVehiculo.setCellValueFactory(new PropertyValueFactory<>("vehiculo"));

        TableColumn<Viaje, String> colRuta = new TableColumn<>("RUTA");
        colRuta.setPrefWidth(x / 5);
        colRuta.setCellValueFactory(new PropertyValueFactory<>("ruta"));

        actualizarObsList();
        tableView = new TableView<>(observableList);
        tableView.getColumns().addAll(colOrigen, colDestino, colFechaHora, colCliente, colConductor, colVehiculo,
                colRuta);
        tableView.setOnMouseClicked(event -> {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                vBoxCRUD.getChildren().remove(0);
                vBoxCRUD.getChildren().add(0, VistaMostrarViaje.getInstancia().getGridPane(
                        (Viaje) tableView.getSelectionModel().getSelectedItem()));
            }
        });
        tableView.setPrefSize(x, y * 0.995);
        gridPane.add(tableView, 0, 2);

        return gridPane;
    }
}
