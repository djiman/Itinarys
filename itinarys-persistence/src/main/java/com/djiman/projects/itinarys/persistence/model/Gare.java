package com.djiman.projects.itinarys.persistence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * @author gorguindong Initial version 1.0.0
 */

@Document(collection = "gare")
public class Gare {

	@Id
	private ObjectId _id;

	private String nom;

	private Character statut;

	private String ville;

	private String commentaire;

	private String idGare;

	private String type;

	public Gare() {
	}

	public ObjectId get_id() {
		return this._id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Character getStatut() {
		return this.statut;
	}

	public void setStatut(Character statut) {
		this.statut = statut;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getIdGare() { return idGare; }

	public void setIdGare(String idGare) { this.idGare = idGare; }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Gare gare = (Gare) o;
		return Objects.equals(_id, gare._id) &&
				Objects.equals(nom, gare.nom) &&
				Objects.equals(statut, gare.statut) &&
				Objects.equals(ville, gare.ville) &&
				Objects.equals(commentaire, gare.commentaire) &&
				Objects.equals(idGare, gare.idGare) &&
				Objects.equals(type, gare.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, nom, statut, ville, commentaire, idGare, type);
	}
}
