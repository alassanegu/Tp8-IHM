package edu.mermet.tp8;

public enum Ressource {
	TITRE("titre"),
	ACTION_QUITTER("actionQuitter"),
	ACTION_DIAPORAMA("actionDiaporama"),
	ACTION_BOUTONS("actionBoutons"),
	ACTION_TEXTE("actionTexte"),
	ACTION_CONVERSION("actionConversion"),
	MENU_FICHIER("menuFichier"),
	MENU_APPLICATIONS("menuApplications"),
	MENU_LANGUES("menuLangues"),
	LANGUE_DEFAUT("langueDefaut"),
	LANGUE_FRANCAIS("langueFrancais"),
	LANGUE_ANGLAIS("langueAnglais"),
	FEN_TEXTE_TITRE("fenTexteTitre"),
	FEN_TEXTE_GRAS("fenTexteGras"),
	FEN_TEXTE_ROUGE("fenTexteRouge"),
	FEN_TEXTE_STYLE("fenTexteStyle"),
    FEN_BOUTONS_TITRE("fenBoutonsTitre"),
	FEN_CONV_TITRE("fenConvTitre"),
	FEN_CONV_ERR_FORMAT("fenConvErrFormat"),
	FEN_CONV_CONVERTIR("fenConvConvertir"),
	FEN_DIAPO_TITRE("fenDiapoTitre");


	
	private String nom;
	Ressource(String leNom) {
		nom = leNom;
	}

	public String getNom() {
		return nom;
	}
}
