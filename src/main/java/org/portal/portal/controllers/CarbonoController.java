package org.portal.portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.portal.portal.exceptions.ExceptionNotFound;
import org.portal.portal.models.CarbonoModel;
import org.portal.portal.repositories.CarbonoRepository;
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
public class CarbonoController {

    @Autowired
    private CarbonoRepository carbonoRepository;

    @GetMapping("/allIndicadoresCarbono")
    @Operation(summary = "Lista todos os dados do indicador copo", method = "GET")
    public ResponseEntity<List<CarbonoModel>> getAllCarbono() { return ResponseEntity.status(HttpStatus.OK).body(carbonoRepository.findAll()); }

    @GetMapping("/getCarbono/{id}")
    @Operation(summary = "Busca as informações do indicador de carbono usando o código", method = "GET")
    public ResponseEntity<Object> getCarbonoModelById(@PathVariable(value = "id") Long idIndicador) {
        Optional<CarbonoModel> carbonoModel = carbonoRepository.findById(idIndicador);

        if(carbonoModel.isEmpty()) { throw new ExceptionNotFound(); }

        return ResponseEntity.status(HttpStatus.OK).body(carbonoModel.get());
    }

    @DeleteMapping("/deleteCarbono/{id}")
    @Operation(summary = "Deleta as informações do indicador de carbono", method = "DELETE")
    public ResponseEntity<Object> deleteCarbono(@PathVariable(value = "id") Long idIndicador) {
        Optional<CarbonoModel> carbonoModel = carbonoRepository.findById(idIndicador);

        if(carbonoModel.isEmpty()) { throw new ExceptionNotFound(); }

        carbonoRepository.delete(carbonoModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Indicador deletado com sucesso");
    }
}
