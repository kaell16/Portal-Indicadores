package org.portal.portal.groovy

class CarbonoSql {

    public static final String get_carbono_by_date = """
        SELECT 
            C.IDINDICADOR,
            C.IDCATEGORIA,
            C.DESCRICAO,
            C.CMINICIAL,
            C.CMFINAL,
            C.PESO,
            C.MEDIDA,
            C.DATAINICIAL,
            C.DATAFINAL,
            C.DATAATUALIZACAO
        FROM
            DBA.CARBONO C
        WHERE 
            C.DATAINICIAL = :dt_inicial
            AND C.DATAFINAL = :dt_final
    """
}
