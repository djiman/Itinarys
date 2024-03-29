package com.djiman.projects.itinarys.manager.impl;

import com.djiman.projects.itinarys.dto.GareDTO;
import com.djiman.projects.itinarys.manager.GareManager;
import com.djiman.projects.itinarys.persistence.model.Gare;
import org.springframework.stereotype.Service;

@Service
public class GareManagerImpl implements GareManager {

    public GareDTO convertGareTGareDto(Gare pGare) {
        return new GareDTO(pGare.getNom(), pGare.getType(), pGare.getIdGare(), pGare.getCommentaire(), pGare.getStatut());
    }

    public Gare convertGareDtoToGare(GareDTO pGareDto) {
        Gare gare = new Gare();
        gare.setIdGare(pGareDto.getIdGare());
        gare.setNom(pGareDto.getNomGare());
        gare.setCommentaire(pGareDto.getCommentaire());
        gare.setStatut(pGareDto.getStatut());
        //TODO A adapter
        if(pGareDto.getLinksBus() != null) {
            gare.setLinksBus(pGareDto.getLinksBus().get(0).getNomLigne());
        }
        if(pGareDto.getLinksTrain() != null) {
            gare.setLinksTrain(pGareDto.getLinksTrain().get(0).getNomLigne());
        }
        gare.setIdImage(pGareDto.getIdImage());
        return gare;
    }
}
