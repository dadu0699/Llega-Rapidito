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
import org.jd.modelos.Vehiculo;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaMostrarVehiculo extends Stage {

    private static VistaMostrarVehiculo instancia;

    private VistaMostrarVehiculo() {
    }

    static VistaMostrarVehiculo getInstancia() {
        if (instancia == null) {
            instancia = new VistaMostrarVehiculo();
        }
        return instancia;
    }

    public GridPane getGridPane(Vehiculo vehiculo) {
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
        gridPane.add(txtTitulo, 0, 0);

        JFXTextField jFTPlaca = new JFXTextField(vehiculo.getPlaca());
        jFTPlaca.setPromptText("PLACA");
        jFTPlaca.setLabelFloat(true);
        jFTPlaca.setPrefWidth(x);
        jFTPlaca.setEditable(false);
        gridPane.add(jFTPlaca, 0, 1);

        JFXTextField jFTMarca = new JFXTextField(vehiculo.getMarca());
        jFTMarca.setPromptText("MARCA");
        jFTMarca.setLabelFloat(true);
        jFTMarca.setPrefWidth(x);
        jFTMarca.setEditable(false);
        gridPane.add(jFTMarca, 0, 2);

        JFXTextField jFTModelo = new JFXTextField(vehiculo.getModelo());
        jFTModelo.setPromptText("MODELO");
        jFTModelo.setLabelFloat(true);
        jFTModelo.setPrefWidth(x);
        jFTModelo.setEditable(false);
        gridPane.add(jFTModelo, 0, 3);

        JFXTextField jFTAnio = new JFXTextField(vehiculo.getAnio());
        jFTAnio.setPromptText("AÑO");
        jFTAnio.setLabelFloat(true);
        jFTAnio.setPrefWidth(x);
        jFTAnio.setEditable(false);
        gridPane.add(jFTAnio, 0, 4);

        JFXTextField jFTColor = new JFXTextField(vehiculo.getColor());
        jFTColor.setPromptText("COLOR");
        jFTColor.setLabelFloat(true);
        jFTColor.setPrefWidth(x);
        jFTColor.setEditable(false);
        gridPane.add(jFTColor, 0, 5);

        JFXTextField jFTPrecio = new JFXTextField(vehiculo.getPrecio());
        jFTPrecio.setPromptText("PRECIO");
        jFTPrecio.setLabelFloat(true);
        jFTPrecio.setPrefWidth(x);
        jFTPrecio.setEditable(false);
        gridPane.add(jFTPrecio, 0, 6);

        JFXTextField jFTTransmision = new JFXTextField(vehiculo.getTransmision());
        jFTTransmision.setPromptText("TRANSMISIÓN");
        jFTTransmision.setLabelFloat(true);
        jFTTransmision.setPrefWidth(x);
        jFTTransmision.setEditable(false);
        gridPane.add(jFTTransmision, 0, 7);

        JFXButton btnCopiar = new JFXButton("COPIAR");
        btnCopiar.getStyleClass().addAll("customButton", "primaryButton");
        btnCopiar.setButtonType(JFXButton.ButtonType.FLAT);
        btnCopiar.setPrefSize(x, y * 0.04);
        btnCopiar.setOnAction(event -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString("PLACA:         " + vehiculo.getPlaca()
                    + "\nMARCA:         " + vehiculo.getMarca()
                    + "\nMODELO:        " + vehiculo.getModelo()
                    + "\nAÑO:           " + vehiculo.getAnio()
                    + "\nCOLOR:         " + vehiculo.getColor()
                    + "\nPRECIO:        " + vehiculo.getPrecio()
                    + "\nTRANSMISIÓN:   " + vehiculo.getTransmision());
            clipboard.setContent(content);
        });
        gridPane.add(btnCopiar, 0, 8);
        return gridPane;
    }
}
