package com.example.todo.dto;

import com.example.todo.entity.Todo;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class TodoResponseDto {
    private Long id;
    private String userName;
    private String contents;
    private LocalDate createdAt;
    private LocalDate modifiedAt;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.userName = todo.getUserName();
        this.contents = todo.getContents();
        this.createdAt = LocalDate.now();
        this.modifiedAt = LocalDate.now();
    }
}

