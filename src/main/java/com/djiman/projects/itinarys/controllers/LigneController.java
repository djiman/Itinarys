/**
 * 
 */
package com.djiman.projects.itinarys.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djiman.projects.itinarys.model.Ligne;
import com.djiman.projects.itinarys.persistence.LigneRepository;

/**
 * @author gorguindong
 * Initial version 1.0.0
 */

@RestController
public class LigneController {

	@Autowired
	LigneRepository ligneRepository;
	
	@RequestMapping("/ligne")
    public Iterable<Ligne> getGare() {
		return ligneRepository.findAll();
    }
}
