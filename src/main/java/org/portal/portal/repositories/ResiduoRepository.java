package org.portal.portal.repositories;

import org.portal.portal.groovy.CopoSql;
import org.portal.portal.groovy.ResiduoSql;
import org.portal.portal.models.ResiduoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ResiduoRepository extends JpaRepository<ResiduoModel, Long> {

    @Query(value = ResiduoSql.get_residuo_by_date, nativeQuery = true)
    ResiduoModel findIndicadorResiduoByDate(@Param("dt_inicial") LocalDate dataInicial, @Param("dt_final") LocalDate dataFinal);
}
