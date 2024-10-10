package org.portal.portal.groovy

class ResiduoSql {

    public static final String get_residuo_by_date = """
        SELECT 
            R.IDINDICADOR,
            R.IDCATEGORIA,
            R.DESCRICAO,
            R.SUBDESCRICAO,
            R.QUANTIDADE,
            R.MEDIDA,
            R.DATAINICIAL,
            R.DATAFINAL,
            R.DATAATUALIZACAO
        FROM
            DBA.RESIDUO R
        WHERE 
            R.DATAINICIAL = :dt_inicial
            AND R.DATAFINAL = :dt_final
    """
}
