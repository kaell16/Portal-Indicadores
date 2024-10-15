package org.portal.portal.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.portal.portal.exceptions.ExceptionDataEmpty;
import org.portal.portal.interfaces.IndicadoresProcessor;
import org.portal.portal.models.CarbonoModel;
import org.portal.portal.repositories.CarbonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CarbonoService implements IndicadoresProcessor {

    @Autowired
    private ValidacaoService validacaoService;

    @Autowired
    private CarbonoRepository carbonoRepository;

    @Override
    public List<Object> lerExcel(Sheet sheet) {

        List<CarbonoModel> carbonoList = new ArrayList<>();
        String dadoColumn;

        for (int rowindex = 1; rowindex <= sheet.getLastRowNum(); rowindex++) { // Percorre as linhas
            Row row = sheet.getRow(rowindex);

            if (row != null) {
                CarbonoModel carbono = new CarbonoModel();

                Cell idCategoria = row.getCell(0);
                if (idCategoria != null) {
                    dadoColumn = validacaoService.validarColuna(idCategoria);
                    if ("".equals(dadoColumn)){ throw new IllegalArgumentException("Categoria do indicador não informada na linha " + row.getRowNum()); }
                    carbono.setIdCategoria(Double.valueOf(dadoColumn).longValue());
                }

                Cell descricao = row.getCell(1);
                if (descricao != null) {
                    carbono.setDescricao(validacaoService.validarColuna(descricao));
                }

                Cell cmInicial = row.getCell(2);
                if (cmInicial != null) {
                    carbono.setCmInicial(Double.parseDouble(validacaoService.validarColuna(cmInicial)));
                }

                Cell cmFinal = row.getCell(3);
                if (cmFinal != null) {
                    carbono.setCmFinal(Double.parseDouble(validacaoService.validarColuna(cmFinal)));
                }

                Cell peso = row.getCell(4);
                if (peso != null) {
                    carbono.setPeso(Double.parseDouble(validacaoService.validarColuna(peso)));
                }

                Cell medida = row.getCell(5);
                if (medida != null) {
                    carbono.setMedida(validacaoService.validarColuna(medida));
                }

                Cell dataInicial = row.getCell(6);
                if (dataInicial != null) {
                    dadoColumn = validacaoService.validarColuna(dataInicial);
                    if (!"".equals(dadoColumn)) {
                        carbono.setDataInicial(LocalDate.parse(dadoColumn));
                    }
                }

                Cell dataFinal = row.getCell(7);
                if (dataFinal != null) {
                    dadoColumn = validacaoService.validarColuna(dataFinal);
                    if (!"".equals(dadoColumn)) {
                        carbono.setDataFinal(LocalDate.parse(dadoColumn));
                    }
                }

                if (carbono.getDataInicial() == null || carbono.getDataFinal() == null) {
                    throw new ExceptionDataEmpty("Verifique a integridade dos dados na linha " + row.getRowNum());
                }

                CarbonoModel carbonoValidacao = carbonoRepository.findIndicadorCarbonoByDate(carbono.getDataInicial(), carbono.getDataFinal());

                //Validar se o dado ja existe, caso nao exista inserir na lista
                if (carbonoValidacao == null){
                    carbonoList.add(carbono);
                }
            }
        }

        if (!carbonoList.isEmpty()) {
            carbonoRepository.saveAll(carbonoList);
        }

        return carbonoList.isEmpty() ? new ArrayList<>() : Collections.singletonList(carbonoList);
    }
}
