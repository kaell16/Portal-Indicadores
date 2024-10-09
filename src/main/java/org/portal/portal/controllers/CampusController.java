package org.portal.portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.portal.portal.dtos.CampusRecordDto;
import org.portal.portal.exceptions.ExceptionNotFound;
import org.portal.portal.models.CampusModel;
import org.portal.portal.repositories.CampusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CampusController {

    @Autowired
    private CampusRepository campusRepository;

    @GetMapping("/allCampus")
    @Operation(summary = "Lista todos os campus cadastrados", method = "GET")
    public ResponseEntity<List<CampusModel>> getAllCampus() { return ResponseEntity.status(HttpStatus.OK).body(campusRepository.findAll()); }

    @GetMapping("/getCampus/{id}")
    @Operation(summary = "Busca o campus usando o código", method = "GET")
    public ResponseEntity<Object> getCampusById(@PathVariable(value = "id") Long idCampus) {
        Optional<CampusModel> campusModel = campusRepository.findById(idCampus);

        if(campusModel.isEmpty()) { throw new ExceptionNotFound(); }

        return ResponseEntity.status(HttpStatus.OK).body(campusModel.get());
    }

    @PostMapping("/saveCampus")
    @Operation(summary = "Realiza o cadastro de campus da UTFPR", method = "POST")
    public ResponseEntity<CampusModel> saveCampus(@RequestBody @Valid CampusRecordDto campusRecordDto) {
        var campusModel = new CampusModel();
        BeanUtils.copyProperties(campusRecordDto, campusModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(campusRepository.save(campusModel));
    }


    @PutMapping("/updateCampus/{id}")
    @Operation(summary = "Atualiza o cadastro do campus", method = "PUT")
    public ResponseEntity<Object> updateCampus(@PathVariable(value = "id") Long idCampus, @RequestBody @Valid CampusRecordDto campusRecordDto) {
        Optional<CampusModel> campusModel = campusRepository.findById(idCampus);

        if(campusModel.isEmpty()) { throw new ExceptionNotFound(); }

        var campusModelSave = campusModel.get();
        BeanUtils.copyProperties(campusRecordDto, campusModelSave);

        return ResponseEntity.status(HttpStatus.OK).body(campusRepository.save(campusModelSave));
    }

    @DeleteMapping("/deleteCampus/{id}")
    @Operation(summary = "Deleta o cadastro do campus", method = "DELETE")
    public ResponseEntity<Object> deleteCampus(@PathVariable(value = "id") Long idCampus) {
        Optional<CampusModel> campusModel = campusRepository.findById(idCampus);

        if(campusModel.isEmpty()) { throw new ExceptionNotFound(); }

        campusRepository.delete(campusModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Campus deletado com sucesso");
    }
}
