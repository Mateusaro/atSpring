package com.example.atSpring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String codigo;

    @ManyToMany(mappedBy = "disciplinas")
    @JsonManagedReference
    private Set<Aluno> alunos = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "disciplina_notas",
            joinColumns = @JoinColumn(name = "disciplina_id"))
    @MapKeyJoinColumn(name = "aluno_id")
    @Column(name = "nota")
    private Map<Aluno, Double> notas = new HashMap<>();

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Map<Aluno, Double> getNotas() {
        return notas;
    }

    public void setNotas(Map<Aluno, Double> notas) {
        this.notas = notas;
    }
}
