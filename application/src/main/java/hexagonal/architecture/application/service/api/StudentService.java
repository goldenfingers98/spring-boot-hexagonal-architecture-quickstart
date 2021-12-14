package hexagonal.architecture.application.service.api;

import java.util.List;
import java.util.UUID;

import hexagonal.architecture.domain.model.Student;

public interface StudentService {

    UUID addStudent(Student student);

    List<Student> getStudents();
}
