package com.djiman.projects.itinarys.persistence;

import java.util.List;

import com.djiman.projects.itinarys.model.Gare;

public interface GareRepositoryCustom {
	public List<Gare> getAllGaresOfOneLigne(Long pIdLigne);
}
