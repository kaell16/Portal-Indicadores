package org.portal.portal.repositories;

import org.portal.portal.groovy.CopoSql;
import org.portal.portal.models.CopoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CopoRepository extends JpaRepository<CopoModel, Long> {

    @Query(value = CopoSql.get_copo_by_date, nativeQuery = true)
    CopoModel findIndicadorCopoByDate(@Param("dt_inicial") LocalDate dataInicial, @Param("dt_final") LocalDate dataFinal);
}
