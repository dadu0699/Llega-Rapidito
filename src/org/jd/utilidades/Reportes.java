package org.jd.utilidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jd.estructuras.ListaDoble;
import org.jd.modelos.Cliente;
import org.jd.modelos.Conductor;
import org.jd.modelos.Reporte;
import org.jd.modelos.Vehiculo;
import org.jd.modelos.Viaje;
import org.jd.vistas.Alerta;

public class Reportes {

    private static Reportes instancia;

    public Reportes() {
    }

    public static Reportes getInstancia() {
        if (instancia == null) {
            instancia = new Reportes();
        }
        return instancia;
    }

    public ArrayList<Reporte> topViajesLargos() {
        ArrayList<Reporte> reporte = new ArrayList<>();
        ArrayList<Viaje> viajes = new ArrayList<>();
        Comparator<Viaje> comparador = (Viaje arg0, Viaje arg1) -> arg0.getRuta().getCantidadDestinos() - arg1.getRuta().getCantidadDestinos();
        StringBuilder contenido = new StringBuilder();

        viajes.addAll(ListaDoble.getInstancia().obtenerDatos());
        viajes.sort(comparador);
        Collections.reverse(viajes);

        contenido.append("REPORTE TOP 10 DE VIAJES MAS LARGOS\n");
        for (int i = 0; i < 10; i++) {
            if (i < viajes.size()) {
                reporte.add(new Reporte(viajes.get(i).getRuta().getCantidadDestinos(), viajes.get(i).toString()));

                contenido.append("CANTIDAD DE DESTINOS: ")
                        .append(viajes.get(i).getRuta().getCantidadDestinos())
                        .append(",\tVIAJE: ").append(viajes.get(i).toString())
                        .append("\n");
            }
        }
        ManejoDeArchivos.getInstancia().escribirArchivo(contenido.toString(), "TOP10VIAJES.edd", "reportes");
        ManejoDeArchivos.getInstancia().escribirArchivo(Huffman.getInstancia()
                .comprimirContenido(contenido.toString(), "Viajes"),
                "top10ViajesCOMPRIMIDO.edd", "reportes");
        Alerta.getInstancia().mostrarNotificacion("TOP", "REPORTE Y ARCHIVO COMPRIMIDO GENERADO");
        return reporte;
    }

    public ArrayList<Reporte> topClientes() {
        ArrayList<Reporte> reporte = new ArrayList<>();
        ArrayList<Viaje> viajes = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        viajes.addAll(ListaDoble.getInstancia().obtenerDatos());

        viajes.forEach((viaje) -> {
            clientes.add(viaje.getCliente());
        });

        Map<Cliente, Long> mapClientes = clientes.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> topClientes = new LinkedHashMap<>();
        mapClientes.entrySet().stream().sorted(Map.Entry.<Cliente, Long>comparingByValue().reversed()).forEachOrdered(e
                -> topClientes.put(e.toString(), e.getValue()));

        StringBuilder contenido = new StringBuilder();
        contenido.append("REPORTE TOP 10 DE CLIENTES CON MAYOR CANTIDAD DE VIAJES\n");
        topClientes.entrySet().forEach((entry) -> {
            int contador = 1;
            reporte.add(new Reporte(entry.getValue().intValue(), entry.getKey().substring(0, entry.getKey().length() - 2)));

            contenido.append("CLIENTE: ").append(entry.getKey().substring(0, entry.getKey().length() - 2))
                    .append(", VIAJES: ").append(entry.getValue()).append("\n");
            contador++;
            if (contador == 10) {
                return;
            }
        });

        ManejoDeArchivos.getInstancia().escribirArchivo(contenido.toString(), "TOP10CLIENTES.edd", "reportes");
        ManejoDeArchivos.getInstancia().escribirArchivo(Huffman.getInstancia()
                .comprimirContenido(contenido.toString(), "Clientes"),
                "top10ClientesCOMPRIMIDO.edd", "reportes");
        Alerta.getInstancia().mostrarNotificacion("TOP", "REPORTE Y ARCHIVO COMPRIMIDO GENERADO");
        return reporte;
    }

    public ArrayList<Reporte> topConductores() {
        ArrayList<Reporte> reporte = new ArrayList<>();
        ArrayList<Viaje> viajes = new ArrayList<>();
        ArrayList<Conductor> conductores = new ArrayList<>();
        viajes.addAll(ListaDoble.getInstancia().obtenerDatos());

        viajes.forEach((viaje) -> {
            conductores.add(viaje.getConductor());
        });

        Map<Conductor, Long> mapConductores = conductores.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> topConductores = new LinkedHashMap<>();
        mapConductores.entrySet().stream().sorted(Map.Entry.<Conductor, Long>comparingByValue().reversed()).forEachOrdered(e
                -> topConductores.put(e.toString(), e.getValue()));

        StringBuilder contenido = new StringBuilder();
        contenido.append("REPORTE TOP 10 DE CONDUCTORES CON MAYOR CANTIDAD DE VIAJES\n");
        topConductores.entrySet().forEach((entry) -> {
            int contador = 1;

            reporte.add(new Reporte(entry.getValue().intValue(), entry.getKey().substring(0, entry.getKey().length() - 2)));

            contenido.append("CONDUCTOR: ").append(entry.getKey().substring(0, entry.getKey().length() - 2))
                    .append(", VIAJES: ").append(entry.getValue()).append("\n");

            contador++;
            if (contador == 10) {
                return;
            }
        });

        ManejoDeArchivos.getInstancia().escribirArchivo(contenido.toString(), "TOP10CONDUCTORES.edd", "reportes");
        ManejoDeArchivos.getInstancia().escribirArchivo(Huffman.getInstancia()
                .comprimirContenido(contenido.toString(), "Conductores"),
                "top10ConductoresCOMPRIMIDO.edd", "reportes");
        Alerta.getInstancia().mostrarNotificacion("TOP", "REPORTE Y ARCHIVO COMPRIMIDO GENERADO");
        return reporte;
    }

    public ArrayList<Reporte> topVehiculos() {
        ArrayList<Reporte> reporte = new ArrayList<>();
        ArrayList<Viaje> viajes = new ArrayList<>();
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        viajes.addAll(ListaDoble.getInstancia().obtenerDatos());

        viajes.forEach((viaje) -> {
            vehiculos.add(viaje.getVehiculo());
        });

        Map<Vehiculo, Long> mapClientes = vehiculos.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> topVehiculos = new LinkedHashMap<>();
        mapClientes.entrySet().stream().sorted(Map.Entry.<Vehiculo, Long>comparingByValue().reversed()).forEachOrdered(e
                -> topVehiculos.put(e.toString(), e.getValue()));

        StringBuilder contenido = new StringBuilder();
        contenido.append("REPORTE TOP 10 DE VEHICULOS CON MAYOR CANTIDAD DE VIAJES\n");
        topVehiculos.entrySet().forEach((entry) -> {
            int contador = 1;

            reporte.add(new Reporte(entry.getValue().intValue(), entry.getKey().substring(0, entry.getKey().length() - 2)));

            contenido.append("VEHICULO: ").append(entry.getKey().substring(0, entry.getKey().length() - 2))
                    .append(", VIAJES: ").append(entry.getValue()).append("\n");

            contador++;
            if (contador == 10) {
                return;
            }
        });

        ManejoDeArchivos.getInstancia().escribirArchivo(contenido.toString(), "TOP10VEHICULOSS.edd", "reportes");
        ManejoDeArchivos.getInstancia().escribirArchivo(Huffman.getInstancia()
                .comprimirContenido(contenido.toString(), "Vehiculos"),
                "top10VehiculosCOMPRIMIDO.edd", "reportes");
        Alerta.getInstancia().mostrarNotificacion("TOP", "REPORTE Y ARCHIVO COMPRIMIDO GENERADO");
        return reporte;
    }

    public void descromprimir(String contenido) {
        contenido = Huffman.getInstancia().descomprimirContenido(contenido);
        ManejoDeArchivos.getInstancia().escribirArchivo(contenido, "descomprimido.edd", "reportes");
        Alerta.getInstancia().mostrarNotificacion("HUFFMAN", "ARCHIVO DESCOMPRIMIDO");
    }
}
