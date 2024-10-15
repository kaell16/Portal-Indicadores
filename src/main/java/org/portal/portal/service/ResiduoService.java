package org.portal.portal.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.portal.portal.exceptions.ExceptionDataEmpty;
import org.portal.portal.interfaces.IndicadoresProcessor;
import org.portal.portal.models.ResiduoModel;
import org.portal.portal.repositories.ResiduoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ResiduoService implements IndicadoresProcessor {

    @Autowired
    private ValidacaoService validacaoService;

    @Autowired
    private ResiduoRepository residuoRepository;

    @Override
    public List<Object> lerExcel(Sheet sheet) {

        List<ResiduoModel> residuoList = new ArrayList<>();
        String dadoColumn;

        for (int rowindex = 1; rowindex <= sheet.getLastRowNum(); rowindex++) { // Percorre as linhas
            Row row = sheet.getRow(rowindex);

            if (row != null) {
                ResiduoModel residuo = new ResiduoModel();

                Cell idCategoria = row.getCell(0);
                if (idCategoria != null) {
                    dadoColumn = validacaoService.validarColuna(idCategoria);
                    if ("".equals(dadoColumn)){ throw new IllegalArgumentException("Categoria do indicador não informada na linha " + row.getRowNum()); }
                    residuo.setIdCategoria(Double.valueOf(dadoColumn).longValue());
                }

                Cell descricao = row.getCell(1);
                if (descricao != null) {
                    residuo.setDescricao(validacaoService.validarColuna(descricao));
                }

                Cell subDescricao = row.getCell(2);
                if (subDescricao != null) {
                    residuo.setSubDescricao(validacaoService.validarColuna(subDescricao));
                }

                // TIPO RESIDUO - FUTURAMENTE

                Cell quantidade = row.getCell(3);
                if (quantidade != null) {
                    dadoColumn = validacaoService.validarColuna(quantidade);
                    if ("".equals(dadoColumn)){ throw new IllegalArgumentException("Quantidade não informada na linha " + row.getRowNum()); }
                    residuo.setQuantidade(Double.parseDouble(dadoColumn));
                }

                Cell medida = row.getCell(4);
                if (medida != null) {
                    residuo.setMedida(validacaoService.validarColuna(medida));
                }

                Cell dataInicial = row.getCell(5);
                if (dataInicial != null) {
                    dadoColumn = validacaoService.validarColuna(dataInicial);
                    if (!"".equals(dadoColumn)) {
                        residuo.setDataInicial(LocalDate.parse(dadoColumn));
                    }
                }

                Cell dataFinal = row.getCell(6);
                if (dataFinal != null) {
                    dadoColumn = validacaoService.validarColuna(dataFinal);
                    if (!"".equals(dadoColumn)) {
                        residuo.setDataFinal(LocalDate.parse(dadoColumn));
                    }
                }

                if (residuo.getDataInicial() == null || residuo.getDataFinal() == null) {
                    throw new ExceptionDataEmpty("Verifique a integridade dos dados na linha " + row.getRowNum());
                }

                ResiduoModel residuoValidacao = residuoRepository.findIndicadorResiduoByDate(residuo.getDataInicial(), residuo.getDataFinal());

                //Validar se o dado ja existe, caso nao exista inserir na lista
                if (residuoValidacao == null){
                    residuoList.add(residuo);
                }
            }
        }

        if (!residuoList.isEmpty()) {
            residuoRepository.saveAll(residuoList);
        }

        return residuoList.isEmpty() ? new ArrayList<>() : Collections.singletonList(residuoList);
    }
}
