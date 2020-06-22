package org.jd.estructuras;

public class ListadoAristas {

    private Arista primero;
    private Arista ultimo;

    public ListadoAristas() {
        primero = null;
        ultimo = null;
    }

    public Arista getPrimero() {
        return primero;
    }

    public Arista getUltimo() {
        return ultimo;
    }

    private boolean estaVacia() {
        return primero == null;
    }

    public void insertar(Arista arista) {
        if (estaVacia()) {
            primero = arista;
        } else {
            ultimo.setSiguiente(arista);
        }
        ultimo = arista;
    }

    public void leer() {
        Arista auxiliar = primero;
        while (auxiliar != null) {
            System.out.print(auxiliar.getDestino().getLugar() + " | " + auxiliar.getPeso() + " -> ");
            auxiliar = auxiliar.getSiguiente();
        }
        System.out.println();
    }

    public Arista buscar(String destino) {
        Arista auxiliar = primero;
        while (auxiliar != null) {
            if (auxiliar.getDestino().getLugar().equalsIgnoreCase(destino)) {
                return auxiliar;
            }
            auxiliar = auxiliar.getSiguiente();
        }
        return null;
    }

    public Arista buscar(Integer peso) {
        Arista auxiliar = primero;
        while (auxiliar != null) {
            if (auxiliar.getPeso().compareTo(peso) == 0) {
                return auxiliar;
            }
            auxiliar = auxiliar.getSiguiente();
        }
        return null;
    }

    public boolean eliminar(String destino) {
        if (!estaVacia()) {
            if (primero.getDestino().getLugar().equalsIgnoreCase(destino)) {
                if (primero == ultimo) {
                    primero = null;
                    ultimo = null;
                } else {
                    primero = primero.getSiguiente();
                }
                return true;
            } else {
                Arista anterior = primero;
                Arista auxiliar = primero.getSiguiente();

                while (auxiliar != null) {
                    if (auxiliar.getDestino().getLugar().equalsIgnoreCase(destino)) {
                        if (auxiliar == ultimo) {
                            ultimo = anterior;
                        }
                        anterior.setSiguiente(auxiliar.getSiguiente());
                        return true;
                    }
                    anterior = anterior.getSiguiente();
                    auxiliar = auxiliar.getSiguiente();
                }
            }
        }
        return false;
    }
}
