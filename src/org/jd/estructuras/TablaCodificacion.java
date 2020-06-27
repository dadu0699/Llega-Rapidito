package org.jd.estructuras;

import java.util.ArrayList;

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
}
