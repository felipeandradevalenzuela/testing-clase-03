package com.meli.obtenerdiploma.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.service.IStudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    IStudentService studentService;
    @InjectMocks
    StudentController studentController;
    private static StudentDTO student = new StudentDTO();
    private static List<SubjectDTO> subject = new ArrayList();

    @BeforeAll
    static void setUp() {
        subject.add(new SubjectDTO("Algoritmos",4.0));
        subject.add(new SubjectDTO("Cumbia",10.0));
        subject.add(new SubjectDTO("Cocina",6.0));
        student.setStudentName("Alumno 2");
        student.setId(5L);
        student.setSubjects(subject);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerStudentCorrectoTest() throws Exception {
        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.post("/student/registerStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        assertEquals(200,mvcResult.getResponse().getStatus()) ;
    }

    @Test
    void registerStudentFallidoTest() throws Exception {
        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.post("/student/registerStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(null)))
                        .andDo(print())
                        .andReturn();
        assertEquals(400,mvcResult.getResponse().getStatus()) ;
    }

    @Test
    void getStudentCorrectoTest() throws Exception {
        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/student/getStudent/{id}",2))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        assertEquals(200,mvcResult.getResponse().getStatus()) ;
    }

    @Test
    void getStudentIncorrectoTest() throws Exception {
        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/student/getStudent/{id}",10))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers. jsonPath("$.name" ).value( "StudentNotFoundException"))
                        .andExpect(MockMvcResultMatchers. jsonPath("$.description" ).value( "El alumno con Id 10 no se encuentra registrado."))
                        .andReturn();
        assertEquals(404,mvcResult.getResponse().getStatus()) ;
    }

    @Test
    void modifyStudentCorrectoTest() throws Exception {
        student.setStudentName("Alumno 2 modificado");
        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.post("/student/modifyStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                        .andDo(print())
                        .andReturn();
        assertEquals(200,mvcResult.getResponse().getStatus()) ;
    }

    @Test
    void removeStudentCorrectoTest() throws Exception {
        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/student/removeStudent/{id}",3))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        assertEquals(200,mvcResult.getResponse().getStatus()) ;
    }

    @Test
    void listStudents() throws Exception {
        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/student/listStudents"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        assertEquals("application/json",mvcResult.getResponse().getContentType()) ;
    }
}