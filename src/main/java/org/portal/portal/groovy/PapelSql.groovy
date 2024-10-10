package org.portal.portal.groovy

class PapelSql {

    public static final String get_papel_by_date = """
        SELECT 
            P.IDINDICADOR,
            P.IDCATEGORIA,
            P.DESCRICAO,
            P.QUANTIDADE,
            P.MEDIDA,
            P.VALOR,
            P.DATAINICIAL,
            P.DATAFINAL,
            P.DATAATUALIZACAO
        FROM
            DBA.PAPEL P
        WHERE 
            P.DATAINICIAL = :dt_inicial
            AND P.DATAFINAL = :dt_final
    """
}
