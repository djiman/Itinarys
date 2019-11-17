/**
 * 
 */
package com.djiman.projects.itinarys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.djiman.projects.itinarys.dto.LigneDTO;
import com.djiman.projects.itinarys.manager.LigneManager;
import com.djiman.projects.itinarys.model.Gare;
import com.djiman.projects.itinarys.model.GaresLigne;
import com.djiman.projects.itinarys.model.Ligne;
import com.djiman.projects.itinarys.persistence.GareRepository;
import com.djiman.projects.itinarys.persistence.LigneRepository;
import com.djiman.projects.itinarys.persistence.LigneRepositoryCustom;

/**
 * @author gorguindong Initial version 1.0.0
 */

@RestController
public class LigneController {

	@Autowired
	LigneRepository ligneRepository;

	@Autowired
	GareRepository gareRepository;

	@Autowired
	LigneManager ligneManager;

	@Autowired
	LigneRepositoryCustom ligneRepositoryCustom;

	@RequestMapping("/lignes")
	public Iterable<LigneDTO> getAllLignes() {
		return ligneManager.getAllLignes();
	}

	@RequestMapping("/ligneByName")
	public Object getLigneByName(@RequestParam(value = "nomLigne") String nomLigne) {
		return ligneManager.getLigneByName(nomLigne);
	}

	@RequestMapping("/lignes/{nomLigne}")
	void createModifyLigne(@PathVariable String nomLigne) {
		Ligne newLigne = new Ligne();
		newLigne.setCommentaire("Maj from controller");
		newLigne.setNom("test ligne");
		newLigne.setStatut('1');
		newLigne.setType("Test");

		Gare gare = gareRepository.findOne(1L);
		GaresLigne garesLigne1 = new GaresLigne(newLigne, gare, 2);
		newLigne.addGareLigne(garesLigne1);

		Gare gare2 = gareRepository.findOne(2L);
		GaresLigne garesLigne2 = new GaresLigne(newLigne, gare2, 1);
		newLigne.addGareLigne(garesLigne2);

		Ligne ligne = ligneRepositoryCustom.getLigneByName(nomLigne);
		if (ligne != null) {
			ligne.setCommentaire(newLigne.getCommentaire());
			ligne.setNom(newLigne.getNom());
			ligne.setStatut(newLigne.getStatut());
			ligne.setType(newLigne.getType());
		}
		ligneRepository.save(newLigne);

		LigneDTO ligneDTO = new LigneDTO();
		ligneManager.createModifyLigne(ligneDTO);
	}
}
