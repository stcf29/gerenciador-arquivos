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


public class DiretorioServiceTest {

    @InjectMocks
    private DiretorioService diretorioService;

    @Mock
    private DiretorioRepository diretorioRepository;

    @Mock
    private ArquivoRepository arquivoRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    public void testCreateDirectory() throws Exception {
        Diretorio diretorio = new Diretorio();
        diretorio.setNome("Test Directory");

        // Mocking um arquivo associado
        Arquivo arquivo = new Arquivo();
        arquivo.setNome("Test File");
        arquivo.setTamanho("500KB");
        arquivo.setDiretorio(diretorio);

        diretorio.getFiles().add(arquivo);

        // Simulando comportamento do repository
        when(diretorioRepository.save(any(Diretorio.class))).thenReturn(diretorio);
        when(arquivoRepository.save(any(Arquivo.class))).thenReturn(arquivo);

        Diretorio createdDiretorio = diretorioService.createDirectory(diretorio);

        // Verificando se o diretório e o arquivo foram criados corretamente
        assertEquals("Test Directory", createdDiretorio.getNome());
        assertEquals(1, createdDiretorio.getFiles().size());
        assertEquals("Test File", createdDiretorio.getFiles().get(0).getNome());
    }

    @Test
    public void testDeleteDirectory() {
        Diretorio diretorio = new Diretorio();
        diretorio.setId(1L);

        doNothing().when(diretorioRepository).deleteById(1L);

        diretorioService.deleteDirectory(1L);

        verify(diretorioRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllDirectories() {
        Diretorio diretorio1 = new Diretorio();
        diretorio1.setNome("Dir 1");

        Diretorio diretorio2 = new Diretorio();
        diretorio2.setNome("Dir 2");

        when(diretorioRepository.findAll()).thenReturn(List.of(diretorio1, diretorio2));

        List<Diretorio> diretorios = diretorioService.getAllDirectories();

        assertEquals(2, diretorios.size());
        assertEquals("Dir 1", diretorios.get(0).getNome());
        assertEquals("Dir 2", diretorios.get(1).getNome());
    }


}
