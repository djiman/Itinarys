package com.djiman.projects.itinarys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author gorguindong Initial version 1.0.0
 */
@Entity
@Table(name = "gares_ligne")
public class GaresLigne implements Serializable, Comparable<GaresLigne> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3264880269413940662L;

	@Id
	@Column(name = "gare_ligneid")
	@GeneratedValue(generator = "SEQ_GARELIGNE")
	@GenericGenerator(name = "SEQ_GARELIGNE", strategy = "increment", parameters = {
			@Parameter(name = "sequence_name", value = "ITINARYS_GARE_LIGNE"),
			@Parameter(name = "optimizer", value = "none"), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	private long id;

	@ManyToOne
	@JoinColumn(name = "LigneID")
	private Ligne ligne;

	@ManyToOne
	@JoinColumn(name = "GareID")
	private Gare gare;

	@Column(name = "ORDRE", nullable = false)
	private Integer ordre;

	public GaresLigne() {
	}

	public GaresLigne(Ligne ligne, Gare gare, Integer ordre) {
		this.ligne = ligne;
		this.gare = gare;
		this.ordre = ordre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

		if (this.getOrdre() == null)
			return 0;
		if (gareLigne.getOrdre() == null)
			return 0;
		if (this.getOrdre() > gareLigne.getOrdre()) {
			return 1;
		}
		if (this.getOrdre() < gareLigne.getOrdre()) {
			return -1;
		}
		return 0;
	}
}
