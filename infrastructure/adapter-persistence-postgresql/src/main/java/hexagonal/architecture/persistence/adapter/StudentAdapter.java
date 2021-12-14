package hexagonal.architecture.persistence.adapter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import hexagonal.architecture.domain.model.Student;
import hexagonal.architecture.domain.spi.StudentPersistencePort;
import hexagonal.architecture.persistence.entity.StudentEntity;
import hexagonal.architecture.persistence.repository.StudentRepository;

public class StudentAdapter implements StudentPersistencePort {

    private StudentRepository studentRepository;

    
    public StudentAdapter(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UUID addStudent(Student student) {
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(student, studentEntity);
        return studentRepository.save(studentEntity).getId();
        
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll()
        .stream()
        .map(studentEntity->{
            Student student = new Student();
            BeanUtils.copyProperties(studentEntity, student);
            return student;
        })
        .collect(Collectors.toList());
    }
    
}
