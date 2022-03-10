package edu.mermet.tp8;


import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

import edu.mermet.tp8.actions.ActionAfficherBoutons;
import edu.mermet.tp8.actions.ActionAfficherCommentFaire;
import edu.mermet.tp8.actions.ActionAfficherConfig;
import edu.mermet.tp8.actions.ActionAfficherConversion;
import edu.mermet.tp8.actions.ActionAfficherDiaporama;
import edu.mermet.tp8.actions.ActionAfficherTexte;
import edu.mermet.tp8.actions.ActionQuitter;
import edu.mermet.tp8.fenetres.AbstractFenetreInterne;
import edu.mermet.tp8.fenetres.FenetreBoutons;
import edu.mermet.tp8.fenetres.FenetreConversion;
import edu.mermet.tp8.fenetres.FenetreDiaporama;
import edu.mermet.tp8.fenetres.FenetreSuggestion;
import edu.mermet.tp8.fenetres.FenetreTexte;

/**
 *
 * @author brunomermet
 */
public class Application extends JFrame {
	AbstractFenetreInterne conversion;
	AbstractFenetreInterne texte;
	AbstractFenetreInterne diaporama;
	AbstractFenetreInterne boutons;
	private Action actionQuitter;
	private Action actionAfficherConversion;
	private Action actionAfficherTexte;
	private Action actionAfficherDiaporama;
	private Action actionAfficherBoutons;
	private Action actionAfficherCommentFaire;
	private Action actionAfficherConfig;


	private JMenu menuFichier;
	private JMenu menuApplications;
	private JMenu menuAides;

	static JMenuItem itemConversion, itemTexte, itemDiaporama, itemBoutons;

	public Application(String utilisateur) {
		super("multi-fenêtres");
		this.setContentPane(new JDesktopPane());

		Application.ajouterUtilisateur(utilisateur);
		// ****** Barre de menu ******
		JMenuBar barre = new JMenuBar();
		// ------ menu Fichier ------
		menuFichier = new JMenu("Fichier");
		menuFichier.setMnemonic(KeyEvent.VK_F);
		actionQuitter = new ActionQuitter(this);
		JMenuItem quitter = new JMenuItem(actionQuitter);
		menuFichier.add(quitter);
		barre.add(menuFichier);
		this.setJMenuBar(barre);
		// ------ menu Applications ------
		menuApplications = new JMenu("Applications");
		menuApplications.setMnemonic(KeyEvent.VK_A);
		actionAfficherConversion = new ActionAfficherConversion(this);
		itemConversion = new JMenuItem(actionAfficherConversion);
		menuApplications.add(itemConversion);
		actionAfficherTexte = new ActionAfficherTexte(this);
		itemTexte = new JMenuItem(actionAfficherTexte);
		menuApplications.add(itemTexte);
		actionAfficherDiaporama = new ActionAfficherDiaporama(this);
		itemDiaporama = new JMenuItem(actionAfficherDiaporama);
		menuApplications.add(itemDiaporama);
		actionAfficherBoutons = new ActionAfficherBoutons(this);
		itemBoutons = new JMenuItem(actionAfficherBoutons);
		menuApplications.add(itemBoutons);
		barre.add(menuApplications);

		// ------ menu Aide ------
		menuAides = new JMenu("Aide");
		menuAides.setMnemonic(KeyEvent.VK_M);
		actionAfficherCommentFaire = new ActionAfficherCommentFaire(this);
		JMenuItem itemCommentFaire = new JMenuItem(actionAfficherCommentFaire);
		menuAides.add(itemCommentFaire);
		actionAfficherConfig = new ActionAfficherConfig(this,actionAfficherConfig,utilisateur);
		JMenuItem itemConfig = new JMenuItem(actionAfficherConfig);
		menuAides.add(itemConfig);
		barre.add(menuAides);
		// ****** Fin barre de menu ******


		// ****** Création des fenêtres ******
		// ------ fenêtre conversion ------
		conversion = new FenetreConversion(this, actionAfficherConversion);
		this.add(conversion);
		// ------ fenêtre texte ------
		texte = new FenetreTexte(this, actionAfficherTexte);
		this.add(texte);
		// ------ fenêtre diaporama ------
		diaporama = new FenetreDiaporama(this, actionAfficherDiaporama);
		this.add(diaporama);
		// ------ fenêtre boutons ------
		boutons = new FenetreBoutons(this,actionAfficherBoutons);
		this.add(boutons);

		// ****** Fin création fenêtres ******
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,300);
		this.setLocationRelativeTo(null);
		
		Application.popSuggestion(utilisateur);
		setVisible(true);







	}

	public Action getActionAfficherCommentFaire() {
		return actionAfficherCommentFaire;
	}

	public Action getActionAfficherConfig() {
		return actionAfficherConfig;
	}

	public void enableConversion(boolean b) {
		actionAfficherConversion.setEnabled(b);
	}

	public void enableTexte(boolean b) {
		actionAfficherTexte.setEnabled(b);
	}

	public void enableDiaporama(boolean b) {
		actionAfficherDiaporama.setEnabled(b);
	}

	public void enableBoutons(boolean b) {
		actionAfficherBoutons.setEnabled(b);
	}

	public Action getActionAfficherConversion() {
		return actionAfficherConversion;
	}

	public Action getActionAfficherTexte() {
		return actionAfficherTexte;
	}

	public Action getActionAfficherDiaporama() {
		return actionAfficherDiaporama;
	}

	public void afficherBoutons() {
		boutons.setVisible(true);
	}
	public void afficherConversion() {
		conversion.setVisible(true);
	}
	public void afficherDiaporama() {
		diaporama.setVisible(true);
	}
	public void afficherTexte() {
		texte.setVisible(true);
	}

	/**
	 * Ajoute un utilisateur s'il n'existe pas déjà, et initialise ses options de
	 * configuration par défaut.
	 * 
	 * @param utilisateur
	 */
	public static void ajouterUtilisateur(String utilisateur) {

		File f = new File(utilisateur + ".xml");
		if (!f.exists()) {
			OutputStream os = null;
			Properties prop = new Properties();
			prop.setProperty("conversion", "auto");
			prop.setProperty("saisie", "auto");
			prop.setProperty("diaporama", "auto");
			prop.setProperty("boutons", "auto");

			prop.setProperty("key_conversion", "on");
			prop.setProperty("key_saisie", "on");
			prop.setProperty("key_diaporama", "on");
			prop.setProperty("key_boutons", "on");

			prop.setProperty("info_conversion", "on");
			prop.setProperty("info_saisie", "on");
			prop.setProperty("info_diaporama", "on");
			prop.setProperty("info_boutons", "on");
			try {
				os = new FileOutputStream(utilisateur + ".xml");
				prop.storeToXML(os, null);
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lit l'option de configuration passée en paramètre appartenant à l'utilisateur
	 * passé en paramètre également.
	 * 
	 * @param utilisateur
	 * @param donnee
	 */
	public static String lireConfig(String utilisateur, String donnee) {

		try {
			Properties prop = new Properties();
			prop.loadFromXML(new FileInputStream(utilisateur + ".xml"));
			return prop.getProperty(donnee);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Modifie l'option de configuration passée en paramètre appartenant à
	 * l'utilisateur passé en paramètre également.
	 * 
	 * @param utilisateur
	 * @param donnee
	 * @param valeur
	 */
	public static void modifierConfig(String utilisateur, String donnee, String valeur) {

		try {

			Properties oldProp = new Properties();
			oldProp.loadFromXML(new FileInputStream(utilisateur + ".xml"));

			Properties prop = new Properties();

			prop.setProperty("conversion", oldProp.getProperty("conversion"));
			prop.setProperty("saisie", oldProp.getProperty("saisie"));
			prop.setProperty("diaporama", oldProp.getProperty("diaporama"));
			prop.setProperty("boutons", oldProp.getProperty("boutons"));

			prop.setProperty("key_conversion", oldProp.getProperty("key_conversion"));
			prop.setProperty("key_saisie", oldProp.getProperty("key_saisie"));
			prop.setProperty("key_diaporama", oldProp.getProperty("key_diaporama"));
			prop.setProperty("key_boutons", oldProp.getProperty("key_boutons"));

			prop.setProperty("info_conversion", oldProp.getProperty("info_conversion"));
			prop.setProperty("info_saisie", oldProp.getProperty("info_saisie"));
			prop.setProperty("info_diaporama", oldProp.getProperty("info_diaporama"));
			prop.setProperty("info_boutons", oldProp.getProperty("info_boutons"));

			switch(oldProp.getProperty("conversion")){
			case "auto" : itemConversion.setEnabled(true); break;
			case "on" : itemConversion.setEnabled(true); break;
			case "off" : itemConversion.setEnabled(false); break;
			}

			switch(oldProp.getProperty("saisie")){
			case "auto" : itemTexte.setEnabled(true); break;
			case "on" : itemTexte.setEnabled(true); break;
			case "off" : itemTexte.setEnabled(false); break;
			}

			switch(oldProp.getProperty("diaporama")){
			case "auto" : itemDiaporama.setEnabled(true); break;
			case "on" : itemDiaporama.setEnabled(true); break;
			case "off" : itemDiaporama.setEnabled(false); break;
			}

			switch(oldProp.getProperty("boutons")){
			case "auto" : itemBoutons.setEnabled(true); break;
			case "on" : itemBoutons.setEnabled(true); break;
			case "off" : itemBoutons.setEnabled(false); break;
			}

			prop.setProperty(donnee, valeur);

			OutputStream os = new FileOutputStream(utilisateur + ".xml");
			prop.storeToXML(os, null);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lit l'option de configuration passée en paramètre appartenant à l'utilisateur
	 * passé en paramètre également.
	 * 
	 * @param utilisateur
	 * @param donnee
	 */
	public static void popSuggestion(String utilisateur) {

		try {
			ArrayList<String> alSuggestion = new ArrayList<String>();
			Properties prop = new Properties();
			prop.loadFromXML(new FileInputStream(utilisateur + ".xml"));
			if (prop.getProperty("key_conversion").equals("on"))
				alSuggestion.add("key_conversion");
			if (prop.getProperty("key_saisie").equals("on"))
				alSuggestion.add("key_saisie");
			if (prop.getProperty("key_diaporama").equals("on"))
				alSuggestion.add("key_diaporama");
			if (prop.getProperty("key_boutons").equals("on"))
				alSuggestion.add("key_boutons");
			if (prop.getProperty("info_conversion").equals("on"))
				alSuggestion.add("info_conversion");
			if (prop.getProperty("info_saisie").equals("on"))
				alSuggestion.add("info_saisie");
			if (prop.getProperty("info_diaporama").equals("on"))
				alSuggestion.add("info_diaporama");
			if (prop.getProperty("info_boutons").equals("on"))
				alSuggestion.add("info_boutons");

			int rand = (int) (Math.random() * alSuggestion.size());
			new FenetreSuggestion(alSuggestion.get(rand), utilisateur);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void enableCommentFaire(boolean b) {
		actionAfficherCommentFaire.setEnabled(b);
	}

	public void enableConfig(boolean b) {
		actionAfficherConfig.setEnabled(b);
	}
	
	
	
	
	
	public static void main(String[] args) {
//		SwingUtilities.invokeLater(Application::new);
		if (args.length == 1)
			new Application(args[0]);
		else
			new Application("default");
	}

}
