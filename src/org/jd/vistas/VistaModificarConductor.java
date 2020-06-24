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
import org.jd.modelos.Conductor;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaModificarConductor extends Stage {

    private static VistaModificarConductor instancia;

    private VistaModificarConductor() {
    }

    static VistaModificarConductor getInstancia() {
        if (instancia == null) {
            instancia = new VistaModificarConductor();
        }
        return instancia;
    }

    public GridPane getGridPane(Conductor conductor) {
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
        gridPane.add(txtTitulo, 0, 3, 2, 1);

        JFXTextField jFTDPI = new JFXTextField(conductor.getDPI());
        jFTDPI.setPromptText("DPI");
        jFTDPI.setLabelFloat(true);
        jFTDPI.setPrefWidth(x);
        gridPane.add(jFTDPI, 0, 4, 2, 1);

        JFXTextField jFTNombres = new JFXTextField(conductor.getNombres());
        jFTNombres.setPromptText("NOMBRES");
        jFTNombres.setLabelFloat(true);
        jFTNombres.setPrefWidth(x);
        gridPane.add(jFTNombres, 0, 5, 2, 1);

        JFXTextField jFTApellidos = new JFXTextField(conductor.getApellidos());
        jFTApellidos.setPromptText("APELLIDOS");
        jFTApellidos.setLabelFloat(true);
        jFTApellidos.setPrefWidth(x);
        gridPane.add(jFTApellidos, 0, 6, 2, 1);

        JFXTextField jFTLicencia = new JFXTextField(conductor.getLicencia());
        jFTLicencia.setPromptText("LICENCIA");
        jFTLicencia.setLabelFloat(true);
        jFTLicencia.setPrefWidth(x);
        gridPane.add(jFTLicencia, 0, 7, 2, 1);

        String[] generos = {"Masculino", "Femenino"};
        ObservableList obsGenero = FXCollections.observableArrayList(generos);
        ObservableList<String> informacion = obsGenero;
        JFXComboBox<String> cbGenero = new JFXComboBox<>(informacion);
        cbGenero.getSelectionModel().select(conductor.getGenero());
        cbGenero.setPromptText("GENERO");
        cbGenero.setLabelFloat(true);
        cbGenero.setPrefWidth(x);
        gridPane.add(cbGenero, 0, 8, 2, 1);

        JFXTextField jFTFNacimiento = new JFXTextField(conductor.getFechaNacimiento());
        jFTFNacimiento.setPromptText("FECHA NACIMIENTO");
        jFTFNacimiento.setLabelFloat(true);
        jFTFNacimiento.setPrefWidth(x);
        gridPane.add(jFTFNacimiento, 0, 9, 2, 1);

        JFXTextField jFTTelefono = new JFXTextField(conductor.getTelefono());
        jFTTelefono.setPromptText("TELEFONO");
        jFTTelefono.setLabelFloat(true);
        jFTTelefono.setPrefWidth(x);
        gridPane.add(jFTTelefono, 0, 10, 2, 1);

        JFXTextField jFTDireccion = new JFXTextField(conductor.getDireccion());
        jFTDireccion.setPromptText("DIRECCION");
        jFTDireccion.setLabelFloat(true);
        jFTDireccion.setPrefWidth(x);
        gridPane.add(jFTDireccion, 0, 11, 2, 1);

        JFXButton btnModificar = new JFXButton("MODIFICAR");
        btnModificar.getStyleClass().addAll("customButton", "primaryButton");
        btnModificar.setButtonType(JFXButton.ButtonType.FLAT);
        btnModificar.setPrefSize(x, y * 0.04);
        btnModificar.setOnAction(event -> {
            if (jFTDPI.getText().length() == 0
                    || jFTNombres.getText().length() == 0
                    || jFTApellidos.getText().length() == 0
                    || jFTLicencia.getText().length() == 0
                    || cbGenero.getSelectionModel().getSelectedItem() == null
                    || jFTFNacimiento.getText().length() == 0
                    || jFTTelefono.getText().length() == 0
                    || jFTDireccion.getText().length() == 0) {
                Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "UNO O MÁS DATOS SON INCORRECTOS");
            } else {
                conductor.setNombres(jFTNombres.getText().trim());
                conductor.setApellidos(jFTApellidos.getText().trim());
                conductor.setLicencia(jFTLicencia.getText().trim());
                conductor.setGenero(cbGenero.getSelectionModel().getSelectedItem());
                conductor.setFechaNacimiento(jFTFNacimiento.getText().trim());
                conductor.setTelefono(jFTTelefono.getText().trim());
                conductor.setDireccion(jFTDireccion.getText().trim());

                if (conductor.getDPI().equalsIgnoreCase(jFTDPI.getText().trim())) {
                    Alerta.getInstancia().mostrarNotificacion("CONDUCTORES", "CONDUCTOR ACTUALIZADO EXITOSAMENTE");
                } else {
                    if (ListaCircular.getInstancia().buscar(jFTDPI.getText().trim()) == null) {
                        ListaCircular.getInstancia().eliminar(conductor.getDPI());
                        conductor.setDPI(jFTDPI.getText().trim());
                        ListaCircular.getInstancia().agregar(conductor);
                        Alerta.getInstancia().mostrarNotificacion("CONDUCTORES", "CONDUCTOR ACTUALIZADO EXITOSAMENTE");
                    } else {
                        Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "ERROR AL MODIFICAR DPI");
                    }
                }
                VistaConductor.getInstancia().actualizarItemsTabla();
            }
        });
        gridPane.add(btnModificar, 0, 12);

        JFXButton btnCancelar = new JFXButton("CANCELAR");
        btnCancelar.getStyleClass().addAll("customButton", "dangerButton");
        btnCancelar.setButtonType(JFXButton.ButtonType.FLAT);
        btnCancelar.setPrefSize(x, y * 0.04);
        btnCancelar.setOnAction(event -> VistaConductor.getInstancia().reiniciarHBox());
        gridPane.add(btnCancelar, 1, 12);

        return gridPane;
    }
}
