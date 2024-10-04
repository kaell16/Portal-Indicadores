package org.portal.portal.repositories;

import org.portal.portal.models.CampusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusRepository extends JpaRepository<CampusModel, Long> {
}
