package com.example.repositorio_arquivos.integration;



import com.example.repositorio_arquivos.entity.Diretorio;
import com.example.repositorio_arquivos.repository.DiretorioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

public class DiretorioIntegrationTest {

    @Autowired
    private DiretorioRepository diretorioRepository;

    @Test
    public void testCreateAndFindDirectory() {
        Diretorio diretorio = new Diretorio();
        diretorio.setNome("Integração");

        Diretorio saved = diretorioRepository.save(diretorio);

        Diretorio found = diretorioRepository.findById(saved.getId()).orElse(null);

        assertNotNull(found);
        assertEquals("Integração", found.getNome());
    }
}
