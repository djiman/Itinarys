package com.djiman.projects.itinarys.persistence;

import com.djiman.projects.itinarys.model.Ligne;

public interface LigneRepositoryCustom  { 
	public Ligne getLigneByName(String string);
}
