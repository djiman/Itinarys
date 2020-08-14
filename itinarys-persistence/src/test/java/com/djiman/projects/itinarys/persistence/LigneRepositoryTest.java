package com.djiman.projects.itinarys.persistence;

import com.djiman.projects.builder.LigneBuilder;
import com.djiman.projects.itinarys.persistence.model.Gare;
import com.djiman.projects.itinarys.persistence.model.Ligne;
import com.djiman.projects.itinarys.helper.ModelHelper;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LigneRepositoryTest {

    @Autowired
    LigneRepository ligneRepository;

    @Autowired
    GareRepository gareRepository;

    Ligne ligne, ligne1, ligne2, ligne3, ligne4;

    @Before
    public void setUp() {
        gareRepository.deleteAll();
        ligneRepository.deleteAll();

        Gare gare = ModelHelper.gareBuilder("PG", "PremiereGare", "Test gare", '0', "Ville1");
        Gare gare2 = ModelHelper.gareBuilder("DG", "DeuxiemeGare", "Test gare 2", '0', "Ville2");
        Gare gare3 = ModelHelper.gareBuilder("TG","TroisiemeGare", "Test gare 3", '0', "Ville3");

        gareRepository.save(gare);
        gareRepository.save(gare2);
        gareRepository.save(gare3);

        // Ligne
        ligne = ModelHelper.ligneBuilder("Ligne", "Test ligne", '0', "Train");

        List<String> gareIds = new ArrayList<>();
        gareIds.add(gare3.getIdGare());
        gareIds.add(gare.getIdGare());
        gareIds.add(gare2.getIdGare());
        ligne.getGare_ids().addAll(gareIds);

        // Ligne1
        ligne1 = ModelHelper.ligneBuilder("PremiereLigne", "Test ligne", '0', "Train");
        List<String> gareIds2 = new ArrayList<>();
        gareIds2.add(gare.getIdGare());
        gareIds2.add(gare2.getIdGare());
        ligne1.getGare_ids().addAll(gareIds2);

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
        assertFalse(lignesFromBDD.get(0).getGare_ids().isEmpty());
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
        Optional<Ligne> ligneFromBdd = ligneRepository.findById(ligne1.get_id());
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
        Optional<Ligne> ligneFromBdd = ligneRepository.findById(ligne.get_id());
        List<String> garesLigneFromBdd = ligneFromBdd.get().getGare_ids();
        assertTrue(ligneFromBdd != null);
        assertTrue(garesLigneFromBdd.size() == 3);
    }

    @Test
    public void testRecupererToutesLesGaresDuneLigneByNomLigne() {
        Ligne ligneFromBdd = ligneRepository.getLigneByNom("PremiereLigne").get();
        List<String> garesLigneFromBdd = ligneFromBdd.getGare_ids();
        assertTrue(ligneFromBdd != null);
        assertTrue(garesLigneFromBdd.size() == 2);
    }

    @Test
    public void testRecupererToutesLesGaresDuneLigneAvecOrdre() {
        Ligne ligneFromBdd = ligneRepository.getLigneByNom("Ligne").get();
        List<String> garesFromBdd = new ArrayList<>(ligneFromBdd.getGare_ids());

        assertTrue(ligneFromBdd != null);
        assertTrue(garesFromBdd.size() == 3);

        Gare gareBdd1 = gareRepository.getByIdGare(garesFromBdd.get(0)).get();
        Gare gareBdd2 = gareRepository.getByIdGare(garesFromBdd.get(1)).get();
        Gare gareBdd3 = gareRepository.getByIdGare(garesFromBdd.get(2)).get();

		assertEquals("TroisiemeGare", gareBdd1.getNom());
		assertEquals("PremiereGare", gareBdd2.getNom());
		assertEquals("DeuxiemeGare", gareBdd3.getNom());
    }

}
