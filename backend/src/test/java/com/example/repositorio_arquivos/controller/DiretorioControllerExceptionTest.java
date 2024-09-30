package com.example.repositorio_arquivos.controller;


import com.example.repositorio_arquivos.services.DiretorioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DiretorioController.class)
public class DiretorioControllerExceptionTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiretorioService diretorioService;

    @Test
    public void testGetAllDirectoriesThrowsException() throws Exception {
        // Mockando o comportamento para lançar uma exceção
        when(diretorioService.getAllDirectories()).thenThrow(new RuntimeException("Erro no servidor"));

        // Executando o teste
        mockMvc.perform(get("/diretorios"))
                .andExpect(status().isInternalServerError()); // Verifica se o status é 500
    }
}


