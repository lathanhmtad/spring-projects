package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class TodoDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
