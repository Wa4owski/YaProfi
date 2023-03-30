package com.example.yaprofi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupCreationRequest {
    @NotNull(message = "имя группы не может быть null")
    @NotBlank(message = "имя группы не может быть пустым")
    String name;
    String description;
}
