package org.jd.utilidades;

import java.util.Comparator;
import org.jd.estructuras.NodoHuffman;

public class ComparadorNodoHuffman implements Comparator<NodoHuffman> {

    @Override
    public int compare(NodoHuffman s1, NodoHuffman s2) {
        if (s1.getFrecuencia() > s2.getFrecuencia()) {
            return 1;
        } else if (s1.getFrecuencia() < s2.getFrecuencia()) {
            return -1;
        }
        return 0;
    }
}
