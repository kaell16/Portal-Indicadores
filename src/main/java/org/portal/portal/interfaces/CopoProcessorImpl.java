package org.portal.portal.interfaces;

import org.apache.poi.ss.usermodel.Sheet;
import org.portal.portal.service.CopoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CopoProcessorImpl implements IndicadoresProcessor {

    @Autowired
    private CopoService copoService;

    @Override
    public List<Object> lerExcel(Sheet sheet) {
        return Collections.singletonList(copoService.lerExcel(sheet));
    }
}
