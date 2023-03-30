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
public class MemberCreationRequest {
    @NotNull(message = "имя участника не может быть null")
    @NotBlank(message = "имя участника не может быть пустым")
    private String name;
    private String wish;
}
