package com.tpe.repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//Repository interface
//CRUDRepository-CRUD Operasyonları
//JPARepository-->CRUD + PagingAndSortingRepository
//PagingAndSortingRepository-->Paging ve Sorting
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.grade=:pGrade")
    List<Student> findAllEqualsGrade(@Param("pGrade") Integer grade);

    @Query(value = "select * from Student s where s.grade=:pGrade",nativeQuery = true)
    List<Student> findAllEqualsGradeWithSQL(@Param("pGrade") Integer grade);

    //JPQL
    @Query("select new com.tpe.dto.StudentDTO(s) from Student s where s.id=:id")
    Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);

    List<Student> findByLastName(String lastName);

    Boolean existsByEmail(String email) throws ConflictException;
}
