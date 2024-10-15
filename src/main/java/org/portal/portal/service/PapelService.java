package org.portal.portal.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.portal.portal.exceptions.ExceptionDataEmpty;
import org.portal.portal.interfaces.IndicadoresProcessor;
import org.portal.portal.models.PapelModel;
import org.portal.portal.repositories.PapelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PapelService implements IndicadoresProcessor {

    @Autowired
    private ValidacaoService validacaoService;

    @Autowired
    PapelRepository papelRepository;

    @Override
    public List<Object> lerExcel(Sheet sheet) {

        List<PapelModel> papelList = new ArrayList<>();
        String dadoColumn;

        for (int rowindex = 1; rowindex <= sheet.getLastRowNum(); rowindex++) { // Percorre as linhas
            Row row = sheet.getRow(rowindex);

            if (row != null) {
                PapelModel papel = new PapelModel();

                Cell idCategoria = row.getCell(0);
                if (idCategoria != null) {
                    dadoColumn = validacaoService.validarColuna(idCategoria);
                    if ("".equals(dadoColumn)){ throw new IllegalArgumentException("Categoria do indicador não informada na linha " + row.getRowNum()); }
                    papel.setIdCategoria(Double.valueOf(dadoColumn).longValue());
                }

                Cell descricao = row.getCell(1);
                if (descricao != null) {
                    papel.setDescricao(validacaoService.validarColuna(descricao));
                }

                Cell quantidade = row.getCell(2);
                if (quantidade != null) {
                    dadoColumn = validacaoService.validarColuna(quantidade);
                    if ("".equals(dadoColumn)){ throw new IllegalArgumentException("Quantidade não informada na linha " + row.getRowNum()); }
                    papel.setQuantidade(Double.parseDouble(dadoColumn));
                }

                Cell medida = row.getCell(3);
                if (medida != null) {
                    papel.setMedida(validacaoService.validarColuna(medida));
                }

                Cell valor = row.getCell(4);
                if (valor != null) {
                    papel.setValor(new BigDecimal(validacaoService.validarColuna(valor)));
                }

                Cell dataInicial = row.getCell(5);
                if (dataInicial != null) {
                    dadoColumn = validacaoService.validarColuna(dataInicial);
                    if (!"".equals(dadoColumn)) {
                        papel.setDataInicial(LocalDate.parse(dadoColumn));
                    }
                }

                Cell dataFinal = row.getCell(6);
                if (dataFinal != null) {
                    dadoColumn = validacaoService.validarColuna(dataFinal);
                    if (!"".equals(dadoColumn)) {
                        papel.setDataFinal(LocalDate.parse(dadoColumn));
                    }
                }

                if (papel.getDataInicial() == null || papel.getDataFinal() == null) {
                    throw new ExceptionDataEmpty("Verifique a integridade dos dados na linha " + row.getRowNum());
                }

                PapelModel papelValidacao = papelRepository.findIndicadorPapelByDate(papel.getDataInicial(), papel.getDataFinal());

                //Validar se o dado ja existe, caso nao exista inserir na lista
                if (papelValidacao == null){
                    papelList.add(papel);
                }
            }
        }

        if (!papelList.isEmpty()) {
            papelRepository.saveAll(papelList);
        }

        return papelList.isEmpty() ? new ArrayList<>() : Collections.singletonList(papelList);
    }
}
