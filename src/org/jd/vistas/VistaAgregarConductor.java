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

public class VistaAgregarConductor extends Stage {

    private static VistaAgregarConductor instancia;

    private VistaAgregarConductor() {
    }

    public static VistaAgregarConductor getInstancia() {
        if (instancia == null) {
            instancia = new VistaAgregarConductor();
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
        gridPane.add(txtTitulo, 0, 0);

        JFXTextField jFTDPI = new JFXTextField();
        jFTDPI.setPromptText("DPI");
        jFTDPI.setLabelFloat(true);
        jFTDPI.setPrefWidth(x);
        gridPane.add(jFTDPI, 0, 1);

        JFXTextField jFTNombres = new JFXTextField();
        jFTNombres.setPromptText("NOMBRES");
        jFTNombres.setLabelFloat(true);
        jFTNombres.setPrefWidth(x);
        gridPane.add(jFTNombres, 0, 2);

        JFXTextField jFTApellidos = new JFXTextField();
        jFTApellidos.setPromptText("APELLIDOS");
        jFTApellidos.setLabelFloat(true);
        jFTApellidos.setPrefWidth(x);
        gridPane.add(jFTApellidos, 0, 3);

        JFXTextField jFTLicencia = new JFXTextField();
        jFTLicencia.setPromptText("LICENCIA");
        jFTLicencia.setLabelFloat(true);
        jFTLicencia.setPrefWidth(x);
        gridPane.add(jFTLicencia, 0, 4);

        String[] generos = {"Masculino", "Femenino"};
        ObservableList obsGenero = FXCollections.observableArrayList(generos);
        ObservableList<String> informacion = obsGenero;
        JFXComboBox<String> cbGenero = new JFXComboBox<>(informacion);
        cbGenero.setPromptText("GENERO");
        cbGenero.setLabelFloat(true);
        cbGenero.setPrefWidth(x);
        gridPane.add(cbGenero, 0, 5);

        JFXTextField jFTFNacimiento = new JFXTextField();
        jFTFNacimiento.setPromptText("FECHA NACIMIENTO");
        jFTFNacimiento.setLabelFloat(true);
        jFTFNacimiento.setPrefWidth(x);
        gridPane.add(jFTFNacimiento, 0, 6);

        JFXTextField jFTTelefono = new JFXTextField();
        jFTTelefono.setPromptText("TELEFONO");
        jFTTelefono.setLabelFloat(true);
        jFTTelefono.setPrefWidth(x);
        gridPane.add(jFTTelefono, 0, 7);

        JFXTextField jFTDireccion = new JFXTextField();
        jFTDireccion.setPromptText("DIRECCION");
        jFTDireccion.setLabelFloat(true);
        jFTDireccion.setPrefWidth(x);
        gridPane.add(jFTDireccion, 0, 8);

        JFXButton buttonAdd = new JFXButton("AGREGAR");
        buttonAdd.getStyleClass().addAll("customButton", "primaryButton");
        buttonAdd.setButtonType(JFXButton.ButtonType.FLAT);
        buttonAdd.setPrefSize(x, y * 0.04);
        buttonAdd.setOnAction(event -> {
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
                boolean conductor = ListaCircular.getInstancia().agregar(new Conductor(jFTDPI.getText().trim(),
                        jFTNombres.getText().trim(),
                        jFTApellidos.getText().trim(),
                        jFTLicencia.getText().trim(),
                        cbGenero.getSelectionModel().getSelectedItem(),
                        jFTFNacimiento.getText().trim(),
                        jFTTelefono.getText().trim(),
                        jFTDireccion.getText().trim()));

                if (!conductor) {
                    Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "EL CONDUCTOR FUE REGISTRADO PREVIAMENTE");
                } else {
                    // VistaConductor.getInstancia().actualizarItemsTabla();
                    VistaConductor.getInstancia().reiniciarHBox();
                    Alerta.getInstancia().mostrarNotificacion("CONDUCTOR", "REGISTRO REALIZADO EXITOSAMENTE");
                }
            }
        });
        gridPane.add(buttonAdd, 0, 9);
        return gridPane;
    }
}
