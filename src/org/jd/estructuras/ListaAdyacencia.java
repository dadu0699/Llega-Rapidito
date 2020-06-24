package org.jd.estructuras;

import java.util.ArrayList;
import org.jd.modelos.Ruta;
import org.jd.vistas.Alerta;

public class ListaAdyacencia {

    private static ListaAdyacencia instancia;
    private Vertice primero;
    private Vertice ultimo;

    private ListaAdyacencia() {
        primero = null;
        ultimo = null;
    }

    public static ListaAdyacencia getInstancia() {
        if (instancia == null) {
            instancia = new ListaAdyacencia();
        }
        return instancia;
    }

    public Vertice getPrimero() {
        return primero;
    }

    public Vertice getUltimo() {
        return ultimo;
    }

    private boolean estaVacia() {
        return primero == null;
    }

    public Vertice buscar(String lugar) {
        Vertice auxiliar = primero;
        while (auxiliar != null) {
            if (auxiliar.getLugar().equalsIgnoreCase(lugar)) {
                return auxiliar;
            }
            auxiliar = auxiliar.getSiguiente();
        }
        return null;
    }

    public boolean insertar(String lugarOrigen, Integer peso, String lugarDestino) {
        Vertice origen = insertar(lugarOrigen);
        Vertice destino = insertar(lugarDestino);

        if (origen.getAristas().buscar(lugarDestino) == null) {
            origen.getAristas().insertar(new Arista(peso, destino));
            // destino.getAristas().insertar(new Arista(peso, origen));
            return true;
        }
        return false;
    }

    private Vertice insertar(String lugar) {
        Vertice nuevo = buscar(lugar);
        if (nuevo == null) {
            nuevo = new Vertice(lugar);
            if (estaVacia()) {
                primero = nuevo;
            } else {
                ultimo.setSiguiente(nuevo);
            }
            ultimo = nuevo;
        }
        return nuevo;
    }

    public ArrayList<Ruta> obtenerDatos() {
        ArrayList<Ruta> rutas = new ArrayList<>();
        Vertice vertice = primero;
        Arista arista;

        while (vertice != null) {
            arista = vertice.getAristas().getPrimero();
            while (arista != null) {
                rutas.add(new Ruta(vertice.getLugar(), arista.getDestino().getLugar(), String.valueOf(arista.getPeso())));
                arista = arista.getSiguiente();
            }
            vertice = vertice.getSiguiente();
        }
        return rutas;
    }

    public ArrayList<Ruta> buscarRuta(String buscar) {
        ArrayList<Ruta> rutas = new ArrayList<>();
        buscar = buscar.toLowerCase();
        Vertice vertice = primero;
        Arista arista;

        while (vertice != null) {
            arista = vertice.getAristas().getPrimero();
            while (arista != null) {
                if (vertice.getLugar().toLowerCase().contains(buscar)
                        || arista.getDestino().getLugar().toLowerCase().contains(buscar)
                        || String.valueOf(arista.getPeso()).toLowerCase().contains(buscar)) {
                    rutas.add(new Ruta(vertice.getLugar(), arista.getDestino().getLugar(), String.valueOf(arista.getPeso())));
                }
                arista = arista.getSiguiente();
            }
            vertice = vertice.getSiguiente();
        }
        return rutas;
    }

    public void agregarArchivo(String contenido) {
        String[] rutas = contenido.split("%");
        String[] atributos;
        boolean rutaInsertada;
        if (rutas.length > 1) {
            for (String ruta : rutas) {
                atributos = ruta.split("/");
                rutaInsertada = insertar(atributos[0].trim(), Integer.parseInt(atributos[2].trim()),
                        atributos[1].trim());
                if (!rutaInsertada) {
                    Alerta.getInstancia().mostrarNotificacion("ARCHIVO RUTA", "LA RUTA FUE REGISTRADA PREVIAMENTE");
                } else {
                    Alerta.getInstancia().mostrarNotificacion("ARCHIVO RUTA", "REGISTRO REALIZADO EXITOSAMENTE");
                }
            }
        }
    }

    public String contenidoGrafica() {
        StringBuilder stringBuilder = new StringBuilder();
        Vertice vertice = primero;
        Arista arista;

        stringBuilder.append("digraph G {");
        stringBuilder.append("\n\tgraph [bgcolor=transparent];");
        stringBuilder.append("\n\trankdir = LR;");
        stringBuilder.append("\n\tnode[shape=record, style=filled, color=\"#393C4BFF\""
                + " fillcolor=\"#393C4BFF\", fontcolor = \"#F8F8F2FF\"];");

        while (vertice != null) {
            stringBuilder.append("\n\tN").append(vertice.getLugar().replaceAll(" ", "_"))
                    .append("[label =\"").append(vertice.getLugar()).append("\"];");

            arista = vertice.getAristas().getPrimero();
            if (arista != null) {
                stringBuilder.append("\n\tN").append(vertice.getLugar().replaceAll(" ", "_"))
                        .append(arista.getDestino().getLugar().replaceAll(" ", "_"))
                        .append(arista.getPeso()).append("[label =\"")
                        .append(arista.getDestino().getLugar()).append("\\n")
                        .append(arista.getPeso()).append("\"];");

                stringBuilder.append("\n\tN").append(vertice.getLugar().replaceAll(" ", "_")).append(" -> N")
                        .append(vertice.getLugar().replaceAll(" ", "_"))
                        .append(arista.getDestino().getLugar().replaceAll(" ", "_"))
                        .append(arista.getPeso()).append("[color=\"#E91E63\"];");
            }
            while (arista != null) {
                if (arista.getSiguiente() != null) {
                    stringBuilder.append("\n\tN").append(vertice.getLugar().replaceAll(" ", "_"))
                            .append(arista.getSiguiente().getDestino().getLugar().replaceAll(" ", "_"))
                            .append(arista.getSiguiente().getPeso()).append("[label =\"")
                            .append(arista.getSiguiente().getDestino().getLugar()).append("\\n")
                            .append(arista.getSiguiente().getPeso()).append("\"];");

                    stringBuilder.append("\n\tN")
                            .append(vertice.getLugar().replaceAll(" ", "_"))
                            .append(arista.getDestino().getLugar().replaceAll(" ", "_"))
                            .append(arista.getPeso()).append(" -> N")
                            .append(vertice.getLugar().replaceAll(" ", "_"))
                            .append(arista.getSiguiente().getDestino().getLugar().replaceAll(" ", "_"))
                            .append(arista.getSiguiente().getPeso()).append("[color=\"#E91E63\"];");
                }
                arista = arista.getSiguiente();
            }
            vertice = vertice.getSiguiente();
        }

        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }

    private ArrayList<Ruta> obtenerListaGrafo() {
        ArrayList<Ruta> rutas = new ArrayList<>();
        Vertice vertice = primero;
        Arista arista;

        while (vertice != null) {
            arista = vertice.getAristas().getPrimero();
            while (arista != null) {
                if (!buscarRutaEnLista(arista.getDestino().getLugar(), vertice.getLugar(), rutas)) {
                    rutas.add(new Ruta(vertice.getLugar(), arista.getDestino().getLugar(), String.valueOf(arista.getPeso())));
                }
                arista = arista.getSiguiente();
            }
            vertice = vertice.getSiguiente();
        }
        return rutas;
    }

    private boolean buscarRutaEnLista(String origen, String destino, ArrayList<Ruta> rutas) {
        for (Ruta ruta : rutas) {
            if (ruta.getOrigen().equalsIgnoreCase(origen) && ruta.getDestino().equalsIgnoreCase(destino)) {
                return true;
            }
        }
        return false;
    }

    public String contenidoGrafo() {
        StringBuilder stringBuilder = new StringBuilder();
        Vertice vertice = primero;
        Arista arista;

        stringBuilder.append("digraph G {");
        stringBuilder.append("\n\tgraph [bgcolor=transparent];");
        stringBuilder.append("\n\trankdir = LR;");
        stringBuilder.append("\n\tnode[style=filled, color=\"#393C4BFF\""
                + " fillcolor=\"#393C4BFF\", fontcolor = \"#F8F8F2FF\"];");

        while (vertice != null) {
            stringBuilder.append("\n\tN").append(vertice.getLugar().replaceAll(" ", "_"))
                    .append("[label =\"").append(vertice.getLugar()).append("\"];");
            vertice = vertice.getSiguiente();
        }

        vertice = primero;
        while (vertice != null) {
            arista = vertice.getAristas().getPrimero();
            while (arista != null) {
                stringBuilder.append("\n\tN")
                        .append(vertice.getLugar().replaceAll(" ", "_"))
                        .append(" -> N")
                        .append(arista.getDestino().getLugar().replaceAll(" ", "_"))
                        .append("[label=\"")
                        .append(arista.getPeso())
                        .append("\",color=\"#E91E63\", fontcolor = \"#F8F8F2FF\"];");
                arista = arista.getSiguiente();
            }
            vertice = vertice.getSiguiente();
        }

        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }

    public ArrayList<Vertice> obtenerLugares() {
        ArrayList<Vertice> lugares = new ArrayList<>();
        Vertice vertice = primero;
        Arista arista;

        while (vertice != null) {
            lugares.add(vertice);
            vertice = vertice.getSiguiente();
        }
        return lugares;
    }
}
