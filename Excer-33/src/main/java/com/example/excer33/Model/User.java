package com.example.excer33.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Enter the name")
    @Size(min = 4,message = "Enter name above 4 characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;
    @NotEmpty(message = "Enter the username")
    @Size(min = 4,message = "Enter name above 4 characters")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String username;
    @NotEmpty(message = "Enter the password")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;
    @NotEmpty(message = "Enter the email")
    @Email
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;
    @NotEmpty(message = "Enter the role")
    @Pattern(regexp = "^(user|admin)$",message = "Enter user or admin only")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;
    @NotNull(message = "Enter the age")
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer age;
}
