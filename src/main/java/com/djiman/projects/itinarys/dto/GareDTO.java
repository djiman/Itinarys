package com.djiman.projects.itinarys.dto;

public class GareDTO {

	private String nomGare;

	private int ordre;

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
