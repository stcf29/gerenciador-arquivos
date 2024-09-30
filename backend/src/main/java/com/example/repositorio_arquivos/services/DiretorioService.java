package com.example.repositorio_arquivos.services;

import com.example.repositorio_arquivos.entity.Arquivo;
import com.example.repositorio_arquivos.entity.Diretorio;
import com.example.repositorio_arquivos.exceptions.ResourceNotFoundException;
import com.example.repositorio_arquivos.repository.ArquivoRepository;
import com.example.repositorio_arquivos.repository.DiretorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiretorioService {
    @Autowired
    private DiretorioRepository repository;

    @Autowired
    private ArquivoRepository arquivoRepository;

    public List<Diretorio> findAllDirectoriesWithFiles() {
        return repository.findAllWithFiles();
    }

    public Diretorio createDirectory(Diretorio diretorio) throws Exception {
        if(diretorio.getNome().equals("")){
            throw new Exception("nao foi inserido nome do diretório");
        }
        Diretorio savedDirectory = repository.save(diretorio);

        if (diretorio.getFiles() != null) {
            for (Arquivo file : diretorio.getFiles()) {
                file.setDiretorio(savedDirectory);
                arquivoRepository.save(file);
            }

            for (Diretorio subDir : diretorio.getSubDirectories()) {
                subDir.setParentDirectory(savedDirectory);
                repository.save(subDir);
            }
        }


        return repository.save(diretorio);
    }

    public List<Diretorio> getAllDirectories() {
        return repository.findAll();
    }

    public void deleteDirectory(Long id) {
        repository.deleteById(id);
    }


    public Diretorio updateDirectory(Long id, Diretorio updatedDirectory) {
        Diretorio existingDirectory = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diretório não encontrado"));

        existingDirectory.setNome(updatedDirectory.getNome());
        existingDirectory.setParentDirectory(updatedDirectory.getParentDirectory());


        for (Arquivo updatedFile : updatedDirectory.getFiles()) {
            if (updatedFile.getId() != null) {

                Optional<Arquivo> existingFileOpt = arquivoRepository.findById(updatedFile.getId());

                if (existingFileOpt.isPresent()) {

                    Arquivo existingFile = existingFileOpt.get();
                    existingFile.setNome(updatedFile.getNome());
                    existingFile.setTamanho(updatedFile.getTamanho());
                    existingFile.setDiretorio(existingDirectory);
                    arquivoRepository.save(existingFile);
                } else {

                    updatedFile.setDiretorio(existingDirectory);
                    arquivoRepository.save(updatedFile);
                }
            } else {

                updatedFile.setDiretorio(existingDirectory);
                arquivoRepository.save(updatedFile);
            }
        }

        return repository.save(existingDirectory);
    }
}