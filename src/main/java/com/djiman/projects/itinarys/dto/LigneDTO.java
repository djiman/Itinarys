package com.djiman.projects.itinarys.dto;

import java.io.Serializable;
import java.util.List;

public class LigneDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6296302827665865447L;
	private String nomLigne;
	private String commentaire;
	private Character statut;
	private String type;
	List<GareDTO> garesDto;

	public String getNomLigne() {
		return nomLigne;
	}

	public void setNomLigne(String nomLigne) {
		this.nomLigne = nomLigne;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Character getStatut() {
		return statut;
	}

	public void setStatut(Character statut) {
		this.statut = statut;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<GareDTO> getGaresDto() {
		return garesDto;
	}

	public void setGaresDto(List<GareDTO> garesDto) {
		this.garesDto = garesDto;
	}

}
