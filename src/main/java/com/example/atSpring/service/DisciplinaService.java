package com.example.atSpring.service;

import com.example.atSpring.model.Aluno;
import com.example.atSpring.model.Disciplina;
import com.example.atSpring.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AlunoService alunoService;

    public Disciplina save(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> findAll() {
        return disciplinaRepository.findAll();
    }

    public Disciplina alocarAluno(Long disciplinaId, Long alunoId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com ID: " + disciplinaId));
        Aluno aluno = alunoService.findById(alunoId);

        disciplina.getAlunos().add(aluno);
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina atribuirNota(Long disciplinaId, Long alunoId, Double nota) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com ID: " + disciplinaId));
        Aluno aluno = alunoService.findById(alunoId);

        disciplina.getNotas().put(aluno, nota);
        return disciplinaRepository.save(disciplina);
    }

    public List<Aluno> listarAprovados(Long disciplinaId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com ID: " + disciplinaId));

        return disciplina.getNotas().entrySet().stream()
                .filter(entry -> entry.getValue() >= 7.0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Aluno> listarReprovados(Long disciplinaId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com ID: " + disciplinaId));

        return disciplina.getNotas().entrySet().stream()
                .filter(entry -> entry.getValue() < 7.0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
