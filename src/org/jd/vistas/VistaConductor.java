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
import org.jd.modelos.Conductor;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaConductor extends Stage {

    private static VistaConductor instancia;
    private HBox hBoxPaneles;
    private VBox vBoxCRUD;
    private TableView tableView;
    private ObservableList observableList;

    private VistaConductor() {
    }

    public static VistaConductor getInstancia() {
        if (instancia == null) {
            instancia = new VistaConductor();
        }
        return instancia;
    }

    public void reiniciarHBox() {
        hBoxPaneles.getChildren().clear();
        vBoxCRUD.getChildren().clear();
        vBoxCRUD.getChildren().add(VistaAgregarConductor.getInstancia().getFormulario());
        hBoxPaneles.getChildren().addAll(getTablaConductor(), vBoxCRUD);
    }

    private void actualizarObsList() {
        ArrayList<Conductor> conductor = new ArrayList<>();
        conductor.add(new Conductor("18", "Juan", "Perez", "C", "Masculino", "18/06/1985", "5555-5555", "Ciudad"));
        conductor.add(new Conductor("19", "Mario", "Garcia", "A", "Masculino", "18/06/1995", "5555-5555", "Jutiapa"));

        if (observableList != null) {
            observableList.clear();
        }
        observableList = FXCollections.observableArrayList(conductor);
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

    public VBox getVistaConductor() {
        VBox vBox = new VBox();
        GridPane gridPaneTitulo = new GridPane();

        double x = PropiedadesPantalla.getInstancia().getX();
        double y = PropiedadesPantalla.getInstancia().getY();

        gridPaneTitulo.setVgap(10);
        gridPaneTitulo.setPadding(new Insets(20));
        gridPaneTitulo.setPrefSize(x, y * 0.005);
        vBox.setPrefSize(x, y);

        Text txtTitulo = new Text("CONDUCTORES");
        txtTitulo.getStyleClass().add("textTitle");
        txtTitulo.setFont(new Font(30));
        gridPaneTitulo.add(txtTitulo, 0, 0);
        gridPaneTitulo.setPadding(new Insets(10, 20, 2.5, 20));

        hBoxPaneles = new HBox();
        hBoxPaneles.setPrefSize(x, y * 0.995);

        vBoxCRUD = new VBox();
        vBoxCRUD.setPrefSize(x * 0.30, y * 0.995);
        vBoxCRUD.getChildren().add(VistaAgregarConductor.getInstancia().getFormulario());

        hBoxPaneles.getChildren().addAll(getTablaConductor(), vBoxCRUD);
        vBox.getChildren().addAll(gridPaneTitulo, hBoxPaneles);
        return vBox;
    }

    private GridPane getTablaConductor() {
        GridPane gridPane = new GridPane();

        double x = PropiedadesPantalla.getInstancia().getX();
        double y = PropiedadesPantalla.getInstancia().getY();

        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(5, 10, 20, 20));
        gridPane.setPrefSize(x * 0.70, y);

        JFXTextField txtBuscar = new JFXTextField();
        txtBuscar.setPromptText("BUSCAR");
        txtBuscar.setPrefSize(x, y * 0.005);
        txtBuscar.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
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
            vBoxCRUD.getChildren().addAll(VistaAgregarConductor.getInstancia().getFormulario());
        });

        JFXButton btnModificar = new JFXButton("MODIFICAR");
        btnModificar.getStyleClass().addAll("customButton", "warningButton");
        btnModificar.setButtonType(JFXButton.ButtonType.FLAT);
        btnModificar.setPrefSize(x, y);
        btnModificar.setOnAction(event -> {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                vBoxCRUD.getChildren().remove(0);
                vBoxCRUD.getChildren().add(0, VistaModificarConductor.getInstancia().getGridPane((Conductor) tableView.getSelectionModel().getSelectedItem()));
            } else {
                reiniciarHBox();
            }
        });

        JFXButton btnEliminar = new JFXButton("ELIMINAR");
        btnEliminar.getStyleClass().addAll("customButton", "dangerButton");
        btnEliminar.setButtonType(JFXButton.ButtonType.FLAT);
        btnEliminar.setPrefSize(x, y);
        btnEliminar.setOnAction(event -> {
            Conductor conductor = (Conductor) tableView.getSelectionModel().getSelectedItem();
            if (conductor != null) {
                reiniciarHBox();
                // ELIMINAR Conductor (METODO)
                VistaConductor.this.actualizarItemsTabla();
                Alerta.getInstancia().mostrarNotificacion("CONDUCTORES", "CONDUCTOR ELIMINADO EXITOSAMENTE");
            }
        });

        hBoxBotones.getChildren().addAll(btnArchivo, btnAgregar, btnModificar, btnEliminar);
        hBoxBotones.setPrefSize(x, y * 0.005);
        hBoxBotones.setMargin(btnArchivo, new Insets(0, 5, 0, 0));
        hBoxBotones.setMargin(btnAgregar, new Insets(0, 5, 0, 0));
        hBoxBotones.setMargin(btnModificar, new Insets(0, 5, 0, 0));
        gridPane.add(hBoxBotones, 0, 1);

        TableColumn<Conductor, String> colDPI = new TableColumn<>("DPI");
        colDPI.setPrefWidth(x / 10);
        colDPI.setCellValueFactory(new PropertyValueFactory<>("DPI"));

        TableColumn<Conductor, String> colNombres = new TableColumn<>("NOMBRES");
        colNombres.setPrefWidth(x / 10);
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));

        TableColumn<Conductor, String> colApellidos = new TableColumn<>("APELLIDOS");
        colApellidos.setPrefWidth(x / 10);
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));

        TableColumn<Conductor, String> colLicencia = new TableColumn<>("LICENCIA");
        colLicencia.setPrefWidth(x / 10);
        colLicencia.setCellValueFactory(new PropertyValueFactory<>("licencia"));

        TableColumn<Conductor, String> colGenero = new TableColumn<>("GENERO");
        colGenero.setPrefWidth(x / 10);
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));

        TableColumn<Conductor, String> colFechaNacimiento = new TableColumn<>("FECHA NACIMIENTO");
        colFechaNacimiento.setPrefWidth(x / 10);
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));

        TableColumn<Conductor, String> colTelefono = new TableColumn<>("TELEFONO");
        colTelefono.setPrefWidth(x / 10);
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        TableColumn<Conductor, String> colDireccion = new TableColumn<>("DIRECCION");
        colDireccion.setPrefWidth(x / 10);
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        actualizarObsList();
        tableView = new TableView<>(observableList);
        tableView.getColumns().addAll(colDPI, colNombres, colApellidos, colLicencia, colGenero, colFechaNacimiento, colTelefono,
                colDireccion);

        tableView.setOnMouseClicked(event -> {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                vBoxCRUD.getChildren().remove(0);
                vBoxCRUD.getChildren().add(0, VistaMostrarConductor.getInstancia().getGridPane((Conductor) tableView.getSelectionModel().getSelectedItem()));
            }
        });

        tableView.setPrefSize(x, y * 0.995);

        gridPane.add(tableView, 0, 3);
        return gridPane;
    }
}
