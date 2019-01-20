package com.djiman.projects.builder;

import java.util.Set;

import com.djiman.projects.itinarys.model.GaresLigne;
import com.djiman.projects.itinarys.model.Ligne;

/**
 * @author gorguindong Initial version 1.0.0
 */
public class LigneBuilder {

	private String nomLigne;
	private Character statut;
	private String typeTransport;
	private String commentaire;
	private Set<GaresLigne> garesligne;

	public LigneBuilder nomLigne(String nomLigne) {
		this.nomLigne = nomLigne;
		return this;
	}

	public LigneBuilder statut(Character statut) {
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
	
	public LigneBuilder garesLigne(Set<GaresLigne> garesLigne) {
		this.garesligne = garesLigne;
		return this;
	}

	public Ligne build() {
		Ligne ligne = new Ligne();
		ligne.setNom(this.nomLigne);
		ligne.setStatut(this.statut);
		ligne.setType(this.typeTransport);
		ligne.setCommentaire(this.commentaire);
		// ligne.setGaresLignes(this.garesligne);
		return ligne;
	}
}
