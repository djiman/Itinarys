package com.djiman.projects.builder;

import java.util.Set;

import com.djiman.projects.itinarys.model.Gare;
import com.djiman.projects.itinarys.model.Ligne;

/**
 * @author gorguindong Initial version 1.0.0
 */
public class LigneBuilder {

	private Integer ligneId;
	private String nomLigne;
	private String statut;
	private String typeTransport;
	private String commentaire;
	private Set<Gare> garesligne;

	public LigneBuilder ligneId(Integer ligneId) {
		this.ligneId = ligneId;
		return this;
	}

	public LigneBuilder nomLigne(String nomLigne) {
		this.nomLigne = nomLigne;
		return this;
	}

	public LigneBuilder statut(String statut) {
		this.statut = statut;
		return this;
	}

	public LigneBuilder typeTransport(String typeTransport) {
		this.typeTransport = typeTransport;
		return this;
	}

	public LigneBuilder commentaire(String commentaire) {
		this.commentaire = commentaire;
		return this;
	}
	
	public LigneBuilder garesLigne(Set<Gare> garesLigne) {
		this.garesligne = garesLigne;
		return this;
	}

	public Ligne build() {
		Ligne ligne = new Ligne();
		ligne.setLigneId(this.ligneId);
		ligne.setNomLigne(this.nomLigne);
		ligne.setStatut(this.statut);
		ligne.setTypeTransport(this.typeTransport);
		ligne.setCommentaire(this.commentaire);
		ligne.setGaresDesservies(this.garesligne);
		return ligne;
	}
}
