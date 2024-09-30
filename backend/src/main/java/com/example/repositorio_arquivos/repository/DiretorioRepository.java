package com.example.repositorio_arquivos.repository;

import com.example.repositorio_arquivos.entity.Diretorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiretorioRepository  extends JpaRepository<Diretorio, Long> {
    @Query("SELECT d FROM Diretorio d LEFT JOIN FETCH d.files")
    List<Diretorio> findAllWithFiles();
}
