package com.meli.obtenerdiploma.service;

import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.repository.IStudentDAO;
import com.meli.obtenerdiploma.repository.IStudentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IStudentServiceTest {

    @Mock
    IStudentRepository studentRepository;
    @Mock
    IStudentDAO studentDAO;
    @InjectMocks
    private StudentService studentService;

    private static StudentDTO student = new StudentDTO();
    private static List<SubjectDTO> subject = new ArrayList();
    private long studentId = 1L;

    @BeforeAll
    static void beforeAll(){
        subject.add(new SubjectDTO("Desarrollo",6.0));
        subject.add(new SubjectDTO("Cocina",4.0));
        subject.add(new SubjectDTO("Lenguaje",3.0));
        student.setStudentName("Alumno 1");
        student.setId(1L);
        student.setSubjects(subject);
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Disabled("Metodo de paso, no es necesario realizar unit test")
    @Test
    void create() {
        //Arrange
        //Act
        //Assert

    }

    @Test
    void read() {
        //Arrange
        //Act
        when(studentDAO.findById(studentId)).thenReturn(student);
        var actual = studentService.read(studentId);
        //Assert
        assertEquals(student.getId(),actual.getId());
    }

    @Disabled("Metodo de paso, no es necesario realizar unit test")
    @Test
    void update() {
        //Arrange
        //Act
        //Assert
    }

    @Test
    void delete() {
        //Arrange
        //Act
        //Assert
    }

    @Test
    void getAllSameSizeTest() {
        //Arrange
        Set<StudentDTO> expected = new HashSet<StudentDTO>();
        expected.add(student);
        //Act
        when(studentRepository.findAll()).thenReturn(expected);
        var actual = studentService.getAll();
        //Assert
        assertEquals(expected,actual);
    }

}