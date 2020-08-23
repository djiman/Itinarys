package com.djiman.projects.itinarys.controllers;

import com.djiman.projects.itinarys.dto.GareDTO;
import com.djiman.projects.itinarys.manager.GareManager;
import org.springframework.web.bind.annotation.*;
import com.djiman.projects.itinarys.persistence.model.Gare;
import com.djiman.projects.itinarys.persistence.GareRepository;

/**
 * @author gorguindong
 * Initial version 1.0.0
 */

@CrossOrigin
@RestController
public class GareController {

    GareRepository gareRepository;

    GareManager gareManager;

    public GareController(GareRepository gareRepository, GareManager gareManager) {
        this.gareRepository = gareRepository;
        this.gareManager = gareManager;
    }

    @GetMapping("/gares")
    public Iterable<Gare> getGares() {
        return gareRepository.findAll();
    }

    @PostMapping("/gare")
    public @ResponseBody Gare createGare(@RequestBody  GareDTO pGareDto)
    {
        Gare gare = gareManager.convertGareDtoToGare(pGareDto);
        gareRepository.save(gare);
        return gare;
    }

}
