package com.first_spring_project.first_spring_project.models;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    final int ENCRYPTION_CYCLES = 1;
    final int MEMORY = 1024;
    final int NUMBER_OF_THREADS = 1;

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

    public void encryptPassword() {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashedPassword = argon2.hash(ENCRYPTION_CYCLES, MEMORY, NUMBER_OF_THREADS, password);
        password = hashedPassword;
    }
}
