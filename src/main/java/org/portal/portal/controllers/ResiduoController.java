package org.portal.portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.portal.portal.exceptions.ExceptionNotFound;
import org.portal.portal.models.ResiduoModel;
import org.portal.portal.repositories.ResiduoRepository;
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
public class ResiduoController {

    @Autowired
    ResiduoRepository residuoRepository;

    @GetMapping("/allIndicadoresResiduo")
    @Operation(summary = "Lista todos os dados do indicador de residuos", method = "GET")
    public ResponseEntity<List<ResiduoModel>> getAllResiduo() { return ResponseEntity.status(HttpStatus.OK).body(residuoRepository.findAll()); }

    @GetMapping("/getResiduo/{id}")
    @Operation(summary = "Busca as informações do indicador de residuo usando o código", method = "GET")
    public ResponseEntity<Object> getResiduoModelById(@PathVariable(value = "id") Long idIndicador) {
        Optional<ResiduoModel> residuoModel = residuoRepository.findById(idIndicador);

        if(residuoModel.isEmpty()) { throw new ExceptionNotFound(); }

        return ResponseEntity.status(HttpStatus.OK).body(residuoModel.get());
    }

    @DeleteMapping("/deleteResiduo/{id}")
    @Operation(summary = "Deleta as informações do indicador de residuo", method = "DELETE")
    public ResponseEntity<Object> deleteResiduo(@PathVariable(value = "id") Long idIndicador) {
        Optional<ResiduoModel> residuoModel = residuoRepository.findById(idIndicador);

        if(residuoModel.isEmpty()) { throw new ExceptionNotFound(); }

        residuoRepository.delete(residuoModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Indicador deletado com sucesso");
    }
}
