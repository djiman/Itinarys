package com.djiman.projects.itinarys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.djiman.projects.builder.LigneBuilder;
import com.djiman.projects.itinarys.helper.ModelHelper;
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

		Gare gare = ModelHelper.gareBuilder("PremiereGare", "Test gare", '0', "Ville1");
		Gare gare2 = ModelHelper.gareBuilder("DeuxiemeGare", "Test gare 2", '0', "Ville2");
		Gare gare3 = ModelHelper.gareBuilder("TroisiemeGare", "Test gare 3", '0', "Ville3");

		gareRepository.save(gare);
		gareRepository.save(gare2);
		gareRepository.save(gare3);

		// Ligne
		ligne = ModelHelper.ligneBuilder("Ligne", "Test ligne", '0', "Train");
		GaresLigne garesLigne1 = new GaresLigne(ligne, gare, 2);
		ligne.addGareLigne(garesLigne1);
		GaresLigne garesLigne2 = new GaresLigne(ligne, gare2, 3);
		ligne.addGareLigne(garesLigne2);
		GaresLigne garesLigne3 = new GaresLigne(ligne, gare3, 1);
		ligne.addGareLigne(garesLigne3);

		// Ligne1
		ligne1 = ModelHelper.ligneBuilder("PremiereLigne", "Test ligne", '0', "Train");
		ligne1.addGareLigne(new GaresLigne(ligne1, gare, 1));
		ligne1.addGareLigne(new GaresLigne(ligne1, gare2, 2));

		// Ligne2
		ligne2 = ModelHelper.ligneBuilder("DeuxiemeLigne", "Test ligne2", '0', "Bus");

		// Ligne3
		ligne3 = ModelHelper.ligneBuilder("TroisiemeLigne", "Test ligne3", '1', "Bus");

		// Ligne4
		ligne4 = ModelHelper.ligneBuilder("QuatriemeLigne", "Test ligne4", '0', "Metro");

		ligneRepository.save(ligne);
		ligneRepository.save(ligne1);
		ligneRepository.save(ligne2);
		ligneRepository.save(ligne3);
		ligneRepository.save(ligne4);
	}

	@Test
	public void testSaveUneLigne() {
		List<Ligne> lignesFromBDD = ligneRepository.findAll();
		assertFalse(lignesFromBDD.isEmpty());
		assertEquals("Ligne", lignesFromBDD.get(0).getNom());
		assertFalse(lignesFromBDD.get(0).getGaresLignes().isEmpty());
	}

	@Test
	public void testSavePlusieursLignes() {
		List<Ligne> lignesFromBDD = ligneRepository.findAll();
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
		List<Ligne> lignesBdd = ligneRepository.findAll();
		assertTrue(lignesBdd.size() == 5);
	}

	@Test
	public void testRecupererToutesLesGaresDuneLigne() {
		Ligne ligneFromBdd = ligneRepository.findOne(ligne.getLigneId());
		Set<GaresLigne> garesLigneFromBdd = ligneFromBdd.getGaresLignes();
		assertTrue(ligneFromBdd != null);
		assertTrue(garesLigneFromBdd.size() == 3);
	}

	@Test
	public void testRecupererToutesLesGaresDuneLigneByNomLigne() {
		Ligne ligneFromBdd = ligneRepositoryCustom.getLigneByName("PremiereLigne");
		Set<GaresLigne> garesLigneFromBdd = ligneFromBdd.getGaresLignes();
		assertTrue(ligneFromBdd != null);
		assertTrue(garesLigneFromBdd.size() == 2);
	}

	@Test
	public void testRecupererToutesLesGaresDuneLigneAvecOrdre() {
		Ligne ligneFromBdd = ligneRepositoryCustom.getLigneByName("Ligne");
		List<GaresLigne> garesLigneFromBdd = new ArrayList<GaresLigne>(ligneFromBdd.getGaresLignes());

		assertTrue(ligneFromBdd != null);
		assertTrue(garesLigneFromBdd.size() == 3);

		assertEquals("PremiereGare", garesLigneFromBdd.get(1).getGare().getNom());
		assertEquals("TroisiemeGare", garesLigneFromBdd.get(0).getGare().getNom());
		assertEquals("DeuxiemeGare", garesLigneFromBdd.get(2).getGare().getNom());
	}
}
