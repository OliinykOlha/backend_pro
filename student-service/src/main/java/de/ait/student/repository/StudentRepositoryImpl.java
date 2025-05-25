package de.ait.student.repository;

import de.ait.student.model.Student;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StudentRepositoryImpl implements StudentRepository {
    private Map<Long, Student> studentMap = new ConcurrentHashMap<>();

    @Override
    public Student save(Student student) {
        studentMap.put(student.getId(), student);
        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(studentMap.get(id));
    }

    @Override
    public void deleteBId(Long id) {
       studentMap.remove(id);
    }

    @Override
    public Iterable<Student> findAll() {
        return studentMap.values();
    }
}
