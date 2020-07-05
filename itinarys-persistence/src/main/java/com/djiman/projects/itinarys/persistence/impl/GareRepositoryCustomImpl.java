package com.djiman.projects.itinarys.persistence.impl;

import com.djiman.projects.itinarys.persistence.model.Gare;
import org.springframework.stereotype.Repository;
import com.djiman.projects.itinarys.persistence.GareRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class GareRepositoryCustomImpl implements GareRepositoryCustom {

	@PersistenceContext
	EntityManager em;

	@Override
	public Optional<Gare> getGareByName(String pNomGare) {
		return em.createQuery("from Gare g where g.nom =:nomGare", Gare.class).setParameter("nomGare", pNomGare)
				.setMaxResults(1).getResultList().stream().findFirst();
	}
}
