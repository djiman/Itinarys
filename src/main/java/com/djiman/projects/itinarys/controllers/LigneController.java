/**
 * 
 */
package com.djiman.projects.itinarys.controllers;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.djiman.projects.itinarys.dto.LigneDTO;
import com.djiman.projects.itinarys.manager.LigneManager;
import com.djiman.projects.itinarys.persistence.GareRepository;
import com.djiman.projects.itinarys.persistence.LigneRepository;
import com.djiman.projects.itinarys.persistence.LigneRepositoryCustom;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author gorguindong Initial version 1.0.0
 */

@CrossOrigin
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

	@ApiOperation(value = "get Lignes",notes = "Recupere toutes les lignes")
	@RequestMapping("/lignes")
	public List<LigneDTO> getAllLignes() {
		return (List<LigneDTO>) ligneManager.getAllLignes();
	}

	@ApiOperation(value = "get Ligne by name",notes = "Recupere une ligne avec comme paramètre le nom de la ligne")
	@ApiParam(
			name =  "${LigneController.getLigneByName.nomLigne}",
			type = "String",
			value = "Nom de la ligne",
			example = "PTB",
			required = true)
	@RequestMapping("/lignes/{nomLigne}")
	public LigneDTO getLigneByName(@PathVariable(value = "nomLigne") String nomLigne) {
		return ligneManager.getLigneByName(nomLigne);
	}

	@ApiOperation(value = "create Ligne",notes = "Créer une nouvelle ligne")
	@RequestMapping(value = "/createLigne", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	void createOrModifyLigne(@RequestBody LigneDTO pLigneDto) throws JsonProcessingException {
		ligneManager.createOrModifyLigne(pLigneDto);
	}

}
