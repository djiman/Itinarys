package com.djiman.projects.itinarys.util;

import java.util.Comparator;

import com.djiman.projects.itinarys.model.GaresLigne;

/**
 * @author gorguindong
 *
 */
public class GareLigneComarator implements Comparator<GaresLigne> {

	@Override
	public int compare(GaresLigne gareLigne1, GaresLigne gareLigne2) {
		if (gareLigne1.getOrdre() > gareLigne2.getOrdre()) {
			return 1;
		}
		if (gareLigne1.getOrdre() < gareLigne2.getOrdre()) {
			return -1;
		}
		return 0;
	}

}
