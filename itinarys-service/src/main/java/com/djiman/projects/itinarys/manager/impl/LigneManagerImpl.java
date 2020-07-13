package com.djiman.projects.itinarys.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.djiman.projects.itinarys.manager.LigneManager;

import com.djiman.projects.itinarys.dto.GareDTO;
import com.djiman.projects.itinarys.dto.LigneDTO;
import com.djiman.projects.itinarys.persistence.model.Gare;
import com.djiman.projects.itinarys.persistence.model.Ligne;
import com.djiman.projects.itinarys.persistence.GareRepository;
import com.djiman.projects.itinarys.persistence.LigneRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LigneManagerImpl implements LigneManager {

    @Autowired
    LigneRepository ligneRepository;

    @Autowired
    GareRepository gareRepository;

    public Iterable<LigneDTO> getAllLignes() {
        List<Ligne> result = ligneRepository.findAll();
        List<LigneDTO> resultDTO = new ArrayList<>();
        result.forEach(ligne -> {
            resultDTO.add(convertLigneToLigneDto(ligne));
        });
        return resultDTO;
    }

    public LigneDTO getLigneByName(String pNomLigne) {
        LigneDTO result = null;
        Optional<Ligne> ligneOptional = ligneRepository.getLigneByNom(pNomLigne);
        if (ligneOptional.isPresent())
            return convertLigneToLigneDto(ligneOptional.get());
        return result;
    }

    public void createOrModifyLigne(LigneDTO pLigneDto) {
        Ligne newLigne = convertLigneDtoToLigne(pLigneDto);
        ligneRepository.save(newLigne);
    }

    public Ligne convertLigneDtoToLigne(LigneDTO pLigneDto) {

        Optional<Ligne> ligneOptional = ligneRepository.getLigneByNom(pLigneDto.getNomLigne());
        Ligne ligne = null;
        if (ligneOptional != null && ligneOptional.isPresent()) {
            ligne = ligneOptional.get();
            // Vider les gares avant l'ajout des nouvelles
            ligneRepository.deleteById(ligne.get_id());
        } else {
            ligne = new Ligne();
        }
        ligne.setCommentaire(pLigneDto.getCommentaire());
        ligne.setNom(pLigneDto.getNomLigne());
        ligne.setStatut(pLigneDto.getStatut());
        ligne.setType(pLigneDto.getType());
        ligne.setGare_ids(getGareIds(pLigneDto));
        return ligne;
    }

    private List<ObjectId> getGareIds(LigneDTO pLigneDto) {
        List<ObjectId> garesIds = new ArrayList<>();
        for (GareDTO gareDTO : pLigneDto.getGaresDto()) {
            Optional<Gare> gareOptional = gareRepository.getGareByNom(gareDTO.getGare());
            if (!gareOptional.isPresent())
                throw new IllegalArgumentException("Gare inconnue" + gareDTO.getGare());
            garesIds.add(gareOptional.get().get_id());
        }
        return garesIds;
    }

    public LigneDTO convertLigneToLigneDto(Ligne pLigne) {
        LigneDTO result = new LigneDTO();
        result.setCommentaire(pLigne.getCommentaire());
        result.setNomLigne(pLigne.getNom());
        result.setStatut(pLigne.getStatut());
        result.setType(pLigne.getType());
        result.setCouleur(pLigne.getCouleur());
        List<GareDTO> garesDto = new ArrayList<>();
        int index = 1;
        for (ObjectId gareId : pLigne.getGare_ids()) {
            Optional<Gare> gare = gareRepository.findById(gareId);
            GareDTO gareDto = new GareDTO();
            gareDto.setOrdre(index++);
            gareDto.setGare(gare.get().getNom());
            garesDto.add(gareDto);
        }
        Optional<GareDTO> firstGare = garesDto.stream().findFirst();
        if(firstGare.isPresent()) {
            firstGare.get().setType("D");
        }
        Optional<GareDTO> lastGare = garesDto.stream().reduce((first, second) -> second);
        if(lastGare.isPresent()) {
            lastGare.get().setType("T");
        }
        result.setGaresDto(garesDto);
        return result;
    }

    @Override
    public void setLigneRepository(LigneRepository ligneRepository) {
        this.ligneRepository = ligneRepository;
    }

    @Override
    public void setGareRepository(GareRepository gareRepository) {
        this.gareRepository = gareRepository;
    }

}
