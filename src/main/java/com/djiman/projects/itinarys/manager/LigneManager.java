package com.djiman.projects.itinarys.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djiman.projects.itinarys.dto.GareDTO;
import com.djiman.projects.itinarys.dto.LigneDTO;
import com.djiman.projects.itinarys.model.GaresLigne;
import com.djiman.projects.itinarys.model.Ligne;
import com.djiman.projects.itinarys.persistence.GareRepository;
import com.djiman.projects.itinarys.persistence.GareRepositoryCustom;
import com.djiman.projects.itinarys.persistence.LigneRepository;
import com.djiman.projects.itinarys.persistence.LigneRepositoryCustom;
import com.djiman.projects.itinarys.util.ConstantesMessages;

@Service
public class LigneManager {

	@Autowired
	LigneRepository ligneRepository;

	@Autowired
	GareRepository gareRepository;

	@Autowired
	LigneRepositoryCustom ligneRepositoryCustom;
		
	@Autowired
	GareRepositoryCustom gareRepositoryCustom;

	public Iterable<LigneDTO> getAllLignes() {
		List<Ligne> result = ligneRepository.findAll();
		List<LigneDTO> resultDTO = new ArrayList<>();
		result.forEach(ligne -> {
			resultDTO.add(convertLigneToLigneDto(ligne));
		});
		return resultDTO;
	}

	public Object getLigneByName(String pNomLigne) {
		Object result = null;
		try {
			result = convertLigneToLigneDto(ligneRepositoryCustom.getLigneByName(pNomLigne));
		} catch (Exception e) {
			result = ConstantesMessages.AUCUNE_LIGNE_TROUVEE;
		}
		return result;
	}

	public void createModifyLigne(LigneDTO pLigneDto) {

		Ligne newLigne = convertLigneDtoToLigne(pLigneDto);

		Ligne ligne = ligneRepositoryCustom.getLigneByName(pLigneDto.getNomLigne());
		if (ligne != null) {
			ligne.setCommentaire(newLigne.getCommentaire());
			ligne.setNom(newLigne.getNom());
			ligne.setStatut(newLigne.getStatut());
			ligne.setType(newLigne.getType());
		}
		ligneRepository.save(newLigne);
	}

	public Ligne convertLigneDtoToLigne(LigneDTO pLigneDto) {
		Ligne newLigne = new Ligne();
		newLigne.setCommentaire(pLigneDto.getCommentaire());
		newLigne.setNom(pLigneDto.getNomLigne());
		newLigne.setStatut(pLigneDto.getStatut());
		newLigne.setType(pLigneDto.getType());

		for (GareDTO gareDTO : pLigneDto.getGaresDto()) {
			newLigne.addGareLigne(new GaresLigne(
					newLigne, gareRepositoryCustom.getGareByName(gareDTO.getGare()), gareDTO.getOrdre()));
		}
		return newLigne;
	}

	public LigneDTO convertLigneToLigneDto(Ligne pLigne) {
		LigneDTO result = new LigneDTO();
		result.setCommentaire(pLigne.getCommentaire());
		result.setNomLigne(pLigne.getNom());
		result.setStatut(pLigne.getStatut());
		result.setType(pLigne.getType());
		List<GareDTO> garesDto = new ArrayList<>();
		for (GaresLigne gareLigne : pLigne.getGaresLignes()) {
			GareDTO gareDto = new GareDTO(gareLigne.getGare().getNom(), gareLigne.getOrdre());
			garesDto.add(gareDto);
		}
		result.setGaresDto(garesDto);
		return result;
	}

	public void setGareRepositoryCustom(GareRepositoryCustom gareRepositoryCustom) {
		this.gareRepositoryCustom = gareRepositoryCustom;
	}

}
