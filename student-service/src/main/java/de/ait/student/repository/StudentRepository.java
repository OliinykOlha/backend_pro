package de.ait.student.repository;

import de.ait.student.model.Student;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Stream<Student> findByNameIgnoreCase(String name);
    Long countByNameInIgnoreCase(Set<String> names);
    @Query("{ 'scores.?0': { $gt: ?1 } }")
    Stream<Student> findByExamAndScoresGreaterThan(String exam, Integer score);
}
