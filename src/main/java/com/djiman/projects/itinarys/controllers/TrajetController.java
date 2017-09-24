/**
 * 
 */
package com.djiman.projects.itinarys.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.djiman.projects.itinarys.beans.TrajetBean;

/**
 * @author gorguindong
 * Initial version 1.0.0
 * 24/09/2017
 */

@RestController
public class TrajetController {
	
	@RequestMapping("/trajet")
    public TrajetBean getTrajet(@RequestParam(value="name", defaultValue="Rio") String name) {
        return new TrajetBean("Rio","Dakar");
    }
	
}
