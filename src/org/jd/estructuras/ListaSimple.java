package org.jd.estructuras;

import org.jd.modelos.Cliente;

public class ListaSimple {

    private NodoListaSimple primero;
    private NodoListaSimple ultimo;

    public ListaSimple() {
        primero = null;
        ultimo = null;
    }

    public NodoListaSimple getPrimero() {
        return primero;
    }

    public NodoListaSimple getUltimo() {
        return ultimo;
    }

    private boolean estaVacia() {
        return primero == null;
    }

    public void agregar(Cliente cliente) {
        NodoListaSimple nuevo = new NodoListaSimple(cliente);
        if (estaVacia()) {
            primero = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
    }

    public void leer() {
        NodoListaSimple auxiliar = primero;
        while (auxiliar != null) {
            System.out.print(auxiliar.getCliente().getDPI() + " --> ");
            auxiliar = auxiliar.getSiguiente();
        }
        System.out.println();
    }

    public Cliente buscar(String dpi) {
        NodoListaSimple auxiliar = primero;
        while (auxiliar != null) {
            if (auxiliar.getCliente().getDPI().equalsIgnoreCase(dpi)) {
                return auxiliar.getCliente();
            }
            auxiliar = auxiliar.getSiguiente();
        }
        return null;
    }

    public boolean eliminar(String dpi) {
        if (!estaVacia()) {
            if (primero.getCliente().getDPI().equalsIgnoreCase(dpi)) {
                if (primero == ultimo) {
                    primero = null;
                    ultimo = null;
                } else {
                    primero = primero.getSiguiente();
                }
                return true;
            } else {
                NodoListaSimple anterior = primero;
                NodoListaSimple auxiliar = primero.getSiguiente();

                while (auxiliar != null) {
                    if (auxiliar.getCliente().getDPI().equalsIgnoreCase(dpi)) {
                        if (auxiliar == ultimo) {
                            ultimo = anterior;
                        }
                        anterior.setSiguiente(auxiliar.getSiguiente());
                        return true;
                    }
                    anterior = anterior.getSiguiente();
                    auxiliar = auxiliar.getSiguiente();
                }
            }
        }
        return false;
    }
}
