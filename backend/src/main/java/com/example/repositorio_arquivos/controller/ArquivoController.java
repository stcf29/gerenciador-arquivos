package com.example.repositorio_arquivos.controller;


import com.example.repositorio_arquivos.entity.Arquivo;
import com.example.repositorio_arquivos.services.ArquivoService;
import com.example.repositorio_arquivos.services.DiretorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/arquivos")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @PostMapping
    public ResponseEntity<Arquivo> createFile(@RequestBody Arquivo file) {
        Arquivo newFile = arquivoService.createFile(file);
        return new ResponseEntity<>(newFile, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Arquivo> getAllFiles() {
        return arquivoService.getAllFiles();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Arquivo> updateFile(@PathVariable Long id, @RequestBody Arquivo arquivo) {
        Arquivo updatedFile = arquivoService.updateFile(id, arquivo);
        return ResponseEntity.ok(updatedFile);
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable Long id) {
        arquivoService.deleteFile(id);
    }
}
