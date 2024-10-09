package org.portal.portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.portal.portal.dtos.CategoriaRecordDto;
import org.portal.portal.exceptions.ExceptionNotFound;
import org.portal.portal.models.CategoriaModel;
import org.portal.portal.repositories.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/allCategoria")
    @Operation(summary = "Lista todas as categorias cadastradas", method = "GET")
    public ResponseEntity<List<CategoriaModel>> getAllCategoria() { return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findAll()); }

    @GetMapping("/getCategoria/{id}")
    @Operation(summary = "Busca a categoria usando o código", method = "GET")
    public ResponseEntity<Object> getCategoriaById(@PathVariable(value = "id") Long idCategoria) {
        Optional<CategoriaModel> categoriaModel = categoriaRepository.findById(idCategoria);

        if(categoriaModel.isEmpty()) { throw new ExceptionNotFound(); }

        return ResponseEntity.status(HttpStatus.OK).body(categoriaModel.get());
    }

    @PostMapping("/saveCategoria")
    @Operation(summary = "Realiza o cadastro da categoria do indicador", method = "POST")
    public ResponseEntity<CategoriaModel> saveCategoria(@RequestBody @Valid CategoriaRecordDto categoriaRecordDto) {
        var categoriaModel = new CategoriaModel();
        BeanUtils.copyProperties(categoriaRecordDto, categoriaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoriaModel));
    }


    @PutMapping("/updateCategoria/{id}")
    @Operation(summary = "Atualiza o cadastro da categoria", method = "PUT")
    public ResponseEntity<Object> updateCategoria(@PathVariable(value = "id") Long idCategoria, @RequestBody @Valid CategoriaRecordDto categoriaRecordDto) {
        Optional<CategoriaModel> categoriaModel = categoriaRepository.findById(idCategoria);

        if(categoriaModel.isEmpty()) { throw new ExceptionNotFound(); }

        var categoriaModelSave = categoriaModel.get();
        BeanUtils.copyProperties(categoriaRecordDto, categoriaModelSave);

        return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoriaModelSave));
    }

    @DeleteMapping("/deleteCategoria/{id}")
    @Operation(summary = "Deleta o cadastro da categoria", method = "DELETE")
    public ResponseEntity<Object> deleteCategoria(@PathVariable(value = "id") Long idCategoria) {
        Optional<CategoriaModel> categoriaModel = categoriaRepository.findById(idCategoria);

        if(categoriaModel.isEmpty()) { throw new ExceptionNotFound(); }

        categoriaRepository.delete(categoriaModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada com sucesso");
    }
}
