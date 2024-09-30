package com.example.repositorio_arquivos.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
//@Table(name = "tb_file")
@Entity
public class Arquivo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String tamanho;

    @ManyToOne
    @JoinColumn(name = "diretorio_id") //
    @JsonBackReference
    private Diretorio diretorio;
    }
