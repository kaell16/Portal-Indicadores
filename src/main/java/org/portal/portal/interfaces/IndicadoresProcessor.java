package org.portal.portal.interfaces;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IndicadoresProcessor {

    List<Object> lerExcel(Sheet sheet);
}
