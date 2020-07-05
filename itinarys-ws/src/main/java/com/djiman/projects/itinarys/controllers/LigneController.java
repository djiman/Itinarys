package com.djiman.projects.itinarys.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.djiman.projects.itinarys.dto.LigneDTO;
import com.djiman.projects.itinarys.manager.LigneManager;
import com.djiman.projects.itinarys.persistence.GareRepository;
import com.djiman.projects.itinarys.persistence.LigneRepository;
import com.djiman.projects.itinarys.persistence.LigneRepositoryCustom;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author gorguindong Initial version 1.0.0
 */

@CrossOrigin
@RestController
public class LigneController {

    LigneRepository ligneRepository;

    GareRepository gareRepository;

    LigneManager ligneManager;

    LigneRepositoryCustom ligneRepositoryCustom;

    public LigneController(LigneRepository ligneRepository, GareRepository gareRepository, LigneManager ligneManager, LigneRepositoryCustom ligneRepositoryCustom) {
        this.ligneRepository = ligneRepository;
        this.gareRepository = gareRepository;
        this.ligneManager = ligneManager;
        this.ligneRepositoryCustom = ligneRepositoryCustom;
    }

    @ApiOperation(value = "get Lignes", notes = "Recupere toutes les lignes")
    @GetMapping("/lignes")
    public List<LigneDTO> getAllLignes() {
        return (List<LigneDTO>) ligneManager.getAllLignes();
    }

    @ApiOperation(value = "get Ligne by name", notes = "Recupere une ligne avec comme paramètre le nom de la ligne")
    @ApiParam(
            name = "${LigneController.getLigneByName.nomLigne}",
            type = "String",
            value = "Nom de la ligne",
            example = "PTB",
            required = true)
    @GetMapping("/lignes/{nomLigne}")
    public LigneDTO getLigneByName(@PathVariable(value = "nomLigne") String nomLigne) {
        return ligneManager.getLigneByName(nomLigne);
    }

    @ApiOperation(value = "create Ligne", notes = "Créer une nouvelle ligne")
    @PostMapping(value = "/createLigne")
    void createOrModifyLigne(@RequestBody LigneDTO pLigneDto) throws JsonProcessingException {
        ligneManager.createOrModifyLigne(pLigneDto);
    }

}
