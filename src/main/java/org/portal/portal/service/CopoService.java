package org.portal.portal.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.portal.portal.exceptions.ExceptionDataEmpty;
import org.portal.portal.models.CopoModel;
import org.portal.portal.repositories.CopoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CopoService {

    @Autowired
    private ValidacaoService validacaoService;

    @Autowired
    private CopoRepository copoRepository;

    public List<CopoModel> lerExcel(Sheet sheet){
        List<CopoModel> copoList = new ArrayList<>();
        String dadoColumn;

        for (int rowindex = 1; rowindex <= sheet.getLastRowNum(); rowindex++) { // Percorre as linhas
            Row row = sheet.getRow(rowindex);

            if (row != null) {
                CopoModel copo = new CopoModel();

                Cell idCategoria = row.getCell(0);
                if (idCategoria != null) {
                    dadoColumn = validacaoService.validarColuna(idCategoria);
                    if ("".equals(dadoColumn)){ throw new IllegalArgumentException("Categoria do indicador não informada"); }
                    copo.setIdCategoria(Double.valueOf(dadoColumn).longValue());
                }

                Cell descricao = row.getCell(1);
                if (descricao != null) {
                    copo.setDescricao(validacaoService.validarColuna(descricao));
                }

                Cell quantidade = row.getCell(2);
                if (quantidade != null) {
                    dadoColumn = validacaoService.validarColuna(quantidade);
                    if ("".equals(dadoColumn)){ throw new IllegalArgumentException("Quantidade não informada"); }
                    copo.setQuantidade(Double.parseDouble(dadoColumn));
                }

                Cell medida = row.getCell(3);
                if (medida != null) {
                    copo.setMedida(validacaoService.validarColuna(medida));
                }

                Cell valor = row.getCell(4);
                if (valor != null) {
                    copo.setValor(new BigDecimal(validacaoService.validarColuna(valor)));
                }

                Cell dataInicial = row.getCell(5);
                if (dataInicial != null) {
                    dadoColumn = validacaoService.validarColuna(dataInicial);
                    if (!"".equals(dadoColumn)) {
                        copo.setDataInicial(LocalDate.parse(dadoColumn));
                    }
                }

                Cell dataFinal = row.getCell(6);
                if (dataFinal != null) {
                    dadoColumn = validacaoService.validarColuna(dataFinal);
                    if (!"".equals(dadoColumn)) {
                        copo.setDataFinal(LocalDate.parse(dadoColumn));
                    }
                }

                if (copo.getDataInicial() == null || copo.getDataFinal() == null) {
                    throw new ExceptionDataEmpty();
                }

                CopoModel copoValidacao = copoRepository.findIndicadorCopoByDate(copo.getDataInicial(), copo.getDataFinal());

                //Validar se o dado ja existe, caso nao exista inserir na lista
                if (copoValidacao == null){
                    copoList.add(copo);
                }
            }
        }

        if (!copoList.isEmpty()) {
            return copoRepository.saveAll(copoList);
        }

        return copoList;
    }
}
