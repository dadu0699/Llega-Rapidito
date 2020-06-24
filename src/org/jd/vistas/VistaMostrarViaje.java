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
import org.jd.modelos.Viaje;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaMostrarViaje {

    private static VistaMostrarViaje instancia;

    private VistaMostrarViaje() {
    }

    static VistaMostrarViaje getInstancia() {
        if (instancia == null) {
            instancia = new VistaMostrarViaje();
        }
        return instancia;
    }

    public GridPane getGridPane(Viaje viaje) {
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

        JFXTextField jFTID = new JFXTextField(String.valueOf(viaje.getId()));
        jFTID.setPromptText("ID");
        jFTID.setLabelFloat(true);
        jFTID.setPrefWidth(x);
        jFTID.setEditable(false);
        gridPane.add(jFTID, 0, 5);

        JFXTextField jFTOrigen = new JFXTextField(viaje.getOrigen().toString());
        jFTOrigen.setPromptText("ORIGEN");
        jFTOrigen.setLabelFloat(true);
        jFTOrigen.setPrefWidth(x);
        jFTOrigen.setEditable(false);
        gridPane.add(jFTOrigen, 0, 6);

        JFXTextField jFTDestino = new JFXTextField(viaje.getDestino().toString());
        jFTDestino.setPromptText("DESTINO");
        jFTDestino.setLabelFloat(true);
        jFTDestino.setPrefWidth(x);
        jFTDestino.setEditable(false);
        gridPane.add(jFTDestino, 0, 7);

        JFXTextField jFTFecha = new JFXTextField(viaje.getFecha());
        jFTFecha.setPromptText("FECHA Y HORA");
        jFTFecha.setLabelFloat(true);
        jFTFecha.setPrefWidth(x);
        jFTFecha.setEditable(false);
        gridPane.add(jFTFecha, 0, 8);

        JFXTextField jFTCliente = new JFXTextField(viaje.getCliente().toString());
        jFTCliente.setPromptText("CLIENTE");
        jFTCliente.setLabelFloat(true);
        jFTCliente.setPrefWidth(x);
        jFTCliente.setEditable(false);
        gridPane.add(jFTCliente, 0, 9);

        JFXTextField jFTConductor = new JFXTextField(viaje.getConductor().toString());
        jFTConductor.setPromptText("CONDUCTOR");
        jFTConductor.setLabelFloat(true);
        jFTConductor.setPrefWidth(x);
        jFTConductor.setEditable(false);
        gridPane.add(jFTConductor, 0, 10);

        JFXTextField jFTVehiculo = new JFXTextField(viaje.getVehiculo().toString());
        jFTVehiculo.setPromptText("VEHICULO");
        jFTVehiculo.setLabelFloat(true);
        jFTVehiculo.setPrefWidth(x);
        jFTVehiculo.setEditable(false);
        gridPane.add(jFTVehiculo, 0, 11);

        JFXButton btnCopiar = new JFXButton("COPIAR");
        btnCopiar.getStyleClass().addAll("customButton", "primaryButton");
        btnCopiar.setButtonType(JFXButton.ButtonType.FLAT);
        btnCopiar.setPrefSize(x, y * 0.04);
        btnCopiar.setOnAction(event -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString("ID:                   " + viaje.getId()
                    + "\nORIGEN:               " + viaje.getOrigen().toString()
                    + "\nDESTINO:             " + viaje.getDestino().toString()
                    + "\nFECHA Y HORA:                " + viaje.getFecha()
                    + "\nCLIENTE:              " + viaje.getCliente().toString()
                    + "\nCONDUCTOR:             " + viaje.getConductor().toString()
                    + "\nVEHICULO:             " + viaje.getVehiculo().toString());
            clipboard.setContent(content);
        });
        gridPane.add(btnCopiar, 0, 12);
        return gridPane;
    }
}
