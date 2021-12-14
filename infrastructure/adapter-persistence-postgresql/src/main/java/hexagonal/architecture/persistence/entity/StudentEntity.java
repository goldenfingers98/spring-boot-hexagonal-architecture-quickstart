package hexagonal.architecture.persistence.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "Students")
public class StudentEntity {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false, name = "s_id")
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "s_firstName")
    private String firstName;

    @Column(name = "s_lastName")
    private String lastName;

    @Column(name = "s_email")
    private String email;

   
    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public StudentEntity() {
    }

    
}
