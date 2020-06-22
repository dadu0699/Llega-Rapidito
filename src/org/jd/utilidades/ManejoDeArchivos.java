package org.jd.utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javafx.stage.FileChooser;

public class ManejoDeArchivos {

    private static ManejoDeArchivos instancia;
    private final String directorio;
    private File fileControl;

    private ManejoDeArchivos() {
        directorio = System.getProperty("user.dir");
    }

    public static ManejoDeArchivos getInstancia() {
        if (instancia == null) {
            instancia = new ManejoDeArchivos();
        }
        return instancia;
    }

    private void crearCarpeta(String nombre) {
        File directorio = new File(this.directorio + "/" + nombre);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
    }

    public void subirArchivo(String description, String extension) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new java.io.File(directorio));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(description, extension));

        fileControl = fileChooser.showOpenDialog(null);
        if (fileControl != null) {
            System.out.println(fileControl.getAbsolutePath());
        }
    }

    public String leerArchivo() {
        String contenidoArchivo = "";
        String linea;
        try {
            if (fileControl != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(fileControl), StandardCharsets.UTF_8));
                while ((linea = bufferedReader.readLine()) != null) {
                    // System.out.println(linea);
                    contenidoArchivo += linea;
                }
                return contenidoArchivo;
            }
        } catch (IOException ex) {
            System.out.println("El archivo no se encontró");
        }
        return contenidoArchivo;
    }

    public void escribirArchivo(String contenido, String nombre, String ruta) {
        crearCarpeta(ruta);
        File file = null;

        try {
            file = new File(directorio + "/" + ruta + "/" + nombre);
            FileWriter fileWriter = new FileWriter(file, false);
            PrintWriter printWriter = new PrintWriter(fileWriter, true);
            printWriter.println(contenido);
            printWriter.close();
        } catch (IOException ignored) {
            System.out.println(ignored);
        }
    }

    public void compilarDOT(String nombre, String ruta) {
        String comandoDOT = "dot.exe -Tpng " + nombre + ".dot -o " + nombre + ".png";

        try {
            Process process = Runtime.getRuntime().exec("cmd /c " + comandoDOT, null, new File(directorio + "/" + ruta));

            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String linea;
            while ((linea = reader.readLine()) != null) {
                output.append(linea).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                // System.out.println("Success!");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void compilarSFDP(String nombre, String ruta) {
        String comandoDOT = "sfdp.exe -Tpng " + nombre + ".sfdp -o " + nombre + ".png";

        try {
            Process process = Runtime.getRuntime().exec("cmd /c " + comandoDOT, null, new File(directorio + "/" + ruta));

            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String linea;
            while ((linea = reader.readLine()) != null) {
                output.append(linea).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                // System.out.println("Success!");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
