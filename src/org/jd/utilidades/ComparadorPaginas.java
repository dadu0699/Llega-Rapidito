package org.jd.utilidades;

import java.util.Comparator;
import org.jd.estructuras.NodoArbolB;

public class ComparadorPaginas implements Comparator<NodoArbolB> {

    @Override
    public int compare(NodoArbolB n1, NodoArbolB n2) {
        if (n1.getVehiculoClave(0).getPlaca().compareTo(n2.getVehiculoClave(0).getPlaca()) > 0) {
            return 1;
        } else if (n1.getVehiculoClave(0).getPlaca().compareTo(n2.getVehiculoClave(0).getPlaca()) < 0) {
            return -1;
        }
        return 0;
    }
}

