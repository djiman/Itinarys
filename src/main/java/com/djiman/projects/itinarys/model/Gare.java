package com.djiman.projects.itinarys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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

	@Column(name = "Ordre")
	private Integer ordre;

	@Column(name = "Nom")
	private String nomGare;

	@Column(name = "Statut")
	private String statut;

	@Column(name = "Ville")
	private String ville;

	@Column(name = "Commentaire")
	private String commentaire;

	public Long getGareId() {
		return gareId;
	}

	public void setGareId(Long gareId) {
		this.gareId = gareId;
	}


	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public String getNomGare() {
		return nomGare;
	}

	public void setNomGare(String nomGare) {
		this.nomGare = nomGare;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentaire == null) ? 0 : commentaire.hashCode());
		result = prime * result + ((gareId == null) ? 0 : gareId.hashCode());
		result = prime * result + ((nomGare == null) ? 0 : nomGare.hashCode());
		result = prime * result + ((ordre == null) ? 0 : ordre.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gare other = (Gare) obj;
		if (commentaire == null) {
			if (other.commentaire != null)
				return false;
		} else if (!commentaire.equals(other.commentaire))
			return false;
		if (gareId == null) {
			if (other.gareId != null)
				return false;
		} else if (!gareId.equals(other.gareId))
			return false;
		if (nomGare == null) {
			if (other.nomGare != null)
				return false;
		} else if (!nomGare.equals(other.nomGare))
			return false;
		if (ordre == null) {
			if (other.ordre != null)
				return false;
		} else if (!ordre.equals(other.ordre))
			return false;
		if (statut == null) {
			if (other.statut != null)
				return false;
		} else if (!statut.equals(other.statut))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}
}
