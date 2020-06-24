package org.jd.modelos;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.jd.estructuras.Vertice;
import org.jd.utilidades.Encriptamiento;

public class Viaje {

    private String id; // incriptado con MD5 o SH1
    private Vertice origen;
    private Vertice destino;
    private String fecha;
    private Cliente cliente;
    private Conductor conductor;
    private Vehiculo vehiculo;
    private Ruta ruta;  // ruta mas corta

    public Viaje(Vertice origen, Vertice destino, Cliente cliente, Conductor conductor, Vehiculo vehiculo, Ruta ruta) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = obtenerFechaHora();
        this.cliente = cliente;
        this.conductor = conductor;
        this.vehiculo = vehiculo;
        this.ruta = ruta;
        this.id = encriptarLlave();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vertice getOrigen() {
        return origen;
    }

    public void setOrigen(Vertice origen) {
        this.origen = origen;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    private String obtenerFechaHora() {
        Date fechaActual = new Date();
        SimpleDateFormat darFormato = new SimpleDateFormat("dd/MM/yy HH:mm");
        return darFormato.format(fechaActual);
    }

    private String encriptarLlave() {
        Encriptamiento encriptar = new Encriptamiento();
        return encriptar.encode(vehiculo.getPlaca() + fecha.replaceAll("/", "").replaceAll(" ", ""));
    }

    @Override
    public String toString() {
        return (id + " |  " + origen.toString() + " |  " + destino.toString() + " | " + fecha + " | " + cliente.toString() + " | " + conductor.toString() 
                + " | " + vehiculo.toString());
    }
}
