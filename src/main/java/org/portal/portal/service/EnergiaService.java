package org.portal.portal.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.portal.portal.exceptions.ExceptionDataEmpty;
import org.portal.portal.interfaces.IndicadoresProcessor;
import org.portal.portal.models.EnergiaModel;
import org.portal.portal.repositories.EnergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EnergiaService implements IndicadoresProcessor {

    @Autowired
    private ValidacaoService validacaoService;

    @Autowired
    private EnergiaRepository energiaRepository;

    @Override
    public List<Object> lerExcel(Sheet sheet) {

        List<EnergiaModel> energiaList = new ArrayList<>();
        String dadoColumn;

        for (int rowindex = 1; rowindex <= sheet.getLastRowNum(); rowindex++) { // Percorre as linhas
            Row row = sheet.getRow(rowindex);

            if (row != null) {
                EnergiaModel energia = new EnergiaModel();

                Cell idCategoria = row.getCell(0);
                if (idCategoria != null) {
                    dadoColumn = validacaoService.validarColuna(idCategoria);
                    if ("".equals(dadoColumn)){ throw new IllegalArgumentException("Categoria do indicador não informada na linha " + row.getRowNum()); }
                    energia.setIdCategoria(Double.valueOf(dadoColumn).longValue());
                }

                Cell descricao = row.getCell(1);
                if (descricao != null) {
                    energia.setDescricao(validacaoService.validarColuna(descricao));
                }

                Cell consumo = row.getCell(2);
                if (consumo != null) {
                    energia.setConsumo(Double.parseDouble(validacaoService.validarColuna(consumo)));
                }

                Cell medida = row.getCell(3);
                if (medida != null) {
                    energia.setMedida(validacaoService.validarColuna(medida));
                }

                Cell valor = row.getCell(4);
                if (valor != null) {
                    energia.setValor(new BigDecimal(validacaoService.validarColuna(valor)));
                }

                Cell dataInicial = row.getCell(5);
                if (dataInicial != null) {
                    dadoColumn = validacaoService.validarColuna(dataInicial);
                    if (!"".equals(dadoColumn)) {
                        energia.setDataInicial(LocalDate.parse(dadoColumn));
                    }
                }

                Cell dataFinal = row.getCell(6);
                if (dataFinal != null) {
                    dadoColumn = validacaoService.validarColuna(dataFinal);
                    if (!"".equals(dadoColumn)) {
                        energia.setDataFinal(LocalDate.parse(dadoColumn));
                    }
                }

                if (energia.getDataInicial() == null || energia.getDataFinal() == null) {
                    throw new ExceptionDataEmpty("Verifique a integridade dos dados na linha " + row.getRowNum());
                }

                EnergiaModel energiaValidacao = energiaRepository.findIndicadorEnergiaByDate(energia.getDataInicial(), energia.getDataFinal());

                //Validar se o dado ja existe, caso nao exista inserir na lista
                if (energiaValidacao == null){
                    energiaList.add(energia);
                }
            }
        }

        if (!energiaList.isEmpty()) {
            energiaRepository.saveAll(energiaList);
        }

        return energiaList.isEmpty() ? new ArrayList<>() : Collections.singletonList(energiaList);
    }
}
