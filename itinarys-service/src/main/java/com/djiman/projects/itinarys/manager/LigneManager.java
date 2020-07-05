package com.djiman.projects.itinarys.manager;

import com.djiman.projects.itinarys.dto.LigneDTO;
import com.djiman.projects.itinarys.persistence.GareRepository;
import com.djiman.projects.itinarys.persistence.LigneRepository;
import com.djiman.projects.itinarys.persistence.model.Ligne;
import com.djiman.projects.itinarys.persistence.LigneRepositoryCustom;

public interface LigneManager {

	Iterable<LigneDTO> getAllLignes() ;

	LigneDTO getLigneByName(String pNomLigne);

	void createOrModifyLigne(LigneDTO pLigneDto) ;

	Ligne convertLigneDtoToLigne(LigneDTO pLigneDto);

	LigneDTO convertLigneToLigneDto(Ligne pLigne);

	void setLigneRepositoryCustom(LigneRepositoryCustom ligneRepositoryCustom) ;

	void setGareRepository(GareRepository gareRepository);

	void setLigneRepository(LigneRepository ligneRepository);
}
