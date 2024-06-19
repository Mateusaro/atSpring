package com.example.atSpring.controller;

import com.example.atSpring.dto.NotaDTO;
import com.example.atSpring.model.Disciplina;
import com.example.atSpring.model.Nota;
import com.example.atSpring.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {
    @Autowired
    private NotaService notaService;

    @PostMapping
    public Nota save(@RequestBody NotaDTO notaDTO) {
        Nota nota = new Nota();
        nota.setValor(notaDTO.getValor());
        // Fetch Aluno and Disciplina by ID (not shown here)
        return notaService.save(nota);
    }

    @GetMapping("/aprovados")
    public List<Nota> findAprovados(@RequestParam Long disciplinaId) {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);
        return notaService.findAprovados(disciplina);
    }

    @GetMapping("/reprovados")
    public List<Nota> findReprovados(@RequestParam Long disciplinaId) {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);
        return notaService.findReprovados(disciplina);
    }
}
