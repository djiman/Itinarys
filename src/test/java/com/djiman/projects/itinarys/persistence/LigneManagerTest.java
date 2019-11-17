package com.djiman.projects.itinarys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.djiman.projects.itinarys.dto.GareDTO;
import com.djiman.projects.itinarys.dto.LigneDTO;
import com.djiman.projects.itinarys.helper.ModelHelper;
import com.djiman.projects.itinarys.manager.LigneManager;
import com.djiman.projects.itinarys.model.Gare;
import com.djiman.projects.itinarys.model.GaresLigne;
import com.djiman.projects.itinarys.model.Ligne;

@RunWith(SpringRunner.class)
@Configuration
@ComponentScan("com.djiman.projects.itinarys.manager")
public class LigneManagerTest {

	// @Autowired
	// TODO Utiliser Spring pour injecter le manager
	private LigneManager ligneManager;

	@Mock
	private GareRepositoryCustom gareRepositoryCustom;

	@Test
	public void testConvertLigneDtoToLigne() {
		ligneManager = new LigneManager();
		ligneManager.setGareRepositoryCustom(gareRepositoryCustom);
		LigneDTO ligneDto = new LigneDTO();
		ligneDto.setCommentaire("Test Ligne Dto");
		ligneDto.setNomLigne("Test Nom Ligne");
		ligneDto.setStatut('1');
		ligneDto.setType("Train");
		List<GareDTO> garesDto = new ArrayList<>();
		GareDTO gareDto = new GareDTO("nom gare", 1);
		garesDto.add(gareDto);
		ligneDto.setGaresDto(garesDto);

		Mockito.when(gareRepositoryCustom.getGareByName("nom gare"))
				.thenReturn(ModelHelper.gareBuilder("nom gare", "Test gare", '0', "Ville1"));

		Ligne ligne = ligneManager.convertLigneDtoToLigne(ligneDto);
		assertEquals("Test Nom Ligne", ligne.getNom());
		assertEquals("Train", ligne.getType());
		assertEquals("Test Ligne Dto", ligne.getCommentaire());
		assertEquals('1', ligne.getStatut().charValue());
		assertEquals("nom gare", ligne.getGaresLignes().iterator().next().getGare().getNom());
	}

	@Test
	public void testConvertLigneToLigneDto() {

		ligneManager = new LigneManager();
		Ligne ligne = ModelHelper.ligneBuilder("Ligne", "Test ligne", '0', "Train");
		
		Gare gare = ModelHelper.gareBuilder("PremiereGare", "Test gare", '0', "Ville1");
		Gare gare2 = ModelHelper.gareBuilder("DeuxiemeGare", "Test gare 2", '0', "Ville2");

		GaresLigne garesLigne1 = new GaresLigne(ligne, gare, 1);
		ligne.addGareLigne(garesLigne1);
		GaresLigne garesLigne2 = new GaresLigne(ligne, gare2, 2);
		ligne.addGareLigne(garesLigne2);

		LigneDTO ligneDTO = ligneManager.convertLigneToLigneDto(ligne);
		assertEquals("Ligne", ligneDTO.getNomLigne());
		assertEquals("Test ligne", ligneDTO.getCommentaire());
		assertEquals('0', ligneDTO.getStatut().charValue());
		assertTrue(ligneDTO.getGaresDto().size() == 2);
	}

}
