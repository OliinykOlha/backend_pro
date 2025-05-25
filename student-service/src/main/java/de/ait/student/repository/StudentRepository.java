package de.ait.student.repository;

import de.ait.student.model.Student;

import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional <Student> findById(Long id);
    void deleteBId(Long id);
    Iterable <Student> findAll();
}
