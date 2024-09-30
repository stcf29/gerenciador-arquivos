package com.example.repositorio_arquivos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
//@Table(name = "tb_directory")
public class Diretorio implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;

    @ManyToOne
    @JsonIgnore
    private Diretorio parentDirectory;

    @OneToMany(mappedBy = "diretorio", fetch = FetchType.LAZY ,  cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Arquivo> files = new ArrayList<>();;
    //arquivos
    @OneToMany(mappedBy = "parentDirectory", fetch = FetchType.LAZY)
    private List<Diretorio> subDirectories = new ArrayList<>();
    //subdiretorios

}
