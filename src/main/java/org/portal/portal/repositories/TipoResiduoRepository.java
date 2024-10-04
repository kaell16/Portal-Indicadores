package org.portal.portal.repositories;

import org.portal.portal.models.TipoResiduoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoResiduoRepository extends JpaRepository<TipoResiduoModel, Long> {
}
