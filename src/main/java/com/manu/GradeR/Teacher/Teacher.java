package com.manu.GradeR.Teacher;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Teacher {

    @Id
    private Long Id;
    private String username;
    private String password;
    private String role;

    public Teacher(Long id, String username, String password, String role) {
        Id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Teacher() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
