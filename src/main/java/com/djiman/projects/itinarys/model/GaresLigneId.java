package com.djiman.projects.itinarys.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author gorguindong Initial version 1.0.0
 */
@Embeddable
public class GaresLigneId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6308069938368125654L;

	@Column(name = "GareID", nullable = false)
	private Long gareId;

	@Column(name = "LigneID", nullable = false)
	private Long ligneId;

	public GaresLigneId() {
	}

	public GaresLigneId(Long ligneId, Long gareId) {
		this.ligneId = ligneId;
		this.gareId = gareId;
	}

	public Long getLigneId() {
		return this.ligneId;
	}

	public void setLigneId(Long ligneId) {
		this.ligneId = ligneId;
	}

	public Long getGareId() {
		return this.gareId;
	}

	public void setGareId(Long gareId) {
		this.gareId = gareId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gareId == null) ? 0 : gareId.hashCode());
		result = prime * result + ((ligneId == null) ? 0 : ligneId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GaresLigneId other = (GaresLigneId) obj;
		if (gareId == null) {
			if (other.gareId != null)
				return false;
		} else if (!gareId.equals(other.gareId))
			return false;
		if (ligneId == null) {
			if (other.ligneId != null)
				return false;
		} else if (!ligneId.equals(other.ligneId))
			return false;
		return true;
	}

}
