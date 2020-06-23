package org.jd.estructuras;

import java.util.ArrayList;
import org.jd.modelos.Conductor;

public class ListaCircular {

    private static ListaCircular instancia;
    private NodoListaCircular primero;
    private NodoListaCircular ultimo;

    private ListaCircular() {
        primero = null;
        ultimo = null;
    }

    public static ListaCircular getInstancia() {
        if (instancia == null) {
            instancia = new ListaCircular();
        }
        return instancia;
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

    public boolean agregar(Conductor conductor) {
        if (buscar(conductor.getDPI()) == null) {
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
            ordenar();
            return true;
        }
        return false;
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

    public void modificar(String dpi, String nombres, String apellidos, String licencia,
            String genero, String fechaNacimiento, String telefono, String direccion) {

        Conductor aux = buscar(dpi);

        if (aux != null) {
            aux.setNombres(nombres);
            aux.setApellidos(apellidos);
            aux.setLicencia(licencia);
            aux.setGenero(genero);
            aux.setFechaNacimiento(fechaNacimiento);
            aux.setTelefono(telefono);
            aux.setDireccion(direccion);
        }
    }

    private void ordenar() {
        if (!estaVacia()) {
            NodoListaCircular aux = primero;
            NodoListaCircular ayuda = null;
            NodoListaCircular temporal = new NodoListaCircular(new Conductor("", "", "", "", "", "", "", ""));

            while (aux.getSiguiente() != primero) {
                ayuda = aux.getSiguiente();
                while (ayuda != primero) {
                    if (Integer.parseInt(aux.getConductor().getDPI()) > Integer.parseInt(ayuda.getConductor().getDPI())) {

                        temporal.getConductor().setDPI(aux.getConductor().getDPI());
                        temporal.getConductor().setNombres(aux.getConductor().getNombres());
                        temporal.getConductor().setApellidos(aux.getConductor().getApellidos());
                        temporal.getConductor().setLicencia(aux.getConductor().getLicencia());
                        temporal.getConductor().setGenero(aux.getConductor().getGenero());
                        temporal.getConductor().setFechaNacimiento(aux.getConductor().getFechaNacimiento());
                        temporal.getConductor().setTelefono(aux.getConductor().getTelefono());
                        temporal.getConductor().setDireccion(aux.getConductor().getDireccion());

                        aux.getConductor().setDPI(ayuda.getConductor().getDPI());
                        aux.getConductor().setNombres(ayuda.getConductor().getNombres());
                        aux.getConductor().setApellidos(ayuda.getConductor().getApellidos());
                        aux.getConductor().setLicencia(ayuda.getConductor().getLicencia());
                        aux.getConductor().setGenero(ayuda.getConductor().getGenero());
                        aux.getConductor().setFechaNacimiento(ayuda.getConductor().getFechaNacimiento());
                        aux.getConductor().setTelefono(ayuda.getConductor().getTelefono());
                        aux.getConductor().setDireccion(ayuda.getConductor().getDireccion());

                        ayuda.getConductor().setDPI(temporal.getConductor().getDPI());
                        ayuda.getConductor().setNombres(temporal.getConductor().getNombres());
                        ayuda.getConductor().setApellidos(temporal.getConductor().getApellidos());
                        ayuda.getConductor().setLicencia(temporal.getConductor().getLicencia());
                        ayuda.getConductor().setGenero(temporal.getConductor().getGenero());
                        ayuda.getConductor().setFechaNacimiento(temporal.getConductor().getFechaNacimiento());
                        ayuda.getConductor().setTelefono(temporal.getConductor().getTelefono());
                        ayuda.getConductor().setDireccion(temporal.getConductor().getDireccion());
                    }
                    ayuda = ayuda.getSiguiente();
                }
                aux = aux.getSiguiente();
            }
        }
    }

    public ArrayList<Conductor> obtenerDatos() {
        ArrayList<Conductor> conductores = new ArrayList<>();
        NodoListaCircular aux = primero;
        do {
            if (aux != null) {
                conductores.add(aux.getConductor());
                aux = aux.getSiguiente();
            }
            System.out.println();
        } while (aux != primero);

        return conductores;
    }

    public ArrayList<Conductor> buscarConductor(String conductor) {
        ArrayList<Conductor> conductores = new ArrayList<>();
        NodoListaCircular aux = primero;
        conductor = conductor.toLowerCase();
        do {
            if (aux != null) {
                if (aux.getConductor().getDPI().toLowerCase().contains(conductor)
                        || aux.getConductor().getNombres().toLowerCase().contains(conductor)
                        || aux.getConductor().getApellidos().toLowerCase().contains(conductor)
                        || aux.getConductor().getTelefono().toLowerCase().contains(conductor)
                        || aux.getConductor().getLicencia().toLowerCase().contains(conductor)
                        || aux.getConductor().getFechaNacimiento().toLowerCase().contains(conductor)
                        || aux.getConductor().getDireccion().toLowerCase().contains(conductor)) {
                    conductores.add(aux.getConductor());
                }
                aux = aux.getSiguiente();
            }
            System.out.println();
        } while (aux != primero);

        return conductores;
    }
}
