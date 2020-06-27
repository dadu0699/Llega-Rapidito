package org.jd.estructuras;

public class NodoHuffman {

    private String letra;
    private int frecuencia;
    private String codigo;
    private NodoHuffman ramaCero;
    private NodoHuffman ramaUno;

    public NodoHuffman(String letra, int frecuencia, String codigo) {
        this.letra = letra;
        this.frecuencia = frecuencia;
        this.codigo = codigo;
        this.ramaCero = null;
        this.ramaUno = null;
    }

    public NodoHuffman(String letra, int frecuencia) {
        this.letra = letra;
        this.frecuencia = frecuencia;
        this.codigo = "";
        this.ramaCero = null;
        this.ramaUno = null;
    }

    public NodoHuffman(NodoHuffman ramaCero, NodoHuffman ramaUno) {
        this.letra = "";
        this.frecuencia = ramaCero.getFrecuencia() + ramaUno.getFrecuencia();
        this.codigo = "";
        this.ramaCero = ramaCero;
        this.ramaUno = ramaUno;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public NodoHuffman getRamaCero() {
        return ramaCero;
    }

    public void setRamaCero(NodoHuffman ramaCero) {
        this.ramaCero = ramaCero;
    }

    public NodoHuffman getRamaUno() {
        return ramaUno;
    }

    public void setRamaUno(NodoHuffman ramaUno) {
        this.ramaUno = ramaUno;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void actualizarCodigos(String codigo) {
        if (ramaCero != null) {
            ramaCero.actualizarCodigos(codigo + '0');
            ramaUno.actualizarCodigos(codigo + '1');
        } else {
            this.codigo = codigo;
            TablaCodificacion.getInstancia().agregar(this);
        }
    }
}
