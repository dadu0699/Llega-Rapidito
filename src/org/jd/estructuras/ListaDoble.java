package org.jd.estructuras;

import org.jd.modelos.Viaje;

public class ListaDoble {

    private static ListaDoble instancia;
    private NodoListaDoble primero;
    private NodoListaDoble ultimo;

    public ListaDoble() {
        primero = null;
        ultimo = null;
    }

    public static ListaDoble getInstancia() {
        if (instancia == null) {
            instancia = new ListaDoble();
        }
        return instancia;
    }

    public NodoListaDoble getPrimero() {
        return primero;
    }

    public void setPrimero(NodoListaDoble primero) {
        this.primero = primero;
    }

    public NodoListaDoble getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoListaDoble ultimo) {
        this.ultimo = ultimo;
    }

    private boolean estaVacia() {
        return primero == null;
    }

    public void agregar(Viaje viaje) {
        NodoListaDoble nuevo = new NodoListaDoble(viaje);
        if (estaVacia()) {
            primero = nuevo;
        } else {
            nuevo.setAnterior(ultimo);
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
    }

    public Viaje buscar(String id) {
        NodoListaDoble aux = primero;
        do {
            if (aux != null) {
                if (aux.getViaje().getId().equalsIgnoreCase(id)) {
                    return aux.getViaje();
                }
                aux = aux.getSiguiente();
            }
        } while (aux != ultimo.getSiguiente());
        return null;
    }

    public void leer() {
        NodoListaDoble aux = primero;
        do {
            if (aux != null) {
                System.out.println(aux.getViaje().getId() + " --> ");
                aux = aux.getSiguiente();
            }
            System.out.println();
        } while (aux != ultimo.getSiguiente());
    }
}
