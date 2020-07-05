package com.djiman.projects.itinarys.persistence;

import com.djiman.projects.builder.LigneBuilder;
import com.djiman.projects.itinarys.persistence.model.Gare;
import com.djiman.projects.itinarys.persistence.model.GaresLigne;
import com.djiman.projects.itinarys.persistence.model.Ligne;
import com.djiman.projects.itinarys.helper.ModelHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LigneRepositoryTest {

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
		GaresLigne garesLigne1 = new GaresLigne(ligne, gare, 2, null);

		GaresLigne garesLigne2 = new GaresLigne(ligne, gare2, 3, "T");

		GaresLigne garesLigne3 = new GaresLigne(ligne, gare3, 1, "D");
		Set<GaresLigne> gareLigne1 = new HashSet<>();
		gareLigne1.add(garesLigne1);
		gareLigne1.add(garesLigne2);
		gareLigne1.add(garesLigne3);
		ligne.getGaresLignes().addAll(gareLigne1);

		// Ligne1
		ligne1 = ModelHelper.ligneBuilder("PremiereLigne", "Test ligne", '0', "Train");
		Set<GaresLigne> gareLigne2 = new HashSet<>();
		gareLigne2.add(new GaresLigne(ligne1, gare, 1, null));
		gareLigne2.add(new GaresLigne(ligne1, gare2, 2, null));
		ligne1.getGaresLignes().addAll(gareLigne2);

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
		Optional<Ligne> ligneFromBdd = ligneRepository.findById(ligne1.getLigneId());
		assertTrue(ligneFromBdd != null);
		assertEquals("PremiereLigne", ligneFromBdd.get().getNom());
		assertEquals("Train", ligneFromBdd.get().getType());
	}

	@Test
	public void testRecupererToutesLesLignes() {
		List<Ligne> lignesBdd = ligneRepository.findAll();
		assertTrue(lignesBdd.size() == 5);
	}

	@Test
	public void testRecupererToutesLesGaresDuneLigne() {
		Optional<Ligne> ligneFromBdd = ligneRepository.findById(ligne.getLigneId());
		Set<GaresLigne> garesLigneFromBdd = ligneFromBdd.get().getGaresLignes();
		assertTrue(ligneFromBdd != null);
		assertTrue(garesLigneFromBdd.size() == 3);
	}

	@Test
	public void testRecupererToutesLesGaresDuneLigneByNomLigne() {
		Ligne ligneFromBdd = ligneRepository.getLigneByNom("PremiereLigne").get();
		Set<GaresLigne> garesLigneFromBdd = ligneFromBdd.getGaresLignes();
		assertTrue(ligneFromBdd != null);
		assertTrue(garesLigneFromBdd.size() == 2);
	}

	@Test
	public void testRecupererToutesLesGaresDuneLigneAvecOrdre() {
		Ligne ligneFromBdd = ligneRepository.getLigneByNom("Ligne").get();
		List<GaresLigne> garesLigneFromBdd = new ArrayList<>(ligneFromBdd.getGaresLignes());

		assertTrue(ligneFromBdd != null);
		assertTrue(garesLigneFromBdd.size() == 3);

		assertEquals(1, garesLigneFromBdd.get(0).getOrdre().intValue());
		assertEquals(2, garesLigneFromBdd.get(1).getOrdre().intValue());
		assertEquals(3, garesLigneFromBdd.get(2).getOrdre().intValue());
	}

}
