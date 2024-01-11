package com.example.springboot.controller;

import com.example.springboot.dtos.UsuarioRecordDto;
import com.example.springboot.models.UsuarioModel;
import com.example.springboot.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioModel> saveUsuario(@RequestBody @Valid UsuarioRecordDto usuarioRecordDto) {
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioRecordDto, usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuarioModel));
    }
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioModel>> getAllUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Object> getOneUsuario(@PathVariable(value="id") UUID id) {
        Optional<UsuarioModel> usuarioO = usuarioRepository.findById(id);
        if(usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario not found");

        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioO.get());
    }


    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value="id") UUID id, @RequestBody @Valid UsuarioRecordDto usuarioRecordDto) {

        Optional<UsuarioModel> usuarioO = usuarioRepository.findById(id);
        if(usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario not found.");
        }
        var usuarioModel = usuarioO.get();
        BeanUtils.copyProperties(usuarioRecordDto, usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuarioModel));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Object> deleteUsario(@PathVariable(value="id") UUID id) {
        Optional<UsuarioModel> usuarioO = usuarioRepository.findById(id);
        if(usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario not found.");
        }
        usuarioRepository.delete(usuarioO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deleted sucessfully.");
    }
}

