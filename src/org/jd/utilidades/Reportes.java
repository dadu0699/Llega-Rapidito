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
import org.jd.modelos.Vehiculo;
import org.jd.modelos.Viaje;

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

    public String TopViajesLargos() {
        ArrayList<Viaje> viajes = new ArrayList<>();
        Comparator<Viaje> comparador = (Viaje arg0, Viaje arg1) -> arg0.getRuta().getCantidadDestinos() - arg1.getRuta().getCantidadDestinos();
        StringBuilder contenido = new StringBuilder();

        viajes.addAll(ListaDoble.getInstancia().obtenerDatos());
        viajes.sort(comparador);
        Collections.reverse(viajes);

        contenido.append("REPORTE TOP 10 DE VIAJES MAS LARGOS\n");
        for (int i = 0; i < 10; i++) {
            if (i < viajes.size()) {
                contenido.append("CANTIDAD DE DESTINOS: ")
                        .append(viajes.get(i).getRuta().getCantidadDestinos())
                        .append(",\tVIAJE: ").append(viajes.get(i).toString())
                        .append("\n");
            }
        }

        return contenido.toString();
    }

    public String TopClientes() {
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
            contenido.append("CLIENTE: ").append(entry.getKey().substring(0, entry.getKey().length() - 2))
                    .append(", VIAJES: ").append(entry.getValue()).append("\n");
        });
        return contenido.toString();
    }

    public String TopConductores() {
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
            contenido.append("CONDUCTOR: ").append(entry.getKey().substring(0, entry.getKey().length() - 2))
                    .append(", VIAJES: ").append(entry.getValue()).append("\n");
        });
        return contenido.toString();
    }

    public String TopVehiculos() {
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
            contenido.append("VEHICULO: ").append(entry.getKey().substring(0, entry.getKey().length() - 2))
                    .append(", VIAJES: ").append(entry.getValue()).append("\n");
        });
        return contenido.toString();
    }
}
