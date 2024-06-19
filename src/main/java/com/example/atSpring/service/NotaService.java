package com.example.atSpring.service;

import com.example.atSpring.model.Disciplina;
import com.example.atSpring.model.Nota;
import com.example.atSpring.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {
    @Autowired
    private NotaRepository notaRepository;

    public Nota save(Nota nota) {
        return notaRepository.save(nota);
    }

    public List<Nota> findAprovados(Disciplina disciplina) {
        return notaRepository.findByDisciplinaAndValorGreaterThanEqual(disciplina, 7.0);
    }

    public List<Nota> findReprovados(Disciplina disciplina) {
        return notaRepository.findByDisciplinaAndValorLessThan(disciplina, 7.0);
    }
}
