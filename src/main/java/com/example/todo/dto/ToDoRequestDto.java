package com.example.todo.dto;

import lombok.Getter;

import java.time.LocalDate;


@Getter
public class ToDoRequestDto {
    private String userName;
    private String password;
    private String title;
    private String contents;
}
