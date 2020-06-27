package org.jd.vistas;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jd.estructuras.ListaAdyacencia;
import org.jd.estructuras.ListaCircular;
import org.jd.estructuras.ListaDoble;
import org.jd.estructuras.NodoListaDoble;
import org.jd.estructuras.TablaHash;
import org.jd.modelos.Viaje;
import org.jd.utilidades.Encriptamiento;
import org.jd.utilidades.ManejoDeArchivos;
import org.jd.utilidades.PropiedadesPantalla;
import org.jd.utilidades.Reportes;

public class VistaReporte extends Stage {

    private static VistaReporte instancia;
    private HBox hBox;
    private GridPane gridPane;
    private ImageView imageView;
    private Encriptamiento encriptar;

    public VistaReporte() {
        encriptar = new Encriptamiento();
    }

    public static VistaReporte getInstancia() {
        if (instancia == null) {
            instancia = new VistaReporte();
        }
        return instancia;
    }

    public HBox getVistaReporte() {
        hBox = new HBox();
        gridPane = new GridPane();
        imageView = new ImageView();

        double x = PropiedadesPantalla.getInstancia().getX();
        double y = PropiedadesPantalla.getInstancia().getY();

        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 10, 20, 20));
        gridPane.setMinWidth(x / 2);
        gridPane.setPrefSize(x, y);
        hBox.setPrefSize(x, y);

        Text txtTitulo = new Text("REPORTES");
        txtTitulo.getStyleClass().add("textTitle");
        txtTitulo.setFont(new Font(25));
        gridPane.add(txtTitulo, 0, 0);

        HBox hBoxBotonesSup = new HBox();
        JFXButton btnTablaClientes = new JFXButton("CLIENTES (TABLA HASH)");
        btnTablaClientes.getStyleClass().addAll("customButton", "primaryButton");
        btnTablaClientes.setButtonType(JFXButton.ButtonType.FLAT);
        btnTablaClientes.setPrefSize(x, y);
        btnTablaClientes.setOnAction(event -> {
            ManejoDeArchivos.getInstancia().escribirArchivo(TablaHash.getInstancia().reporteTablaHash(),
                    "clientes.dot", "reportes");
            ManejoDeArchivos.getInstancia().compilarDOT("clientes", "reportes");

            String ruta = System.getProperty("user.dir") + "\\reportes\\clientes.png";
            File directory = new File(ruta);
            if (directory.exists()) {
                try {
                    Image image = new Image(new FileInputStream(ruta));
                    imageView.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        JFXButton btnVehiculos = new JFXButton("VEHICULOS (ARBOL B)");
        btnVehiculos.getStyleClass().addAll("customButton", "primaryButton");
        btnVehiculos.setButtonType(JFXButton.ButtonType.FLAT);
        btnVehiculos.setPrefSize(x, y);
        btnVehiculos.setOnAction(event -> {
        });

        JFXButton btnConductores = new JFXButton("CONDUCTORES (LISTA CIRCULAR)");
        btnConductores.getStyleClass().addAll("customButton", "warningButton");
        btnConductores.setButtonType(JFXButton.ButtonType.FLAT);
        btnConductores.setPrefSize(x, y);
        btnConductores.setOnAction(event -> {
            ManejoDeArchivos.getInstancia().escribirArchivo(ListaCircular.getInstancia().reporteListaCircular(),
                    "conductores.dot", "reportes");
            ManejoDeArchivos.getInstancia().compilarDOT("conductores", "reportes");

            String ruta = System.getProperty("user.dir") + "\\reportes\\conductores.png";
            File directory = new File(ruta);
            if (directory.exists()) {
                try {
                    Image image = new Image(new FileInputStream(ruta));
                    imageView.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        JFXButton btnViajes = new JFXButton("VIAJES (BLOCKCHAIN)");
        btnViajes.getStyleClass().addAll("customButton", "dangerButton");
        btnViajes.setButtonType(JFXButton.ButtonType.FLAT);
        btnViajes.setPrefSize(x, y);
        btnViajes.setOnAction(event -> {
            ManejoDeArchivos.getInstancia().escribirArchivo(ListaDoble.getInstancia().reporteListaDoble(),
                    "viajes.dot", "reportes");
            ManejoDeArchivos.getInstancia().compilarDOT("viajes", "reportes");

            String ruta = System.getProperty("user.dir") + "\\reportes\\viajes.png";
            File directory = new File(ruta);
            if (directory.exists()) {
                try {
                    Image image = new Image(new FileInputStream(ruta));
                    imageView.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        JFXButton btnRutasListaAdyacencia = new JFXButton("RUTAS (LISTA ADYACENCIA)");
        btnRutasListaAdyacencia.getStyleClass().addAll("customButton", "dangerButton");
        btnRutasListaAdyacencia.setButtonType(JFXButton.ButtonType.FLAT);
        btnRutasListaAdyacencia.setPrefSize(x, y);
        btnRutasListaAdyacencia.setOnAction(event -> {
            ManejoDeArchivos.getInstancia().escribirArchivo(ListaAdyacencia.getInstancia().reporteListaAdyacencia(),
                    "rutasListaAdyacencia.dot", "reportes");
            ManejoDeArchivos.getInstancia().compilarDOT("rutasListaAdyacencia", "reportes");

            String ruta = System.getProperty("user.dir") + "\\reportes\\rutasListaAdyacencia.png";
            File directory = new File(ruta);
            if (directory.exists()) {
                try {
                    Image image = new Image(new FileInputStream(ruta));
                    imageView.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        JFXButton btnRutasGrafo = new JFXButton("RUTAS (GRAFO)");
        btnRutasGrafo.getStyleClass().addAll("customButton", "dangerButton");
        btnRutasGrafo.setButtonType(JFXButton.ButtonType.FLAT);
        btnRutasGrafo.setPrefSize(x, y);
        btnRutasGrafo.setOnAction(event -> {
            ManejoDeArchivos.getInstancia().escribirArchivo(ListaAdyacencia.getInstancia().reporteGrafo(),
                    "rutasGrafo.sfdp", "reportes");
            ManejoDeArchivos.getInstancia().compilarSFDP("rutasGrafo", "reportes");

            String ruta = System.getProperty("user.dir") + "\\reportes\\rutasGrafo.png";
            File directory = new File(ruta);
            if (directory.exists()) {
                try {
                    Image image = new Image(new FileInputStream(ruta));
                    imageView.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        hBoxBotonesSup.getChildren().addAll(btnTablaClientes, btnVehiculos,
                btnConductores, btnViajes, btnRutasListaAdyacencia, btnRutasGrafo);
        hBoxBotonesSup.setPrefSize(x, y / 10);
        hBoxBotonesSup.setMargin(btnTablaClientes, new Insets(0, 5, 0, 0));
        hBoxBotonesSup.setMargin(btnVehiculos, new Insets(0, 5, 0, 0));
        hBoxBotonesSup.setMargin(btnConductores, new Insets(0, 5, 0, 0));
        hBoxBotonesSup.setMargin(btnViajes, new Insets(0, 5, 0, 0));
        hBoxBotonesSup.setMargin(btnRutasListaAdyacencia, new Insets(0, 5, 0, 0));
        gridPane.add(hBoxBotonesSup, 0, 1, 1, 1);

        HBox hBoxBotonesMed = new HBox();
        JFXButton btnEstructura = new JFXButton("ESTRUCTURA COMPLETA");
        btnEstructura.getStyleClass().addAll("customButton", "dangerButton");
        btnEstructura.setButtonType(JFXButton.ButtonType.FLAT);
        btnEstructura.setPrefSize(x, y);
        btnEstructura.setOnAction(event -> {
            StringBuilder contenido = new StringBuilder();

            contenido.append("digraph G {");
            contenido.append("\n\tgraph [bgcolor=transparent];");
            contenido.append("\n\trankdir = LR;");
            contenido.append("\n\tnode[shape=record, style=filled color=\"#393C4BFF\""
                    + " fillcolor=\"#393C4BFF\", fontcolor = \"#F8F8F2FF\"];");
            contenido.append(TablaHash.getInstancia().contenidoGrafica());
            contenido.append(ListaCircular.getInstancia().contenidoGrafica());
            contenido.append(ListaAdyacencia.getInstancia().contenidoGrafica());

            contenido.append(ListaDoble.getInstancia().contenidoGrafica());
            NodoListaDoble aux = ListaDoble.getInstancia().getPrimero();
            String llave;
            while (aux != null) {
                contenido.append(aux.getViaje().getRuta()
                        .viajesCaminos(encriptar.decode(aux.getViaje().getId())));
                aux = aux.getSiguiente();
            }

            contenido.append(ListaAdyacencia.getInstancia().contenidoGrafo());
            contenido.append("\n}");

            ManejoDeArchivos.getInstancia().escribirArchivo(contenido.toString(), "estructuraCompleta.dot", "reportes");
            ManejoDeArchivos.getInstancia().compilarDOT("estructuraCompleta", "reportes");

            String ruta = System.getProperty("user.dir") + "\\reportes\\estructuraCompleta.png";
            File directory = new File(ruta);
            if (directory.exists()) {
                try {
                    Image image = new Image(new FileInputStream(ruta));
                    imageView.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        JFXButton btnListaViajes = new JFXButton("CAMINO CORTO (LISTA SIMPLE)");
        btnListaViajes.getStyleClass().addAll("customButton", "dangerButton");
        btnListaViajes.setButtonType(JFXButton.ButtonType.FLAT);
        btnListaViajes.setPrefSize(x, y);
        btnListaViajes.setOnAction(event -> {
            JFXAlert<String> alerta = new JFXAlert<>((Stage) gridPane.getScene().getWindow());
            alerta.initModality(Modality.APPLICATION_MODAL);
            alerta.setOverlayClose(false);

            GridPane gridAlerta = new GridPane();
            gridAlerta.setVgap(25);
            gridAlerta.setPadding(new Insets(20));

            ObservableList obsViajes = FXCollections.observableArrayList(ListaDoble.getInstancia().obtenerDatos());
            ObservableList<Viaje> viajeLista = obsViajes;
            JFXComboBox<Viaje> cbViaje = new JFXComboBox<>(viajeLista);
            cbViaje.setPromptText("VIAJE");
            cbViaje.setLabelFloat(true);
            cbViaje.setPrefWidth(x);
            gridAlerta.add(cbViaje, 0, 0, 2, 1);

            JFXDialogLayout layout = new JFXDialogLayout();
            layout.setHeading(new Label("VIAJES"));
            layout.setBody(new VBox(gridAlerta));

            JFXButton btnCancelar = new JFXButton("Cerrar");
            btnCancelar.setCancelButton(true);
            btnCancelar.getStyleClass().addAll("customButton", "primaryButton");
            btnCancelar.setButtonType(JFXButton.ButtonType.FLAT);
            btnCancelar.setOnAction(closeEvent -> alerta.hideWithAnimation());

            JFXButton btnAceptar = new JFXButton("Aceptar");
            btnAceptar.getStyleClass().addAll("customButton", "primaryButton");
            btnAceptar.setButtonType(JFXButton.ButtonType.FLAT);
            btnAceptar.setOnAction(eventoAceptar -> {
                if (cbViaje.getSelectionModel().getSelectedItem() == null) {
                    Alerta.getInstancia().mostrarAlerta(gridAlerta, "ERROR", "NO SE SELECCIONO UN VIAJE");
                } else {
                    Viaje viajeSeleccionado = cbViaje.getSelectionModel().getSelectedItem();
                    ManejoDeArchivos.getInstancia().escribirArchivo(viajeSeleccionado.getRuta()
                            .reporteListaSimple(encriptar.decode(viajeSeleccionado.getId())),
                            "caminoListaSimple.dot", "reportes");
                    ManejoDeArchivos.getInstancia().compilarDOT("caminoListaSimple", "reportes");

                    String ruta = System.getProperty("user.dir") + "\\reportes\\caminoListaSimple.png";
                    File directory = new File(ruta);
                    if (directory.exists()) {
                        try {
                            Image image = new Image(new FileInputStream(ruta));
                            imageView.setImage(image);
                            alerta.hideWithAnimation();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            layout.setActions(btnAceptar, btnCancelar);
            alerta.setContent(layout);
            alerta.show();
        });

        JFXButton btnGrafoCamino = new JFXButton("CAMINO GRAFO (LISTA SIMPLE)");
        btnGrafoCamino.getStyleClass().addAll("customButton", "dangerButton");
        btnGrafoCamino.setButtonType(JFXButton.ButtonType.FLAT);
        btnGrafoCamino.setPrefSize(x, y);
        btnGrafoCamino.setOnAction(event -> {
            JFXAlert<String> alerta = new JFXAlert<>((Stage) gridPane.getScene().getWindow());
            alerta.initModality(Modality.APPLICATION_MODAL);
            alerta.setOverlayClose(false);

            GridPane gridAlerta = new GridPane();
            gridAlerta.setVgap(25);
            gridAlerta.setPadding(new Insets(20));

            ObservableList obsViajes = FXCollections.observableArrayList(ListaDoble.getInstancia().obtenerDatos());
            ObservableList<Viaje> viajeLista = obsViajes;
            JFXComboBox<Viaje> cbViaje = new JFXComboBox<>(viajeLista);
            cbViaje.setPromptText("VIAJE");
            cbViaje.setLabelFloat(true);
            cbViaje.setPrefWidth(x);
            gridAlerta.add(cbViaje, 0, 0, 2, 1);

            JFXDialogLayout layout = new JFXDialogLayout();
            layout.setHeading(new Label("VIAJES"));
            layout.setBody(new VBox(gridAlerta));

            JFXButton btnCancelar = new JFXButton("Cerrar");
            btnCancelar.setCancelButton(true);
            btnCancelar.getStyleClass().addAll("customButton", "primaryButton");
            btnCancelar.setButtonType(JFXButton.ButtonType.FLAT);
            btnCancelar.setOnAction(closeEvent -> alerta.hideWithAnimation());

            JFXButton btnAceptar = new JFXButton("Aceptar");
            btnAceptar.getStyleClass().addAll("customButton", "primaryButton");
            btnAceptar.setButtonType(JFXButton.ButtonType.FLAT);
            btnAceptar.setOnAction(eventoAceptar -> {
                if (cbViaje.getSelectionModel().getSelectedItem() == null) {
                    Alerta.getInstancia().mostrarAlerta(gridAlerta, "ERROR", "NO SE SELECCIONO UN VIAJE");
                } else {
                    Viaje viajeSeleccionado = cbViaje.getSelectionModel().getSelectedItem();
                    ManejoDeArchivos.getInstancia().escribirArchivo(viajeSeleccionado.getRuta().contenidoGrafo(),
                            "caminoGrafo.sfdp", "reportes");
                    ManejoDeArchivos.getInstancia().compilarSFDP("caminoGrafo", "reportes");

                    String ruta = System.getProperty("user.dir") + "\\reportes\\caminoGrafo.png";
                    File directory = new File(ruta);
                    if (directory.exists()) {
                        try {
                            Image image = new Image(new FileInputStream(ruta));
                            imageView.setImage(image);
                            alerta.hideWithAnimation();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            layout.setActions(btnAceptar, btnCancelar);
            alerta.setContent(layout);
            alerta.show();
        });

        hBoxBotonesMed.getChildren().addAll(btnEstructura, btnListaViajes, btnGrafoCamino);
        hBoxBotonesMed.setPrefSize(x, y / 10);
        hBoxBotonesMed.setMargin(btnEstructura, new Insets(0, 5, 0, 0));
        hBoxBotonesMed.setMargin(btnListaViajes, new Insets(0, 5, 0, 0));
        gridPane.add(hBoxBotonesMed, 0, 2, 1, 1);

        HBox hBoxBotonesInf = new HBox();
        JFXButton btnTopViajesLg = new JFXButton("TOP 10 VIAJES LARGOS");
        btnTopViajesLg.getStyleClass().addAll("customButton", "dangerButton");
        btnTopViajesLg.setButtonType(JFXButton.ButtonType.FLAT);
        btnTopViajesLg.setPrefSize(x, y);
        btnTopViajesLg.setOnAction(event -> {
            System.out.println(Reportes.getInstancia().TopViajesLargos());
        });

        JFXButton btnTopClientes = new JFXButton("TOP 10 CLIENTES");
        btnTopClientes.getStyleClass().addAll("customButton", "dangerButton");
        btnTopClientes.setButtonType(JFXButton.ButtonType.FLAT);
        btnTopClientes.setPrefSize(x, y);
        btnTopClientes.setOnAction(event -> {
            System.out.println(Reportes.getInstancia().TopClientes());
        });

        JFXButton btnTopConductores = new JFXButton("TOP 10 CONDUCTORES");
        btnTopConductores.getStyleClass().addAll("customButton", "dangerButton");
        btnTopConductores.setButtonType(JFXButton.ButtonType.FLAT);
        btnTopConductores.setPrefSize(x, y);
        btnTopConductores.setOnAction(event -> {
            System.out.println(Reportes.getInstancia().TopConductores());
        });

        JFXButton btnTopVehiculos = new JFXButton("TOP 10 VEHICULOS");
        btnTopVehiculos.getStyleClass().addAll("customButton", "dangerButton");
        btnTopVehiculos.setButtonType(JFXButton.ButtonType.FLAT);
        btnTopVehiculos.setPrefSize(x, y);
        btnTopVehiculos.setOnAction(event -> {
            System.out.println(Reportes.getInstancia().TopVehiculos());
        });

        hBoxBotonesInf.getChildren().addAll(btnTopViajesLg, btnTopClientes,
                btnTopConductores, btnTopVehiculos);
        hBoxBotonesInf.setPrefSize(x, y / 10);
        hBoxBotonesInf.setMargin(btnTopViajesLg, new Insets(0, 5, 0, 0));
        hBoxBotonesInf.setMargin(btnTopClientes, new Insets(0, 5, 0, 0));
        hBoxBotonesInf.setMargin(btnTopConductores, new Insets(0, 5, 0, 0));
        gridPane.add(hBoxBotonesInf, 0, 3, 1, 1);

        imageView.setPreserveRatio(true);
        ScrollPane sp = new ScrollPane();
        sp.setPrefHeight(4 * y / 5);
        sp.setPrefWidth(x);
        sp.setContent(imageView);
        gridPane.add(sp, 0, 4);

        hBox.getChildren().add(gridPane);
        return hBox;
    }
}
