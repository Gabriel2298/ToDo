package com.app.ToDo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Date dateOfBirth;
    private LocalDate registrationDate;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String userName;
    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> task;
}
