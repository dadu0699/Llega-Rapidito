package org.jd.modelos;

public class Ruta {

    private String origen;
    private String destino;
    private String tiempoRuta;

    public Ruta(String origen, String destino, String tiempoRuta) {
        this.origen = origen;
        this.destino = destino;
        this.tiempoRuta = tiempoRuta;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTiempoRuta() {
        return tiempoRuta;
    }

    public void setTiempoRuta(String tiempoRuta) {
        this.tiempoRuta = tiempoRuta;
    }
}
