package de.ait.student.service;

import de.ait.StudentServiceApplication;
import de.ait.student.dto.ScoreDto;
import de.ait.student.dto.StudentAddDto;
import de.ait.student.dto.StudentDto;
import de.ait.student.dto.StudentUpdateDto;

import java.util.List;
import java.util.Set;

public interface StudentService {

    Boolean addStudent(StudentAddDto studentAddDto);
    StudentDto findStudent(Long id);
    StudentDto removeStudent(Long id);
    StudentAddDto updateStudent(Long id, StudentUpdateDto studentUpdateDto);
    Boolean addScore(Long id, ScoreDto scoreDto);
    List<StudentDto> getStudentsByName(String name);
    Long getQuantity(Set<String> names);
    List<StudentDto> getStudent(String exam, Integer minScore);


}
