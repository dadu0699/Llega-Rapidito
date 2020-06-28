package org.jd.utilidades;

import java.util.Comparator;
import org.jd.modelos.Vehiculo;

public class ComparadorLlaves implements Comparator<Vehiculo> {

    @Override
    public int compare(Vehiculo v1, Vehiculo v2) {
        if (v1.getPlaca().compareTo(v2.getPlaca()) > 0) {
            return 1;
        } else if (v1.getPlaca().compareTo(v2.getPlaca()) < 0) {
            return -1;
        }
        return 0;
    }
}
