package com.first_spring_project.first_spring_project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class User {
    @Getter
    @Setter
    @Column(name = "id")
    @Id
    private Long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "last_name")
    private String lastName;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name = "phone")
    private String phone;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    public User() {
        this(
                "",
                "",
                "",
                "",
                "",
                1L);
    }

    public User(String name,
            String lastName,
            String email,
            String phone,
            String password,
            Long id) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.id = id;
    }
}
