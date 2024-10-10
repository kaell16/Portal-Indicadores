package org.portal.portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.portal.portal.exceptions.ExceptionNotFound;
import org.portal.portal.models.PapelModel;
import org.portal.portal.repositories.PapelRepository;
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
public class PapelController {

    @Autowired
    private PapelRepository papelRepository;

    @GetMapping("/allIndicadoresPapel")
    @Operation(summary = "Lista todos os dados do indicador de papel", method = "GET")
    public ResponseEntity<List<PapelModel>> getAllPapel() { return ResponseEntity.status(HttpStatus.OK).body(papelRepository.findAll()); }

    @GetMapping("/getPapel/{id}")
    @Operation(summary = "Busca as informações do indicador de papel usando o código", method = "GET")
    public ResponseEntity<Object> getPapelModelById(@PathVariable(value = "id") Long idIndicador) {
        Optional<PapelModel> papelModel = papelRepository.findById(idIndicador);

        if(papelModel.isEmpty()) { throw new ExceptionNotFound(); }

        return ResponseEntity.status(HttpStatus.OK).body(papelModel.get());
    }

    @DeleteMapping("/deletePapel/{id}")
    @Operation(summary = "Deleta as informações do indicador de papel", method = "DELETE")
    public ResponseEntity<Object> deletePapel(@PathVariable(value = "id") Long idIndicador) {
        Optional<PapelModel> papelModel = papelRepository.findById(idIndicador);

        if(papelModel.isEmpty()) { throw new ExceptionNotFound(); }

        papelRepository.delete(papelModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Indicador deletado com sucesso");
    }
}
