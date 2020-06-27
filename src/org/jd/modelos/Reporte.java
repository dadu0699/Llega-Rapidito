package org.jd.modelos;

public class Reporte {

    private Integer cantidad;
    private String informacion;

    public Reporte(Integer cantidad, String informacion) {
        this.cantidad = cantidad;
        this.informacion = informacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
}
