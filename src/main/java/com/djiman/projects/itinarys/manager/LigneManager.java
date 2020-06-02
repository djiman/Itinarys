package com.djiman.projects.itinarys.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djiman.projects.itinarys.dto.GareDTO;
import com.djiman.projects.itinarys.dto.LigneDTO;
import com.djiman.projects.itinarys.model.Gare;
import com.djiman.projects.itinarys.model.GaresLigne;
import com.djiman.projects.itinarys.model.Ligne;
import com.djiman.projects.itinarys.persistence.GareRepository;
import com.djiman.projects.itinarys.persistence.GareRepositoryCustom;
import com.djiman.projects.itinarys.persistence.LigneRepository;
import com.djiman.projects.itinarys.persistence.LigneRepositoryCustom;
import com.djiman.projects.itinarys.util.GareDtoComparator;

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

	public LigneDTO getLigneByName(String pNomLigne) {
		LigneDTO result = null;
		Optional<Ligne> ligneOptional = ligneRepositoryCustom.getLigneByName(pNomLigne);
		if (ligneOptional.isPresent())
			return convertLigneToLigneDto(ligneOptional.get());
		return result;
	}

	public void createOrModifyLigne(LigneDTO pLigneDto) {
		Ligne newLigne = convertLigneDtoToLigne(pLigneDto);
		ligneRepository.save(newLigne);
	}

	public Ligne convertLigneDtoToLigne(LigneDTO pLigneDto) {

		Optional<Ligne> ligneOptional = ligneRepositoryCustom.getLigneByName(pLigneDto.getNomLigne());
		Ligne ligne = null;
		if (ligneOptional != null && ligneOptional.isPresent()) {
			ligne = ligneOptional.get();
			// Vider les gares avant l'ajout des nouvelles
			ligneRepositoryCustom.deleteAllGaresOfLine(ligne.getLigneId());
		} else {
			ligne = new Ligne();
		}
		ligne.setCommentaire(pLigneDto.getCommentaire());
		ligne.setNom(pLigneDto.getNomLigne());
		ligne.setStatut(pLigneDto.getStatut());
		ligne.setType(pLigneDto.getType());

		SortedSet<GaresLigne> garesLigne = new TreeSet<>();
		// On ajoute les gares
		for (GareDTO gareDTO : pLigneDto.getGaresDto()) {
			Optional<Gare> gareOptional = gareRepositoryCustom.getGareByName(gareDTO.getGare());
			if (!gareOptional.isPresent())
				throw new IllegalArgumentException("Gare inconnue" + gareDTO.getGare());
			garesLigne.add(
					new GaresLigne(ligne, gareOptional.get(), gareDTO.getOrdre(), gareDTO.getType()));
		}
		ligne.setGaresLignes(garesLigne);
		return ligne;
	}

	public LigneDTO convertLigneToLigneDto(Ligne pLigne) {
		LigneDTO result = new LigneDTO();
		result.setCommentaire(pLigne.getCommentaire());
		result.setNomLigne(pLigne.getNom());
		result.setStatut(pLigne.getStatut());
		result.setType(pLigne.getType());
		result.setCouleur(pLigne.getCouleur());
		List<GareDTO> garesDto = new ArrayList<>();
		for (GaresLigne gareLigne : pLigne.getGaresLignes()) {
			GareDTO gareDto = new GareDTO(gareLigne.getGare().getNom(), gareLigne.getOrdre(), gareLigne.getType());
			garesDto.add(gareDto);
		}
		garesDto.sort(new GareDtoComparator());
		result.setGaresDto(garesDto);
		return result;
	}

	public void setGareRepositoryCustom(GareRepositoryCustom gareRepositoryCustom) {
		this.gareRepositoryCustom = gareRepositoryCustom;
	}

	public void setLigneRepositoryCustom(LigneRepositoryCustom ligneRepositoryCustom) {
		this.ligneRepositoryCustom = ligneRepositoryCustom;
	}

}
