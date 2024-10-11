package org.portal.portal.service;

import org.apache.poi.ss.usermodel.*;
import org.portal.portal.exceptions.ExceptionExcelEmpty;
import org.portal.portal.factory.IndicadoresFactory;
import org.portal.portal.interfaces.IndicadoresProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

        @Autowired
        private IndicadoresFactory indicadoresFactory;

        public List<Object> extrair(MultipartFile file, String indicador) {
            List<Object> list = new ArrayList<>();

            try {
                Workbook workbook = WorkbookFactory.create(file.getInputStream());
                Sheet sheet = workbook.getSheetAt(0);

                if (sheet.getLastRowNum() == 0) { throw new ExceptionExcelEmpty(); }

                // Factory para retornar a classe do indicador e evitar injetar classes sem necessidade
                IndicadoresProcessor indicadoresProcessor = indicadoresFactory.getProcessor(indicador);

                if (indicadoresProcessor != null) {
                    list = indicadoresProcessor.lerExcel(sheet);
                }

                if (list.isEmpty()) { throw new ExceptionExcelEmpty(); }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return list;
        }

}
