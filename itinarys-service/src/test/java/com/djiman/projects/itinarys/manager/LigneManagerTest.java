package com.djiman.projects.itinarys.manager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.djiman.projects.itinarys.manager.impl.LigneManagerImpl;
import com.djiman.projects.itinarys.persistence.GareRepository;
import com.djiman.projects.itinarys.persistence.LigneRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.djiman.projects.itinarys.dto.GareDTO;
import com.djiman.projects.itinarys.dto.LigneDTO;
import com.djiman.projects.itinarys.helper.ModelHelper;
import com.djiman.projects.itinarys.persistence.model.Gare;
import com.djiman.projects.itinarys.persistence.model.GaresLigne;
import com.djiman.projects.itinarys.persistence.model.Ligne;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class LigneManagerTest {

    private final LigneManager ligneManager = Mockito.spy(LigneManagerImpl.class);

    @MockBean
    private LigneRepository ligneRepository;

    @MockBean
    private GareRepository gareRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ligneManager.setLigneRepository(ligneRepository);
        ligneManager.setGareRepository(gareRepository);
    }

    @Test
    public void testConvertLigneDtoToLigne() {
        LigneDTO ligneDto = new LigneDTO();
        ligneDto.setCommentaire("Test Ligne Dto");
        ligneDto.setNomLigne("Test Nom Ligne");
        ligneDto.setStatut('1');
        ligneDto.setType("Train");
        List<GareDTO> garesDto = new ArrayList<>();
        GareDTO gareDto = new GareDTO("nom gare", 1, "");
        garesDto.add(gareDto);
        ligneDto.setGaresDto(garesDto);
        Gare gareExpected = new Gare();
        gareExpected.setNom("nom gare");
        Optional<Gare> opt = Optional.of(gareExpected);
        Mockito.when(gareRepository.getGareByNom("nom gare")).thenReturn(opt);

        Ligne ligne = ligneManager.convertLigneDtoToLigne(ligneDto);
        assertEquals("Test Nom Ligne", ligne.getNom());
        assertEquals("Train", ligne.getType());
        assertEquals("Test Ligne Dto", ligne.getCommentaire());
        assertEquals('1', ligne.getStatut().charValue());
        assertEquals("nom gare", ligne.getGaresLignes().iterator().next().getGare().getNom());
    }

    @Test
    public void testConvertLigneToLigneDto() {
        Ligne ligne = ModelHelper.ligneBuilder("Ligne", "Test ligne", '0', "Train");
        Gare gare = ModelHelper.gareBuilder("PremiereGare", "Test gare", '0', "Ville1");
        Gare gare2 = ModelHelper.gareBuilder("DeuxiemeGare", "Test gare 2", '0', "Ville2");

        GaresLigne garesLigne1 = new GaresLigne(ligne, gare, 1, "D");
        ligne.addGareLigne(garesLigne1);
        GaresLigne garesLigne2 = new GaresLigne(ligne, gare2, 2, "T");
        ligne.addGareLigne(garesLigne2);

        LigneDTO ligneDTO = ligneManager.convertLigneToLigneDto(ligne);
        assertEquals("Ligne", ligneDTO.getNomLigne());
        assertEquals("Test ligne", ligneDTO.getCommentaire());
        assertEquals('0', ligneDTO.getStatut().charValue());
        assertEquals(2, ligneDTO.getGaresDto().size());
    }

}