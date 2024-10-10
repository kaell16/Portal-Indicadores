package org.portal.portal.repositories;

import org.portal.portal.groovy.CarbonoSql;
import org.portal.portal.models.CarbonoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CarbonoRepository extends JpaRepository<CarbonoModel, Long> {

    @Query(value = CarbonoSql.get_carbono_by_date, nativeQuery = true)
    CarbonoModel findIndicadorCarbonoByDate(@Param("dt_inicial") LocalDate dataInicial, @Param("dt_final") LocalDate dataFinal);
}
