package com.example.atSpring.controller;

import com.example.atSpring.dto.DisciplinaDTO;
import com.example.atSpring.model.Aluno;
import com.example.atSpring.model.Disciplina;
import com.example.atSpring.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping
    public Disciplina save(@RequestBody DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(disciplinaDTO.getNome());
        disciplina.setCodigo(disciplinaDTO.getCodigo());
        return disciplinaService.save(disciplina);
    }

    @GetMapping
    public List<Disciplina> findAll() {
        return disciplinaService.findAll();
    }

    @PostMapping("/{disciplinaId}/alunos/{alunoId}")
    public Disciplina alocarAluno(@PathVariable Long disciplinaId, @PathVariable Long alunoId) {
        return disciplinaService.alocarAluno(disciplinaId, alunoId);
    }

    @PostMapping("/{disciplinaId}/alunos/{alunoId}/nota")
    public Disciplina atribuirNota(@PathVariable Long disciplinaId, @PathVariable Long alunoId, @RequestBody Double nota) {
        return disciplinaService.atribuirNota(disciplinaId, alunoId, nota);
    }

    @GetMapping("/{disciplinaId}/aprovados")
    public List<Aluno> listarAprovados(@PathVariable Long disciplinaId) {
        return disciplinaService.listarAprovados(disciplinaId);
    }

    @GetMapping("/{disciplinaId}/reprovados")
    public List<Aluno> listarReprovados(@PathVariable Long disciplinaId) {
        return disciplinaService.listarReprovados(disciplinaId);
    }
}
