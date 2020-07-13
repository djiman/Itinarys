package com.djiman.projects.itinarys.persistence;

import com.djiman.projects.itinarys.persistence.model.Gare;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GareRepository extends MongoRepository<Gare, ObjectId> {
    Optional<Gare> getGareByNom(String nomGare);
}
