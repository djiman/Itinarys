package com.djiman.projects.itinarys.persistence;

import java.util.Optional;

import com.djiman.projects.itinarys.model.Ligne;

public interface LigneRepositoryCustom  { 
	public Optional<Ligne> getLigneByName(String string);

	public void deleteAllGaresOfLine(Long pLigneId);
}
