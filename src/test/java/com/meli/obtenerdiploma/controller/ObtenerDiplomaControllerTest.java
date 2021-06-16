package com.meli.obtenerdiploma.controller;

import com.meli.obtenerdiploma.service.IObtenerDiplomaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ObtenerDiplomaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    IObtenerDiplomaService service;
    @InjectMocks
    private ObtenerDiplomaController obtenerDiplomaController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void analyzeScoresRequestCorrectoTest() throws Exception {
        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/analyzeScores/{studentId}",1))
                        .andDo(print()).andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers. jsonPath("$.message" ).value( "El alumno Alumno 1 ha obtenido un promedio de 4.33. Puedes mejorar.")).andReturn();
        assertEquals("application/json",mvcResult.getResponse().getContentType()) ;
    }

    @Test
    void analyzeScoresRequestErroneoTest() throws Exception {
        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/analyzeScores/{studentId}",10))
                        .andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.description").value("El alumno con Id 10 no se encuentra registrado."))
                        .andReturn();
        assertEquals(404,mvcResult.getResponse().getStatus()) ;
    }

    @Test
    void analyzeScoresRequestTipoErroneoTest() throws Exception {
        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/analyzeScores/{studentId}","test"))
                        .andDo(print())
                        .andReturn();
        assertEquals(400,mvcResult.getResponse().getStatus()) ;
    }
}