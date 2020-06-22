package org.jd.estructuras;

public class Arista {

    private Integer peso;
    private Vertice destino;
    private Arista siguiente;

    public Arista(Integer peso, Vertice destino) {
        this.peso = peso;
        this.destino = destino;
        this.siguiente = null;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public Arista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Arista siguiente) {
        this.siguiente = siguiente;
    }
}
