package com.meli.obtenerdiploma.service;

import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.repository.IStudentDAO;
import com.meli.obtenerdiploma.repository.StudentDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObtenerDiplomaServiceTest {

    @Mock
    private static IStudentDAO studentDAO;

    @InjectMocks
    private ObtenerDiplomaService obtenerDiplomaService;

    private static StudentDTO student = new StudentDTO();
    private static List<SubjectDTO> subject = new ArrayList();
    private static long studentId = 1L;


    @BeforeAll
    static void beforeAll(){
        student.setId(1L);
        student.setStudentName("Alumno 1");
        subject.add(new SubjectDTO("Desarrollo",6.0));
        subject.add(new SubjectDTO("Cocina",4.0));
        subject.add(new SubjectDTO("Lenguaje",3.0));
        student.setSubjects(subject);
    }

    @BeforeEach
    void setUp() {
        when(studentDAO.findById(studentId)).thenReturn(student);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void analyzeScoresNotNull() {
        //Arrange
        //act
        var actual = obtenerDiplomaService.analyzeScores(studentId);
        assertNotNull(actual);
    }

    @Test
    void datosSalidaIdenticosEntrada() {
        //Arrange
        var tmp = new StudentDTO();
        tmp.setId(1L);
        tmp.setStudentName("Alumno 1");
        tmp.setSubjects(subject);
        //act
        var actual = obtenerDiplomaService.analyzeScores(studentId);
        assertEquals(tmp.getId(), actual.getId());
    }

    @Test
    void verificarPromedioTest() {
        //Arrange
        double prom = 4.3;
        //act
        var actual = obtenerDiplomaService.analyzeScores(studentId);
        //Assert
        assertEquals(prom,actual.getAverageScore(),0.1);
    }

    @Test
    void verificarMensajeTest() {
        //Arrange
        String msg = "El alumno " + student.getStudentName() + " ha obtenido un promedio de " +4.33+ ". Puedes mejorar.";
        //act
        var actual = obtenerDiplomaService.analyzeScores(studentId);
        //Assert
        assertEquals(msg,actual.getMessage());
    }

    @Test
    void verificarMensajeHonoresTest() {
        //Arrange
        subject.clear();
        //student.setMessage(null);
        subject.add(new SubjectDTO("Desarrollo",10.0));
        subject.add(new SubjectDTO("Cocina",10.0));
        subject.add(new SubjectDTO("Lenguaje",10.0));
        String msg = "El alumno " + student.getStudentName() + " ha obtenido un promedio de " +10+ ". Felicitaciones!";
        //act
        var actual = obtenerDiplomaService.analyzeScores(studentId);
        //Assert
        assertEquals(msg,actual.getMessage());
    }

    /*/ACA INICIAS EL VALIDATOR
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    StudentDTO student = new StudentDTO();
        student.setStudentName("Tomi");
        student.setId(5L);

    SubjectDTO subjectOne = new SubjectDTO("Matematica" , 3.0);
    List<SubjectDTO> listSubjects = new ArrayList<>();
        listSubjects.add(subjectOne);
        student.setSubjects(listSubjects);

    //PASAS LISTA DE RESTRICCIONES
    Set<ConstraintViolation<StudentDTO>> violations = validator.validate(student);
        System.out.println(violations);

    //Comprobas si tuvo restricciones o no
        Assertions.assertTrue(violations.isEmpty());*/
}