package de.ait.student.controller;

import de.ait.student.dto.ScoreDto;
import de.ait.student.dto.StudentAddDto;
import de.ait.student.dto.StudentDto;
import de.ait.student.dto.StudentUpdateDto;
import de.ait.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/student")
    public Boolean addStudent(@RequestBody StudentAddDto studentAddDto) {
        return studentService.addStudent(studentAddDto);
    }

    @GetMapping("/student/{id}")
    public StudentDto findStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @DeleteMapping("/student/{id}")
    public StudentDto removeStudent(@PathVariable Long id) {
        return studentService.removeStudent(id);
    }

    @PatchMapping("/student/{id}")
    public StudentAddDto updateStudent(@PathVariable Long id, @RequestBody StudentUpdateDto studentUpdateDto) {
        return studentService.updateStudent(id, studentUpdateDto);
    }

    @PatchMapping("score/student/{id}")
    public Boolean addScore(@PathVariable Long id, @RequestBody ScoreDto scoreDto) {
        return null;
    }

    @GetMapping("/students/name/{name}")
    public List<StudentDto> getStudentsByName(@PathVariable String name) {
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/quantity/students")
    public Long getQuantity(@RequestParam List<String> names) {
        return studentService.getQuantity(new HashSet<>(names));
    }

    @GetMapping("/students/exam/{exam}/minScore/{minScore}")
    public List<StudentDto> getStudentByMinScore(@PathVariable String exam, @PathVariable Integer minScore) {
        return studentService.getStudent(exam, minScore);
    }
}
