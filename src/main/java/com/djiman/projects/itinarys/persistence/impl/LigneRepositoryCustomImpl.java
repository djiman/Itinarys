package com.djiman.projects.itinarys.persistence.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.djiman.projects.itinarys.model.Ligne;
import com.djiman.projects.itinarys.persistence.LigneRepositoryCustom;

@Repository
public class LigneRepositoryCustomImpl implements LigneRepositoryCustom {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Optional<Ligne> getLigneByName(String pNomLigne) {

		return em.createQuery("from Ligne l where l.nom =:nomLigne", Ligne.class).setParameter("nomLigne", pNomLigne)
				.setMaxResults(1).getResultList().stream().findFirst();
	}

	@Override
	@Transactional
	public void deleteAllGaresOfLine(Long pLigneId) {
		em.createQuery("delete from GaresLigne garesLigne where garesLigne.ligne.ligneId=:pLigneId")
				.setParameter("pLigneId", pLigneId)
				.executeUpdate();
	}

}
