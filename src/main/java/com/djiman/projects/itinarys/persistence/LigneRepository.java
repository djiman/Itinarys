package com.djiman.projects.itinarys.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.djiman.projects.itinarys.model.Ligne;

public interface LigneRepository extends JpaRepository<Ligne, Long> {
}
