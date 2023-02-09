package com.placar;

import com.armazenamento.Armazenamento;

import java.util.*;

public class Placar {

    private final Armazenamento armazenamento;
    public Placar(Armazenamento _armazenamento) {
        armazenamento = _armazenamento;
    }

    public void registrar(String usuario, Integer pontos, String tipoPonto) {
        armazenamento.armazenar(usuario, pontos, tipoPonto);
    }

    public List<String> retornarTodosPontosUsuario(String usuario) {
        return armazenamento.retornarTodosTiposPontosUsuario(usuario);
    }

    public List<String> retornarRankingDeUmTipoDePonto(String tipoPonto) {
        Map<String, Integer> mapTipoPonto = new HashMap<>();

        Map<String, Map<String, Integer>> listaComTodosUsuarios = armazenamento.retornarTodosUsuarios();

        listaComTodosUsuarios.forEach((item, i) -> {
            if (armazenamento.recuperarPontosUsuario(item, tipoPonto) != null)
                mapTipoPonto.put(item, armazenamento.recuperarPontosUsuario(item, tipoPonto));
        });

        return listaRanking(mapTipoPonto);
    }

    private List<String> listaRanking(Map<String, Integer> mapTipoPonto) {
        List<String> listaOrdenada = new ArrayList<>();
        List<Integer> listaPontos = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : mapTipoPonto.entrySet()) {
            listaPontos.add(entry.getValue());
        }

        listaPontos.sort(Collections.reverseOrder());

        listaPontos.forEach((num) -> {
            for (Map.Entry<String, Integer> entry : mapTipoPonto.entrySet()){
                if(entry.getValue().equals(num))
                    listaOrdenada.add(String.format("'%s' com '%d'", entry.getKey(), entry.getValue()));
            }
        });

        return listaOrdenada;
    }
}
