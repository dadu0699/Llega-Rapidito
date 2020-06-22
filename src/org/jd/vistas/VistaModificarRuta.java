package org.jd.vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jd.modelos.Ruta;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaModificarRuta extends Stage {

    private static VistaModificarRuta instancia;

    private VistaModificarRuta() {
    }

    static VistaModificarRuta getInstancia() {
        if (instancia == null) {
            instancia = new VistaModificarRuta();
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

        Text txtTitulo = new Text("MODIFICAR");
        txtTitulo.getStyleClass().add("textTitle");
        txtTitulo.setFont(new Font(25));
        gridPane.add(txtTitulo, 0, 4, 2, 1);

        JFXTextField jFTOrigen = new JFXTextField(ruta.getOrigen());
        jFTOrigen.setPromptText("ORIGEN");
        jFTOrigen.setLabelFloat(true);
        jFTOrigen.setPrefWidth(x);
        jFTOrigen.setEditable(false);
        gridPane.add(jFTOrigen, 0, 7, 2, 1);

        JFXTextField jFTDestino = new JFXTextField(ruta.getDestino());
        jFTDestino.setPromptText("DESTINO");
        jFTDestino.setLabelFloat(true);
        jFTDestino.setPrefWidth(x);
        jFTDestino.setEditable(false);
        gridPane.add(jFTDestino, 0, 8, 2, 1);

        JFXTextField jFTTiempoRuta = new JFXTextField(ruta.getTiempoRuta());
        jFTTiempoRuta.setPromptText("TIEMPO RUTA");
        jFTTiempoRuta.setLabelFloat(true);
        jFTTiempoRuta.setPrefWidth(x);
        jFTTiempoRuta.setEditable(false);
        gridPane.add(jFTTiempoRuta, 0, 9, 2, 1);

        JFXButton btnModificar = new JFXButton("MODIFICAR");
        btnModificar.getStyleClass().addAll("customButton", "primaryButton");
        btnModificar.setButtonType(JFXButton.ButtonType.FLAT);
        btnModificar.setPrefSize(x, y * 0.04);
        btnModificar.setDisable(true);
        btnModificar.setOnAction(event -> {
            if (jFTOrigen.getText().length() == 0
                    || jFTDestino.getText().length() == 0
                    || jFTTiempoRuta.getText().length() == 0) {
                Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "UNO O MÁS DATOS SON INCORRECTOS");
            } else {
                VistaRuta.getInstancia().actualizarItemsTabla();
            }
        });
        gridPane.add(btnModificar, 0, 10);

        JFXButton btnCancelar = new JFXButton("CANCELAR");
        btnCancelar.getStyleClass().addAll("customButton", "dangerButton");
        btnCancelar.setButtonType(JFXButton.ButtonType.FLAT);
        btnCancelar.setPrefSize(x, y * 0.04);
        btnCancelar.setOnAction(event -> VistaRuta.getInstancia().reiniciarHBox());
        gridPane.add(btnCancelar, 1, 10);

        return gridPane;
    }
}
