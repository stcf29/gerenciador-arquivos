package com.example.repositorio_arquivos.services;


import com.example.repositorio_arquivos.entity.Arquivo;
import com.example.repositorio_arquivos.entity.Diretorio;
import com.example.repositorio_arquivos.repository.ArquivoRepository;
import com.example.repositorio_arquivos.repository.DiretorioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ArquivoServiceTest {

    @InjectMocks
    private ArquivoService arquivoService;

    @Mock
    private ArquivoRepository arquivoRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }
    //test create
    @Test
    public void testCreateArquivo() throws Exception {
        Arquivo arquivo = new Arquivo();
        arquivo.setNome("Teste Arquivo");

        // Configura o comportamento do mock
        when(arquivoRepository.save(arquivo)).thenReturn(arquivo);

        // Chama o método a ser testado
        Arquivo created = arquivoService.createFile(arquivo);

        // Verifica se o resultado está correto
        assertEquals("Teste Arquivo", created.getNome());
    }
    //teste get all
    @Test
    public void testGetAllFiles() {
        Arquivo arquivo1 = new Arquivo();
        arquivo1.setNome("Teste_arquivo_1");

        Arquivo arquivo2 = new Arquivo();
        arquivo2.setNome("Teste_arquivo_2");

        when(arquivoRepository.findAll()).thenReturn(List.of(arquivo1, arquivo2));

        List<Arquivo> arquivosList = arquivoService.getAllFiles();

        assertEquals(2, arquivosList.size());
        assertEquals("Teste_arquivo_1", arquivosList.get(0).getNome());
        assertEquals("Teste_arquivo_2", arquivosList.get(1).getNome());
    }

    @Test
    public void testDeleteArquivo() {
        Arquivo arquivo1 = new Arquivo();
        arquivo1.setId(1L);

        doNothing().when(arquivoRepository).deleteById(1L);

        arquivoService.deleteFile(1L);

        verify(arquivoRepository, times(1)).deleteById(1L);
    }
}
