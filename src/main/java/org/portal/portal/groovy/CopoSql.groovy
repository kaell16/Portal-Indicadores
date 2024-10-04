package org.portal.portal.groovy

class CopoSql {

    public static final String get_copo_by_chave = """
        SELECT 
            C.IDINDICADOR,
            C.IDCATEGORIA,
            C.DESCRICAO,
            C.QUANTIDADE,
            C.MEDIDA,
            C.VALOR,
            C.DATAINICIAL,
            C.DATAFINAL,
            C.DATAATUALIZACAO
        FROM
            DBA.COPO C
        WHERE 
            DATAINICIAL = :dt_inicial
            AND DATAFINAL = :dt_final
    """
}
