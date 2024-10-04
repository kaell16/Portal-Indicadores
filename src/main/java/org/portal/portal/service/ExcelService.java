package org.portal.portal.service;

import org.apache.poi.ss.usermodel.*;
import org.portal.portal.enums.Indicadores;
import org.portal.portal.exceptions.ExceptionExcelEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ExcelService {

        @Autowired
        CopoService copoService;

        public List<Object> extrair(MultipartFile file, String indicador) {
            List<Object> list = new ArrayList<>();

            try {
                Workbook workbook = WorkbookFactory.create(file.getInputStream());
                Sheet sheet = workbook.getSheetAt(0);

                if (sheet.getLastRowNum() == 0) { throw new ExceptionExcelEmpty(); }

                switch (Indicadores.fromString(indicador.toUpperCase())) {
                    case COPO:
                        list = Collections.singletonList(copoService.lerExcel(sheet));
                        break;
                    case CARBONO:
                        // Implementar
                        break;
                    case ENERGIA:
                        // Implementar
                        break;
                    case PAPEL:
                        // Implementar
                        break;
                    case RESIDUOS:
                        // Implementar
                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return list;
        }

}
