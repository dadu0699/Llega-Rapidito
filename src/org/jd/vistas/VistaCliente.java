package org.jd.vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import org.jd.estructuras.TablaHash;
import org.jd.modelos.Cliente;
import org.jd.utilidades.ManejoDeArchivos;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaCliente extends Stage {

    private static VistaCliente instancia;
    private HBox hBoxPaneles;
    private VBox vBoxCRUD;
    private TableView tableView;
    private ObservableList observableList;

    private VistaCliente() {
    }

    public static VistaCliente getInstancia() {
        if (instancia == null) {
            instancia = new VistaCliente();
        }
        return instancia;
    }

    public void reiniciarHBox() {
        hBoxPaneles.getChildren().clear();
        vBoxCRUD.getChildren().clear();
        vBoxCRUD.getChildren().add(VistaAgregarCliente.getInstancia().getFormulario());
        hBoxPaneles.getChildren().addAll(getTablaCliente(), vBoxCRUD);
    }

    private void actualizarObsList() {
        ArrayList<Cliente> clientes = TablaHash.getInstancia().obtenerDatos();

        if (observableList != null) {
            observableList.clear();
        }
        observableList = FXCollections.observableArrayList(clientes);
    }

    public void actualizarItemsTabla() {
        actualizarObsList();
        tableView.setItems(observableList);
    }

    // Lista de busqueda
    private void actualizarObsList(String buscar) {
        observableList = FXCollections.observableArrayList(TablaHash.getInstancia().buscarCliente(buscar));
    }

    public void actualizarItemsTabla(String search) {
        actualizarObsList(search);
        tableView.setItems(observableList);
    }

    public VBox getVistaCliente() {
        VBox vBox = new VBox();
        GridPane gridPaneTitulo = new GridPane();

        double x = PropiedadesPantalla.getInstancia().getX();
        double y = PropiedadesPantalla.getInstancia().getY();

        gridPaneTitulo.setVgap(10);
        gridPaneTitulo.setPadding(new Insets(20));
        // gridPaneTitulo.setGridLinesVisible(true);
        gridPaneTitulo.setPrefSize(x, y * 0.005);
        vBox.setPrefSize(x, y);

        Text txtTitulo = new Text("CLIENTES");
        txtTitulo.getStyleClass().add("textTitle");
        txtTitulo.setFont(new Font(30));
        gridPaneTitulo.add(txtTitulo, 0, 0);
        gridPaneTitulo.setPadding(new Insets(10, 20, 2.5, 20));

        hBoxPaneles = new HBox();
        hBoxPaneles.setPrefSize(x, y * 0.995);

        vBoxCRUD = new VBox();
        vBoxCRUD.setPrefSize(x * 0.30, y * 0.995);
        vBoxCRUD.getChildren().add(VistaAgregarCliente.getInstancia().getFormulario());

        hBoxPaneles.getChildren().addAll(getTablaCliente(), vBoxCRUD);
        vBox.getChildren().addAll(gridPaneTitulo, hBoxPaneles);
        return vBox;
    }

    private GridPane getTablaCliente() {
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
            ManejoDeArchivos.getInstancia().subirArchivo("Archivo de Clientes", "*.txt");
            TablaHash.getInstancia().agregarArchivo(ManejoDeArchivos.getInstancia().leerArchivo());
            actualizarItemsTabla();
        });

        JFXButton btnAgregar = new JFXButton("AGREGAR");
        btnAgregar.getStyleClass().addAll("customButton", "primaryButton");
        btnAgregar.setButtonType(JFXButton.ButtonType.FLAT);
        btnAgregar.setPrefSize(x, y);
        btnAgregar.setOnAction(event -> {
            vBoxCRUD.getChildren().clear();
            vBoxCRUD.getChildren().addAll(VistaAgregarCliente.getInstancia().getFormulario());
        });

        JFXButton btnModificar = new JFXButton("MODIFICAR");
        btnModificar.getStyleClass().addAll("customButton", "warningButton");
        btnModificar.setButtonType(JFXButton.ButtonType.FLAT);
        btnModificar.setPrefSize(x, y);
        btnModificar.setOnAction(event -> {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                vBoxCRUD.getChildren().remove(0);
                vBoxCRUD.getChildren().add(0, VistaModificarCliente.getInstancia().getGridPane(
                        (Cliente) tableView.getSelectionModel().getSelectedItem()));
            } else {
                reiniciarHBox();
            }
        });

        JFXButton btnEliminar = new JFXButton("ELIMINAR");
        btnEliminar.getStyleClass().addAll("customButton", "dangerButton");
        btnEliminar.setButtonType(JFXButton.ButtonType.FLAT);
        btnEliminar.setPrefSize(x, y);
        btnEliminar.setOnAction(event -> {
            Cliente cliente = (Cliente) tableView.getSelectionModel().getSelectedItem();
            if (cliente != null) {
                TablaHash.getInstancia().eliminar(cliente.getDPI());
                reiniciarHBox();
                VistaCliente.this.actualizarItemsTabla();
                Alerta.getInstancia().mostrarNotificacion("CLIENTES", "CLIENTE ELIMINADO EXITOSAMENTE");
            }
        });

        hBoxBotones.getChildren().addAll(btnArchivo, btnAgregar, btnModificar, btnEliminar);
        hBoxBotones.setPrefSize(x, y * 0.005);
        hBoxBotones.setMargin(btnArchivo, new Insets(0, 5, 0, 0));
        hBoxBotones.setMargin(btnAgregar, new Insets(0, 5, 0, 0));
        hBoxBotones.setMargin(btnModificar, new Insets(0, 5, 0, 0));
        gridPane.add(hBoxBotones, 0, 1);

        TableColumn<Cliente, String> colDPI = new TableColumn<>("DPI");
        colDPI.setPrefWidth(x / 10);
        colDPI.setCellValueFactory(new PropertyValueFactory<>("DPI"));

        TableColumn<Cliente, String> colNombres = new TableColumn<>("NOMBRES");
        colNombres.setPrefWidth(x / 10);
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));

        TableColumn<Cliente, String> colApellidos = new TableColumn<>("APELLIDOS");
        colApellidos.setPrefWidth(x / 10);
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));

        TableColumn<Cliente, String> colGenero = new TableColumn<>("GENERO");
        colGenero.setPrefWidth(x / 10);
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));

        TableColumn<Cliente, String> colFechaNacimiento = new TableColumn<>("FECHA NACIMIENTO");
        colFechaNacimiento.setPrefWidth(x / 10);
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));

        TableColumn<Cliente, String> colTelefono = new TableColumn<>("TELEFONO");
        colTelefono.setPrefWidth(x / 10);
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        TableColumn<Cliente, String> colDireccion = new TableColumn<>("DIRECCION");
        colDireccion.setPrefWidth(x / 10);
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        actualizarObsList();
        tableView = new TableView<>(observableList);
        tableView.getColumns().addAll(colDPI, colNombres, colApellidos, colGenero, colFechaNacimiento, colTelefono,
                colDireccion);
        tableView.setOnMouseClicked(event -> {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                vBoxCRUD.getChildren().remove(0);
                vBoxCRUD.getChildren().add(0, VistaMostrarCliente.getInstancia()
                        .getGridPane((Cliente) tableView.getSelectionModel().getSelectedItem()));
            }
        });
        tableView.setPrefSize(x, y * 0.995);

        gridPane.add(tableView, 0, 3);
        return gridPane;
    }
}
