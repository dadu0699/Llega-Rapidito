package org.jd.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jd.beans.Cliente;
import org.jd.util.PropiedadesPantalla;

public class VistaMostrarCliente extends Stage {
    private static VistaMostrarCliente instancia;

    private VistaMostrarCliente() {
    }

    static VistaMostrarCliente getInstancia() {
        if (instancia == null) {
            instancia = new VistaMostrarCliente();
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
        
        Text txtTitulo = new Text("MOSTRAR");
        txtTitulo.getStyleClass().add("textTitle");
        txtTitulo.setFont(new Font(25));
        gridPane.add(txtTitulo, 0, 4);

        JFXTextField jFTDPI = new JFXTextField(String.valueOf(cliente.getDPI()));
        jFTDPI.setPromptText("DPI");
        jFTDPI.setLabelFloat(true);
        jFTDPI.setPrefWidth(x);
        gridPane.add(jFTDPI, 0, 5);

        JFXTextField jFTNombres = new JFXTextField(cliente.getNombres());
        jFTNombres.setPromptText("NOMBRES");
        jFTNombres.setLabelFloat(true);
        jFTNombres.setPrefWidth(x);
        jFTNombres.setEditable(false);
        gridPane.add(jFTNombres, 0, 6);

        JFXTextField jFTApellidos = new JFXTextField(cliente.getApellidos());
        jFTApellidos.setPromptText("APELLIDOS");
        jFTApellidos.setLabelFloat(true);
        jFTApellidos.setPrefWidth(x);
        jFTApellidos.setEditable(false);
        gridPane.add(jFTApellidos, 0, 7);

        JFXTextField jFTGenero = new JFXTextField(cliente.getGenero());
        jFTGenero.setPromptText("GENERO");
        jFTGenero.setLabelFloat(true);
        jFTGenero.setPrefWidth(x);
        jFTGenero.setEditable(false);
        gridPane.add(jFTGenero, 0, 8);

        JFXTextField jFTFNacimiento = new JFXTextField(cliente.getFechaNacimiento());
        jFTFNacimiento.setPromptText("FECHA NACIMIENTO");
        jFTFNacimiento.setLabelFloat(true);
        jFTFNacimiento.setPrefWidth(x);
        jFTFNacimiento.setEditable(false);
        gridPane.add(jFTFNacimiento, 0, 9);

        JFXTextField jFTTelefono = new JFXTextField(cliente.getTelefono());
        jFTTelefono.setPromptText("TELEFONO");
        jFTTelefono.setLabelFloat(true);
        jFTTelefono.setPrefWidth(x);
        jFTTelefono.setEditable(false);
        gridPane.add(jFTTelefono, 0, 10);

        JFXTextField jFTDireccion = new JFXTextField(cliente.getDireccion());
        jFTDireccion.setPromptText("DIRECCION");
        jFTDireccion.setLabelFloat(true);
        jFTDireccion.setPrefWidth(x);
        jFTDireccion.setEditable(false);
        gridPane.add(jFTDireccion, 0, 11);

        JFXButton buttonCopy = new JFXButton("COPIAR");
        buttonCopy.getStyleClass().addAll("customButton", "primaryButton");
        buttonCopy.setButtonType(JFXButton.ButtonType.FLAT);
        buttonCopy.setPrefSize(x, y * 0.04);
        buttonCopy.setOnAction(event -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            /*content.putString("CARNET:        " + cliente.getID() + "\nNOMBRE:        " + cliente.getName()
                    + "\nAPELLIDO:      " + cliente.getLastName() + "\nCARRERA:       " + cliente.getCareer());*/
            clipboard.setContent(content);
        });
        gridPane.add(buttonCopy, 0, 12);
        return gridPane;
    }
}
