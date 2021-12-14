package hexagonal.architecture.application.service.adapter;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import hexagonal.architecture.application.service.api.StudentService;
import hexagonal.architecture.domain.model.Student;
import hexagonal.architecture.domain.spi.StudentPersistencePort;

public class StudentServiceApdater implements StudentService {

    private StudentPersistencePort studentPersistencePort;

    @Autowired
    public StudentServiceApdater(StudentPersistencePort studentPersistencePort) {
        this.studentPersistencePort = studentPersistencePort;
    }

    @Override
    public UUID addStudent(Student student) {
        studentPersistencePort.addStudent(student);
        return null;
        
    }

    @Override
    public List<Student> getStudents() {
        return studentPersistencePort.getStudents();
    }
    
}
