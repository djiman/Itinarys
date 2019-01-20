package com.djiman.projects.itinarys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.djiman.projects.builder.GareBuilder;
import com.djiman.projects.builder.LigneBuilder;
import com.djiman.projects.itinarys.dto.GareDTO;
import com.djiman.projects.itinarys.model.Gare;
import com.djiman.projects.itinarys.model.GaresLigne;
import com.djiman.projects.itinarys.model.Ligne;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("com.djiman.projects.itinarys")
public class LigneRepositoryTest {

	@Autowired
	LigneRepositoryCustom ligneRepositoryCustom;

	@Autowired
	LigneRepository ligneRepository;

	@Autowired
	GareRepository gareRepository;

	Ligne ligne, ligne1, ligne2, ligne3, ligne4;

	@Before
	public void setUp() {

		Gare gare = new GareBuilder().commentaire("Test gare").nomGare("PremiereGare").statut('0').ville("Gare1")
				.build();
		Gare gare2 = new GareBuilder().commentaire("Test gare 2").nomGare("DeuxiemeGare").statut('0').ville("Gare2")
				.build();
		Gare gare3 = new GareBuilder().commentaire("Test gare 3").nomGare("TroisiemeGare").statut('0').ville("Gare3")
				.build();

		gareRepository.save(gare);
		gareRepository.save(gare2);
		gareRepository.save(gare3);

		ligne = new LigneBuilder().commentaire("Test ligne").nomLigne("Ligne").statut('0').typeTransport("Train")
				.build();
		GareDTO gareDTO3 = new GareDTO(gare3, new Integer(2));
		ligne.addGare(gareDTO3);
		GareDTO gareDTO2 = new GareDTO(gare2, new Integer(3));
		ligne.addGare(gareDTO2);
		GareDTO gareDTO = new GareDTO(gare, new Integer(1));
		ligne.addGare(gareDTO);

		ligne1 = new LigneBuilder().commentaire("Test ligne").nomLigne("PremiereLigne").statut('0')
				.typeTransport("Train").build();
		ligne1.addGare(gareDTO);
		ligne1.addGare(gareDTO3);

		ligne2 = new LigneBuilder().commentaire("Test ligne2").nomLigne("DeuxiemeLigne").statut('0')
				.typeTransport("Bus").build();
		ligne2.addGare(gareDTO2);
		ligne2.addGare(gareDTO);

		ligne3 = new LigneBuilder().commentaire("Test ligne3").nomLigne("TroisiemeLigne").statut('1')
				.typeTransport("Bus").build();
		ligne3.addGare(gareDTO);
		ligne3.addGare(gareDTO3);

		ligne4 = new LigneBuilder().commentaire("Test ligne4").nomLigne("QuatriemeLigne").statut('0')
				.typeTransport("Metro").build();
		ligne4.addGare(gareDTO2);
		ligne4.addGare(gareDTO);
		ligneRepository.save(ligne);
		ligneRepository.save(ligne1);
		ligneRepository.save(ligne2);
		ligneRepository.save(ligne3);
		ligneRepository.save(ligne4);
	}

	@Test
	public void testSaveUneLigne() {
		List<Ligne> lignesFromBDD = (List<Ligne>) ligneRepository.findAll();
		assertFalse(lignesFromBDD.isEmpty());
		assertEquals("Ligne", lignesFromBDD.get(0).getNom());
		assertFalse(lignesFromBDD.get(0).getGaresLignes().isEmpty());
	}

	@Test
	public void testSavePlusieursLignes() {
		List<Ligne> lignesFromBDD = (List<Ligne>) ligneRepository.findAll();
		assertFalse(lignesFromBDD.isEmpty());
		assertEquals(5, lignesFromBDD.size());
	}

	@Test
	public void testRecupererUneLigne() {
		Ligne ligne1 = new LigneBuilder().commentaire("Test ligne").nomLigne("PremiereLigne").statut('0')
				.typeTransport("Train").build();
		ligneRepository.save(ligne1);
		Ligne ligneFromBdd = ligneRepository.findOne(ligne1.getLigneId());
		assertTrue(ligneFromBdd != null);
		assertEquals("PremiereLigne", ligneFromBdd.getNom());
		assertEquals("Train", ligneFromBdd.getType());
	}

	@Test
	public void testRecupererToutesLesLignes() {
		List<Ligne> lignesBdd = (List<Ligne>) ligneRepository.findAll();
		assertTrue(lignesBdd.size() == 5);
	}

	@Test
	public void testRecupererToutesLesGaresDuneLigne() {
		Ligne ligneFromBdd = ligneRepository.findOne(ligne.getLigneId());
		List<GaresLigne> garesLigneFromBdd = ligneFromBdd.getGaresLignes();
		assertTrue(ligneFromBdd != null);
		assertTrue(garesLigneFromBdd.size() == 3);
	}

	@Test
	public void testRecupererToutesLesGaresDuneLigneByNomLigne() {
		Ligne ligneFromBdd = ligneRepositoryCustom.getLigneByName("PremiereLigne");
		List<GaresLigne> garesLigneFromBdd = ligneFromBdd.getGaresLignes();
		assertTrue(ligneFromBdd != null);
		assertTrue(garesLigneFromBdd.size() == 2);
	}

	@Test
	public void testRecupererToutesLesGaresDuneLigneAvecOrdre() {
		Ligne ligneFromBdd = ligneRepositoryCustom.getLigneByName("Ligne");
		List<GaresLigne> garesLigneFromBdd = ligneFromBdd.getGaresLignes();
		assertTrue(ligneFromBdd != null);
		assertTrue(garesLigneFromBdd.size() == 3);

		assertEquals("PremiereGare", garesLigneFromBdd.get(0).getGare().getNom());
		assertEquals("TroisiemeGare", garesLigneFromBdd.get(1).getGare().getNom());
		assertEquals("DeuxiemeGare", garesLigneFromBdd.get(2).getGare().getNom());
	}
}
