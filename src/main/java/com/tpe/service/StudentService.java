package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ResorceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentService {

    List<Student> getAll();
    List<Student> findStudent(String lastName);
    Student findStudent(Long id) throws ResorceNotFoundException;
    void createStudent(Student student);
    void updateStudent(Long id, StudentDTO student);
    void deleteStudent(Long id);

    Page<Student> getAllWithPage(Pageable pageable);

    List<Student> findAllEqualsGrade(Integer grade);
    StudentDTO findStudentDTOById(Long id);

}
