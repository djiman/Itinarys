package com.djiman.projects.itinarys.persistence;

import com.djiman.projects.itinarys.persistence.model.Ligne;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LigneRepository extends MongoRepository<Ligne, ObjectId> {
    Optional<Ligne> getLigneByNom(String nomLigne);
}
