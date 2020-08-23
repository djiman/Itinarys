package com.djiman.projects.itinarys.manager;

import com.djiman.projects.itinarys.dto.GareDTO;
import com.djiman.projects.itinarys.persistence.model.Gare;


public interface GareManager {
	 GareDTO convertGareTGareDto(Gare pGare);

	 Gare convertGareDtoToGare(GareDTO pGareDto) ;
}
