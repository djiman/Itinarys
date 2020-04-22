package com.djiman.projects.itinarys.dto;

import java.io.Serializable;

public class GareDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7467815422176817165L;

	private String nomGare;

	private int ordre;

	private String type;

	public GareDTO() {
		// Default constructor
	}

	public GareDTO(String gare, int ordre, String type) {
		this.nomGare = gare;
		this.ordre = ordre;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
