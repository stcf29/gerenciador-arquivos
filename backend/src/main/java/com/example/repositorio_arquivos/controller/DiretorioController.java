package com.example.repositorio_arquivos.controller;


import com.example.repositorio_arquivos.entity.Diretorio;
import com.example.repositorio_arquivos.services.DiretorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/diretorios")
@CrossOrigin(origins = "http://localhost:5173")
public class DiretorioController {

    @Autowired
    private DiretorioService diretorioService;

    @PostMapping
    public Diretorio createDirectory(@RequestBody Diretorio diretorio) throws Exception {
        return diretorioService.createDirectory(diretorio);
    }

    @GetMapping
    public List<Diretorio> getDirectories() {
        return diretorioService.findAllDirectoriesWithFiles(); // Chama o método do serviço
    }

    // Atualizar diretório por ID
    @PutMapping("/{id}")
    public ResponseEntity<Diretorio> updateDirectory(@PathVariable Long id, @RequestBody Diretorio diretorio) {
        Diretorio updatedDiretorio = diretorioService.updateDirectory(id, diretorio);
        return ResponseEntity.ok(updatedDiretorio);
    }

    @DeleteMapping("/{id}")
    public void deleteDirectory(@PathVariable Long id) {
        diretorioService.deleteDirectory(id);
    }
}