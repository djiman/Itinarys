package com.djiman.projects.builder;

import com.djiman.projects.itinarys.persistence.model.Ligne;
import org.bson.types.ObjectId;

/**
 * @author gorguindong Initial version 1.0.0
 */
public class LigneBuilder {

	private ObjectId _id;
	private String nomLigne;
	private Character statut;
	private String typeTransport;
	private String commentaire;

	public  LigneBuilder _id(ObjectId _id) {
		this._id = _id;
		return this;
	}

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
		ligne.set_id(this._id);
		ligne.setNom(this.nomLigne);
		ligne.setStatut(this.statut);
		ligne.setType(this.typeTransport);
		ligne.setCommentaire(this.commentaire);
		return ligne;
	}
}
