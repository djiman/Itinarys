package com.djiman.projects.builder;

import com.djiman.projects.itinarys.persistence.model.Gare;

/**
 * @author gorguindong Initial version 1.0.0
 */
public class GareBuilder {

	private Long gareId;
	private String nomGare;
	private Character statut;
	private String ville;
	private String commentaire;

	public GareBuilder gareId(Long gareId) {
		this.gareId = gareId;
		return this;
	}

	public GareBuilder nomGare(String nomGare) {
		this.nomGare = nomGare;
		return this;
	}

	public GareBuilder statut(Character statut) {
		this.statut = statut;
		return this;
	}

	public GareBuilder ville(String ville) {
		this.ville = ville;
		return this;
	}

	public GareBuilder commentaire(String commentaire) {
		this.commentaire = commentaire;
		return this;
	}

	public Gare build() {
		Gare gare = new Gare();
		gare.setGareId(this.gareId);
		gare.setCommentaire(this.commentaire);
		gare.setNom(this.nomGare);
		gare.setStatut(this.statut);
		gare.setVille(this.ville);
		return gare;
	}
}
