package org.jd.estructuras;

import java.util.ArrayList;
import org.jd.modelos.Ruta;

public class Camino {

    private NodoCamino primero;
    private NodoCamino ultimo;
    private Integer cantidadDestinos;

    public Camino() {
        primero = null;
        ultimo = null;
        cantidadDestinos = 0;
    }

    public NodoCamino getPrimero() {
        return primero;
    }

    public NodoCamino getUltimo() {
        return ultimo;
    }

    public Integer getCantidadDestinos() {
        return cantidadDestinos - 1;
    }

    private boolean estaVacia() {
        return primero == null;
    }

    public void agregar(Vertice vertice) {
        NodoCamino nuevo = new NodoCamino(vertice);
        if (estaVacia()) {
            ultimo = nuevo;
        } else {
            nuevo.setSiguiente(primero);
        }
        primero = nuevo;
        cantidadDestinos++;
    }

    public void agregarCola(Vertice vertice) {
        NodoCamino nuevo = new NodoCamino(vertice);
        if (estaVacia()) {
            primero = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
    }

    public Vertice eliminarCola() {
        Vertice vertice = null;
        if (!estaVacia()) {
            vertice = primero.getVertice();
            primero = primero.getSiguiente();
            return vertice;
        }
        return vertice;
    }

    @Override
    public String toString() {
        NodoCamino aux = primero;
        String camino = "";
        while (aux != null) {
            camino += aux.getVertice().getLugar();
            if (aux != ultimo) {
                camino += " - ";
            }
            aux = aux.getSiguiente();
        }
        return camino;
    }

    public String reporteListaSimple(String llave) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("digraph G {");
        stringBuilder.append("\n\tgraph [bgcolor=transparent];");
        stringBuilder.append("\n\trankdir = LR;");
        stringBuilder.append("\n\tnode[shape=record, style=filled color=\"#393C4BFF\""
                + " fillcolor=\"#393C4BFF\", fontcolor = \"#F8F8F2FF\"];");
        stringBuilder.append(contenidoGrafica(llave));
        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }

    public String viajesCaminos(String llave) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(contenidoGrafica(llave));
        llave = llave.replaceAll(":", "");
        if (primero != null) {
            stringBuilder.append("\n\tN").append(llave).append(" -> N")
                    .append(llave).append(primero.getVertice().getLugar().replaceAll(" ", "_"))
                    .append("[color=\"#E91E63\"];");
        }
        return stringBuilder.toString();
    }

    private String contenidoGrafica(String llave) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Ruta> rutas = ListaAdyacencia.getInstancia().obtenerDatos();
        Ruta ruta;
        Integer tiempo = 0;
        llave = llave.replaceAll(":", "");
        NodoCamino aux = primero;

        while (aux != null) {
            stringBuilder.append("\n\tN").append(llave)
                    .append(aux.getVertice().getLugar().replaceAll(" ", "_")).append("[label =\"")
                    .append("LUGAR: ").append(aux.getVertice().getLugar()).append("\\n")
                    .append("TIEMPO: ").append(tiempo).append("\"];");

            if (aux.getSiguiente() != null) {
                ruta = ListaAdyacencia.getInstancia().buscarRutaEnLista(
                        aux.getVertice().getLugar(), aux.getSiguiente().getVertice().getLugar(),
                        rutas);
                tiempo += Integer.parseInt(ruta.getTiempoRuta());

                stringBuilder.append("\n\tN").append(llave).append(aux.getVertice().getLugar().replaceAll(" ", "_"))
                        .append(" -> N")
                        .append(llave).append(aux.getSiguiente().getVertice().getLugar().replaceAll(" ", "_"))
                        .append("[color=\"#E91E63\"];");
            }
            aux = aux.getSiguiente();
        }

        return stringBuilder.toString();
    }

    public String contenidoGrafo() {
        return ListaAdyacencia.getInstancia().grafoRuta(primero);
    }
}
