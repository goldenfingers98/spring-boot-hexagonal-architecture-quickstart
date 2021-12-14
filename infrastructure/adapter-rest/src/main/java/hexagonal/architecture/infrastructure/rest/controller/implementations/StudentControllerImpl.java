package hexagonal.architecture.infrastructure.rest.controller.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hexagonal.architecture.application.service.api.StudentService;
import hexagonal.architecture.domain.model.Student;
import hexagonal.architecture.infrastructure.rest.controller.adapters.StudentController;


@RestController
@RequestMapping("api/")
public class StudentControllerImpl implements StudentController {

    private StudentService studentService;

    public StudentControllerImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentService.getStudents();
        return ResponseEntity.ok(students);
    }

    @Override
    public ResponseEntity<UUID> addStudents(Student student) {
        UUID id = studentService.addStudent(student);
        return new ResponseEntity<UUID>(id, HttpStatus.CREATED);
    }
    
}
