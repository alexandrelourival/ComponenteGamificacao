package com.armazenamento.test;

import com.armazenamento.Armazenamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArmazenamentoTest {

    private Armazenamento armazenamento;

    @BeforeEach
    public void beforeEach(){
        armazenamento = new Armazenamento();
    }

    @Test
    public void armazenarUmUsuario() {
        String usuario = "guerra";
        int pontos = 10;
        String tipoPonto = "estrela";

        assertNotNull(armazenamento);
        armazenamento.armazenar(usuario, pontos, tipoPonto);
        assertEquals(pontos, armazenamento.recuperarPontosUsuario(usuario, tipoPonto));
    }

    @Test
    public void recuperarPontosUsuario() {
        String usuario = "guerra";
        int pontos = 10;
        String tipoPonto = "estrela";

        assertNotNull(armazenamento);
        armazenamento.armazenar(usuario, pontos, tipoPonto);
        assertEquals(pontos, armazenamento.recuperarPontosUsuario(usuario, tipoPonto));
    }

    @Test
    public void armazenarDoisUsuarios() {
        assertNotNull(armazenamento);
        armazenamento.armazenar("guerra", 10, "estrela");
        armazenamento.armazenar("paz", 100, "estrela");
        assertEquals(10, armazenamento.recuperarPontosUsuario("guerra", "estrela"));
        assertEquals(100, armazenamento.recuperarPontosUsuario("paz", "estrela"));
    }

    @Test
    public void armazenarUmUsuarioComDoisTipos() {
        assertNotNull(armazenamento);
        armazenamento.armazenar("guerra", 10, "estrela");
        armazenamento.armazenar("guerra", 100, "raiva");
        assertEquals(10, armazenamento.recuperarPontosUsuario("guerra", "estrela"));
        assertEquals(100, armazenamento.recuperarPontosUsuario("guerra", "raiva"));
    }

    @Test
    public void retornarTodosUsuarios() {
        assertNotNull(armazenamento);
        armazenamento.armazenar("guerra", 10, "estrela");
        armazenamento.armazenar("guerra", 100, "raiva");
        armazenamento.armazenar("paz", 100, "estrela");

        Map<String, Map<String, Integer>> armazem = new HashMap<>();
        Map<String, Integer> tipopontousuario1 = new HashMap<>();
        Map<String, Integer> tipopontousuario2 = new HashMap<>();
        tipopontousuario1.put("estrela", 10);
        tipopontousuario1.put("raiva", 100);
        armazem.put("guerra", tipopontousuario1);
        tipopontousuario2.put("estrela", 100);
        armazem.put("paz", tipopontousuario2);

        assertEquals(armazem, armazenamento.retornarTodosUsuarios());
    }

    @Test
    public void retornarTodosTiposPontosUsuario() {
        assertNotNull(armazenamento);
        armazenamento.armazenar("guerra", 10, "estrela");
        armazenamento.armazenar("guerra", 100, "raiva");
        armazenamento.armazenar("paz", 100, "estrela");

        List<String> resultGuerra = new ArrayList<>();
        resultGuerra.add("raiva");
        resultGuerra.add("estrela");
        assertEquals(resultGuerra, armazenamento.retornarTodosTiposPontosUsuario("guerra"));
    }
}