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
import org.jd.estructuras.TablaHash;
import org.jd.modelos.Cliente;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaModificarCliente extends Stage {

    private static VistaModificarCliente instancia;

    private VistaModificarCliente() {
    }

    static VistaModificarCliente getInstancia() {
        if (instancia == null) {
            instancia = new VistaModificarCliente();
        }
        return instancia;
    }

    public GridPane getGridPane(Cliente cliente) {
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
        gridPane.add(txtTitulo, 0, 0, 2, 1);

        JFXTextField jFTDPI = new JFXTextField(String.valueOf(cliente.getDPI()));
        jFTDPI.setPromptText("DPI");
        jFTDPI.setLabelFloat(true);
        jFTDPI.setPrefWidth(x);
        jFTDPI.setEditable(true);
        gridPane.add(jFTDPI, 0, 1, 2, 1);

        JFXTextField jFTNombres = new JFXTextField(cliente.getNombres());
        jFTNombres.setPromptText("NOMBRES");
        jFTNombres.setLabelFloat(true);
        jFTNombres.setPrefWidth(x);
        gridPane.add(jFTNombres, 0, 2, 2, 1);

        JFXTextField jFTApellidos = new JFXTextField(cliente.getApellidos());
        jFTApellidos.setPromptText("APELLIDOS");
        jFTApellidos.setLabelFloat(true);
        jFTApellidos.setPrefWidth(x);
        gridPane.add(jFTApellidos, 0, 3, 2, 1);

        String[] generos = {"Masculino", "Femenino"};
        ObservableList obsGenero = FXCollections.observableArrayList(generos);
        ObservableList<String> informacion = obsGenero;
        JFXComboBox<String> cbGenero = new JFXComboBox<>(informacion);
        cbGenero.getSelectionModel().select(cliente.getGenero());
        cbGenero.setPromptText("GENERO");
        cbGenero.setLabelFloat(true);
        cbGenero.setPrefWidth(x);
        gridPane.add(cbGenero, 0, 4, 2, 1);

        JFXTextField jFTFNacimiento = new JFXTextField(cliente.getFechaNacimiento());
        jFTFNacimiento.setPromptText("FECHA NACIMIENTO");
        jFTFNacimiento.setLabelFloat(true);
        jFTFNacimiento.setPrefWidth(x);
        gridPane.add(jFTFNacimiento, 0, 5, 2, 1);

        JFXTextField jFTTelefono = new JFXTextField(cliente.getTelefono());
        jFTTelefono.setPromptText("TELEFONO");
        jFTTelefono.setLabelFloat(true);
        jFTTelefono.setPrefWidth(x);
        gridPane.add(jFTTelefono, 0, 6, 2, 1);

        JFXTextField jFTDireccion = new JFXTextField(cliente.getDireccion());
        jFTDireccion.setPromptText("DIRECCION");
        jFTDireccion.setLabelFloat(true);
        jFTDireccion.setPrefWidth(x);
        gridPane.add(jFTDireccion, 0, 7, 2, 1);

        JFXButton btnModificar = new JFXButton("MODIFICAR");
        btnModificar.getStyleClass().addAll("customButton", "primaryButton");
        btnModificar.setButtonType(JFXButton.ButtonType.FLAT);
        btnModificar.setPrefSize(x, y * 0.04);
        btnModificar.setOnAction(event -> {
            if (jFTNombres.getText().length() == 0
                    || jFTApellidos.getText().length() == 0
                    || cbGenero.getSelectionModel().getSelectedItem() == null
                    || jFTFNacimiento.getText().length() == 0
                    || jFTTelefono.getText().length() == 0
                    // || !Verificaciones.getInstancia().esNumeroEntero(jFTTelefono.getText().trim())
                    || jFTDireccion.getText().length() == 0) {
                Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "UNO O MÁS DATOS SON INCORRECTOS");
            } else {
                cliente.setNombres(jFTNombres.getText().trim());
                cliente.setApellidos(jFTApellidos.getText().trim());
                cliente.setGenero(cbGenero.getSelectionModel().getSelectedItem().trim());
                cliente.setFechaNacimiento(jFTFNacimiento.getText().trim());
                cliente.setTelefono(jFTTelefono.getText().trim());
                cliente.setDireccion(jFTDireccion.getText().trim());

                if (cliente.getDPI().equalsIgnoreCase(jFTDPI.getText().trim())) {
                    Alerta.getInstancia().mostrarNotificacion("CLIENTES", "CLIENTE ACTUALIZADO EXITOSAMENTE");
                } else {
                    if (TablaHash.getInstancia().buscar(jFTDPI.getText().trim()) == null) {
                        TablaHash.getInstancia().eliminar(cliente.getDPI());
                        cliente.setDPI(jFTDPI.getText().trim());
                        TablaHash.getInstancia().insertar(cliente);
                        Alerta.getInstancia().mostrarNotificacion("CLIENTES", "CLIENTE ACTUALIZADO EXITOSAMENTE");
                    } else {
                        Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "ERROR AL MODIFICAR DPI");
                    }
                }
                VistaCliente.getInstancia().actualizarItemsTabla();
            }
        });
        gridPane.add(btnModificar, 0, 8);

        JFXButton btnCancelar = new JFXButton("CANCELAR");
        btnCancelar.getStyleClass().addAll("customButton", "dangerButton");
        btnCancelar.setButtonType(JFXButton.ButtonType.FLAT);
        btnCancelar.setPrefSize(x, y * 0.04);
        btnCancelar.setOnAction(event -> VistaCliente.getInstancia().reiniciarHBox());
        gridPane.add(btnCancelar, 1, 8);

        return gridPane;
    }
}
