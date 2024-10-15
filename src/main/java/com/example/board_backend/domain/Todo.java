package com.example.board_backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
@Entity
public class Todo extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String content;

    @Setter
    private boolean isDone;

    @Setter
    private LocalDate dueDate;

    protected Todo() { }

    @Builder
    public Todo(String content, boolean isDone, LocalDate dueDate) {
        this.content = content;
        this.isDone = isDone;
        this.dueDate = dueDate;
    }

}
