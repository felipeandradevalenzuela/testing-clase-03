package com.meli.obtenerdiploma.repository;

import com.meli.obtenerdiploma.exception.StudentNotFoundException;
import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IStudentDAOTest {

    private static StudentDAO studentDAO;
    private static StudentDTO student = new StudentDTO();
    private static List<SubjectDTO> subject = new ArrayList();

    @BeforeAll
    public static void setUpAll(){
        studentDAO = new StudentDAO();
        subject.add(new SubjectDTO("Desarrollo",6.0));
        subject.add(new SubjectDTO("Cocina",4.0));
        subject.add(new SubjectDTO("Lenguaje",3.0));
        student.setStudentName("Alumno 1");
        student.setSubjects(subject);
        //student.setMessage("Se graduo...");
        //student.setAverageScore(4.3);
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @AfterAll
    public static void tearDownAll(){
        studentDAO = null;
        student = null;
    }

    @Test
    void save() {
        //Arrange
        student.setId(4L);
        //Act
        studentDAO.save(student);
        //Assert
        assertEquals(true,studentDAO.exists(student));
        studentDAO.delete(4L);
    }

    @Test
    void deleteTrueTest() {
        //Arrange
        List<SubjectDTO> subjectList = new ArrayList<>();
        subjectList.add(new SubjectDTO("Matemática",10.0));
        subjectList.add(new SubjectDTO("Física",8.0));
        subjectList.add(new SubjectDTO("Química",4.0));
        StudentDTO expected = new StudentDTO(2L,"Pedro",null,null,subjectList);
        //Act
        var valorActual = studentDAO.delete(2L);
        //Assert
        assertEquals(true,valorActual);
        studentDAO.save(expected);

    }

    @Test
    void deleteFalseTest() {
        //Arrange
        //Act
        var valorActual = studentDAO.delete(5L);
        //Assert
        assertEquals(false,valorActual);
    }

    @Test
    void existsTrueTest() {
        //Arrange
        student.setId(1L);
        //Act
        var valorActual = studentDAO.exists(student);
        //Assert
        assertEquals(true,valorActual);

    }

    @Test
    void existsFalseTest() {
        //Arrange
        student.setId(6L);
        //Act
        var valorActual = studentDAO.exists(student);
        //Assert
        assertEquals(false,valorActual);

    }

    @Test
    void findById() {
        //Arrange
        //Act
        //Assert
        assertThrows(StudentNotFoundException.class, ()->studentDAO.findById(6L), "No existe un usuario con el ID 6");
    }
}