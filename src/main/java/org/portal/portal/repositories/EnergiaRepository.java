package org.portal.portal.repositories;

import org.portal.portal.groovy.EnergiaSql;
import org.portal.portal.models.EnergiaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EnergiaRepository extends JpaRepository<EnergiaModel, Long> {

    @Query(value = EnergiaSql.get_energia_by_date, nativeQuery = true)
    EnergiaModel findIndicadorEnergiaByDate(@Param("dt_inicial") LocalDate dataInicial, @Param("dt_final") LocalDate dataFinal);
}
