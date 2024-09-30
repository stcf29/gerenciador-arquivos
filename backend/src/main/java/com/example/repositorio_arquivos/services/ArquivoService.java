package com.example.repositorio_arquivos.services;


import com.example.repositorio_arquivos.entity.Arquivo;
import com.example.repositorio_arquivos.exceptions.ResourceNotFoundException;
import com.example.repositorio_arquivos.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArquivoService {
    @Autowired
    private ArquivoRepository repository;

    public Arquivo createFile(Arquivo arquivo) {
        return repository.save(arquivo);
    }

    public List<Arquivo> getAllFiles() {
        return repository.findAll();
    }

    public void deleteFile(Long id) {
        repository.deleteById(id);
    }

    // Atualizar arquivo
    public Arquivo updateFile(Long id, Arquivo updatedFile) {
        Arquivo existingFile = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arquivo não encontrado"));

        existingFile.setNome(updatedFile.getNome());
        existingFile.setTamanho(updatedFile.getTamanho());
        existingFile.setDiretorio(updatedFile.getDiretorio());

        return repository.save(existingFile);
    }
}
