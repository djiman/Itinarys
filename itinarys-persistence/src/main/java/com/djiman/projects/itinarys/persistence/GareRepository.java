package com.djiman.projects.itinarys.persistence;

import com.djiman.projects.itinarys.persistence.model.Gare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GareRepository extends JpaRepository<Gare, Long> {

}
