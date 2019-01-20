package com.djiman.projects.itinarys.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.djiman.projects.itinarys.dto.GareDTO;

/**
 * @author gorguindong Initial version 1.0.0
 */
@Entity(name = "Ligne")
@Table(name = "Ligne")
public class Ligne {

	@Id
	@Column(name = "LigneID")
	@GeneratedValue(generator = "SEQ_LIGNE")
	@GenericGenerator(name = "SEQ_LIGNE", strategy = "increment", parameters = {
			@Parameter(name = "sequence_name", value = "ITINARYS_LIG"), @Parameter(name = "optimizer", value = "none"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	private Long ligneId;

	@Column(name = "Nom", nullable = false)
	private String nom;

	@Column(name = "Statut", length = 1)
	private Character statut;

	@Column(name = "Type", length = 100)
	private String type;

	@Column(name = "commentaire", length = 4000)
	private String commentaire;

	@OneToMany(mappedBy = "ligne", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("ordre")
	private List<GaresLigne> garesLignes = new ArrayList<>();

	public Ligne() {
	}

	public Ligne(Long ligneId, String nom) {
		this.ligneId = ligneId;
		this.nom = nom;
	}

	public Ligne(Long ligneId, String nom, Character statut, String type, String commentaire) {
		this.ligneId = ligneId;
		this.nom = nom;
		this.statut = statut;
		this.type = type;
		this.commentaire = commentaire;
	}

	public Long getLigneId() {
		return this.ligneId;
	}

	public void setLigneId(Long ligneId) {
		this.ligneId = ligneId;
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

	public List<GaresLigne> getGaresLignes() {
		Collections.sort(garesLignes);
		return garesLignes;
	}

	public void setGaresLignes(List<GaresLigne> garesDesservies) {
		this.garesLignes = garesDesservies;
	}

	public void addGare(GareDTO gareDto) {
		GaresLigne gareLigne = new GaresLigne(this, gareDto.getGare(), gareDto.getOrdre());
		this.getGaresLignes().add(gareLigne);
	}

	// TODO removeGare
}
