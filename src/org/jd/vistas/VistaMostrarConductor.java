package org.jd.vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jd.modelos.Conductor;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaMostrarConductor extends Stage {

    private static VistaMostrarConductor instancia;

    private VistaMostrarConductor() {
    }

    static VistaMostrarConductor getInstancia() {
        if (instancia == null) {
            instancia = new VistaMostrarConductor();
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

        Text txtTitulo = new Text("MOSTRAR");
        txtTitulo.getStyleClass().add("textTitle");
        txtTitulo.setFont(new Font(25));
        gridPane.add(txtTitulo, 0, 3);

        JFXTextField jFTDPI = new JFXTextField(conductor.getDPI());
        jFTDPI.setPromptText("DPI");
        jFTDPI.setLabelFloat(true);
        jFTDPI.setPrefWidth(x);
        jFTDPI.setEditable(false);
        gridPane.add(jFTDPI, 0, 4);

        JFXTextField jFTNombres = new JFXTextField(conductor.getNombres());
        jFTNombres.setPromptText("NOMBRES");
        jFTNombres.setLabelFloat(true);
        jFTNombres.setPrefWidth(x);
        jFTNombres.setEditable(false);
        gridPane.add(jFTNombres, 0, 5);

        JFXTextField jFTApellidos = new JFXTextField(conductor.getApellidos());
        jFTApellidos.setPromptText("APELLIDOS");
        jFTApellidos.setLabelFloat(true);
        jFTApellidos.setPrefWidth(x);
        jFTApellidos.setEditable(false);
        gridPane.add(jFTApellidos, 0, 6);

        JFXTextField jFTLicencia = new JFXTextField(conductor.getLicencia());
        jFTLicencia.setPromptText("LICENCIA");
        jFTLicencia.setLabelFloat(true);
        jFTLicencia.setPrefWidth(x);
        jFTLicencia.setEditable(false);
        gridPane.add(jFTLicencia, 0, 7);

        JFXTextField jFTGenero = new JFXTextField(conductor.getGenero());
        jFTGenero.setPromptText("GENERO");
        jFTGenero.setLabelFloat(true);
        jFTGenero.setPrefWidth(x);
        jFTGenero.setEditable(false);
        gridPane.add(jFTGenero, 0, 8);

        JFXTextField jFTFNacimiento = new JFXTextField(conductor.getFechaNacimiento());
        jFTFNacimiento.setPromptText("FECHA NACIMIENTO");
        jFTFNacimiento.setLabelFloat(true);
        jFTFNacimiento.setPrefWidth(x);
        jFTFNacimiento.setEditable(false);
        gridPane.add(jFTFNacimiento, 0, 9);

        JFXTextField jFTTelefono = new JFXTextField(conductor.getTelefono());
        jFTTelefono.setPromptText("TELEFONO");
        jFTTelefono.setLabelFloat(true);
        jFTTelefono.setPrefWidth(x);
        jFTTelefono.setEditable(false);
        gridPane.add(jFTTelefono, 0, 10);

        JFXTextField jFTDireccion = new JFXTextField(conductor.getDireccion());
        jFTDireccion.setPromptText("DIRECCION");
        jFTDireccion.setLabelFloat(true);
        jFTDireccion.setPrefWidth(x);
        jFTDireccion.setEditable(false);
        gridPane.add(jFTDireccion, 0, 11);

        JFXButton btnCopiar = new JFXButton("COPIAR");
        btnCopiar.getStyleClass().addAll("customButton", "primaryButton");
        btnCopiar.setButtonType(JFXButton.ButtonType.FLAT);
        btnCopiar.setPrefSize(x, y * 0.04);
        btnCopiar.setOnAction(event -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString("DPI:                   " + conductor.getDPI()
                    + "\nNOMBRES:               " + conductor.getNombres()
                    + "\nAPELLIDOS:             " + conductor.getApellidos()
                    + "\nLICENCIA:                " + conductor.getLicencia()
                    + "\nGENERO:                " + conductor.getGenero()
                    + "\nFECHA NACIMIENTO:      " + conductor.getFechaNacimiento()
                    + "\nTELEFONO:              " + conductor.getTelefono()
                    + "\nDIRECCION:             " + conductor.getDireccion());
            clipboard.setContent(content);
        });
        gridPane.add(btnCopiar, 0, 12);
        return gridPane;
    }
}
