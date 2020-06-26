package org.jd.estructuras;

import java.util.ArrayList;
import org.jd.modelos.Vehiculo;
import org.jd.modelos.Viaje;
import org.jd.utilidades.Encriptamiento;

public class ListaDoble {

    private static ListaDoble instancia;
    private NodoListaDoble primero;
    private NodoListaDoble ultimo;
    private Encriptamiento encriptar;

    public ListaDoble() {
        primero = null;
        ultimo = null;
        encriptar = new Encriptamiento();
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
        ordenar();
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

        while (aux != null) {
            System.out.print(encriptar.decode(aux.getViaje().getId()) + " " + aux.getViaje().getId() + " --> ");
            aux = aux.getSiguiente();
        }
    }

    public void ordenar() {
        if (!estaVacia()) {
            NodoListaDoble aux = primero;
            NodoListaDoble ayuda = null;
            Vehiculo tempVehiculo = new Vehiculo("temp", "", "", "", "", "", "");
            NodoListaDoble temporal = new NodoListaDoble(new Viaje(null, null, null, null, tempVehiculo, null));
            String idAux, idAyuda;

            while (aux.getSiguiente() != null) {
                ayuda = aux.getSiguiente();
                while (ayuda != null) {
                    idAux = encriptar.decode(aux.getViaje().getId());
                    idAyuda = encriptar.decode(ayuda.getViaje().getId());
                    if (idAux.compareToIgnoreCase(idAyuda) > 0) {
                        temporal.setViaje(aux.getViaje());

                        aux.setViaje(ayuda.getViaje());

                        ayuda.setViaje(temporal.getViaje());
                    }
                    ayuda = ayuda.getSiguiente();
                }
                aux = aux.getSiguiente();
            }
        }
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
            if ((aux.getViaje().getVehiculo().getPlaca() + aux.getViaje().getFecha()).replaceAll("/", "")
                    .replaceAll(" ", "").toLowerCase().contains(buscar)
                    || aux.getViaje().getId().toLowerCase().contains(buscar)) {
                viajes.add(aux.getViaje());
            }
            aux = aux.getSiguiente();
        }
        return viajes;
    }

    public String contenidoGrafica() {
        StringBuilder stringBuilder = new StringBuilder();
        NodoListaDoble aux = primero;

        stringBuilder.append("digraph G {");
        stringBuilder.append("\n\tgraph [bgcolor=transparent];");
        stringBuilder.append("\n\trankdir = LR;");
        stringBuilder.append("\n\tnode[shape=record, style=filled color=\"#393C4BFF\""
                + " fillcolor=\"#393C4BFF\", fontcolor = \"#F8F8F2FF\"];");

        if (!estaVacia()) {
            do {
                stringBuilder.append("\n\tN").append(encriptar.decode(aux.getViaje().getId()).replaceAll(":", "")).append("[label =\"")
                        .append("LLAVE: ").append(aux.getViaje().getId()).append("\\n")
                        .append("ORIGEN: ").append(aux.getViaje().getOrigen()).append("\\n")
                        .append("DESTINO: ").append(aux.getViaje().getDestino()).append("\\n")
                        .append("FECHA: ").append(aux.getViaje().getFecha()).append("\\n")
                        .append("CLIENTE: ").append(aux.getViaje().getCliente().toString()).append("\\n")
                        .append("CONDUCTOR: ").append(aux.getViaje().getConductor().toString()).append("\\n")
                        .append("VEHICULO: ").append(aux.getViaje().getVehiculo().toString()).append("\\n")
                        .append("RUTA: ").append(aux.getViaje().getRuta().toString()).append("\"];");

                if (aux != ultimo) {
                    stringBuilder.append("\n\tN").append(encriptar.decode(aux.getViaje().getId()).replaceAll(":", "")).append(" -> N")
                            .append(encriptar.decode(aux.getSiguiente().getViaje().getId()).replaceAll(":", "")).append("[color=\"#E91E63\"];");
                }
                if (aux != primero) {
                    stringBuilder.append("\n\tN").append(encriptar.decode(aux.getViaje().getId()).replaceAll(":", "")).append(" -> N")
                            .append(encriptar.decode(aux.getAnterior().getViaje().getId()).replaceAll(":", "")).append("[color=\"#E91E63\"];");
                }

                aux = aux.getSiguiente();
            } while (aux != ultimo.getSiguiente());
        }

        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }
}
