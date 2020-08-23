package com.djiman.projects.itinarys.persistence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * @author gorguindong Initial version 1.0.0
 */
@Document(collection = "ligne")
public class Ligne {

	@Id
	private ObjectId _id;

	private String nom;

	private Character statut;

	private String type;

	private String commentaire;

	private String couleur;

	private List<String> gare_ids = new ArrayList<>();

	public Ligne() {
	}

	public Ligne(ObjectId _id, String nom) {
		this._id = _id;
		this.nom = nom;
	}

	public Ligne(ObjectId _id, String nom, Character statut, String type, String commentaire) {
		this._id = _id;
		this.nom = nom;
		this.statut = statut;
		this.type = type;
		this.commentaire = commentaire;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public List<String> getGare_ids() {
		return gare_ids;
	}

	public void setGare_ids(List<String> gare_ids) {
		this.gare_ids = gare_ids;
	}

	public void addGare(String gareLigne) {
		this.gare_ids.add(gareLigne);
	}

	@Override
	public String toString() {
		return "Ligne [ligneId=" + _id + ", nom=" + nom + ", statut=" + statut + ", type=" + type + ", commentaire="
				+ commentaire + ", gareIds=" + gare_ids + "]";
	}

	// TODO removeGare


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ligne ligne = (Ligne) o;
		return Objects.equals(_id, ligne._id) &&
				Objects.equals(nom, ligne.nom) &&
				Objects.equals(statut, ligne.statut) &&
				Objects.equals(type, ligne.type) &&
				Objects.equals(commentaire, ligne.commentaire) &&
				Objects.equals(couleur, ligne.couleur) &&
				Objects.equals(gare_ids, ligne.gare_ids);
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, nom, statut, type, commentaire, couleur, gare_ids);
	}
}
