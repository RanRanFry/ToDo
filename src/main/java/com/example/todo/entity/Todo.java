package com.example.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Todo {


    private Long id;
    private String userName;
    private String password;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public Todo(String userName, String password,String title, String contents) {
        this.userName = userName;
        this.password = password;
        this.title = title;
        this.contents = contents;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

}
