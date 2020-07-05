package com.djiman.projects.itinarys.persistence;

import com.djiman.projects.itinarys.persistence.model.Gare;

import java.util.Optional;

public interface GareRepositoryCustom {
	public Optional<Gare> getGareByName(String string);
}
