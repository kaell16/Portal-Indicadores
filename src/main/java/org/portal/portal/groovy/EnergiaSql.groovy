package org.portal.portal.groovy

class EnergiaSql {

    public static final String get_energia_by_date = """
        SELECT 
            E.IDINDICADOR,
            E.IDCATEGORIA,
            E.DESCRICAO,
            E.CONSUMO,
            E.MEDIDA,
            E.VALOR,
            E.DATAINICIAL,
            E.DATAFINAL,
            E.DATAATUALIZACAO
        FROM
            DBA.ENERGIA E
        WHERE 
            E.DATAINICIAL = :dt_inicial
            AND E.DATAFINAL = :dt_final
    """
}
