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

    public boolean insertar(Vehiculo vehiculo) {
        if (raiz == null) {
            raiz = new NodoArbolB();
            raiz.setVehiculoClave(vehiculo);
            return true;
        }
        return insertar(raiz, vehiculo);
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
            } else if (vehiculo.getPlaca().compareTo(nodo.getVehiculoClave(0).getPlaca()) == 0) {
                return false;
            }

            for (int i = 1; i < nodo.getCantidadClaves(); i++) {
                Vehiculo vehiculoMenor = nodo.getVehiculoClave(i - 1);
                Vehiculo vehiculoMayor = nodo.getVehiculoClave(i);
                if (vehiculo.getPlaca().compareTo(vehiculoMenor.getPlaca()) > 0
                        && vehiculo.getPlaca().compareTo(vehiculoMayor.getPlaca()) < 0) {
                    return insertar(nodo.getPagina(i), vehiculo);
                } else if (vehiculo.getPlaca().compareTo(vehiculoMayor.getPlaca()) == 0) {
                    return false;
                }
            }
        }
        return false;
    }

    private void dividirNodo(NodoArbolB nodo) {
        NodoArbolB nodoAuxiliar = nodo;
        Vehiculo vehiculoCentro = nodo.getVehiculoClave(2); // Se obtiene la clave mediana o central
        NodoArbolB paginaIzquierda = obtenerPaginasIzquierdas(nodoAuxiliar); // Pagina donde se almacenaran los nodos izquierdos
        NodoArbolB paginaDerecha = obtenerPaginasDerechas(nodoAuxiliar); // Pagina donde se almacenaran los nodos derechos

        if (nodo.getPadre() == null) {
            nuevaRaiz(nodoAuxiliar, vehiculoCentro, paginaIzquierda, paginaDerecha);
        } else {
            subirPadre(nodoAuxiliar, vehiculoCentro, paginaIzquierda, paginaDerecha);
        }
    }

    private NodoArbolB obtenerPaginasIzquierdas(NodoArbolB nodo) {
        NodoArbolB paginaIzquierda = new NodoArbolB();
        for (int i = 0; i < 2; i++) {
            paginaIzquierda.setVehiculoClave(nodo.getVehiculoClave(i));
        }

        if (nodo.getCantidadPaginas() > 0) {
            for (int i = 0; i <= 2; i++) {
                paginaIzquierda.setPagina(nodo.getPagina(i));
            }
        }
        return paginaIzquierda;
    }

    private NodoArbolB obtenerPaginasDerechas(NodoArbolB nodo) {
        NodoArbolB paginaDerecha = new NodoArbolB();
        for (int i = 3; i < nodo.getCantidadClaves(); i++) {
            paginaDerecha.setVehiculoClave(nodo.getVehiculoClave(i));
        }

        if (nodo.getCantidadPaginas() > 0) {
            for (int i = 3; i < nodo.getCantidadPaginas(); i++) {
                paginaDerecha.setPagina(nodo.getPagina(i));
            }
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

    public ArrayList<Vehiculo> obtenerDatos() {
        return obtenerDatos(this.raiz);
    }

    private ArrayList<Vehiculo> obtenerDatos(NodoArbolB nodo) {
        ArrayList<Vehiculo> arrayList = new ArrayList<>();

        if (nodo != null) {
            Vehiculo vehiculo;
            for (int i = 0; i < nodo.getCantidadClaves(); i++) {
                vehiculo = nodo.getVehiculoClave(i);
                arrayList.add(vehiculo);
            }

            if (nodo.getPaginas() != null) {
                NodoArbolB auxiliar;
                for (int i = 0; i < nodo.getCantidadPaginas() - 1; i++) {
                    auxiliar = nodo.getPagina(i);
                    arrayList.addAll(obtenerDatos(auxiliar));
                }

                if (nodo.getCantidadPaginas() >= 1) {
                    auxiliar = nodo.getPagina(nodo.getCantidadPaginas() - 1);
                    arrayList.addAll(obtenerDatos(auxiliar));
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

        int n = aux.getCantidadClaves();
        boolean isLeaf = aux.getCantidadPaginas() == 0;

        for (int i = 0; i < n; i++) {
            if (!isLeaf) {
                stringBuilder.append("<f").append(i).append(">|");
            }

            stringBuilder.append("PLACA: ").append(aux.getVehiculosClaves()[i].getPlaca()).append(" \n") /*.append("MARCA: ").append(aux.getVehiculosClaves()[i].getMarca()).append(" \n")
                    .append("MODELO: ").append(aux.getVehiculosClaves()[i].getModelo()).append(" \n")
                    .append("AÑO: ").append(aux.getVehiculosClaves()[i].getAnio()).append(" \n")
                    .append("COLOR: ").append(aux.getVehiculosClaves()[i].getColor()).append(" \n")
                    .append("PRECIO: ").append(aux.getVehiculosClaves()[i].getPrecio()).append(" \n")
                    .append("TRANSMISION: ").append(aux.getVehiculosClaves()[i].getTransmision())*/;

            if (i < n - 1) {
                stringBuilder.append("|");
            }
        }
        if (!isLeaf) {
            stringBuilder.append("|<f").append(n).append(">");
        }

        stringBuilder.append("\", color=\"#9E9BA3\"];");

        for (int i = 0; i < n; i++) {
            if (!isLeaf) {
                stringBuilder.append(contenidoGrafica(aux.getPaginas()[i]));
            }
        }

        if (!isLeaf) {
            stringBuilder.append(contenidoGrafica(aux.getPagina(n)));
        }

        for (int i = 0; i < n + 1; i++) {
            if (!isLeaf) {
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
        if (vehiculos.length > 1) {
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
        }
    }
}
