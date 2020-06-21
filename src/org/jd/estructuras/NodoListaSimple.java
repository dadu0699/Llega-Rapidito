package org.jd.estructuras;

import org.jd.modelos.Cliente;

public class NodoListaSimple {

    private Cliente cliente;
    private NodoListaSimple siguiente;

    public NodoListaSimple(Cliente cliente) {
        this.cliente = cliente;
        this.siguiente = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public NodoListaSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaSimple siguiente) {
        this.siguiente = siguiente;
    }
}
