package com.djiman.projects.builder;

import com.djiman.projects.itinarys.model.Gare;

/**
 * @author gorguindong Initial version 1.0.0
 */
public class GareBuilder {

	private Long gareId;
	private Long ligneId;
	private Integer ordre;
	private String nomGare;
	private String statut;
	private String ville;
	private String commentaire;

	public GareBuilder gareId(Long gareId) {
		this.gareId = gareId;
		return this;
	}

	public GareBuilder ligneId(Long ligneId) {
		this.ligneId = ligneId;
		return this;
	}

	public GareBuilder ordre(Integer ordre) {
		this.ordre = ordre;
		return this;
	}

	public GareBuilder nomGare(String nomGare) {
		this.nomGare = nomGare;
		return this;
	}

	public GareBuilder statut(String statut) {
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
		gare.setLigneId(this.ligneId);
		gare.setOrdre(this.ordre);
		gare.setNomGare(this.nomGare);
		gare.setStatut(this.statut);
		gare.setVille(this.ville);
		return gare;
	}
}
