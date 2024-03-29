package org.jd.estructuras;

import java.util.ArrayList;
import org.jd.modelos.Vehiculo;
import org.jd.vistas.Alerta;

public class ArbolB {

    private static ArbolB instancia;
    private NodoArbolB raiz;

    private ArbolB() {
        raiz = null;
    }

    public static ArbolB getInstancia() {
        if (instancia == null) {
            instancia = new ArbolB();
        }
        return instancia;
    }

    public Vehiculo buscar(String placa) {
        return buscar(this.raiz, placa);
    }

    private Vehiculo buscar(NodoArbolB nodo, String placa) {
        Vehiculo vehiculo = null;
        if (nodo != null) {
            for (int i = 0; i < nodo.getCantidadClaves(); i++) {
                if (nodo.getVehiculoClave(i).getPlaca().equalsIgnoreCase(placa)) {
                    return nodo.getVehiculoClave(i);
                }
            }

            if (nodo.getPaginas() != null) {
                for (int i = 0; i < nodo.getCantidadPaginas(); i++) {
                    vehiculo = buscar(nodo.getPagina(i), placa);
                    if (vehiculo != null) {
                        return vehiculo;
                    }
                }
            }
        }
        return vehiculo;
    }

    public boolean insertar(Vehiculo vehiculo) {
        if (raiz == null) {
            raiz = new NodoArbolB();
            raiz.setVehiculoClave(vehiculo);
            return true;
        } else if (buscar(vehiculo.getPlaca()) == null) {
            return insertar(raiz, vehiculo);
        }
        return false;
    }

    private boolean insertar(NodoArbolB nodo, Vehiculo vehiculo) {
        if (nodo != null) {
            if (nodo.getCantidadPaginas() == 0) {
                nodo.setVehiculoClave(vehiculo);
                if (nodo.getCantidadClaves() > 4) {
                    dividirNodo(nodo);
                }
                return true;
            }

            if (vehiculo.getPlaca().compareTo(nodo.getVehiculoClave(0).getPlaca()) < 0) {
                return insertar(nodo.getPagina(0), vehiculo);
            } else if (vehiculo.getPlaca().compareTo(nodo.getVehiculoClave(
                    nodo.getCantidadClaves() - 1).getPlaca()) > 0) {
                return insertar(nodo.getPagina(nodo.getCantidadClaves()), vehiculo);
            }

            for (int i = 1; i < nodo.getCantidadClaves(); i++) {
                if (vehiculo.getPlaca().compareTo(nodo.getVehiculoClave(i - 1).getPlaca()) > 0
                        && vehiculo.getPlaca().compareTo(nodo.getVehiculoClave(i).getPlaca()) < 0) {
                    return insertar(nodo.getPagina(i), vehiculo);
                }
            }
        }
        return false;
    }

    private void dividirNodo(NodoArbolB nodo) {
        NodoArbolB paginaIzquierda = obtenerPaginasIzquierdas(nodo); // Pagina donde se almacenaran los nodos izquierdos
        NodoArbolB paginaDerecha = obtenerPaginasDerechas(nodo); // Pagina donde se almacenaran los nodos derechos

        if (nodo.getPadre() == null) {
            nuevaRaiz(nodo, nodo.getVehiculoClave(2), paginaIzquierda, paginaDerecha);
        } else {
            subirPadre(nodo, nodo.getVehiculoClave(2), paginaIzquierda, paginaDerecha);
        }
    }

    private NodoArbolB obtenerPaginasIzquierdas(NodoArbolB nodo) {
        NodoArbolB paginaIzquierda = new NodoArbolB();
        if (nodo.getCantidadPaginas() > 0) {
            for (int i = 0; i <= 2; i++) {
                paginaIzquierda.setPagina(nodo.getPagina(i));
            }
        }
        for (int i = 0; i < 2; i++) {
            paginaIzquierda.setVehiculoClave(nodo.getVehiculoClave(i));
        }

        return paginaIzquierda;
    }

    private NodoArbolB obtenerPaginasDerechas(NodoArbolB nodo) {
        NodoArbolB paginaDerecha = new NodoArbolB();
        if (nodo.getCantidadPaginas() > 0) {
            for (int i = 3; i < nodo.getCantidadPaginas(); i++) {
                paginaDerecha.setPagina(nodo.getPagina(i));
            }
        }
        for (int i = 3; i < nodo.getCantidadClaves(); i++) {
            paginaDerecha.setVehiculoClave(nodo.getVehiculoClave(i));
        }
        return paginaDerecha;
    }

    private void nuevaRaiz(NodoArbolB nodoAuxiliar, Vehiculo vehiculo,
            NodoArbolB paginaIzquierda, NodoArbolB paginaDerecha) {
        NodoArbolB nodoTemporal = new NodoArbolB(); // Nodo nuevo para ser la nueva raiz
        nodoTemporal.setVehiculoClave(vehiculo); // Se agrega la clave central
        nodoAuxiliar.setPadre(nodoTemporal);

        this.raiz = nodoTemporal;
        this.raiz.setPagina(paginaIzquierda);
        this.raiz.setPagina(paginaDerecha);
    }

    private void subirPadre(NodoArbolB nodoAuxiliar, Vehiculo vehiculo,
            NodoArbolB paginaIzquierda, NodoArbolB paginaDerecha) {
        NodoArbolB nodoTemporal = nodoAuxiliar.getPadre();
        nodoTemporal.setVehiculoClave(vehiculo);
        nodoTemporal.quitarPagina(nodoAuxiliar);
        nodoTemporal.setPagina(paginaIzquierda);
        nodoTemporal.setPagina(paginaDerecha);

        if (nodoTemporal.getCantidadClaves() > 4) {
            dividirNodo(nodoTemporal);
        }
    }

    public void eliminar(String placa) {
        eliminar(obtenerPagina(placa), placa);
    }

    private NodoArbolB eliminar(NodoArbolB nodo, String placa) {
        if (nodo != null) {
            if (nodo.getCantidadPaginas() == 0) {
                if (nodo.getPadre() == null || nodo.getCantidadClaves() > 2) {
                    nodo.quitarNodo(placa);
                    if (nodo.getCantidadClaves() <= 0) {
                        this.raiz = null;
                    }
                } else {
                    combinarRotar(nodo, placa);
                }
            } else {
                NodoArbolB paginaMayorDeMenores = paginaMayorDeMenores(nodo.getPagina(obtenerPosicionNodo(nodo, placa)));
                if (paginaMayorDeMenores.getCantidadClaves() > 2) {
                    Vehiculo vMayor = paginaMayorDeMenores.getVehiculoClave(paginaMayorDeMenores.getCantidadClaves() - 1);
                    eliminar(paginaMayorDeMenores, vMayor.getPlaca());
                    nodo.setVehiculoClave(vMayor);
                    nodo.quitarNodo(placa);
                }
            }
        }
        return null;
    }

    private void combinarRotar(NodoArbolB nodo, String placa) {
        NodoArbolB padre = nodo.getPadre();
        NodoArbolB paginaIzquierda = null;
        NodoArbolB paginaDerecha = null;
        Integer posicionNodo = obtenerPosicionPagina(padre, nodo);
        Integer tamanioPaginaIzquierda = -1;
        Integer tamanioPaginaDerecha = -1;

        if ((posicionNodo - 1) >= 0) { // Verificar que exista una pagina del lado izquierdo
            paginaIzquierda = padre.getPagina(posicionNodo - 1);
            tamanioPaginaIzquierda = paginaIzquierda.getCantidadClaves();
        }

        if ((posicionNodo + 1) < padre.getCantidadPaginas()) { // Verificar que exista pagina derecha
            paginaDerecha = padre.getPagina(posicionNodo + 1);
            tamanioPaginaDerecha = paginaDerecha.getCantidadClaves();
        }

        if (paginaIzquierda != null && tamanioPaginaIzquierda > 2) {
            Vehiculo vIzquierdo = paginaIzquierda.getVehiculoClave(tamanioPaginaIzquierda - 1);
            eliminar(paginaIzquierda, vIzquierdo.getPlaca());
            padre.setVehiculoClave(vIzquierdo);

            Vehiculo vPadre = padre.getVehiculoClave(obtenerPosicionNodo(padre, vIzquierdo.getPlaca()) + 1);
            padre.quitarNodo(vPadre.getPlaca());
            nodo.setVehiculoClave(vPadre);

            nodo.quitarNodo(placa);
        } else if (paginaDerecha != null && tamanioPaginaDerecha > 2) {
            Vehiculo vDerecho = paginaDerecha.getVehiculoClave(0);
            eliminar(paginaDerecha, vDerecho.getPlaca());
            padre.setVehiculoClave(vDerecho);

            Vehiculo vPadre = padre.getVehiculoClave(obtenerPosicionNodo(padre, vDerecho.getPlaca()) - 1);
            padre.quitarNodo(vPadre.getPlaca());
            nodo.setVehiculoClave(vPadre);

            nodo.quitarNodo(placa);
        }
    }

    private NodoArbolB paginaMayorDeMenores(NodoArbolB nodo) {
        while (nodo.getCantidadPaginas() > 0) {
            nodo = nodo.getPagina(nodo.getCantidadPaginas() - 1);
        }
        return nodo;
    }

    private NodoArbolB obtenerPagina(String placa) {
        return obtenerPagina(this.raiz, placa);
    }

    private NodoArbolB obtenerPagina(NodoArbolB nodo, String placa) {
        NodoArbolB pagina = null;
        if (nodo != null) {
            for (int i = 0; i < nodo.getCantidadClaves(); i++) {
                if (nodo.getVehiculoClave(i).getPlaca().equalsIgnoreCase(placa)) {
                    return nodo;
                }
            }

            if (nodo.getPaginas() != null) {
                for (int i = 0; i < nodo.getCantidadPaginas(); i++) {
                    pagina = obtenerPagina(nodo.getPagina(i), placa);
                    if (pagina != null) {
                        return pagina;
                    }
                }
            }
        }
        return pagina;
    }

    private Integer obtenerPosicionPagina(NodoArbolB nodo, NodoArbolB pagina) {
        Integer posicion = -1;
        if (nodo != null) {
            for (int i = 0; i < nodo.getCantidadPaginas(); i++) {
                if (nodo.getPagina(i).equals(pagina)) {
                    return i;
                }
            }
        }
        return posicion;
    }

    private Integer obtenerPosicionNodo(NodoArbolB nodo, String placa) {
        if (nodo != null) {
            for (int i = 0; i < nodo.getCantidadClaves(); i++) {
                if (nodo.getVehiculoClave(i).getPlaca().equalsIgnoreCase(placa)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public ArrayList<Vehiculo> obtenerDatos() {
        return obtenerDatos(this.raiz);
    }

    private ArrayList<Vehiculo> obtenerDatos(NodoArbolB nodo) {
        ArrayList<Vehiculo> arrayList = new ArrayList<>();

        if (nodo != null) {
            for (int i = 0; i < nodo.getCantidadClaves(); i++) {
                arrayList.add(nodo.getVehiculoClave(i));
            }

            if (nodo.getPaginas() != null) {
                for (int i = 0; i < nodo.getCantidadPaginas(); i++) {
                    arrayList.addAll(obtenerDatos(nodo.getPagina(i)));
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Vehiculo> buscarVehiculo(String buscar) {
        buscar = buscar.toLowerCase();
        return buscarVehiculo(this.raiz, buscar);
    }

    private ArrayList<Vehiculo> buscarVehiculo(NodoArbolB nodo, String buscar) {
        ArrayList<Vehiculo> arrayList = new ArrayList<>();

        if (nodo != null) {
            for (int i = 0; i < nodo.getCantidadClaves(); i++) {
                if (nodo.getVehiculoClave(i).getPlaca().toLowerCase().contains(buscar)
                        || nodo.getVehiculoClave(i).getMarca().toLowerCase().contains(buscar)
                        || nodo.getVehiculoClave(i).getModelo().toLowerCase().contains(buscar)
                        || nodo.getVehiculoClave(i).getPrecio().toLowerCase().contains(buscar)
                        || nodo.getVehiculoClave(i).getAnio().toLowerCase().contains(buscar)
                        || nodo.getVehiculoClave(i).getColor().toLowerCase().contains(buscar)) {
                    arrayList.add(nodo.getVehiculoClave(i));
                }
            }

            if (nodo.getPaginas() != null) {
                for (int i = 0; i < nodo.getCantidadPaginas(); i++) {
                    arrayList.addAll(buscarVehiculo(nodo.getPagina(i), buscar));
                }
            }
        }
        return arrayList;
    }

    public String reporteArbolB() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("digraph G {");
        stringBuilder.append("\n\tgraph [bgcolor=transparent];");
        stringBuilder.append("\n\trankdir = TB;");
        stringBuilder.append("\n\tnode[shape=record, style=filled color=\"#393C4BFF\""
                + " fillcolor=\"#393C4BFF\", fontcolor = \"#F8F8F2FF\"];");
        stringBuilder.append(contenidoGrafica());
        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }

    public String contenidoGrafica() {
        if (raiz != null) {
            return contenidoGrafica(this.raiz);
        }
        return "";
    }

    private String contenidoGrafica(NodoArbolB aux) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\tN").append(aux.getVehiculosClaves()[0].getPlaca()).append("[label=\"");

        int cantidadHojas = aux.getCantidadClaves();
        boolean esHoja = aux.getCantidadPaginas() == 0;

        for (int i = 0; i < cantidadHojas; i++) {
            if (!esHoja) {
                stringBuilder.append("<f").append(i).append(">|");
            }

            stringBuilder.append("PLACA: ").append(aux.getVehiculosClaves()[i].getPlaca()).append(" \n");
            /*
                    .append("MARCA: ").append(aux.getVehiculosClaves()[i].getMarca()).append(" \n")
                    .append("MODELO: ").append(aux.getVehiculosClaves()[i].getModelo()).append(" \n")
                    .append("AÑO: ").append(aux.getVehiculosClaves()[i].getAnio()).append(" \n")
                    .append("COLOR: ").append(aux.getVehiculosClaves()[i].getColor()).append(" \n")
                    .append("PRECIO: ").append(aux.getVehiculosClaves()[i].getPrecio()).append(" \n")
                    .append("TRANSMISION: ").append(aux.getVehiculosClaves()[i].getTransmision())
             */

            if (i < cantidadHojas - 1) {
                stringBuilder.append("|");
            }
        }

        if (!esHoja) {
            stringBuilder.append("|<f").append(cantidadHojas).append(">");
        }

        stringBuilder.append("\", color=\"#9E9BA3\"];");

        for (int i = 0; i < cantidadHojas; i++) {
            if (!esHoja) {
                stringBuilder.append(contenidoGrafica(aux.getPaginas()[i]));
            }
        }

        if (!esHoja) {
            stringBuilder.append(contenidoGrafica(aux.getPagina(cantidadHojas)));
        }

        for (int i = 0; i < cantidadHojas + 1; i++) {
            if (!esHoja) {
                stringBuilder.append("\n\tN").append(aux.getVehiculosClaves()[0].getPlaca())
                        .append(":f").append(i).append(" -> N").append(aux.getPaginas()[i].getVehiculosClaves()[0].getPlaca())
                        .append("[color=\"#E91E63\"];");
            }
        }
        return stringBuilder.toString();
    }

    public void agregarArchivo(String contenido) {
        String[] vehiculos = contenido.replace(System.getProperty("line.separator"), "").split(";");
        String[] atributos;
        boolean vehiculoInsertado;
        try {
            for (String vehiculo : vehiculos) {
                atributos = vehiculo.split(":");
                vehiculoInsertado = insertar(new Vehiculo(atributos[0].trim(),
                        atributos[1].trim(), atributos[2].trim(), atributos[3].trim(),
                        atributos[4].trim(), atributos[5].trim(), atributos[6].trim()));
                if (!vehiculoInsertado) {
                    Alerta.getInstancia().mostrarNotificacion("ARCHIVO VEHICULO", "EL VEHICULO FUE REGISTRADO PREVIAMENTE");
                } else {
                    Alerta.getInstancia().mostrarNotificacion("ARCHIVO VEHICULO", "REGISTRO REALIZADO EXITOSAMENTE");
                }
            }
        } catch (Exception e) {
            Alerta.getInstancia().mostrarNotificacion("ARCHIVO VEHICULO", "NO CONTIENE LA INFORMACION SOLICITADA");
            System.out.println("\n El archivo no contiene la información solicitada para Vehiculos. \n");
        }
    }
}
