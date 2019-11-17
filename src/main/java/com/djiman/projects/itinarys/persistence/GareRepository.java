package com.djiman.projects.itinarys.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djiman.projects.itinarys.model.Gare;

@Repository
public interface GareRepository extends JpaRepository<Gare, Long> {

}
