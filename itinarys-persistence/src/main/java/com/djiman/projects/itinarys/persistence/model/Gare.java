package com.djiman.projects.itinarys.persistence.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * @author gorguindong Initial version 1.0.0
 */
@Entity
@Table(name = "Gare")
public class Gare {

	@Id
	@Column(name = "GareID")
	@GeneratedValue(generator = "SEQ_GARE")
	@GenericGenerator(name = "SEQ_GARE", strategy = "increment", parameters = {
			@Parameter(name = "sequence_name", value = "ITINARYS_GAR"), @Parameter(name = "optimizer", value = "none"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	private Long gareId;

	@Column(name = "Nom", nullable = false)
	private String nom;

	@Column(name = "Statut", length = 1)
	private Character statut;

	@Column(name = "Ville", length = 100)
	private String ville;

	@Column(name = "commentaire", length = 4000)
	private String commentaire;

	public Gare() {
	}

	public Gare(Long gareId, String nom) {
		this.gareId = gareId;
		this.nom = nom;
	}

	public Gare(Long gareId, String nom, Character statut, String ville, String commentaire) {
		this.gareId = gareId;
		this.nom = nom;
		this.statut = statut;
		this.ville = ville;
		this.commentaire = commentaire;
	}

	public Long getGareId() {
		return this.gareId;
	}

	public void setGareId(Long gareId) {
		this.gareId = gareId;
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

}
