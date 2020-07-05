package com.djiman.projects.itinarys.manager;

import com.djiman.projects.itinarys.dto.LigneDTO;
import com.djiman.projects.itinarys.persistence.GareRepository;
import com.djiman.projects.itinarys.persistence.LigneRepository;
import com.djiman.projects.itinarys.persistence.model.Ligne;
import com.djiman.projects.itinarys.persistence.GareRepositoryCustom;
import com.djiman.projects.itinarys.persistence.LigneRepositoryCustom;

public interface LigneManager {

	public Iterable<LigneDTO> getAllLignes() ;

	public LigneDTO getLigneByName(String pNomLigne);

	public void createOrModifyLigne(LigneDTO pLigneDto) ;

	public Ligne convertLigneDtoToLigne(LigneDTO pLigneDto);

	public LigneDTO convertLigneToLigneDto(Ligne pLigne);

	public void setGareRepositoryCustom(GareRepositoryCustom gareRepositoryCustom);

	public void setLigneRepositoryCustom(LigneRepositoryCustom ligneRepositoryCustom) ;

	public GareRepository getGareRepository();

	public void setGareRepository(GareRepository gareRepository);

	public LigneRepository getLigneRepository();

	public void setLigneRepository(LigneRepository ligneRepository);
}
