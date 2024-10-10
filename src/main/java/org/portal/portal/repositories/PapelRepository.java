package org.portal.portal.repositories;

import org.portal.portal.groovy.PapelSql;
import org.portal.portal.models.PapelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PapelRepository extends JpaRepository<PapelModel, Long> {

    @Query(value = PapelSql.get_papel_by_date, nativeQuery = true)
    PapelModel findIndicadorPapelByDate(@Param("dt_inicial") LocalDate dataInicial, @Param("dt_final") LocalDate dataFinal);
}
