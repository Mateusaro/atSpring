package com.example.atSpring.controller;

import com.example.atSpring.model.Disciplina;
import com.example.atSpring.service.DisciplinaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DisciplinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisciplinaService disciplinaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void testAddDisciplina() throws Exception {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Matemática");
        disciplina.setCodigo("MAT101");

        Mockito.when(disciplinaService.save(Mockito.any(Disciplina.class))).thenReturn(disciplina);

        mockMvc.perform(post("/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disciplina)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(disciplina)));
    }

    @Test
    @WithMockUser
    public void testGetAllDisciplinas() throws Exception {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Matemática");
        disciplina.setCodigo("MAT101");

        Mockito.when(disciplinaService.findAll()).thenReturn(Collections.singletonList(disciplina));

        mockMvc.perform(get("/disciplinas"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(disciplina))));
    }
}
