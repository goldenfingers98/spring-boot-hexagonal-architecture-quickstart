package hexagonal.architecture.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hexagonal.architecture.domain.spi.StudentPersistencePort;
import hexagonal.architecture.persistence.adapter.StudentAdapter;
import hexagonal.architecture.persistence.repository.StudentRepository;

@Configuration
public class JpaAdapterConfiguration {
    
    @Bean
    public StudentPersistencePort getStudentPersistencePort(StudentRepository studentRepository){
        return new StudentAdapter(studentRepository);
    }
    
}
