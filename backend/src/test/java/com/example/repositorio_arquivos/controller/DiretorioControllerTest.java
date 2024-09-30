package com.example.repositorio_arquivos.controller;

import com.example.repositorio_arquivos.entity.Diretorio;
import com.example.repositorio_arquivos.services.DiretorioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(DiretorioController.class)
public class DiretorioControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiretorioService diretorioService;

    @Test
    public void testGetAllDirectories() throws Exception {
        // Simula um diretório para o teste
        Diretorio diretorio = new Diretorio();
        diretorio.setId(1L);
        diretorio.setNome("Documentos");

        // Mockando o serviço para retornar esse diretório
        when(diretorioService.getAllDirectories()).thenReturn(Arrays.asList(diretorio));

        // Executando o teste
        mockMvc.perform(get("/diretorios"))
                .andExpect(status().isOk()) // Verifica se o status é 200
                .andExpect(jsonPath("$[0].nome").value("Documentos")); // Verifica se o nome está correto
    }
}
