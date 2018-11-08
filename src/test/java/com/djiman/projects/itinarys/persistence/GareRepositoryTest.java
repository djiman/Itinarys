package com.djiman.projects.itinarys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.djiman.projects.builder.GareBuilder;
import com.djiman.projects.itinarys.model.Gare;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("com.djiman.projects.itinarys")
public class GareRepositoryTest {

	@Autowired
	GareRepository gareRepository;

	@Autowired
	GareRepositoryCustom gareRepositoryCustom;

	@Test
	public void testSaveUneGare() {
		Gare gare = new GareBuilder().commentaire("Test gare").nomGare("PremiereGare").ordre(1)
				.statut("OK").ville("Gare1").build();
		gareRepository.save(gare);
		List<Gare> garesFromBDD = (List<Gare>) gareRepository.findAll();
		assertFalse(garesFromBDD.isEmpty());
		assertEquals("PremiereGare", garesFromBDD.get(0).getNomGare());
	}
	
	@Test
	@Ignore
	public void testGetAllGaresOfOneLigne() {
		buildAndSaveGares();
		Set<Gare> garesFromBDD = (Set<Gare>) gareRepositoryCustom.getAllGaresOfOneLigne(1L);
		assertFalse(garesFromBDD.isEmpty());
		assertEquals(3, garesFromBDD.size());
	}

	@Test
	public void testSavePlusieursGares() {
		buildAndSaveGares();
		List<Gare> garesFromBDD = (List<Gare>) gareRepository.findAll();
		assertFalse(garesFromBDD.isEmpty());
		assertEquals(4, garesFromBDD.size());
	}

	private void buildAndSaveGares() {
		Gare gare1 = new GareBuilder().commentaire("Test gare").ligneId(1L).nomGare("PremiereGare").ordre(1)
				.statut("OK").ville("Gare1").build();

		Gare gare2 = new GareBuilder().commentaire("Test gare 2").ligneId(1L).nomGare("DeuxiemeGare").ordre(3)
				.statut("OK").ville("Gare2").build();

		Gare gare3 = new GareBuilder().commentaire("Test gare 3").ligneId(2L).nomGare("TroisiemeGare").ordre(4)
				.statut("OK").ville("Gare3").build();

		Gare gare4 = new GareBuilder().commentaire("Test gare 4").ligneId(1L).nomGare("QuatriemeGare").ordre(2)
				.statut("OK").ville("Gare4").build();

		gareRepository.save(gare1);
		gareRepository.save(gare2);
		gareRepository.save(gare3);
		gareRepository.save(gare4);
	}
}
