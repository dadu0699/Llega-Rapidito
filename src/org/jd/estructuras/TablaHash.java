package org.jd.estructuras;

import java.util.ArrayList;
import org.jd.modelos.Cliente;
import org.jd.vistas.Alerta;

public class TablaHash {

    private static TablaHash instancia;
    private Integer m;
    private Integer cantidadDatos;
    private ListaSimple[] tablaHash;

    private TablaHash() {
        m = 37;
        cantidadDatos = 0;
        tablaHash = new ListaSimple[m];
        inicializarTabla(tablaHash);
    }

    public static TablaHash getInstancia() {
        if (instancia == null) {
            instancia = new TablaHash();
        }
        return instancia;
    }

    private void inicializarTabla(ListaSimple[] tabla) {
        for (int i = 0; i < tabla.length; i++) {
            tabla[i] = new ListaSimple();
        }
    }

    private int funcionHash(long llave) {
        return (int) (llave % m);
    }

    public Cliente buscar(String llave) {
        return tablaHash[funcionHash(new Long(llave))].buscar(llave);
    }

    public boolean insertar(Cliente cliente) {
        String llave = cliente.getDPI();
        if (buscar(llave) == null) {
            if (tablaHash[funcionHash(new Long(llave))].getPrimero() == null) {
                cantidadDatos++;
            }

            tablaHash[funcionHash(new Long(llave))].agregar(cliente);
            // cantidadDatos++;

            if ((cantidadDatos * 100 / m) >= 72) {
                m += 37;
                tablaHash = migrarTabla();
            }
            return true;
        }
        return false;
    }

    private ListaSimple[] migrarTabla() {
        ListaSimple[] tablaTemporal = new ListaSimple[m];
        NodoListaSimple auxiliar;
        String llave;

        inicializarTabla(tablaTemporal);
        for (ListaSimple listaSimple : tablaHash) {
            auxiliar = listaSimple.getPrimero();
            while (auxiliar != null) {
                llave = auxiliar.getCliente().getDPI();
                tablaTemporal[funcionHash(new Long(llave))].agregar(auxiliar.getCliente());
                listaSimple.eliminar(llave);
                auxiliar = listaSimple.getPrimero();
            }
        }
        return tablaTemporal;
    }

    public boolean eliminar(String llave) {
        return tablaHash[funcionHash(new Long(llave))].eliminar(llave);
    }

    public ArrayList<Cliente> obtenerDatos() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        NodoListaSimple auxiliar;
        for (int i = 0; i < tablaHash.length; i++) {
            auxiliar = tablaHash[i].getPrimero();
            while (auxiliar != null) {
                clientes.add(auxiliar.getCliente());
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return clientes;
    }

    public ArrayList<Cliente> buscarCliente(String buscar) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        buscar = buscar.toLowerCase();
        NodoListaSimple auxiliar;
        for (int i = 0; i < tablaHash.length; i++) {
            auxiliar = tablaHash[i].getPrimero();
            while (auxiliar != null) {
                if (auxiliar.getCliente().getDPI().toLowerCase().contains(buscar)
                        || auxiliar.getCliente().getNombres().toLowerCase().contains(buscar)
                        || auxiliar.getCliente().getApellidos().toLowerCase().contains(buscar)
                        || auxiliar.getCliente().getTelefono().toLowerCase().contains(buscar)
                        || auxiliar.getCliente().getFechaNacimiento().toLowerCase().contains(buscar)
                        || auxiliar.getCliente().getDireccion().toLowerCase().contains(buscar)) {
                    clientes.add(auxiliar.getCliente());
                }
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return clientes;
    }

    public void agregarArchivo(String contenido) {
        String[] clientes = contenido.replace(System.getProperty("line.separator"), "").split(";");
        String[] atributos;
        boolean clienteInsertado;
        try {
            if (clientes.length > 1) {
                for (String cliente : clientes) {
                    atributos = cliente.split(",");
                    clienteInsertado = insertar(new Cliente(atributos[0].trim(),
                            atributos[1].trim(), atributos[2].trim(), atributos[3].trim(),
                            atributos[4].trim(), atributos[5].trim(), atributos[6].trim()));
                    if (!clienteInsertado) {
                        Alerta.getInstancia().mostrarNotificacion("ARCHIVO CLIENTE", "EL CLIENTE FUE REGISTRADO PREVIAMENTE");
                    } else {
                        Alerta.getInstancia().mostrarNotificacion("ARCHIVO CLIENTE", "REGISTRO REALIZADO EXITOSAMENTE");
                    }
                }
            }
        } catch (Exception e) {
            Alerta.getInstancia().mostrarNotificacion("ARCHIVO CLIENTE", "NO CONTIENE LA INFORMACION SOLICITADA");
            System.out.println("\n El archivo no contiene la información solicitada para Clientes. \n");
        }
    }

    public String reporteTablaHash() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("digraph G {");
        stringBuilder.append("\n\tgraph [bgcolor=transparent];");
        stringBuilder.append("\n\trankdir = LR;");
        stringBuilder.append("\n\tnode[shape=record, style=filled color=\"#393C4BFF\""
                + " fillcolor=\"#393C4BFF\", fontcolor = \"#F8F8F2FF\"];");
        stringBuilder.append(contenidoGrafica());
        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }

    public String contenidoGrafica() {
        StringBuilder stringBuilder = new StringBuilder();
        NodoListaSimple auxiliar;

        for (int i = tablaHash.length - 1; i >= 0; i--) {
            stringBuilder.append("\n\tBucket").append(i).append("[label =\"Bucket ").append(i).append("\"];");
            auxiliar = tablaHash[i].getPrimero();

            if (auxiliar != null && auxiliar.getCliente() != null) {
                stringBuilder.append("\n\tNCL").append(auxiliar.getCliente().getDPI()).append("[label =\"")
                        .append("DPI: ").append(auxiliar.getCliente().getDPI()).append("\\n")
                        .append("NOMBRES Y APELLIDOS: ").append(auxiliar.getCliente().getNombres()).append(" ")
                        .append(auxiliar.getCliente().getApellidos()).append("\\n")
                        .append("GENERO: ").append(auxiliar.getCliente().getGenero()).append("\\n")
                        .append("FECHA NACIMIENTO: ").append(auxiliar.getCliente().getFechaNacimiento()).append("\\n")
                        .append("TELEFONO: ").append(auxiliar.getCliente().getTelefono()).append("\\n")
                        .append("DIRECCION: ").append(auxiliar.getCliente().getDireccion()).append("\"];");

                stringBuilder.append("\n\tBucket").append(i).append(" -> NCL")
                        .append(auxiliar.getCliente().getDPI()).append("[color=\"#E91E63\"];");
            }

            while (auxiliar != null) {
                if (auxiliar.getSiguiente() != null) {
                    stringBuilder.append("\n\tNCL").append(auxiliar.getSiguiente().getCliente().getDPI()).append("[label =\"")
                            .append("DPI: ").append(auxiliar.getSiguiente().getCliente().getDPI()).append("\\n")
                            .append("NOMBRES Y APELLIDOS: ").append(auxiliar.getSiguiente().getCliente().getNombres()).append(" ")
                            .append(auxiliar.getSiguiente().getCliente().getApellidos()).append("\\n")
                            .append("GENERO: ").append(auxiliar.getSiguiente().getCliente().getGenero()).append("\\n")
                            .append("FECHA NACIMIENTO: ").append(auxiliar.getSiguiente().getCliente().getFechaNacimiento()).append("\\n")
                            .append("TELEFONO: ").append(auxiliar.getSiguiente().getCliente().getTelefono()).append("\\n")
                            .append("DIRECCION: ").append(auxiliar.getSiguiente().getCliente().getDireccion()).append("\"];");

                    stringBuilder.append("\n\tNCL").append(auxiliar.getCliente().getDPI()).append(" -> NCL")
                            .append(auxiliar.getSiguiente().getCliente().getDPI()).append("[color=\"#E91E63\"];");
                }
                auxiliar = auxiliar.getSiguiente();
            }
        }

        return stringBuilder.toString();
    }

    private void imprimirTabla() {
        NodoListaSimple auxiliar;
        for (int i = 0; i < tablaHash.length; i++) {
            System.out.print("Bucket " + i + ":  ");
            auxiliar = tablaHash[i].getPrimero();
            while (auxiliar != null) {
                System.out.print(auxiliar.getCliente().getDPI() + "->");
                auxiliar = auxiliar.getSiguiente();
            }
            System.out.println();
        }
    }
}
