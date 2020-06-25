package org.jd.estructuras;

public class NodoCamino {

    private Vertice vertice;
    private NodoCamino siguiente;

    public NodoCamino(Vertice vertice) {
        this.vertice = vertice;
        this.siguiente = null;
    }

    public Vertice getVertice() {
        return vertice;
    }

    public void setVertice(Vertice vertice) {
        this.vertice = vertice;
    }

    public NodoCamino getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCamino siguiente) {
        this.siguiente = siguiente;
    }
}
