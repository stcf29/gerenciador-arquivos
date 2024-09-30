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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        List<Diretorio> diretorios = List.of(new Diretorio(1L, "Documents",
                null, new ArrayList<>(), new ArrayList<>()));


        when(diretorioService.getAllDirectories()).thenReturn(diretorios);


        mockMvc.perform(get("/api/diretorios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Documents"));
    }
}
