package com.djiman.projects.itinarys.manager;

import com.djiman.projects.itinarys.dto.GareDTO;
import com.djiman.projects.itinarys.persistence.model.Gare;
import org.springframework.stereotype.Service;


public class GareManager {
	public GareDTO convertGareTGareDto(Gare pGare) {
		GareDTO result = new GareDTO(pGare.getNom(), 1, "");

		return result;
	}
}