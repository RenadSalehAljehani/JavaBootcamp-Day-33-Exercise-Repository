package com.example.usersmanagementsoftware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "system-user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name can't be empty.")
    @Size(min = 5, message = "Name length must be more than 4 charecters.")
    @Column(columnDefinition = "varchar(10) not null")
    @Check(constraints = "length(name) > 4")
    private String name;

    @NotEmpty(message = "Username can't be empty.")
    @Size(min = 5, message = "Username length must be more than 4 charecters.")
    @Column(columnDefinition = "varchar(10) not null unique")
    @Check(constraints = "length(username) > 4")
    private String username;

    @NotEmpty(message = "Password can't be empty.")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @Email(message = "Invalid email format.")
    @NotEmpty(message = "Email can't be empty.")
    @Column(columnDefinition = "varchar(255) not null unique")
    @Check(constraints = "email LIKE '_%@_%._%'")
    private String email;

    @NotEmpty(message = "Role can't be empty.")
    @Pattern(regexp = "^(?i)(user|admin)$", message = "Role must be either user or admin.")
    @Column(columnDefinition = "varchar(5) not null")
    @Check(constraints = "LOWER(role) = 'user' OR LOWER(role) = 'admin'")
    private String role;

    @NotNull(message = "Age can't be empty.")
    @Positive(message = "Age must be a positive number.")
    @Column(columnDefinition = "int not null")
    @Check(constraints = "age > 0")
    private Integer age;
}