package com.meli.obtenerdiploma.repository;

import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp(){
        studentRepository = new StudentRepository();
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void findAll() {
        //Arrange
            List<SubjectDTO> subjectList = new ArrayList<>();
            subjectList.add(new SubjectDTO("Matemática",10.0));
            subjectList.add(new SubjectDTO("Física",8.0));
            subjectList.add(new SubjectDTO("Química",4.0));
            StudentDTO tmp = new StudentDTO(2L,"Pedro",null,null,subjectList);

            Set<StudentDTO> expected = new HashSet<StudentDTO>();
            expected.add(tmp);

            subjectList.clear();
            subjectList.add(new SubjectDTO("Desarrollo",6.0));
            subjectList.add(new SubjectDTO("Cocina",4.0));
            subjectList.add(new SubjectDTO("Lenguaje",3.0));
            StudentDTO tmp2 = new StudentDTO(1L,"Alumno 1",null,null,subjectList);

            expected.add(tmp2);
        //Act
        var valorActual = studentRepository.findAll();
        //Assert
        assertEquals(expected.size(),valorActual.size());
    }
}