package de.ait.student.service;

import de.ait.student.dto.ScoreDto;
import de.ait.student.dto.StudentAddDto;
import de.ait.student.dto.StudentDto;
import de.ait.student.dto.StudentUpdateDto;
import de.ait.student.dto.exeptions.StudentNotFoundException;
import de.ait.student.model.Student;
import de.ait.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private ModelMapper modelMapper;




    @Override
    public Boolean addStudent(StudentAddDto studentAddDto) {
        if (repository.existsById(studentAddDto.getId())) {
            return false;
        }
        Student student = modelMapper.map(studentAddDto, Student.class);
        repository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(Long id) {
        Student student = repository.findById(id).orElseThrow(StudentNotFoundException::new);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto removeStudent(Long id) {
        Student student = repository.findById(id).orElse(null);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        repository.deleteById(id);
        return modelMapper.map(student, StudentDto.class);
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
        return modelMapper.map(student, StudentAddDto.class);
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
                .map(s -> modelMapper.map(s, StudentDto.class))
                .toList();
    }

    @Override
    public Long getQuantity(Set<String> names) {
        return repository.countByNameInIgnoreCase(names);
    }

    @Override
    public List<StudentDto> getStudent(String exam, Integer minScore) {
        return repository.findByExamAndScoresGreaterThan(exam, minScore)
                .map(s -> modelMapper.map(s, StudentDto.class))
                .toList();
    }


}
