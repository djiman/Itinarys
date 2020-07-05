package com.djiman.projects.itinarys.persistence;

import com.djiman.projects.itinarys.persistence.model.Ligne;

import java.util.Optional;

public interface LigneRepositoryCustom  { 
	public Optional<Ligne> getLigneByName(String string);

	public void deleteAllGaresOfLine(Long pLigneId);
}
