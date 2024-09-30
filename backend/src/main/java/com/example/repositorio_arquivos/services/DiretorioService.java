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
    private ArquivoRepository arquivoRepository; // Corrigido: adicione @Autowired

    public List<Diretorio> findAllDirectoriesWithFiles() {
        return repository.findAllWithFiles(); // Chama o método do repositório
    }

    public Diretorio createDirectory(Diretorio diretorio) throws Exception {
        if(diretorio.getNome().equals("")){
            throw new Exception("nao foi inserido nome do diretório");
        }
        Diretorio savedDirectory = repository.save(diretorio);

        if (diretorio.getFiles() != null) {
            for (Arquivo file : diretorio.getFiles()) {
                file.setDiretorio(savedDirectory); // Associa o arquivo ao diretório
                arquivoRepository.save(file); // Salva o arquivo
            }

            for (Diretorio subDir : diretorio.getSubDirectories()) {
                subDir.setParentDirectory(savedDirectory); // Define o diretório pai
                repository.save(subDir); // Salva o subdiretório
            }
        }


        return repository.save(diretorio); // Salva o diretório
    }

    public List<Diretorio> getAllDirectories() {
        return repository.findAll();
    }

    public void deleteDirectory(Long id) {
        repository.deleteById(id);
    }

    // Atualizar diretório
    public Diretorio updateDirectory(Long id, Diretorio updatedDirectory) {
        Diretorio existingDirectory = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diretório não encontrado"));

        existingDirectory.setNome(updatedDirectory.getNome());
        existingDirectory.setParentDirectory(updatedDirectory.getParentDirectory());

        // Atualiza ou insere os arquivos associados ao diretório
        for (Arquivo updatedFile : updatedDirectory.getFiles()) {
            if (updatedFile.getId() != null) {
                // Verifica se o arquivo já existe
                Optional<Arquivo> existingFileOpt = arquivoRepository.findById(updatedFile.getId());

                if (existingFileOpt.isPresent()) {
                    // Atualiza o arquivo existente
                    Arquivo existingFile = existingFileOpt.get();
                    existingFile.setNome(updatedFile.getNome());
                    existingFile.setTamanho(updatedFile.getTamanho());
                    existingFile.setDiretorio(existingDirectory);
                    arquivoRepository.save(existingFile);
                } else {
                    // Arquivo com ID não encontrado, cria um novo
                    updatedFile.setDiretorio(existingDirectory);
                    arquivoRepository.save(updatedFile);
                }
            } else {
                // Caso o arquivo não tenha ID, é um novo arquivo, então insere
                updatedFile.setDiretorio(existingDirectory);
                arquivoRepository.save(updatedFile);
            }
        }

        return repository.save(existingDirectory);
    }
}