package hexagonal.architecture.domain.spi;

import java.util.List;
import java.util.UUID;

import hexagonal.architecture.domain.model.Student;

public interface StudentPersistencePort {

    UUID addStudent(Student student);
    
    List<Student> getStudents();
    
}
