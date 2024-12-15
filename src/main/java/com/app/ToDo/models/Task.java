package com.app.ToDo.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String title;

    @Column(nullable = false)
    public String description;

    public boolean completed;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
