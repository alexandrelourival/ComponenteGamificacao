package com.armazenamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Armazenamento {

    private final Map<String, Map<String, Integer>> armazem = new HashMap<>();

    public void armazenar(String usuario, Integer pontos, String tipoPonto) {
        if(armazem.get(usuario) == null) {
            Map<String, Integer> tipoPontoPontos = new HashMap<>();
            tipoPontoPontos.put(tipoPonto, pontos);
            armazem.put(usuario, tipoPontoPontos);
        }
        else {
            armazem.get(usuario).put(tipoPonto, pontos);
        }
    }

    public Integer recuperarPontosUsuario(String usuario, String tipoPonto) {
        return armazem.get(usuario).get(tipoPonto);
    }

    public Map<String, Map<String, Integer>> retornarTodosUsuarios() {
        return armazem;
    }

    public List<String> retornarTodosTiposPontosUsuario(String usuario) {
        return new ArrayList<>(armazem.get(usuario).keySet());
    }
}
