package de.ait.student.service;

import de.ait.student.dto.ScoreDto;
import de.ait.student.dto.StudentAddDto;
import de.ait.student.dto.StudentDto;
import de.ait.student.dto.StudentUpdateDto;
import de.ait.student.dto.exeptions.StudentNotFoundException;
import de.ait.student.model.Student;
import de.ait.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    @Autowired
    private PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer;



    @Override
    public Boolean addStudent(StudentAddDto studentAddDto) {
        if (repository.existsById(studentAddDto.getId())) {
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
        repository.deleteById(id);
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

        repository.save(student);
        return new StudentAddDto(student.getId(), student.getName(), student.getPassword());
    }

    @Override
    public Boolean addScore(Long id, ScoreDto scoreDto) {
        Student student = repository.findById(id).orElse(null);
        boolean result = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
        repository.save(student);
        return result;

    }

    @Override
    public List<StudentDto> getStudentsByName(String name) {
        return  repository.findByNameIgnoreCase(name)
                .filter(s -> name.equalsIgnoreCase(s.getName()))
                .map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
                .toList();
    }

    @Override
    public Long getQuantity(Set<String> names) {
        return repository.countByNameInIgnoreCase(names);
    }

    @Override
    public List<StudentDto> getStudent(String exam, Integer minScore) {
        return repository.findByExamAndScoresGreaterThan(exam, minScore)
                .map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
                .toList();
    }


}
