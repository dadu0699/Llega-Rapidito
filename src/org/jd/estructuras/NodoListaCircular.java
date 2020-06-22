package org.jd.estructuras;

import org.jd.modelos.Conductor;

public class NodoListaCircular {

    private Conductor conductor;
    private NodoListaCircular siguiente;
    private NodoListaCircular anterior;

    public NodoListaCircular(Conductor conductor) {
        this.conductor = conductor;
        this.siguiente = null;
        this.anterior = null;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public NodoListaCircular getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaCircular siguiente) {
        this.siguiente = siguiente;
    }

    public NodoListaCircular getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoListaCircular anterior) {
        this.anterior = anterior;
    }
}
