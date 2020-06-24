package org.jd.estructuras;

import org.jd.modelos.Viaje;

public class NodoListaDoble {

    private Viaje viaje;
    private NodoListaDoble siguiente;
    private NodoListaDoble anterior;

    public NodoListaDoble(Viaje viaje) {
        this.viaje = viaje;
        this.siguiente = null;
        this.anterior = null;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public NodoListaDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaDoble siguiente) {
        this.siguiente = siguiente;
    }

    public NodoListaDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoListaDoble anterior) {
        this.anterior = anterior;
    }
}
