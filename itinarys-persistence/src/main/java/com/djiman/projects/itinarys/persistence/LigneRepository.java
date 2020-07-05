package com.djiman.projects.itinarys.persistence;

import com.djiman.projects.itinarys.persistence.model.Ligne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LigneRepository extends JpaRepository<Ligne, Long> {
    Optional<Ligne> getLigneByNom(String nomLigne);
}
