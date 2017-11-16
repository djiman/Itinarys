/**
 * 
 */
package com.djiman.projects.itinarys;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.djiman.projects.itinarys.beans.TrajetBean;
import com.djiman.projects.itinarys.model.Trajet;

/**
 * @author gorguindong
 * Initial version 1.0.0
 * 24/09/2017
 */

@RestController
public class TrajetController {

	@Autowired
	TrajetRepository trajetRepository;
	
	@RequestMapping("/trajet")
    public Iterable<Trajet> getTrajet(@RequestParam(value="name", defaultValue="Rio") String name) {
		return trajetRepository.findAll();
    }
	
}
