package org.jd.estructuras;

import java.util.ArrayList;
import org.jd.utilidades.ManejoDeArchivos;

public class TablaCodificacion {

    private static TablaCodificacion instancia;
    private ArrayList<NodoHuffman> tablaCodificacion;

    public TablaCodificacion() {
        tablaCodificacion = new ArrayList<>();
    }

    public static TablaCodificacion getInstancia() {
        if (instancia == null) {
            instancia = new TablaCodificacion();
        }
        return instancia;
    }

    public void limpiarTabla() {
        tablaCodificacion.clear();
    }

    public void agregar(NodoHuffman nodo) {
        tablaCodificacion.add(nodo);
    }

    public void obtenerDatos() {
        tablaCodificacion.forEach((nod) -> {
            System.out.println("DATO: " + nod.getLetra()
                    + ", FRECUENCIA: " + nod.getFrecuencia()
                    + ", CODIGO: " + nod.getCodigo());
        });
    }

    public String getCodigo(String caracter) {
        for (NodoHuffman nodo : tablaCodificacion) {
            if (nodo.getLetra().equals(caracter)) {
                return nodo.getCodigo();
            }
        }
        return "";
    }

    public String getCaracter(String codigo) {
        for (NodoHuffman nodo : tablaCodificacion) {
            if (nodo.getCodigo().equals(codigo)) {
                return nodo.getLetra();
            }
        }
        return "";
    }

    public void generarReporteTabla() {
        StringBuilder contenido = new StringBuilder();
        tablaCodificacion.forEach((nodo) -> {
            contenido.append(nodo.getLetra()).append("%")
                    .append(nodo.getFrecuencia()).append("%")
                    .append(nodo.getCodigo()).append(";");
        });
        ManejoDeArchivos.getInstancia().escribirArchivo(contenido.toString(), "tablaCodificada.edd", "reportes");
    }

    public void agregarArchivo(String contenido) {
        String[] nodosHuffman = contenido.replace(System.getProperty("line.separator"), "").split(";");
        String[] atributos;
        
        tablaCodificacion.clear();
        if (nodosHuffman.length > 1) {
            for (String nodoHuffman : nodosHuffman) {
                atributos = nodoHuffman.split("%");
                agregar(new NodoHuffman(atributos[0], Integer.parseInt(atributos[1]),
                        atributos[2]));
            }
        }
    }
}
