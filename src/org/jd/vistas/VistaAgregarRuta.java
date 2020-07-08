package org.jd.vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jd.estructuras.ListaAdyacencia;
import org.jd.utilidades.PropiedadesPantalla;

public class VistaAgregarRuta extends Stage {

    private static VistaAgregarRuta instancia;

    private VistaAgregarRuta() {
    }

    public static VistaAgregarRuta getInstancia() {
        if (instancia == null) {
            instancia = new VistaAgregarRuta();
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

        JFXTextField jFTOrigen = new JFXTextField();
        jFTOrigen.setPromptText("ORIGEN");
        jFTOrigen.setLabelFloat(true);
        jFTOrigen.setPrefWidth(x);
        gridPane.add(jFTOrigen, 0, 1);

        JFXTextField jFTDestino = new JFXTextField();
        jFTDestino.setPromptText("DESTINO");
        jFTDestino.setLabelFloat(true);
        jFTDestino.setPrefWidth(x);
        gridPane.add(jFTDestino, 0, 2);

        JFXTextField jFTTiempoRuta = new JFXTextField();
        jFTTiempoRuta.setPromptText("TIEMPO RUTA");
        jFTTiempoRuta.setLabelFloat(true);
        jFTTiempoRuta.setPrefWidth(x);
        gridPane.add(jFTTiempoRuta, 0, 3);

        JFXButton btnAgregar = new JFXButton("AGREGAR");
        btnAgregar.getStyleClass().addAll("customButton", "primaryButton");
        btnAgregar.setButtonType(JFXButton.ButtonType.FLAT);
        btnAgregar.setPrefSize(x, y * 0.04);
        btnAgregar.setDisable(true);
        btnAgregar.setOnAction(event -> {
            if (jFTOrigen.getText().length() == 0
                    || jFTDestino.getText().length() == 0
                    || jFTTiempoRuta.getText().length() == 0) {
                Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "UNO O MÁS DATOS SON INCORRECTOS");
            } else {
                boolean ruta = ListaAdyacencia.getInstancia().insertar(jFTOrigen.getText(),
                        Integer.parseInt(jFTTiempoRuta.getText()), jFTDestino.getText());
                if (!ruta) {
                    Alerta.getInstancia().mostrarAlerta(gridPane, "ERROR", "LA RUTA FUE REGISTRADA PREVIAMENTE");
                } else {
                    // VistaRuta.getInstancia().actualizarItemsTabla();
                    VistaRuta.getInstancia().reiniciarHBox();
                    Alerta.getInstancia().mostrarNotificacion("RUTA", "REGISTRO REALIZADO EXITOSAMENTE");
                }
            }
        });
        gridPane.add(btnAgregar, 0, 4);
        return gridPane;
    }
}
