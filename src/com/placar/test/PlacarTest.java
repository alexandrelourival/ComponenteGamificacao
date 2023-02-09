package com.placar.test;

import com.placar.Placar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlacarTest {

    Placar placar;
    MockArmazenamento mockArmazenamento;

    @BeforeEach
    public void beforeEach(){
        mockArmazenamento = new MockArmazenamento();
        placar = new Placar(mockArmazenamento);
    }

    @Test
    public void registrar(){
        assertNotNull(placar);
        placar.registrar("guerra", 10, "estrela");
        assertEquals(10, mockArmazenamento.recuperarPontosUsuario("guerra", "estrela"));
    }

    @Test
    public void retornarTodosPontosUsuario() {
        assertNotNull(placar);
        placar.registrar("guerra", 20, "moeda");
        placar.registrar("guerra", 25, "estrela");
        placar.registrar("paz", 100, "energia");

        assertEquals(mockArmazenamento.retornarTodosTiposPontosUsuario("guerra"), placar.retornarTodosPontosUsuario("guerra"));
    }

    @Test
    public void retornarRankingDeUmTipoDePonto() {
        assertNotNull(placar);
        placar.registrar("rodrigo", 17, "estrela");
        placar.registrar("rodrigo", 12, "moeda");
        placar.registrar("guerra", 25, "estrela");
        placar.registrar("guerra", 20, "energia");
        placar.registrar("fernandes", 19, "estrela");
        placar.registrar("fernandes", 14, "moeda");
        placar.registrar("toco", 30, "energia");

        List<String> resultado = new ArrayList<>();
        resultado.add("'guerra' com '25'");
        resultado.add("'fernandes' com '19'");
        resultado.add("'rodrigo' com '17'");

        assertEquals(resultado, placar.retornarRankingDeUmTipoDePonto("estrela"));

        List<String> resultado2 = new ArrayList<>();
        resultado2.add("'fernandes' com '14'");
        resultado2.add("'rodrigo' com '12'");

        assertEquals(resultado2, placar.retornarRankingDeUmTipoDePonto("moeda"));
    }
}