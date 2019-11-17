package com.djiman.projects.itinarys.helper;

import com.djiman.projects.builder.GareBuilder;
import com.djiman.projects.builder.LigneBuilder;
import com.djiman.projects.itinarys.model.Gare;
import com.djiman.projects.itinarys.model.Ligne;

public class ModelHelper {

	public static Gare gareBuilder(String pNomGare, String pCommentaire, Character pStatut, String pVille) {
		return new GareBuilder().commentaire(pCommentaire).nomGare(pNomGare).statut(pStatut).ville(pVille).build();
	}

	public static Ligne ligneBuilder(String pNomLigne, String pCommentaire, Character pStatut, String pTypeTransport) {
		return new LigneBuilder().commentaire(pCommentaire).nomLigne(pNomLigne).statut(pStatut)
				.typeTransport(pTypeTransport)
				.build();
	}

}
