package com.example.atSpring.controller;

import com.example.atSpring.dto.ProfessorDTO;
import com.example.atSpring.model.Professor;
import com.example.atSpring.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public Professor save(@RequestBody ProfessorDTO professorDTO) {
        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        professor.setEmail(professorDTO.getEmail());
        return professorService.save(professor);
    }
}