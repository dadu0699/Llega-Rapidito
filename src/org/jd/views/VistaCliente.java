package org.jd.views;

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
import org.jd.beans.Cliente;
import org.jd.util.PropiedadesPantalla;

public class VistaCliente extends Stage {

    private static VistaCliente instancia;
    private HBox hBoxPaneles;
    private VBox vBoxApplications;
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

    public void restartHBox() {
        hBoxPaneles.getChildren().clear();
        vBoxApplications.getChildren().clear();
        vBoxApplications.getChildren().add(VistaAgregarCliente.getInstancia().getFormulario());
        hBoxPaneles.getChildren().addAll(getTablaCliente(), vBoxApplications);
    }

    private void updateObservableList() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(12, "Didier", "Dominguez", "Masculino", "06/04/1999", "5555-5555", "Boca del monte"));
        clientes.add(new Cliente(14, "Jacqueline", "Mendez", "Femenino", "06/11/1999", "5555-5555", "Zona 1"));
        // clientes = CategoryController.getInstance().getBooks();

        if (observableList != null) {
            observableList.clear();
        }
        observableList = FXCollections.observableArrayList(clientes);
    }

    public void updateTableViewItems() {
        updateObservableList();
        tableView.setItems(observableList);
    }

    public VBox getVistaCliente() {
        VBox vBox = new VBox();
        GridPane gridPaneTitle = new GridPane();

        double x = PropiedadesPantalla.getInstancia().getX();
        double y = PropiedadesPantalla.getInstancia().getY();

        gridPaneTitle.setVgap(10);
        gridPaneTitle.setPadding(new Insets(20));
        // gridPaneTitle.setGridLinesVisible(true);
        gridPaneTitle.setPrefSize(x, y * 0.005);
        vBox.setPrefSize(x, y);

        Text textTitle = new Text("CLIENTES");
        textTitle.getStyleClass().add("textTitle");
        textTitle.setFont(new Font(30));
        gridPaneTitle.add(textTitle, 0, 0);
        gridPaneTitle.setPadding(new Insets(10, 20, 2.5, 20));

        hBoxPaneles = new HBox();
        hBoxPaneles.setPrefSize(x, y * 0.995);

        vBoxApplications = new VBox();
        vBoxApplications.setPrefSize(x * 0.30, y * 0.995);
        vBoxApplications.getChildren().add(VistaAgregarCliente.getInstancia().getFormulario());

        hBoxPaneles.getChildren().addAll(getTablaCliente(), vBoxApplications);
        vBox.getChildren().addAll(gridPaneTitle, hBoxPaneles);
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
        gridPane.add(txtBuscar, 0, 0);

        HBox hBoxBotones = new HBox();
        JFXButton btnArchivo = new JFXButton("ARCHIVO");
        btnArchivo.getStyleClass().addAll("customButton", "primaryButton");
        btnArchivo.setButtonType(JFXButton.ButtonType.FLAT);
        btnArchivo.setPrefSize(x, y);
        btnArchivo.setOnAction(event -> {
            /*FileControl.getInstance().uploadFile("User File", "*.json");
            FileControl.getInstance().readUserJSON();
            updateTableViewItems();*/
        });

        JFXButton btnAgregar = new JFXButton("AGREGAR");
        btnAgregar.getStyleClass().addAll("customButton", "primaryButton");
        btnAgregar.setButtonType(JFXButton.ButtonType.FLAT);
        btnAgregar.setPrefSize(x, y);
        btnAgregar.setOnAction(event -> {
            vBoxApplications.getChildren().clear();
            vBoxApplications.getChildren().addAll(VistaAgregarCliente.getInstancia().getFormulario());
        });

        JFXButton btnModificar = new JFXButton("MODIFICAR");
        btnModificar.getStyleClass().addAll("customButton", "warningButton");
        btnModificar.setButtonType(JFXButton.ButtonType.FLAT);
        btnModificar.setPrefSize(x, y);
        btnModificar.setOnAction(event -> {
            /*hBox.getChildren().clear();
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                User user = (User) tableView.getSelectionModel().getSelectedItem();
                if (SessionProperties.getInstance().isAuthenticated()
                        && SessionProperties.getInstance().getUser() == user) {
                    hBox.getChildren().addAll(gridPane, UpdateUser.getInstance().getGridPane(user));
                } else {
                    hBox.getChildren().addAll(gridPane, ShowUser.getInstance().getGridPane(user));
                }
            } else {
                hBox.getChildren().add(gridPane);
            }*/
        });

        JFXButton btnEliminar = new JFXButton("ELIMINAR");
        btnEliminar.getStyleClass().addAll("customButton", "dangerButton");
        btnEliminar.setButtonType(JFXButton.ButtonType.FLAT);
        btnEliminar.setPrefSize(x, y);
        btnEliminar.setOnAction(event -> {
            /*User user = (User) tableView.getSelectionModel().getSelectedItem();
            if (user != null && SessionProperties.getInstance().isAuthenticated()
                    && user.getID() == SessionProperties.getInstance().getUser().getID()) {
                restartHBox();
                UserController.getInstance().remove(user.getID());
                updateTableViewItems();
                Alert.getInstance().showNotification("USUARIO", "USUARIO ELIMINADO EXITOSAMENTE");
                SessionProperties.getInstance().setUser(null);
                AdministrativePanel.getInstance().showWindow();
            }*/
        });

        hBoxBotones.getChildren().addAll(btnArchivo, btnAgregar, btnModificar, btnEliminar);
        hBoxBotones.setPrefSize(x, y * 0.005);
        hBoxBotones.setMargin(btnArchivo, new Insets(0, 5, 0, 0));
        hBoxBotones.setMargin(btnAgregar, new Insets(0, 5, 0, 0));
        hBoxBotones.setMargin(btnModificar, new Insets(0, 5, 0, 0));
        gridPane.add(hBoxBotones, 0, 1);

        TableColumn<Cliente, Integer> colDPI = new TableColumn<>("DPI");
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

        updateObservableList();
        tableView = new TableView<>(observableList);
        tableView.getColumns().addAll(colDPI, colNombres, colApellidos, colGenero,
                colFechaNacimiento, colTelefono, colDireccion);
        tableView.setOnMouseClicked(event -> {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                vBoxApplications.getChildren().remove(0);
                vBoxApplications.getChildren().add(0, VistaMostrarCliente.getInstancia().getGridPane(
                        (Cliente) tableView.getSelectionModel().getSelectedItem()));
            }
        });
        tableView.setPrefSize(x, y * 0.995);

        gridPane.add(tableView, 0, 3);
        return gridPane;
    }
}
