package com.djiman.projects.itinarys.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author gorguindong Initial version 1.0.0
 */
@Entity
@Table(name = "Ligne")
public class Ligne {

	@Id
	@Column(name = "LigneID")
	@GeneratedValue(generator = "SEQ_LIGNE")
	@GenericGenerator(name = "SEQ_LIGNE", strategy = "increment", parameters = {
			@Parameter(name = "sequence_name", value = "ITINARYS_LIG"), @Parameter(name = "optimizer", value = "none"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	private Integer ligneId;

	@Column(name = "Nom")
	private String nomLigne;

	@Column(name = "Statut")
	private String statut;

	@Column(name = "Type")
	private String typeTransport;

	@Column(name = "Commentaire")
	private String commentaire;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "garesligne", joinColumns = { 
			@JoinColumn(name = "LigneID") }, 
			inverseJoinColumns = { @JoinColumn(name = "GareID") })
	private Set<Gare> garesligne;

	public Integer getLigneId() {
		return ligneId;
	}

	public void setLigneId(Integer ligneId) {
		this.ligneId = ligneId;
	}

	public String getNomLigne() {
		return nomLigne;
	}

	public void setNomLigne(String nomLigne) {
		this.nomLigne = nomLigne;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getTypeTransport() {
		return typeTransport;
	}

	public void setTypeTransport(String typeTransport) {
		this.typeTransport = typeTransport;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Set<Gare> getGaresDesservies() {
		return garesligne;
	}

	public void setGaresDesservies(Set<Gare> garesDesservies) {
		this.garesligne = garesDesservies;
	}

	@Override
	public String toString() {
		return "Gare [nomLigne=" + nomLigne + ", statut=" + statut + ", typeTransport=" + typeTransport
				+ ", commentaire=" + commentaire + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentaire == null) ? 0 : commentaire.hashCode());
		result = prime * result + ((nomLigne == null) ? 0 : nomLigne.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
		result = prime * result + ((typeTransport == null) ? 0 : typeTransport.hashCode());
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
		Ligne other = (Ligne) obj;
		if (commentaire == null) {
			if (other.commentaire != null)
				return false;
		} else if (!commentaire.equals(other.commentaire))
			return false;
		if (nomLigne == null) {
			if (other.nomLigne != null)
				return false;
		} else if (!nomLigne.equals(other.nomLigne))
			return false;
		if (statut == null) {
			if (other.statut != null)
				return false;
		} else if (!statut.equals(other.statut))
			return false;
		if (typeTransport == null) {
			if (other.typeTransport != null)
				return false;
		} else if (!typeTransport.equals(other.typeTransport))
			return false;
		return true;
	}

}
