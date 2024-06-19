package com.example.atSpring.controller;

import com.example.atSpring.dto.*;
import com.example.atSpring.model.*;
import com.example.atSpring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public Aluno save(@RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setTelefone(alunoDTO.getTelefone());
        aluno.setEndereco(alunoDTO.getEndereco());
        return alunoService.save(aluno);
    }

    @GetMapping
    public List<Aluno> findAll() {
        return alunoService.findAll();
    }
}

