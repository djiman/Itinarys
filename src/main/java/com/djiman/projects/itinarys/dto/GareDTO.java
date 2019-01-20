package com.djiman.projects.itinarys.dto;

import com.djiman.projects.itinarys.model.Gare;

public class GareDTO {

	private Gare gare;

	private int ordre;

	public GareDTO(Gare gare, int ordre) {
		super();
		this.gare = gare;
		this.ordre = ordre;
	}

	public Gare getGare() {
		return gare;
	}

	public void setGare(Gare gare) {
		this.gare = gare;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
}
