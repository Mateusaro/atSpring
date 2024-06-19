package com.example.atSpring.repository;

import com.example.atSpring.model.Disciplina;
import com.example.atSpring.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByDisciplinaAndValorGreaterThanEqual(Disciplina disciplina, Double valor);
    List<Nota> findByDisciplinaAndValorLessThan(Disciplina disciplina, Double valor);
}
