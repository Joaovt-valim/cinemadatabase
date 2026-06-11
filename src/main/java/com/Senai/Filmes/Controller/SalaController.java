package com.Senai.Filmes.Controller;


import com.Senai.Filmes.DTO.Request.SalaRequest;
import com.Senai.Filmes.DTO.Response.SalaResponse;
import com.Senai.Filmes.Model.Sala;
import com.Senai.Filmes.Service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/salas")
@Tag(name="sala", description = "Endpoint para gerencoiamento de salas")
public class SalaController {

    @Autowired
    private SalaService salaService;


    @GetMapping
    @Operation(summary = "Listar todas as salas", description = "Rota para listar todas as salas")

    public ResponseEntity<List<SalaResponse>> listarTodassalas(){
        List<SalaResponse> sala = salaService.listarTodassalas();

        if(sala.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sala, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<SalaResponse> buscarSalaporId(@PathVariable UUID id){
        return new ResponseEntity<>(salaService.buscarPorIdSala(id),HttpStatus.OK);

    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "criar Sala", description = "Rota para cadastrar a sala")
    //@PreAuthorize("has")
    public ResponseEntity<SalaResponse>criarSala(@RequestBody SalaRequest salaRequest){
        return new ResponseEntity<>(salaService.cadastrarSala(salaRequest), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SalaResponse>atualizar(@PathVariable  UUID id, @RequestBody SalaRequest salaRequest ){
        return new ResponseEntity<>(salaService.atualizarSala(id, salaRequest), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> apagar(@PathVariable UUID id){
        salaService.apagarSala(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}