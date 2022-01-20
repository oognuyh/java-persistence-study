package com.oognuyh.item11.repository;

import com.oognuyh.item11.model.Student;
import com.oognuyh.item11.model.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    @Query("SELECT s FROM student s WHERE s.teacher = ?1")
    Student findByTeacher(Teacher teacher);
}
