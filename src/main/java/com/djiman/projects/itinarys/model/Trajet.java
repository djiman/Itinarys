package com.djiman.projects.itinarys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author gorguindong
 * Initial version 1.0.0
 * 24/09/2017
 */

@Entity
@Table(name="Trajet")
public class Trajet {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Column(name="arret")
	private String arret;
	
	@Column(name="prochain_arret")
	private String prochainArret;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArret() {
		return arret;
	}

	public void setArret(String arret) {
		this.arret = arret;
	}

	public String getProchainArret() {
		return prochainArret;
	}

	public void setProchainArret(String prochainArret) {
		this.prochainArret = prochainArret;
	}
}
