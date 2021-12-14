package hexagonal.architecture.application.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hexagonal.architecture.application.service.adapter.StudentServiceApdater;
import hexagonal.architecture.application.service.api.StudentService;
import hexagonal.architecture.domain.spi.StudentPersistencePort;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public StudentService getStudentService(StudentPersistencePort studentPersistencePort){
        return new StudentServiceApdater(studentPersistencePort);
    }
    
}
