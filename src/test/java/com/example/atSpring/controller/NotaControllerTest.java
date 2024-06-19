package com.example.atSpring.controller;

import com.example.atSpring.dto.NotaDTO;
import com.example.atSpring.model.Disciplina;
import com.example.atSpring.model.Nota;
import com.example.atSpring.service.NotaService;
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

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NotaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotaService notaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddNota() throws Exception {
        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setValor(8.5);

        Nota nota = new Nota();
        nota.setId(1L);
        nota.setValor(notaDTO.getValor());
        // Simular o serviço para salvar a nota
        Mockito.when(notaService.save(Mockito.any(Nota.class))).thenReturn(nota);

        mockMvc.perform(MockMvcRequestBuilders.post("/notas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notaDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(nota)));
    }

    @Test
    public void testFindAprovados() throws Exception {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(1L);

        Nota nota1 = new Nota();
        nota1.setId(1L);
        nota1.setValor(8.5);
        nota1.setDisciplina(disciplina);

        // Simular o serviço para encontrar notas aprovadas
        Mockito.when(notaService.findAprovados(Mockito.any(Disciplina.class))).thenReturn(Arrays.asList(nota1));

        mockMvc.perform(MockMvcRequestBuilders.get("/notas/aprovados")
                        .param("disciplinaId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(nota1))));
    }

    @Test
    public void testFindReprovados() throws Exception {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(1L);

        Nota nota2 = new Nota();
        nota2.setId(2L);
        nota2.setValor(6.0);
        nota2.setDisciplina(disciplina);

        // Simular o serviço para encontrar notas reprovadas
        Mockito.when(notaService.findReprovados(Mockito.any(Disciplina.class))).thenReturn(Arrays.asList(nota2));

        mockMvc.perform(MockMvcRequestBuilders.get("/notas/reprovados")
                        .param("disciplinaId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(nota2))));
    }
}
