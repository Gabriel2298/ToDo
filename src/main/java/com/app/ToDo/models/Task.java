package com.app.ToDo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@Entity
@Table(name = "tasks")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @ManyToOne
    private User user;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
