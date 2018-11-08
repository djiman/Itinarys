package com.djiman.projects.itinarys.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.djiman.projects.itinarys.model.Ligne;

@Repository
public interface LigneRepository extends CrudRepository<Ligne, Integer> {
}
