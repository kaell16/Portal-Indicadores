package org.portal.portal.enums;

import lombok.Getter;
import org.portal.portal.exceptions.ExceptionIndicadorNotFound;

@Getter
public enum Indicadores {
    COPO(1, "Copo"),
    CARBONO(2, "Carbono"),
    ENERGIA(3, "Energia"),
    PAPEL(3, "Papel"),
    RESIDUO(4, "Residuo");

    private final int codigo;
    private final String descricao;

    Indicadores(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    
    public static Indicadores fromString(String descricao) {
        for (Indicadores indicador : Indicadores.values()) {
            if (indicador.getDescricao().equalsIgnoreCase(descricao)) {
                return indicador;
            }
        }
        throw new ExceptionIndicadorNotFound();
    }
}
