package org.portal.portal.controllers;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import org.portal.portal.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping(value = "/extrair", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Extrai os dados do excel para inserir no banco", method = "POST")
    public ResponseEntity<List<Object>> extract(@RequestParam MultipartFile file, @NotNull String indicador) { return ResponseEntity.ok(excelService.extrair(file, indicador)); }
}
