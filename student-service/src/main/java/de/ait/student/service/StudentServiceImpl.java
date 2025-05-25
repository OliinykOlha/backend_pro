package de.ait.student.service;

import de.ait.student.dto.ScoreDto;
import de.ait.student.dto.StudentAddDto;
import de.ait.student.dto.StudentDto;
import de.ait.student.dto.StudentUpdateDto;
import de.ait.student.dto.exeptions.StudentNotFoundException;
import de.ait.student.model.Student;
import de.ait.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

@Component
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer;


    @Override
    public Boolean addStudent(StudentAddDto studentAddDto) {
        if (repository.findById(studentAddDto.getId()).isPresent()) {
            return false;
        }
        Student student = new Student(studentAddDto.getId(), studentAddDto.getName(), studentAddDto.getPassword());
        repository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(Long id) {
        Student student = repository.findById(id).orElseThrow(StudentNotFoundException::new);
        return new StudentDto(student.getId(), student.getName(), student.getScores());
    }

    @Override
    public StudentDto removeStudent(Long id) {
        Student student = repository.findById(id).orElse(null);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        repository.deleteBId(id);
        return new StudentDto(student.getId(), student.getName(), student.getScores());
    }

    @Override
    public StudentAddDto updateStudent(Long id, StudentUpdateDto studentUpdateDto) {
        Student student = repository.findById(id).orElse(null);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        if (studentUpdateDto.getName() != null) {
            student.setName(studentUpdateDto.getName());
        }
        if (studentUpdateDto.getPassword() != null) {
            student.setPassword(studentUpdateDto.getPassword());
        }

        return new StudentAddDto(student.getId(), student.getName(), student.getPassword());
    }

    @Override
    public Boolean addScore(Long id, ScoreDto scoreDto) {
        Student student = repository.findById(id).orElse(null);
        return student.addScore(scoreDto.getExamName(), scoreDto.getScore());

    }

    @Override
    public List<StudentDto> getStudentsByName(String name) {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .filter(s -> name.equalsIgnoreCase(s.getName()))
                .map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
                .toList();
    }

    @Override
    public Long getQuantity(Set<String> names) {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(s-> names.contains(s.getName()))
                .count();

    }

    @Override
    public List<StudentDto> getStudent(String exam, Integer minScore) {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .filter(s -> s.getScores().containsKey(exam) && s.getScores().get(exam) > minScore )
                .map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
                .toList();
    }

    private static StudentDto toDto(Student student) {
        return new StudentDto(student.getId(), student.getName(), student.getScores());
    }
}
