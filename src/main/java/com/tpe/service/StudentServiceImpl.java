package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResorceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findStudent(String lastName) {

        return studentRepository.findByLastName(lastName);
    }

    @Override
    public Student findStudent(Long id) throws ResorceNotFoundException {
        return studentRepository.findById(id).
                orElseThrow(()->new ResorceNotFoundException("Student not found with id: "+id));
    }

    @Override
    public void createStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())){
            throw new ConflictException("Email already exist");
        }
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(Long id,StudentDTO studentDTO) {
        Boolean emailExist=studentRepository.existsByEmail(studentDTO.getEmail());
        Student student=findStudent(id);

        if (emailExist&&studentDTO.getEmail().equals(student.getEmail())){
            throw new ConflictException("Email already exist");
        }
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setGrade(studentDTO.getGrade());
        student.setEmail(studentDTO.getEmail());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student=findStudent(id);
        studentRepository.delete(student);
    }

    @Override
    public Page<Student> getAllWithPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<Student> findAllEqualsGrade(Integer grade) {
        return studentRepository.findAllEqualsGrade(grade);
    }

    @Override
    public StudentDTO findStudentDTOById(Long id) {
        return studentRepository.findStudentDTOById(id).
                orElseThrow(()-> new ResorceNotFoundException("Student not found with id: "+id));
    }
}
