package com.djiman.projects.itinarys.dto;

import java.io.Serializable;

public class GareDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7467815422176817165L;

	private String nomGare;

	private int ordre;

	public GareDTO() {
		// Default constructor
	}

	public GareDTO(String gare, int ordre) {
		this.nomGare = gare;
		this.ordre = ordre;
	}

	public String getGare() {
		return nomGare;
	}

	public void setGare(String gare) {
		this.nomGare = gare;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
}
