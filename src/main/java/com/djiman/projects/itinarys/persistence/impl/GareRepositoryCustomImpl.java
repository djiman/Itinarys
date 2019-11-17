package com.djiman.projects.itinarys.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.djiman.projects.itinarys.model.Gare;
import com.djiman.projects.itinarys.persistence.GareRepositoryCustom;

@Repository
public class GareRepositoryCustomImpl implements GareRepositoryCustom {

	@PersistenceContext
	EntityManager em;

	@Override
	public Gare getGareByName(String pNomGare) {
		TypedQuery<Gare> query = (TypedQuery<Gare>) em.createQuery("from Gare g where g.nom =:nomGare", Gare.class);
		query.setParameter("nomGare", pNomGare);
		return query.getSingleResult();
	}
}
