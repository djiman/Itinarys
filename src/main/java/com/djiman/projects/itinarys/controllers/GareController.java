/**
 * 
 */
package com.djiman.projects.itinarys.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djiman.projects.itinarys.model.Gare;
import com.djiman.projects.itinarys.persistence.GareRepository;

/**
 * @author gorguindong
 * Initial version 1.0.0
 */

@CrossOrigin
@RestController
public class GareController {

	GareRepository gareRepository;

	public GareController(GareRepository gareRepository) {
		this.gareRepository = gareRepository;
	}

	@RequestMapping("/gare")
    public Iterable<Gare> getGare() {
		return gareRepository.findAll();
    }
	// TODO createOrModifyGare
	
}
