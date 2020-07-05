package com.djiman.projects.itinarys.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

import com.djiman.projects.itinarys.manager.LigneManager;

import com.djiman.projects.itinarys.dto.GareDTO;
import com.djiman.projects.itinarys.dto.LigneDTO;
import com.djiman.projects.itinarys.persistence.model.Gare;
import com.djiman.projects.itinarys.persistence.model.GaresLigne;
import com.djiman.projects.itinarys.persistence.model.Ligne;
import com.djiman.projects.itinarys.persistence.GareRepository;
import com.djiman.projects.itinarys.persistence.LigneRepository;
import com.djiman.projects.itinarys.util.GareDtoComparator;
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
            ligneRepository.deleteById(ligne.getLigneId());
        } else {
            ligne = new Ligne();
        }
        ligne.setCommentaire(pLigneDto.getCommentaire());
        ligne.setNom(pLigneDto.getNomLigne());
        ligne.setStatut(pLigneDto.getStatut());
        ligne.setType(pLigneDto.getType());

        SortedSet<GaresLigne> garesLigne = new TreeSet<>();
        // On ajoute les gares
        for (GareDTO gareDTO : pLigneDto.getGaresDto()) {
            Optional<Gare> gareOptional = gareRepository.getGareByNom(gareDTO.getGare());
            if (!gareOptional.isPresent())
                throw new IllegalArgumentException("Gare inconnue" + gareDTO.getGare());
            garesLigne.add(
                    new GaresLigne(ligne, gareOptional.get(), gareDTO.getOrdre(), gareDTO.getType()));
        }
        ligne.setGaresLignes(garesLigne);
        return ligne;
    }

    public LigneDTO convertLigneToLigneDto(Ligne pLigne) {
        LigneDTO result = new LigneDTO();
        result.setCommentaire(pLigne.getCommentaire());
        result.setNomLigne(pLigne.getNom());
        result.setStatut(pLigne.getStatut());
        result.setType(pLigne.getType());
        result.setCouleur(pLigne.getCouleur());
        List<GareDTO> garesDto = new ArrayList<>();
        for (GaresLigne gareLigne : pLigne.getGaresLignes()) {
            GareDTO gareDto = new GareDTO(gareLigne.getGare().getNom(), gareLigne.getOrdre(), gareLigne.getType());
            garesDto.add(gareDto);
        }
        garesDto.sort(new GareDtoComparator());
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

