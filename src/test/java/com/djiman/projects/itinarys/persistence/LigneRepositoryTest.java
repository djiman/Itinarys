package com.djiman.projects.itinarys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import com.djiman.projects.builder.GareBuilder;
import com.djiman.projects.builder.LigneBuilder;
import com.djiman.projects.itinarys.model.Gare;
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

	@Test
	public void testSaveUneLigne() {
		Ligne ligne = new LigneBuilder().commentaire("Test ligne").nomLigne("PremiereLigne").statut("OK")
				.typeTransport("Train").build();
		Gare gare1 = new GareBuilder().commentaire("Test gare").ligneId(1L).nomGare("PremiereGare").ordre(1)
				.statut("OK").ville("Gare1").build();
		gareRepository.save(gare1);
		Gare gare2 = new GareBuilder().commentaire("Test gare 2").ligneId(1L).nomGare("DeuxiemeGare").ordre(3)
				.statut("OK").ville("Gare2").build();
		gareRepository.save(gare2);
		Gare gare3 = new GareBuilder().commentaire("Test gare 3").ligneId(2L).nomGare("TroisiemeGare").ordre(4)
				.statut("OK").ville("Gare3").build();
		gareRepository.save(gare3);
		Gare gare4 = new GareBuilder().commentaire("Test gare 4").ligneId(1L).nomGare("QuatriemeGare").ordre(2)
				.statut("OK").ville("Gare4").build();
		gareRepository.save(gare4);
		
		Set<Gare> garesDesservies = new HashSet<Gare>(Arrays.asList(gare1, gare2, gare3, gare4));
		ligne.setGaresDesservies(garesDesservies);

		ligneRepository.save(ligne);
		List<Ligne> lignesFromBDD = (List<Ligne>) ligneRepository.findAll();
		assertFalse(lignesFromBDD.isEmpty());
		assertEquals("PremiereLigne", lignesFromBDD.get(0).getNomLigne());
		assertFalse(lignesFromBDD.get(0).getGaresDesservies().isEmpty());
	}

	@Test
	public void testSavePlusieursLignes() {
		buildAndSaveLignes();
		List<Ligne> lignesFromBDD = (List<Ligne>) ligneRepository.findAll();
		assertFalse(lignesFromBDD.isEmpty());
		assertEquals(4, lignesFromBDD.size());
	}

	private void buildAndSaveLignes() {
		Ligne ligne1 = new LigneBuilder().commentaire("Test ligne").nomLigne("PremiereLigne").statut("OK")
				.typeTransport("Train").build();

		Ligne ligne2 = new LigneBuilder().commentaire("Test ligne2").nomLigne("DeuxiemeLigne").statut("OK")
				.typeTransport("Bus").build();

		Ligne ligne3 = new LigneBuilder().commentaire("Test ligne3").nomLigne("TroisiemeLigne").statut("KO")
				.typeTransport("Bus").build();

		Ligne ligne4 = new LigneBuilder().commentaire("Test ligne4").nomLigne("QuatriemeLigne").statut("OK")
				.typeTransport("Metro").build();

		ligneRepository.save(ligne1);
		ligneRepository.save(ligne2);
		ligneRepository.save(ligne3);
		ligneRepository.save(ligne4);
	}
	
	@Test
	public void testRecupererUneLigne() {
		Ligne ligne1 = new LigneBuilder().commentaire("Test ligne").nomLigne("PremiereLigne").statut("OK")
				.typeTransport("Train").build();
		ligneRepository.save(ligne1);
		Ligne ligneFromBdd = ligneRepository.findOne(ligne1.getLigneId());
		assertTrue(ligneFromBdd != null);
		assertEquals("PremiereLigne",ligneFromBdd.getNomLigne());
		assertEquals("Train",ligneFromBdd.getTypeTransport());
	}
	
	@Test
	public void testRecupererToutesLesLignes(){
		buildAndSaveLignes();
		List<Ligne> lignesBdd = (List<Ligne>) ligneRepository.findAll();
		assertTrue(lignesBdd.size() == 4);
	}
	
	@Test
	public void testRecupererToutesLesGaresDuneLigne() {
		Ligne ligne1 = buildAndSaveLigneAvecGares();
		Ligne ligneFromBdd = ligneRepository.findOne(ligne1.getLigneId());
		Set<Gare> garesLigneFromBdd = ligneFromBdd.getGaresDesservies();
		assertTrue(ligneFromBdd != null);
		assertTrue(garesLigneFromBdd.size() == 3);		
	}
	
	@Test
	public void testRecupererToutesLesGaresDuneLigneByNomLigne() {
		buildAndSaveLigneAvecGares();
		Ligne ligneFromBdd = ligneRepositoryCustom.getLigneByName("PremiereLigne");
		Set<Gare> garesLigneFromBdd = ligneFromBdd.getGaresDesservies();
		assertTrue(ligneFromBdd != null);
		assertTrue(garesLigneFromBdd.size() == 3);
	}

	private Ligne buildAndSaveLigneAvecGares() {
		Gare gare = new GareBuilder().commentaire("Test gare").nomGare("PremiereGare").ordre(1)
				.statut("OK").ville("Gare1").build();
		Gare gare2 = new GareBuilder().commentaire("Test gare 2").ligneId(1L).nomGare("DeuxiemeGare").ordre(3)
				.statut("OK").ville("Gare2").build();

		Gare gare3 = new GareBuilder().commentaire("Test gare 3").ligneId(2L).nomGare("TroisiemeGare").ordre(4)
				.statut("OK").ville("Gare3").build();
		Set<Gare> garesLigne = new HashSet<>();
		garesLigne.addAll(Arrays.asList(gare, gare2, gare3));
		Ligne ligne1 = new LigneBuilder().commentaire("Test ligne").nomLigne("PremiereLigne").statut("OK")
				.typeTransport("Train").garesLigne(garesLigne).build();
		ligneRepository.save(ligne1);
		return ligne1;
	}
}
