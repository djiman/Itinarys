package com.djiman.projects.itinarys.persistence;

import java.util.Optional;

import com.djiman.projects.itinarys.model.Gare;

public interface GareRepositoryCustom {
	public Optional<Gare> getGareByName(String string);
}
