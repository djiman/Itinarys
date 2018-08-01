package com.djiman.projects.itinarys.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.djiman.projects.itinarys.model.Gare;
import com.djiman.projects.itinarys.persistence.GareRepositoryCustom;

@Repository
public class GareRepositoryCustomImpl implements GareRepositoryCustom {
	
	@PersistenceContext
    private EntityManager em;
	
	public List<Gare> getAllGaresOfOneLigne(Long pIdLigne){
		TypedQuery<Gare> query = (TypedQuery<Gare>) em.createQuery("from Gare g where g.ligneId =:pIdLigne order by g.ordre asc", Gare.class);
		query.setParameter("pIdLigne", pIdLigne);
		return query.getResultList();		
	}
}
