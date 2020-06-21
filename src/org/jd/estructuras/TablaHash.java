package org.jd.estructuras;

import java.util.ArrayList;
import org.jd.modelos.Cliente;

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
            tablaHash[funcionHash(new Long(llave))].agregar(cliente);
            cantidadDatos++;

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
    
    public boolean eliminar(String llave){
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
        buscar = buscar.toLowerCase();
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (Cliente cliente : obtenerDatos()) {
            if (cliente.getDPI().toLowerCase().contains(buscar)
                    || cliente.getNombres().toLowerCase().contains(buscar)
                    || cliente.getApellidos().toLowerCase().contains(buscar)
                    || cliente.getTelefono().toLowerCase().contains(buscar)
                    || cliente.getFechaNacimiento().toLowerCase().contains(buscar)
                    || cliente.getDireccion().toLowerCase().contains(buscar)) {
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public void imprimirTabla() {
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
