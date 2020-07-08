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
import org.jd.modelos.Ruta;
import org.jd.utilidades.PropiedadesPantalla;

class VistaMostrarRuta extends Stage {

    private static VistaMostrarRuta instancia;

    private VistaMostrarRuta() {
    }

    static VistaMostrarRuta getInstancia() {
        if (instancia == null) {
            instancia = new VistaMostrarRuta();
        }
        return instancia;
    }

    public GridPane getGridPane(Ruta ruta) {
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

        JFXTextField jFTOrigen = new JFXTextField();
        jFTOrigen.setPromptText("ORIGEN");
        jFTOrigen.setLabelFloat(true);
        jFTOrigen.setPrefWidth(x);
        jFTOrigen.setEditable(false);
        gridPane.add(jFTOrigen, 0, 1);

        JFXTextField jFTDestino = new JFXTextField();
        jFTDestino.setPromptText("DESTINO");
        jFTDestino.setLabelFloat(true);
        jFTDestino.setPrefWidth(x);
        jFTDestino.setEditable(false);
        gridPane.add(jFTDestino, 0, 2);

        JFXTextField jFTTiempoRuta = new JFXTextField();
        jFTTiempoRuta.setPromptText("TIEMPO RUTA");
        jFTTiempoRuta.setLabelFloat(true);
        jFTTiempoRuta.setPrefWidth(x);
        jFTTiempoRuta.setEditable(false);
        gridPane.add(jFTTiempoRuta, 0, 3);

        if (ruta != null) {
            jFTOrigen.setText(ruta.getOrigen());
            jFTDestino.setText(ruta.getDestino());
            jFTTiempoRuta.setText(ruta.getTiempoRuta());
        }

        JFXButton btnCopiar = new JFXButton("COPIAR");
        btnCopiar.getStyleClass().addAll("customButton", "primaryButton");
        btnCopiar.setButtonType(JFXButton.ButtonType.FLAT);
        btnCopiar.setPrefSize(x, y * 0.04);
        btnCopiar.setOnAction(event -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString("ORIGEN::        " + ruta.getOrigen()
                    + "\nDESTINO:       " + ruta.getDestino()
                    + "\nTIEMPO RUTA:   " + ruta.getTiempoRuta());
            clipboard.setContent(content);
        });
        gridPane.add(btnCopiar, 0, 4);
        return gridPane;
    }
}
