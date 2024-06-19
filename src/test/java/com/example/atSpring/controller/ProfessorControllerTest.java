package com.example.atSpring.controller;

import com.example.atSpring.model.Professor;
import com.example.atSpring.service.ProfessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorService professorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddProfessor() throws Exception {
        // Criação de um objeto Professor para simular a requisição POST
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setNome("João da Silva");
        professor.setEmail("joao.silva@example.com");

        // Configura o comportamento esperado do serviço ao salvar um professor
        Mockito.when(professorService.save(Mockito.any(Professor.class))).thenReturn(professor);

        // Realiza a requisição POST para adicionar um professor
        mockMvc.perform(MockMvcRequestBuilders.post("/professores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(professor)))
                .andExpect(status().isOk())  // Espera que o status retornado seja OK (200)
                .andExpect(content().json(objectMapper.writeValueAsString(professor)));  // Verifica se o corpo da resposta é o professor salvo
    }
}
