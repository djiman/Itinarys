/**
 * 
 */
package com.djiman.projects.itinarys.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.djiman.projects.itinarys.model.Ligne;
import com.djiman.projects.itinarys.persistence.LigneRepository;
import com.djiman.projects.itinarys.persistence.LigneRepositoryCustom;
import com.djiman.projects.itinarys.util.ConstantesMessages;

/**
 * @author gorguindong
 * Initial version 1.0.0
 */

@RestController
public class LigneController {

	@Autowired
	LigneRepository ligneRepository;
	
	@Autowired
	LigneRepositoryCustom ligneRepositoryCustom;

	@RequestMapping("/ligne")
	public Iterable<Ligne> getAllLignes() {
		return ligneRepository.findAll();
    }

	@RequestMapping("/ligneByName")
	public Object getLigneByName(@RequestParam(value = "nomLigne") String nomLigne) {
		Object result = null;
		try {
			result = ligneRepositoryCustom.getLigneByName(nomLigne);
		} catch (Exception e) {
			result = ConstantesMessages.AUCUNE_LIGNE_TROUVEE;
		}
		return result;
    }
}
