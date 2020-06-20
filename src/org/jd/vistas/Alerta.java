package org.jd.vistas;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import static javafx.geometry.Pos.TOP_RIGHT;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.LlegaRapidito;
import org.controlsfx.control.Notifications;

public class Alerta extends Stage {

    private static Alerta instancia;

    private Alerta() {
    }

    public static Alerta getInstancia() {
        if (instancia == null) {
            instancia = new Alerta();
        }
        return instancia;
    }

    public void mostrarAlerta(GridPane gridPane, String title, String description) {
        JFXAlert<String> alert = new JFXAlert<>((Stage) gridPane.getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label(title));
        layout.setBody(new Label(description));

        JFXButton cancelButton = new JFXButton("Close");
        cancelButton.setCancelButton(true);
        cancelButton.getStyleClass().addAll("customButton", "primaryButton");
        cancelButton.setButtonType(JFXButton.ButtonType.FLAT);
        cancelButton.setOnAction(closeEvent -> alert.hideWithAnimation());

        layout.setActions(cancelButton);
        alert.setContent(layout);
        alert.show();
    }

    public void mostrarNotificacion(String title, String description) {
        Notifications.create().title(title).text(description).hideAfter(Duration.millis(2500)).position(TOP_RIGHT)
                .owner(LlegaRapidito.getStage()).show();
    }
}
