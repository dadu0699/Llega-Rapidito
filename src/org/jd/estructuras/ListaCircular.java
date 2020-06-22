package org.jd.estructuras;

import org.jd.modelos.Conductor;

public class ListaCircular {

    private NodoListaCircular primero;
    private NodoListaCircular ultimo;

    public ListaCircular() {
        primero = null;
        ultimo = null;
    }

    public NodoListaCircular getPrimero() {
        return primero;
    }

    public void setPrimero(NodoListaCircular primero) {
        this.primero = primero;
    }

    public NodoListaCircular getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoListaCircular ultimo) {
        this.ultimo = ultimo;
    }

    private boolean estaVacia() {
        return primero == null;
    }

    public void agregar(Conductor conductor) {
        NodoListaCircular nuevo = new NodoListaCircular(conductor);
        if (estaVacia()) {
            nuevo.setSiguiente(nuevo);
            nuevo.setAnterior(nuevo);
            primero = nuevo;
        } else {
            nuevo.setSiguiente(primero);
            nuevo.setAnterior(ultimo);
            ultimo.setSiguiente(nuevo);
            primero.setAnterior(nuevo);
        }
        ultimo = nuevo;
    }

    public void leer() {
        NodoListaCircular aux = primero;
        do {
            if (aux != null) {
                System.out.println(aux.getConductor().getDPI() + " --> ");
                aux = aux.getSiguiente();
            }
            System.out.println();
        } while (aux != primero);
    }

    public Conductor buscar(String dpi) {
        NodoListaCircular aux = primero;
        do {
            if (aux != null) {
                if (aux.getConductor().getDPI().equalsIgnoreCase(dpi)) {
                    return aux.getConductor();
                }
                aux = aux.getSiguiente();
            }
        } while (aux != primero);
        return null;
    }

    public boolean eliminar(String dpi) {
        if (!estaVacia()) {
            if (primero.getConductor().getDPI().equalsIgnoreCase(dpi)) {
                if (primero == ultimo) {
                    primero = null;
                    ultimo = null;
                } else {
                    NodoListaCircular aux = primero.getSiguiente();
                    aux.setAnterior(ultimo);
                    ultimo.setSiguiente(aux);
                    primero = aux;
                }
                return true;
            } else {
              NodoListaCircular anterior = primero;
              NodoListaCircular aux = primero.getSiguiente();
              
                while (aux != primero) {                    
                    if (aux.getConductor().getDPI().equalsIgnoreCase(dpi)) {
                        if (aux == ultimo) {
                            ultimo = anterior;
                        }
                        anterior.setSiguiente(aux.getSiguiente());
                        aux.getSiguiente().setAnterior(anterior);
                        return true;
                    }
                    anterior = anterior.getSiguiente();
                    aux = aux.getSiguiente();
                }
            }
        }
        return false;
    }

}
