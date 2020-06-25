package org.jd.vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import org.jd.estructuras.ListaAdyacencia;
import org.jd.modelos.Ruta;
import org.jd.utilidades.ManejoDeArchivos;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaRuta extends Stage {

    private static VistaRuta instancia;
    private HBox hBoxPaneles;
    private VBox vBoxCRUD;
    private TableView tableView;
    private ObservableList observableList;

    private VistaRuta() {
    }

    public static VistaRuta getInstancia() {
        if (instancia == null) {
            instancia = new VistaRuta();
        }
        return instancia;
    }

    public void reiniciarHBox() {
        hBoxPaneles.getChildren().clear();
        vBoxCRUD.getChildren().clear();
        vBoxCRUD.getChildren().add(VistaMostrarRuta.getInstancia().getGridPane(null));
        hBoxPaneles.getChildren().addAll(getTablaRuta(), vBoxCRUD);
    }

    private void actualizarObsList() {
        if (observableList != null) {
            observableList.clear();
        }
        observableList = FXCollections.observableArrayList(ListaAdyacencia.getInstancia().obtenerDatos());
    }

    public void actualizarItemsTabla() {
        actualizarObsList();
        tableView.setItems(observableList);
    }

    // Lista de busqueda
    private void actualizarObsList(String buscar) {
        observableList = FXCollections.observableArrayList(ListaAdyacencia.getInstancia().buscarRuta(buscar));
    }

    public void actualizarItemsTabla(String search) {
        actualizarObsList(search);
        tableView.setItems(observableList);
    }

    public VBox getVistaRuta() {
        VBox vBox = new VBox();
        GridPane gridPaneTitulo = new GridPane();

        double x = PropiedadesPantalla.getInstancia().getX();
        double y = PropiedadesPantalla.getInstancia().getY();

        gridPaneTitulo.setVgap(10);
        gridPaneTitulo.setPadding(new Insets(20));
        // gridPaneTitulo.setGridLinesVisible(true);
        gridPaneTitulo.setPrefSize(x, y * 0.005);
        vBox.setPrefSize(x, y);

        Text txtTitulo = new Text("RUTAS");
        txtTitulo.getStyleClass().add("textTitle");
        txtTitulo.setFont(new Font(30));
        gridPaneTitulo.add(txtTitulo, 0, 0);
        gridPaneTitulo.setPadding(new Insets(10, 20, 2.5, 20));

        hBoxPaneles = new HBox();
        hBoxPaneles.setPrefSize(x, y * 0.995);

        vBoxCRUD = new VBox();
        vBoxCRUD.setPrefSize(x * 0.30, y * 0.995);
        vBoxCRUD.getChildren().add(VistaMostrarRuta.getInstancia().getGridPane(null));

        hBoxPaneles.getChildren().addAll(getTablaRuta(), vBoxCRUD);
        vBox.getChildren().addAll(gridPaneTitulo, hBoxPaneles);
        return vBox;
    }

    private GridPane getTablaRuta() {
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
            ManejoDeArchivos.getInstancia().subirArchivo("Archivo de Rutas", "*.txt");
            ListaAdyacencia.getInstancia().agregarArchivo(ManejoDeArchivos.getInstancia().leerArchivo());
            reiniciarHBox();

            ManejoDeArchivos.getInstancia().escribirArchivo(ListaAdyacencia.getInstancia().contenidoGrafica(), "listaAdyacencia.dot", "reportes");
            ManejoDeArchivos.getInstancia().compilarDOT("listaAdyacencia", "reportes");
            ManejoDeArchivos.getInstancia().escribirArchivo(ListaAdyacencia.getInstancia().contenidoGrafo(), "rutas.sfdp", "reportes");
            ManejoDeArchivos.getInstancia().compilarSFDP("rutas", "reportes");
        });

        JFXButton btnAgregar = new JFXButton("AGREGAR");
        btnAgregar.getStyleClass().addAll("customButton", "primaryButton");
        btnAgregar.setButtonType(JFXButton.ButtonType.FLAT);
        btnAgregar.setPrefSize(x, y);
        btnAgregar.setOnAction(event -> {
            vBoxCRUD.getChildren().clear();
            vBoxCRUD.getChildren().addAll(VistaAgregarRuta.getInstancia().getFormulario());
        });

        JFXButton btnModificar = new JFXButton("MODIFICAR");
        btnModificar.getStyleClass().addAll("customButton", "warningButton");
        btnModificar.setButtonType(JFXButton.ButtonType.FLAT);
        btnModificar.setPrefSize(x, y);
        btnModificar.setOnAction(event -> {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                vBoxCRUD.getChildren().remove(0);
                vBoxCRUD.getChildren().add(0, VistaModificarRuta.getInstancia().getGridPane(
                        (Ruta) tableView.getSelectionModel().getSelectedItem()));
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
            Ruta ruta = (Ruta) tableView.getSelectionModel().getSelectedItem();
            if (ruta != null) {
                reiniciarHBox();
                // ELIMINAR Ruta (METODO)
                VistaRuta.this.actualizarItemsTabla();
                Alerta.getInstancia().mostrarNotificacion("RUTAS", "RUTA ELIMINADO EXITOSAMENTE");
            }
        });

        if (ListaAdyacencia.getInstancia().getPrimero() == null) {
            hBoxBotones.getChildren().addAll(btnArchivo);
            hBoxBotones.setPrefSize(x, y * 0.005);
            gridPane.add(hBoxBotones, 0, 1);
        } else {
            Panel.getInstancia().activarBotones();
        }

        TableColumn<Ruta, String> colOrigen = new TableColumn<>("ORIGEN");
        colOrigen.setPrefWidth(x * 7 / 30);
        colOrigen.setCellValueFactory(new PropertyValueFactory<>("origen"));

        TableColumn<Ruta, String> colDestino = new TableColumn<>("DESTINO");
        colDestino.setPrefWidth(x * 7 / 30);
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));

        TableColumn<Ruta, String> colTiempoRuta = new TableColumn<>("TIEMPO RUTA");
        colTiempoRuta.setPrefWidth(x * 7 / 30);
        colTiempoRuta.setCellValueFactory(new PropertyValueFactory<>("tiempoRuta"));

        actualizarObsList();
        tableView = new TableView<>(observableList);
        tableView.getColumns().addAll(colOrigen, colDestino, colTiempoRuta);
        tableView.setOnMouseClicked(event -> {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                vBoxCRUD.getChildren().remove(0);
                vBoxCRUD.getChildren().add(0, VistaMostrarRuta.getInstancia().getGridPane(
                        (Ruta) tableView.getSelectionModel().getSelectedItem()));
            }
        });
        tableView.setPrefSize(x, y * 0.995);

        gridPane.add(tableView, 0, 3);
        return gridPane;
    }
}
