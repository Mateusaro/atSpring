package com.example.atSpring.service;

import com.example.atSpring.model.Professor;
import com.example.atSpring.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }
}
