package org.jd.utilidades;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jd.estructuras.NodoHuffman;
import org.jd.estructuras.TablaCodificacion;

public class Huffman {

    private static Huffman instancia;

    public Huffman() {
    }

    public static Huffman getInstancia() {
        if (instancia == null) {
            instancia = new Huffman();
        }
        return instancia;
    }

    public String comprimirContenido(String contenido) {
        codificar(contenido);
        List<String> listaCaracteres = Arrays.asList(contenido.split("(?!^)"));

        for (int i = 0; i < listaCaracteres.size(); i++) {
            listaCaracteres.set(i, TablaCodificacion.getInstancia().getCodigo(listaCaracteres.get(i)));
        }
        contenido = String.join(" ", listaCaracteres);
        TablaCodificacion.getInstancia().generarReporteTabla();
        return contenido;
    }

    public String descomprimirContenido(String contenido) {
        List<String> listaCaracteres = Arrays.asList(contenido.split(" "));
        for (int i = 0; i < listaCaracteres.size(); i++) {
            listaCaracteres.set(i, TablaCodificacion.getInstancia().getCaracter(listaCaracteres.get(i)));
        }
        contenido = String.join("", listaCaracteres);
        return contenido;
    }

    public void codificar(String contenido) {
        PriorityQueue<NodoHuffman> cola = new PriorityQueue<>(1, new ComparadorNodoHuffman());

        List<String> listaCaracteres = Arrays.asList(contenido.split("(?!^)"));
        Map<String, Long> conteoCaracteres = listaCaracteres.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> conteoAscendente = new LinkedHashMap<>();
        conteoCaracteres.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEachOrdered(e -> conteoAscendente.put(e.getKey(), e.getValue()));
        // System.out.println(conteoAscendente);

        conteoAscendente.entrySet().forEach((entry) -> {
            int valor = entry.getValue().intValue();
            cola.add(new NodoHuffman(String.valueOf(entry.getKey().charAt(0)), valor));
        });

        while (cola.size() > 1) {
            NodoHuffman ramaCero = cola.peek();
            cola.poll();

            NodoHuffman rama1 = cola.peek();
            cola.poll();

            cola.add(new NodoHuffman(ramaCero, rama1));
        }

        TablaCodificacion.getInstancia().limpiarTabla();
        cola.peek().actualizarCodigos("");
    }
}
