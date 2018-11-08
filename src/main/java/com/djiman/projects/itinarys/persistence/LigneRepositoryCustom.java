package com.djiman.projects.itinarys.persistence;

import org.springframework.data.repository.CrudRepository;

import com.djiman.projects.itinarys.model.Ligne;

public interface LigneRepositoryCustom  { 
	public Ligne getLigneByName(String string);
}
