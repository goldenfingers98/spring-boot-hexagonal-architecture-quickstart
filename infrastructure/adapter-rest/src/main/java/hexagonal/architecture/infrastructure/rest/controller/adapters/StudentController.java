package hexagonal.architecture.infrastructure.rest.controller.adapters;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hexagonal.architecture.domain.model.Student;

public interface StudentController {
    
    @GetMapping("students")
    ResponseEntity<List<Student>> getStudents();

    @PostMapping("students/add")
    ResponseEntity<UUID> addStudents(@RequestBody Student student);

}
