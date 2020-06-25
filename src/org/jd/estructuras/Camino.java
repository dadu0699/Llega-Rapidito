package org.jd.estructuras;

import java.util.ArrayList;
import org.jd.modelos.Ruta;

public class Camino {

    private NodoCamino primero;
    private NodoCamino ultimo;

    public Camino() {
        primero = null;
        ultimo = null;
    }

    public NodoCamino getPrimero() {
        return primero;
    }

    public NodoCamino getUltimo() {
        return ultimo;
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

    public String contenidoGrafica(String llave) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Ruta> rutas = ListaAdyacencia.getInstancia().obtenerDatos();
        Ruta ruta;
        Integer tiempo = 0;
        llave = llave.replaceAll(":", "");
        NodoCamino aux = primero;

        stringBuilder.append("digraph G {");
        stringBuilder.append("\n\tgraph [bgcolor=transparent];");
        stringBuilder.append("\n\trankdir = LR;");
        stringBuilder.append("\n\tnode[shape=record, style=filled color=\"#393C4BFF\""
                + " fillcolor=\"#393C4BFF\", fontcolor = \"#F8F8F2FF\"];");

        while (aux != null) {
            stringBuilder.append("\n\tN").append(llave).append(aux.getVertice().getLugar()).append("[label =\"")
                    .append("LUGAR: ").append(aux.getVertice().getLugar()).append("\\n")
                    .append("TIEMPO: ").append(tiempo).append("\"];");

            if (aux.getSiguiente() != null) {
                ruta = ListaAdyacencia.getInstancia().buscarRutaEnLista(
                        aux.getVertice().getLugar(), aux.getSiguiente().getVertice().getLugar(),
                        rutas);
                tiempo += Integer.parseInt(ruta.getTiempoRuta());
                
                stringBuilder.append("\n\tN").append(llave).append(aux.getVertice().getLugar())
                        .append(" -> N")
                        .append(llave).append(aux.getSiguiente().getVertice().getLugar())
                        .append("[color=\"#E91E63\"];");
            }
            aux = aux.getSiguiente();
        }

        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }
}
