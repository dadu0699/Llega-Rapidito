package org.jd.estructuras;

public class Vertice {

    private String lugar;
    private ListadoAristas aristas;
    private Vertice siguiente;

    public Vertice(String lugar) {
        this.lugar = lugar;
        this.aristas = new ListadoAristas();
        this.siguiente = null;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public ListadoAristas getAristas() {
        return aristas;
    }

    public void setAristas(ListadoAristas aristas) {
        this.aristas = aristas;
    }

    public Vertice getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Vertice siguiente) {
        this.siguiente = siguiente;
    }
}
