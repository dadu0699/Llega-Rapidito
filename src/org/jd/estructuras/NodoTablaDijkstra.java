package org.jd.estructuras;

public class NodoTablaDijkstra {

    private Vertice vertice;
    private Boolean visitado;
    private Integer costo;
    private String camino;
    private NodoTablaDijkstra siguiente;
    private NodoTablaDijkstra anterior;

    public NodoTablaDijkstra(Vertice vertice) {
        this.vertice = vertice;
        this.visitado = false;
        this.costo = null;
        this.camino = "-1";
        this.siguiente = null;
        this.anterior = null;
    }

    public Vertice getVertice() {
        return vertice;
    }

    public void setVertice(Vertice vertice) {
        this.vertice = vertice;
    }

    public Boolean getVisitado() {
        return visitado;
    }

    public void setVisitado(Boolean visitado) {
        this.visitado = visitado;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public String getCamino() {
        return camino;
    }

    public void setCamino(String camino) {
        this.camino = camino;
    }

    public NodoTablaDijkstra getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoTablaDijkstra siguiente) {
        this.siguiente = siguiente;
    }

    public NodoTablaDijkstra getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoTablaDijkstra anterior) {
        this.anterior = anterior;
    }
}
