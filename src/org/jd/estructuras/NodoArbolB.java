package org.jd.estructuras;

import java.util.Arrays;
import org.jd.modelos.Vehiculo;
import org.jd.utilidades.ComparadorLlaves;
import org.jd.utilidades.ComparadorPaginas;

public class NodoArbolB {

    private NodoArbolB padre;
    private Vehiculo[] vehiculosClaves;
    private NodoArbolB[] paginas;
    private int cantidadClaves;
    private int cantidadPaginas;

    public NodoArbolB() {
        this.padre = null;
        this.vehiculosClaves = new Vehiculo[5]; // Claves Maximas
        this.paginas = new NodoArbolB[6]; // Paginas (Hojas) Maximas
        this.cantidadClaves = 0;
        this.cantidadPaginas = 0;
    }

    public NodoArbolB(NodoArbolB padre) {
        this.padre = padre;
        this.vehiculosClaves = new Vehiculo[5]; // Claves Maximas
        this.paginas = new NodoArbolB[6]; // Paginas (Hojas) Maximas
        this.cantidadClaves = 0;
        this.cantidadPaginas = 0;
    }

    public NodoArbolB getPadre() {
        return padre;
    }

    public void setPadre(NodoArbolB padre) {
        this.padre = padre;
    }

    public Vehiculo getVehiculoClave(int posicion) {
        return vehiculosClaves[posicion];
    }

    public void setVehiculoClave(Vehiculo vehiculo) {
        this.vehiculosClaves[this.cantidadClaves] = vehiculo;
        this.cantidadClaves++;
        Arrays.sort(this.vehiculosClaves, 0, this.cantidadClaves, new ComparadorLlaves());
    }

    public Vehiculo[] getVehiculosClaves() {
        return vehiculosClaves;
    }

    public void setVehiculosClaves(Vehiculo[] vehiculosClaves) {
        this.vehiculosClaves = vehiculosClaves;
    }

    public NodoArbolB getPagina(int posicionPagina) {
        if (posicionPagina >= this.cantidadPaginas) {
            return null;
        }
        return paginas[posicionPagina];
    }

    public void setPagina(NodoArbolB pagina) {
        pagina.setPadre(this);
        this.paginas[this.cantidadPaginas] = pagina;
        this.cantidadPaginas++;
        Arrays.sort(this.paginas, 0, this.cantidadPaginas, new ComparadorPaginas());
    }

    public NodoArbolB[] getPaginas() {
        return paginas;
    }

    public void setPaginas(NodoArbolB[] paginas) {
        this.paginas = paginas;
    }

    public int getCantidadClaves() {
        return cantidadClaves;
    }

    public void setCantidadClaves(int cantidadClaves) {
        this.cantidadClaves = cantidadClaves;
    }

    public int getCantidadPaginas() {
        return cantidadPaginas;
    }

    public void setCantidadPaginas(int cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }

    public void quitarPagina(NodoArbolB pagina) {
        if (this.cantidadPaginas != 0) {
            for (int i = 0; i < this.cantidadPaginas; i++) {
                if (paginas[i].equals(pagina)) {
                    for (int j = i; j < this.cantidadPaginas - 1; j++) {
                        paginas[j] = paginas[j + 1];
                    }
                    this.cantidadPaginas--;
                    paginas[cantidadPaginas] = null;
                    break;
                }
            }
        }
    }

    public void quitarNodo(String placa) {
        if (this.cantidadClaves != 0) {
            for (int i = 0; i < this.cantidadClaves; i++) {
                if (vehiculosClaves[i].getPlaca().equalsIgnoreCase(placa)) {
                    for (int j = i; j < this.cantidadClaves - 1; j++) {
                        vehiculosClaves[j] = vehiculosClaves[j + 1];
                    }
                    this.cantidadClaves--;
                    vehiculosClaves[cantidadClaves] = null;
                    break;
                }
            }
        }
    }
}
