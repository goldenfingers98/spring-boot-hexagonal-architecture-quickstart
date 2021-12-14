package hexagonal.architecture.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hexagonal.architecture.persistence.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    
}
