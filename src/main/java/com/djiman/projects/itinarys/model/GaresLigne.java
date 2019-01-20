package com.djiman.projects.itinarys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * @author gorguindong Initial version 1.0.0
 */
@Entity(name = "GaresLigne")
@Table(name = "garesLigne")
public class GaresLigne implements Serializable, Comparable<GaresLigne> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3264880269413940662L;

	@EmbeddedId
	private GaresLigneId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("ligneId")
	private Ligne ligne;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("gareId")
	private Gare gare;

	@Column(name = "ORDRE", nullable = false)
	private Integer ordre;

	public GaresLigne() {
	}

	public GaresLigne(Ligne ligne, Gare gare, Integer ordre) {
		this.ligne = ligne;
		this.gare = gare;
		this.ordre = ordre;
		this.id = new GaresLigneId(ligne.getLigneId(), gare.getGareId());
	}

	public GaresLigne(GaresLigneId id, Integer ordre) {
		this.id = id;
		this.ordre = ordre;
	}

	public GaresLigneId getId() {
		return this.id;
	}

	public void setId(GaresLigneId id) {
		this.id = id;
	}

	public Integer getOrdre() {
		return this.ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public Ligne getLigne() {
		return ligne;
	}

	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}

	public Gare getGare() {
		return gare;
	}

	public void setGare(Gare gare) {
		this.gare = gare;
	}

	@Override
	public int compareTo(GaresLigne gareLigne) {
		if (this.getOrdre() > gareLigne.getOrdre()) {
			return 1;
		}
		if (this.getOrdre() < gareLigne.getOrdre()) {
			return -1;
		}
		return 0;
	}
}
