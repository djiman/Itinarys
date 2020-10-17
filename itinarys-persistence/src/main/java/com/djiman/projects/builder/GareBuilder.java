package com.djiman.projects.builder;

import com.djiman.projects.itinarys.persistence.model.Gare;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author gorguindong Initial version 1.0.0
 */
public class GareBuilder {

	private ObjectId _id;
	private String idGare;
	private String nomGare;
	private Character statut;
	private String ville;
	private String commentaire;
	private String links;

	public GareBuilder _id(ObjectId _id) {
		this._id = _id;
		return this;
	}

	public GareBuilder idGare(String idGare) {
		this.idGare = idGare;
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

	public  GareBuilder links(String links){
		this.links = links;
		return this;
	}

	public Gare build() {
		Gare gare = new Gare();
		gare.set_id(this._id);
		gare.setIdGare(this.idGare);
		gare.setCommentaire(this.commentaire);
		gare.setNom(this.nomGare);
		gare.setStatut(this.statut);
		gare.setVille(this.ville);
		gare.setLinks(this.links);
		return gare;
	}
}
