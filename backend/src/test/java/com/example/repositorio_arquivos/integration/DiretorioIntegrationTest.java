package com.example.repositorio_arquivos.integration;



import com.example.repositorio_arquivos.entity.Diretorio;
import com.example.repositorio_arquivos.repository.DiretorioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
public class DiretorioIntegrationTest {

    @Autowired
    private DiretorioRepository diretorioRepository;

    @Test
    public void testCreateAndFindDirectory() {
        Diretorio diretorio = new Diretorio();
        diretorio.setNome("Novo Diretorio");

        Diretorio saved = diretorioRepository.save(diretorio);

        Optional<Diretorio> encontrado = diretorioRepository.findById(saved.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("Novo Diretorio", encontrado.get().getNome());
    }
}
