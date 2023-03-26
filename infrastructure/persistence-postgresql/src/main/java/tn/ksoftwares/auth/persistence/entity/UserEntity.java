package tn.ksoftwares.auth.persistence.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "u_email", name = "UNIQUE_EMAIL"),
        @UniqueConstraint(columnNames = "u_username", name = "UNIQUE_USERNAME") })
@Entity
public class UserEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "u_id")
    private UUID id;

    @Column(name = "u_firstname", nullable = false)
    private String firstname;

    @Column(name = "u_middlename")
    private String middlename;

    @Column(name = "u_lastname", nullable = false)
    private String lastname;

    @Column(name = "u_email")
    private String email;

    @Column(name = "u_username", nullable = false)
    private String username;

    @Column(name = "u_password")
    private String password;
}
