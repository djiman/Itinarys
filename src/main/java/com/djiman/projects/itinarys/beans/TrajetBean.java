/**
 * 
 */
package com.djiman.projects.itinarys.beans;

/**
 * @author gorguindong
 * Initial version 1.0.0
 * 24/09/2017
 */

public class TrajetBean {
     
	private String arret;
	private String prochain_arret;
	
	public TrajetBean(String arret, String prochain_arret) {
		this.arret = arret;
		this.prochain_arret = prochain_arret;
	}

	public String getArret() {
		return arret;
	}

	public void setArret(String arret) {
		this.arret = arret;
	}

	public String getProchain_arret() {
		return prochain_arret;
	}

	public void setProchain_arret(String prochain_arret) {
		this.prochain_arret = prochain_arret;
	}
	
}
