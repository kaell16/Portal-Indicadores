package org.portal.portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.portal.portal.exceptions.ExceptionNotFound;
import org.portal.portal.models.CopoModel;
import org.portal.portal.repositories.CopoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CopoController {

    @Autowired
    private CopoRepository copoRepository;

    @GetMapping("/allIndicadoresCopo")
    @Operation(summary = "Lista todos os dados do indicador copo", method = "GET")
    public ResponseEntity<List<CopoModel>> getAllCopo() { return ResponseEntity.status(HttpStatus.OK).body(copoRepository.findAll()); }

    @GetMapping("/getCopo/{id}")
    @Operation(summary = "Busca as informações do indicador de copos usando o código", method = "GET")
    public ResponseEntity<Object> getCopoModelById(@PathVariable(value = "id") Long idIndicador) {
        Optional<CopoModel> copoModel = copoRepository.findById(idIndicador);

        if(copoModel.isEmpty()) { throw new ExceptionNotFound(); }

        return ResponseEntity.status(HttpStatus.OK).body(copoModel.get());
    }

    @DeleteMapping("/deleteCopo/{id}")
    @Operation(summary = "Deleta as informações do indicador de copos", method = "DELETE")
    public ResponseEntity<Object> deleteCopo(@PathVariable(value = "id") Long idIndicador) {
        Optional<CopoModel> copoModel = copoRepository.findById(idIndicador);

        if(copoModel.isEmpty()) { throw new ExceptionNotFound(); }

        copoRepository.delete(copoModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Indicador deletado com sucesso");
    }
}
