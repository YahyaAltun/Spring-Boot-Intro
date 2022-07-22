package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
    @RequestMapping("/students")
    public class StudentController {

        Logger logger= LoggerFactory.getLogger(StudentController.class);
        @Autowired
        private StudentService studentService;

        @GetMapping("/welcome")
        public String welcome(HttpServletRequest request){
            logger.warn("--------Welcome{}",request.getServletPath());
            return "Welcome to student controller";
        }

        @PostMapping
        public ResponseEntity<Map<String,String>> createStudent(@Valid @RequestBody Student student){
            studentService.createStudent(student);

            Map<String,String> map=new HashMap<>();
            map.put("message","Student created successfuly");
            map.put("status","true");
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }

        @GetMapping
    public ResponseEntity<List<Student>> getAll(){
            List<Student> students = studentService.getAll();
            return ResponseEntity.ok(students);
        }

        //http://localhost:8080/students?id=1
        @GetMapping("/query")
    public ResponseEntity<Student> getStudent(@RequestParam("id") Long id){
            Student student=studentService.findStudent(id);
            return ResponseEntity.ok(student);
        }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentWithPath(@PathVariable("id") Long id){
        Student student=studentService.findStudent(id);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);

        Map<String,String> map=new HashMap<>();
        map.put("message","Student deleted successfuly");
        map.put("status","true");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO){
            studentService.updateStudent(id,studentDTO);
        Map<String,String> map=new HashMap<>();
        map.put("message","Student updated successfuly");
        map.put("status","true");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //http://localhost:8080/students/pages?page=0&size=3&sort=id&direction=ASC
    @GetMapping("/pages")
    public ResponseEntity<Page<Student>> getAllWithPage(@RequestParam("page")int page, @RequestParam("size") int size,
                                @RequestParam("sort")String prop, @RequestParam("direction")Sort.Direction direction){
        Pageable pageable= PageRequest.of(page,size,Sort.by(direction,prop));
        Page<Student> studentPage=studentService.getAllWithPage(pageable);
        return ResponseEntity.ok(studentPage);
    }

    @GetMapping("/querylastname")
    public ResponseEntity<List<Student>> getStudentByLastName(@RequestParam("lastName") String lastName){
        List<Student> studentList=studentService.findStudent(lastName);
        return ResponseEntity.ok(studentList);
    }

    //http://localhost:8080/students/grade/15
    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<Student>> getStudentsEqualsGrade(@PathVariable("grade") Integer grade){
            List<Student> list=studentService.findAllEqualsGrade(grade);
            return ResponseEntity.ok(list);
    }

    //
    @GetMapping("/query/dto")
    public ResponseEntity<StudentDTO> getStudentDTO(@RequestParam("id") Long id){
        StudentDTO studentDTO=studentService.findStudentDTOById(id);
        return ResponseEntity.ok(studentDTO);
    }
}
