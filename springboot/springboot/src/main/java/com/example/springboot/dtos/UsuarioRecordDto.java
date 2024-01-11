package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UsuarioRecordDto(@NotBlank String name, @NotNull Long cpf, @NotNull Date data_nascimento){

}
