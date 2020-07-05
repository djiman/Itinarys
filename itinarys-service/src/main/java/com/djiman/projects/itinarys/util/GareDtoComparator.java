package com.djiman.projects.itinarys.util;

import java.util.Comparator;

import com.djiman.projects.itinarys.dto.GareDTO;

public class GareDtoComparator implements Comparator<GareDTO> {
	@Override
	public int compare(GareDTO gare1, GareDTO gare2) {
		return gare1.getOrdre() - gare2.getOrdre();
	}
}