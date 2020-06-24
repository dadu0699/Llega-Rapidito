package org.jd.estructuras;

import java.util.ArrayList;
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
        System.out.println(nuevo.getViaje().toString());
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

    public ArrayList<Viaje> obtenerDatos() {
        ArrayList<Viaje> viajes = new ArrayList<>();
        NodoListaDoble aux = primero;
        while (aux != null) {
            viajes.add(aux.getViaje());
            aux = aux.getSiguiente();
        }
        return viajes;
    }

    public ArrayList<Viaje> buscarViaje(String buscar) {
        ArrayList<Viaje> viajes = new ArrayList<>();
        NodoListaDoble aux = primero;
        buscar = buscar.toLowerCase();

        while (aux != null) {
            if ((aux.getViaje().getVehiculo().getPlaca() + aux.getViaje().getFecha()).replaceAll("/", "").replaceAll(" ", "").toLowerCase().contains(buscar)
                    || aux.getViaje().getId().toLowerCase().contains(buscar)) {
                viajes.add(aux.getViaje());
            }
            aux = aux.getSiguiente();
        }
        return viajes;
    }
}
