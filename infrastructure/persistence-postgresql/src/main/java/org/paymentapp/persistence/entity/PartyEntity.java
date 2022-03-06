package org.paymentapp.persistence.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "Parties")
public class PartyEntity {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false, name = "s_id")
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "p_firstname")
    private String firstname;

    @Column(name = "p_middlename")
    private String middlename;

    @Column(name = "p_lastname")
    private String lastname;

    @Column(name = "p_email")
    private String email;

    @Column(name = "p_phone")
    private Long phone;

    @Column(name = "p_address")
    private String address;

    @Column(name = "p_dateOfBirth")
    private Date dateOfBirth;

    public PartyEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(Date date){
        this.dateOfBirth = date;
    }

    public Date getDateOfBirth(){
        return this.dateOfBirth;
    }

    @Override
    public String toString() {
        return "PartyEntity [id=" + id + "]";
    }

    

}
