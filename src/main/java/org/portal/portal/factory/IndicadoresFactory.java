package org.portal.portal.factory;

import jakarta.validation.constraints.NotNull;
import org.portal.portal.enums.Indicadores;
import org.portal.portal.interfaces.IndicadoresProcessor;
import org.portal.portal.service.CarbonoService;
import org.portal.portal.service.CopoService;
import org.portal.portal.service.EnergiaService;
import org.portal.portal.service.PapelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class IndicadoresFactory {

    private final ApplicationContext applicationContext;

    @Autowired
    public IndicadoresFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public IndicadoresProcessor getProcessor(@NotNull String indicador) {
        switch (Indicadores.fromString(indicador.toUpperCase())) {
            case COPO:
                return applicationContext.getBean(CopoService.class, IndicadoresProcessor.class);
            case CARBONO:
                return applicationContext.getBean(CarbonoService.class, IndicadoresProcessor.class);
            case ENERGIA:
                return applicationContext.getBean(EnergiaService.class, IndicadoresProcessor.class);
            case PAPEL:
                return applicationContext.getBean(PapelService.class, IndicadoresProcessor.class);
            case RESIDUO:
                // Retornar implementação de ResiduoProcessor
                break;
            default:
                throw new IllegalArgumentException("Indicador inválido ou não implementado.");
        }
        return null;
    }
}
