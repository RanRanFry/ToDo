package com.example.todo.dto;

import com.example.todo.entity.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TodoResponseDto {
    private Long id;
    private String userName;
    private String title;
    private String contents;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedAt;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.userName = todo.getUserName();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.modifiedAt = todo.getModifiedAt();
    }
}

