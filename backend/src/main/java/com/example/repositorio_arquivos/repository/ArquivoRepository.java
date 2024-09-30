package com.example.repositorio_arquivos.repository;

import com.example.repositorio_arquivos.entity.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivoRepository  extends JpaRepository<Arquivo, Long> {
}
