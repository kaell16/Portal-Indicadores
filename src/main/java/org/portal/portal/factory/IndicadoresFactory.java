package org.portal.portal.factory;

import jakarta.validation.constraints.NotNull;
import org.portal.portal.enums.Indicadores;
import org.portal.portal.interfaces.IndicadoresProcessor;
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
                return applicationContext.getBean("copoProcessorImpl", IndicadoresProcessor.class);
            case CARBONO:
                // Retornar implementação de CarbonoProcessor
                break;
            case ENERGIA:
                // Retornar implementação de EnergiaProcessor
                break;
            case PAPEL:
                // Retornar implementação de PapelProcessor
                break;
            case RESIDUO:
                // Retornar implementação de ResiduoProcessor
                break;
            default:
                throw new IllegalArgumentException("Indicador inválido ou não implementado.");
        }
        return null;
    }
}
