package com.djiman.projects.builder;

import com.djiman.projects.itinarys.persistence.model.Ligne;

/**
 * @author gorguindong Initial version 1.0.0
 */
public class LigneBuilder {

	private String nomLigne;
	private Character statut;
	private String typeTransport;
	private String commentaire;

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
	

	public Ligne build() {
		Ligne ligne = new Ligne();
		ligne.setNom(this.nomLigne);
		ligne.setStatut(this.statut);
		ligne.setType(this.typeTransport);
		ligne.setCommentaire(this.commentaire);
		return ligne;
	}
}
