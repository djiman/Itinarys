package com.djiman.projects.itinarys.persistence;

import com.djiman.projects.builder.GareBuilder;
import com.djiman.projects.itinarys.persistence.model.Gare;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GareRepositoryTest {

	@Autowired
	GareRepository gareRepository;

	@Autowired
	GareRepositoryCustom gareRepositoryCustom;

	@Test
	public void testSaveUneGare() {
		Gare gare = new GareBuilder().commentaire("Test gare").nomGare("PremiereGare").statut('0').ville("Gare1")
				.build();
		gareRepository.save(gare);
		List<Gare> garesFromBDD = (List<Gare>) gareRepository.findAll();
		assertFalse(garesFromBDD.isEmpty());
		assertEquals("PremiereGare", garesFromBDD.get(0).getNom());
	}

	@Test
	public void testSavePlusieursGares() {
		gareRepository.deleteAll();
		buildAndSaveGares();
		List<Gare> garesFromBDD =  gareRepository.findAll();
		assertFalse(garesFromBDD.isEmpty());
		assertEquals(4, garesFromBDD.size());
	}

	private void buildAndSaveGares() {
		Gare gare1 = new GareBuilder().commentaire("Test gare").nomGare("PremiereGare").statut('0').ville("Gare1")
				.build();
		Gare gare2 = new GareBuilder().commentaire("Test gare 2").nomGare("DeuxiemeGare").statut('0').ville("Gare2")
				.build();
		Gare gare3 = new GareBuilder().commentaire("Test gare 3").nomGare("TroisiemeGare").statut('0').ville("Gare3")
				.build();
		Gare gare4 = new GareBuilder().commentaire("Test gare 4").nomGare("QuatriemeGare").statut('0').ville("Gare4")
				.build();
		gareRepository.save(gare1);
		gareRepository.save(gare2);
		gareRepository.save(gare3);
		gareRepository.save(gare4);
	}
}
