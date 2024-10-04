package org.portal.portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.portal.portal.dtos.TipoResiduoRecordDto;
import org.portal.portal.exceptions.ExceptionNotFound;
import org.portal.portal.models.TipoResiduoModel;
import org.portal.portal.repositories.TipoResiduoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TipoResiduoController {

    @Autowired
    private TipoResiduoRepository tipoResiduoRepository;

    @GetMapping("/allTipoResiduo")
    @Operation(summary = "Lista todos os tipos de resíduos cadastrados", method = "GET")
    public ResponseEntity<List<TipoResiduoModel>> getAllTipoResiduo() { return ResponseEntity.status(HttpStatus.OK).body(tipoResiduoRepository.findAll()); }

    @GetMapping("/getTipoResiduo/{id}")
    @Operation(summary = "Busca o tipo de resíduo usando o código", method = "GET")
    public ResponseEntity<Object> getTipoResiduoById(@PathVariable(value = "id") Long idTipoResiduo) {
        Optional<TipoResiduoModel> tipoResiduoModel = tipoResiduoRepository.findById(idTipoResiduo);

        if(tipoResiduoModel.isEmpty()) { throw new ExceptionNotFound(); }

        return ResponseEntity.status(HttpStatus.OK).body(tipoResiduoModel.get());
    }

    @PostMapping("/saveTipoResiduo")
    @Operation(summary = "Realiza o cadastro do tipo de resíduo", method = "POST")
    public ResponseEntity<TipoResiduoModel> saveTipoResiduo(@RequestBody @Valid TipoResiduoRecordDto tipoResiduoRecordDto) {
        var tipoResiduoModel = new TipoResiduoModel();
        BeanUtils.copyProperties(tipoResiduoRecordDto, tipoResiduoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoResiduoRepository.save(tipoResiduoModel));
    }

    @PutMapping("/updateTipoResiduo/{id}")
    @Operation(summary = "Atualiza o cadastro do tipo de resíduo", method = "PUT")
    public ResponseEntity<Object> updateTipoResiduo(@PathVariable(value = "id") Long idTipoResiduo, @RequestBody @Valid TipoResiduoRecordDto tipoResiduoRecordDto) {
        Optional<TipoResiduoModel> tipoResiduoModel = tipoResiduoRepository.findById(idTipoResiduo);

        if(tipoResiduoModel.isEmpty()) { throw new ExceptionNotFound(); }

        var tipoResiduoModelSave = tipoResiduoModel.get();
        BeanUtils.copyProperties(tipoResiduoRecordDto, tipoResiduoModelSave);

        return ResponseEntity.status(HttpStatus.OK).body(tipoResiduoRepository.save(tipoResiduoModelSave));
    }

    @DeleteMapping("/deleteTipoResiduo/{id}")
    @Operation(summary = "Deleta o cadastro do tipo de resíduo", method = "DELETE")
    public ResponseEntity<Object> deleteTipoResiduo(@PathVariable(value = "id") Long idTipoResiduo) {
        Optional<TipoResiduoModel> tipoResiduoModel = tipoResiduoRepository.findById(idTipoResiduo);

        if(tipoResiduoModel.isEmpty()) { throw new ExceptionNotFound(); }

        tipoResiduoRepository.delete(tipoResiduoModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tipo de resíduo deletado com sucesso");
    }
}
