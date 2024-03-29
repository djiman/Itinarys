package com.djiman.projects.itinarys.dto;

import java.io.Serializable;
import java.util.List;

public class GareDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7467815422176817165L;

	private String nomGare;

	private String type;

	private String idGare;

	private String commentaire;

	private Character statut;

	private int ordre;

	private List<LinkDTO> linksBus;
	private List<LinkDTO> linksTrain;
	private String idImage;

	public GareDTO() {
		// Default constructor
	}

	public GareDTO(String gare, String type, String idGare, String commentaire, Character statut) {
		this.nomGare = gare;
		this.type = type;
		this.idGare = idGare;
		this.commentaire = commentaire;
		this.statut = statut;
	}

	public String getGare() {
		return nomGare;
	}

	public void setGare(String gare) {
		this.nomGare = gare;
	}

	public String getNomGare() {
		return nomGare;
	}

	public void setNomGare(String nomGare) {
		this.nomGare = nomGare;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdGare() {
		return idGare;
	}

	public void setIdGare(String idGare) {
		this.idGare = idGare;
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

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public void setStatut(Character statut) {
		this.statut = statut;
	}

	public List<LinkDTO> getLinksBus() {
		return linksBus;
	}

	public void setLinksBus(List<LinkDTO> linksBus) {
		this.linksBus = linksBus;
	}

	public List<LinkDTO> getLinksTrain() {
		return linksTrain;
	}

	public void setLinksTrain(List<LinkDTO> linksTrain) {
		this.linksTrain = linksTrain;
	}

	public String getIdImage() {
		return idImage;
	}

	public void setIdImage(String idImage) {
		this.idImage = idImage;
	}
}
