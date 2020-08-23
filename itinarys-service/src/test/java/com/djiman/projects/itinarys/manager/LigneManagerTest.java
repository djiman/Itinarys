package com.djiman.projects.itinarys.manager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.djiman.projects.itinarys.manager.impl.LigneManagerImpl;
import com.djiman.projects.itinarys.persistence.GareRepository;
import com.djiman.projects.itinarys.persistence.LigneRepository;
import org.bson.types.ObjectId;
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

        String nomGare = "nom gare";
        String idGare = "id gare";
        LigneDTO ligneDto = new LigneDTO();
        ligneDto.setCommentaire("Test Ligne Dto");
        ligneDto.setNomLigne("Test Nom Ligne");
        ligneDto.setStatut('1');
        ligneDto.setType("Train");
        List<GareDTO> garesDto = new ArrayList<>();
        GareDTO gareDto = new GareDTO(nomGare, "type", idGare, "commentaire", '1');
        garesDto.add(gareDto);
        ligneDto.setGaresDto(garesDto);
        Gare gareExpected = new Gare();
        ObjectId gareId = new ObjectId();
        gareExpected.set_id(gareId);
        gareExpected.setNom(nomGare);
        gareExpected.setIdGare(idGare);
        Optional<Gare> opt = Optional.of(gareExpected);
        Mockito.when(gareRepository.getGareByNom("nom gare")).thenReturn(opt);

        Ligne ligne = ligneManager.convertLigneDtoToLigne(ligneDto);
        assertEquals(ligneDto.getNomLigne(), ligne.getNom());
        assertEquals(ligneDto.getType(), ligne.getType());
        assertEquals(ligneDto.getCommentaire(), ligne.getCommentaire());
        assertEquals(ligneDto.getStatut(), ligne.getStatut());
        assertEquals(gareExpected.getIdGare(), ligne.getGare_ids().get(0));
    }

    @Test
    public void testConvertLigneToLigneDto() {
        Ligne ligne = ModelHelper.ligneBuilder("Ligne", "Test ligne", '0', "Train");
        Gare gare = ModelHelper.gareBuilder("PG","PremiereGare", "Test gare", '0', "Ville1");
        Gare gare2 = ModelHelper.gareBuilder("DG","DeuxiemeGare", "Test gare 2", '0', "Ville2");

        ligne.addGare(gare.getIdGare());
        ligne.addGare(gare2.getIdGare());

        Optional<Gare> optionalGare = Optional.of(gare);
        Optional<Gare> optionalGare2 = Optional.of(gare2);

        Mockito.when(gareRepository.getByIdGare(gare.getIdGare())).thenReturn(optionalGare);
        Mockito.when(gareRepository.getByIdGare(gare2.getIdGare())).thenReturn(optionalGare2);

        LigneDTO ligneDTO = ligneManager.convertLigneToLigneDto(ligne);
        assertEquals(ligne.getNom(), ligneDTO.getNomLigne());
        assertEquals(ligne.getCommentaire(), ligneDTO.getCommentaire());
        assertEquals(ligne.getStatut(), ligneDTO.getStatut());
        assertEquals(2, ligneDTO.getGaresDto().size());
    }

}