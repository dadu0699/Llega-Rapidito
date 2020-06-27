package org.jd.estructuras;

import java.util.ArrayList;
import org.jd.modelos.Conductor;
import org.jd.vistas.Alerta;

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
                    if (Long.parseLong(aux.getConductor().getDPI()) > Long.parseLong(ayuda.getConductor().getDPI())) {
                        temporal.setConductor(aux.getConductor());

                        aux.setConductor(ayuda.getConductor());

                        ayuda.setConductor(temporal.getConductor());
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
        } while (aux != primero);

        return conductores;
    }

    public void agregarArchivo(String contenido) {
        String[] conductores = contenido.split(";");
        String[] atributos;
        boolean conductorInsertado;
        if (conductores.length > 1) {
            for (String conductor : conductores) {
                atributos = conductor.split("%");
                conductorInsertado = agregar(new Conductor(atributos[0].trim(),
                        atributos[1].trim(), atributos[2].trim(), atributos[3].trim(),
                        atributos[4].trim(), atributos[5].trim(), atributos[6].trim(),
                        atributos[7].trim()));

                if (!conductorInsertado) {
                    Alerta.getInstancia().mostrarNotificacion("ERROR", "EL CONDUCTOR FUE REGISTRADO PREVIAMENTE");
                } else {
                    Alerta.getInstancia().mostrarNotificacion("CONDUCTOR", "REGISTRO REALIZADO EXITOSAMENTE");
                }
            }
        }
    }

    public String reporteListaCircular() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("digraph G {");
        stringBuilder.append("\n\tgraph [bgcolor=transparent];");
        stringBuilder.append("\n\trankdir = LR;");
        stringBuilder.append("\n\tnode[shape=record, style=filled color=\"#393C4BFF\""
                + " fillcolor=\"#393C4BFF\", fontcolor = \"#F8F8F2FF\"];");
        stringBuilder.append(contenidoGrafica());
        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }

    public String contenidoGrafica() {
        StringBuilder stringBuilder = new StringBuilder();
        NodoListaCircular aux = primero;

        if (!estaVacia()) {
            do {
                stringBuilder.append("\n\tNCON").append(aux.getConductor().getDPI()).append("[label =\"")
                        .append("DPI: ").append(aux.getConductor().getDPI()).append("\\n")
                        .append("NOMBRES Y APELLIDOS: ").append(aux.getConductor().getNombres()).append(" ")
                        .append(aux.getConductor().getApellidos()).append("\\n")
                        .append("GENERO: ").append(aux.getConductor().getGenero()).append("\\n")
                        .append("FECHA NACIMIENTO: ").append(aux.getConductor().getFechaNacimiento()).append("\\n")
                        .append("TELEFONO: ").append(aux.getConductor().getTelefono()).append("\\n")
                        .append("LICENCIA: ").append(aux.getConductor().getLicencia()).append("\\n")
                        .append("DIRECCION: ").append(aux.getConductor().getDireccion()).append("\"];");

                stringBuilder.append("\n\tNCON").append(aux.getConductor().getDPI()).append(" -> NCON")
                        .append(aux.getSiguiente().getConductor().getDPI()).append("[color=\"#E91E63\"];");
                stringBuilder.append("\n\tNCON").append(aux.getConductor().getDPI()).append(" -> NCON")
                        .append(aux.getAnterior().getConductor().getDPI()).append("[color=\"#E91E63\"];");
                aux = aux.getSiguiente();
            } while (aux != primero);
        }

        return stringBuilder.toString();
    }
}
