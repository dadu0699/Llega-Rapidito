package org.jd.estructuras;

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
                camino += " -> ";
            }
            aux = aux.getSiguiente();
        }
        return camino;
    }
}
