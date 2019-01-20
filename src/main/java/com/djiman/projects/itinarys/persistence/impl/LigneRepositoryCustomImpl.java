package com.djiman.projects.itinarys.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.djiman.projects.itinarys.model.Ligne;
import com.djiman.projects.itinarys.persistence.LigneRepositoryCustom;

@Repository
public class LigneRepositoryCustomImpl implements LigneRepositoryCustom {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Ligne getLigneByName(String pNomLigne) {
		TypedQuery<Ligne> query = (TypedQuery<Ligne>) em.createQuery("from Ligne l where l.nom =:nomLigne",
				Ligne.class);
		query.setParameter("nomLigne", pNomLigne);
		return query.getSingleResult();
	}
}
