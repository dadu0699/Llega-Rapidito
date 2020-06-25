package org.jd.estructuras;

public class TablaDijkstra {

    private static TablaDijkstra instancia;
    private NodoTablaDijkstra primero;
    private NodoTablaDijkstra ultimo;

    public TablaDijkstra() {
        primero = null;
        ultimo = null;
    }

    public static TablaDijkstra getInstancia() {
        if (instancia == null) {
            instancia = new TablaDijkstra();
        }
        return instancia;
    }

    public NodoTablaDijkstra getPrimero() {
        return primero;
    }

    public void setPrimero(NodoTablaDijkstra primero) {
        this.primero = primero;
    }

    public NodoTablaDijkstra getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoTablaDijkstra ultimo) {
        this.ultimo = ultimo;
    }

    private void llenarTabla() {
        Vertice vertice = ListaAdyacencia.getInstancia().getPrimero();
        while (vertice != null) {
            agregar(vertice);
            vertice = vertice.getSiguiente();
        }
    }

    private void agregar(Vertice vertice) {
        NodoTablaDijkstra nuevo = new NodoTablaDijkstra(vertice);

        if (primero == null) {
            primero = nuevo;
        } else {
            nuevo.setAnterior(ultimo);
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
    }

    private NodoTablaDijkstra buscarNodo(Vertice vertice) {
        NodoTablaDijkstra auxiliar = primero;
        while (auxiliar != null && auxiliar.getVertice() != vertice) {
            auxiliar = auxiliar.getSiguiente();
        }
        return auxiliar;
    }

    public void buscarRuta(Vertice origen, Vertice destino) {
        primero = null;
        ultimo = null;
        recorridos(origen);

        NodoTablaDijkstra aux = buscarNodo(destino);
        while (aux != null) {            
            System.out.print(aux.getVertice().getLugar() + " | ");
            aux = buscarNodo(aux.getCamino());
        }
        System.out.println();

        imprimirTabla();
    }

    public void imprimirTabla() {
        NodoTablaDijkstra aux = primero;
        System.out.println("----Tabla Dijkstra");
        while (aux != null) {
            System.out.println(aux.getVertice().getLugar() + " | " + aux.getVisitado() + " | " + aux.getCosto() + " | " + aux.getCamino());
            aux = aux.getSiguiente();
        }
    }

    private void recorridos(Vertice origen) {
        llenarTabla();
        NodoTablaDijkstra auxDijkstra = buscarNodo(origen);

        if (auxDijkstra != null) {
            auxDijkstra.setCosto(0);
            visitar(auxDijkstra);
        }
    }

    private void visitar(NodoTablaDijkstra auxDijkstra) {
        if (auxDijkstra != null && !auxDijkstra.getVisitado()) {
            auxDijkstra.setVisitado(true);
            // System.out.println("VISITAR: " + auxDijkstra.getVertice().getLugar() + " | " + auxDijkstra.getVisitado() + " | " + auxDijkstra.getCosto() + " | " + auxDijkstra.getCamino());

            Vertice vertice = auxDijkstra.getVertice();
            Arista arista = vertice.getAristas().getPrimero();
            while (arista != null) {
                costoCamino(arista.getDestino(), auxDijkstra.getCosto() + arista.getPeso(), vertice);
                arista = arista.getSiguiente();
            }

            arista = vertice.getAristas().getPrimero();
            while (arista != null) {
                visitar(buscarNodo(arista.getDestino()));
                arista = arista.getSiguiente();
            }
        }
    }

    private void costoCamino(Vertice vertice, Integer nuevoCosto, Vertice camino) {
        NodoTablaDijkstra auxDijkstra = buscarNodo(vertice);

        if (auxDijkstra != null) {
            // System.out.println("-- COSTO: " + auxDijkstra.getVertice().getLugar() + " | " + auxDijkstra.getVisitado() + " | " + auxDijkstra.getCosto() + " | " + auxDijkstra.getCamino());
            // if (!auxDijkstra.getVisitado()) {
            if (auxDijkstra.getCosto() == null || auxDijkstra.getCosto() > nuevoCosto) {
                auxDijkstra.setCosto(nuevoCosto);
                auxDijkstra.setCamino(camino);
                // System.out.println("--- COSTO AC: " + auxDijkstra.getVertice().getLugar() + " | " + auxDijkstra.getVisitado() + " | " + auxDijkstra.getCosto() + " | " + auxDijkstra.getCamino());
            }
            // }
        }
    }
}
