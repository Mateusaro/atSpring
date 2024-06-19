package com.example.atSpring.controller;

import com.example.atSpring.dto.AlunoDTO;
import com.example.atSpring.model.Aluno;
import com.example.atSpring.service.AlunoService;
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
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void testAddAluno() throws Exception {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome("João");
        alunoDTO.setCpf("12345678900");
        alunoDTO.setEmail("joao@example.com");
        alunoDTO.setTelefone("123456789");
        alunoDTO.setEndereco("Rua A");

        // Convertendo AlunoDTO para Aluno para simular o comportamento do serviço
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setTelefone(alunoDTO.getTelefone());
        aluno.setEndereco(alunoDTO.getEndereco());

        Mockito.when(alunoService.save(Mockito.any(Aluno.class))).thenReturn(aluno);

        mockMvc.perform(post("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alunoDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(alunoDTO)));
    }

    @Test
    @WithMockUser
    public void testGetAllAlunos() throws Exception {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome("João");
        alunoDTO.setCpf("12345678900");
        alunoDTO.setEmail("joao@example.com");
        alunoDTO.setTelefone("123456789");
        alunoDTO.setEndereco("Rua A");

        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setTelefone(alunoDTO.getTelefone());
        aluno.setEndereco(alunoDTO.getEndereco());

        Mockito.when(alunoService.findAll()).thenReturn(Collections.singletonList(aluno));

        mockMvc.perform(get("/alunos"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(alunoDTO))));
    }
}
