package org.portal.portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.portal.portal.exceptions.ExceptionNotFound;
import org.portal.portal.models.EnergiaModel;
import org.portal.portal.repositories.EnergiaRepository;
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
public class EnergiaController {

    @Autowired
    EnergiaRepository energiaRepository;

    @GetMapping("/allIndicadoresEnergia")
    @Operation(summary = "Lista todos os dados do indicador energia", method = "GET")
    public ResponseEntity<List<EnergiaModel>> getAllEnergia() { return ResponseEntity.status(HttpStatus.OK).body(energiaRepository.findAll()); }

    @GetMapping("/getEnergia/{id}")
    @Operation(summary = "Busca as informações do indicador de energia usando o código", method = "GET")
    public ResponseEntity<Object> getEnergiaModelById(@PathVariable(value = "id") Long idIndicador) {
        Optional<EnergiaModel> energiaModel = energiaRepository.findById(idIndicador);

        if(energiaModel.isEmpty()) { throw new ExceptionNotFound(); }

        return ResponseEntity.status(HttpStatus.OK).body(energiaModel.get());
    }

    @DeleteMapping("/deleteEnergia/{id}")
    @Operation(summary = "Deleta as informações do indicador de energia", method = "DELETE")
    public ResponseEntity<Object> deleteEnergia(@PathVariable(value = "id") Long idIndicador) {
        Optional<EnergiaModel> energiaModel = energiaRepository.findById(idIndicador);

        if(energiaModel.isEmpty()) { throw new ExceptionNotFound(); }

        energiaRepository.delete(energiaModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Indicador deletado com sucesso");
    }
}
