package com.example.repositorio_arquivos.repositoy;


import com.example.repositorio_arquivos.entity.Diretorio;
import com.example.repositorio_arquivos.repository.DiretorioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class DiretorioRepositoryTest {

    @Autowired
    private DiretorioRepository diretorioRepository;

    @Test
    public void testSaveAndFindById() {
        Diretorio diretorio = new Diretorio();
        diretorio.setNome("TesteDiretorio");


        Diretorio saved = diretorioRepository.save(diretorio);


        Optional<Diretorio> found = diretorioRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("TesteDiretorio", found.get().getNome());
    }

    @Test
    public void testDeleteById() {
        Diretorio diretorio = new Diretorio();
        diretorio.setNome("DiretorioDeletar");

        Diretorio saved = diretorioRepository.save(diretorio);


        diretorioRepository.deleteById(saved.getId());
        Optional<Diretorio> found = diretorioRepository.findById(saved.getId());
        assertFalse(found.isPresent());
    }
}
