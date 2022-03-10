package edu.mermet.tp8.dialogue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import edu.mermet.tp8.Application;

public class DialogueConfig extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private static final int LARGEUR = 300;
	private static final int HAUTEUR = 400;
	
	private JPanel panelLabel, panelConfig, panelButtons;

	private JLabel labelConv;
	private JLabel labelSaisie;
	private JLabel labelDiapo;
	private JLabel labelButton;

	private ButtonGroup groupConv;
	private JRadioButton autoConv, afficheConv, cacheConv;

	private ButtonGroup groupSaisie;
	private JRadioButton autoSaisie, afficheSaisie, cacheSaisie;

	private ButtonGroup groupDiapo;
	private JRadioButton autoDiapo, afficheDiapo, cacheDiapo;

	private ButtonGroup groupButton;
	private JRadioButton autoButton, afficheButton, cacheButton;

	private JButton valider, annuler;

	
	private String option_conversion;
	private String option_saisie;
	private String option_diaporama;
	private String option_boutons;
	private String utilisateur;
	
	public DialogueConfig(Application appli,Action a,String user) {
		super(appli, "Configurer les menus?");
		init(a,user);
		setSize(LARGEUR, HAUTEUR);
//		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	
	public void init(Action a, String utilisateur) {
		this.utilisateur = utilisateur;

		this.option_conversion = Application.lireConfig(utilisateur, "conversion");
		this.option_saisie = Application.lireConfig(utilisateur, "saisie");
		this.option_diaporama = Application.lireConfig(utilisateur, "diaporama");
		this.option_boutons = Application.lireConfig(utilisateur, "boutons");

		this.panelLabel = new JPanel(new GridLayout(4, 1));
		this.panelConfig = new JPanel(new GridLayout(4, 3));
		this.panelButtons = new JPanel(new FlowLayout());

		this.labelConv = new JLabel("Conversion Celsius/Farenheit");
		this.labelConv.setHorizontalAlignment(SwingConstants.TRAILING);
		this.groupConv = new ButtonGroup();
		this.autoConv = new JRadioButton("Auto", option_conversion.equals("auto"));
		this.afficheConv = new JRadioButton("Affiché", option_conversion.equals("on"));
		this.cacheConv = new JRadioButton("Caché", option_conversion.equals("off"));
		this.groupConv.add(this.autoConv);
		this.groupConv.add(this.afficheConv);
		this.groupConv.add(this.cacheConv);

		this.labelSaisie = new JLabel("Saisie de texte");
		this.labelSaisie.setHorizontalAlignment(SwingConstants.TRAILING);
		this.groupSaisie = new ButtonGroup();
		this.autoSaisie = new JRadioButton("Auto", option_saisie.equals("auto"));
		this.afficheSaisie = new JRadioButton("Affiché", option_saisie.equals("on"));
		this.cacheSaisie = new JRadioButton("Caché", option_saisie.equals("off"));
		this.groupSaisie.add(this.autoSaisie);
		this.groupSaisie.add(this.afficheSaisie);
		this.groupSaisie.add(this.cacheSaisie);

		this.labelDiapo = new JLabel("Diaporama");
		this.labelDiapo.setHorizontalAlignment(SwingConstants.TRAILING);
		this.groupDiapo = new ButtonGroup();
		this.autoDiapo = new JRadioButton("Auto", option_diaporama.equals("auto"));
		this.afficheDiapo = new JRadioButton("Affiché", option_diaporama.equals("on"));
		this.cacheDiapo = new JRadioButton("Caché", option_diaporama.equals("off"));
		this.groupDiapo.add(this.autoDiapo);
		this.groupDiapo.add(this.afficheDiapo);
		this.groupDiapo.add(this.cacheDiapo);

		this.labelButton = new JLabel("Boutons");
		this.labelButton.setHorizontalAlignment(SwingConstants.TRAILING);
		this.groupButton = new ButtonGroup();
		this.autoButton = new JRadioButton("Auto", option_boutons.equals("auto"));
		this.afficheButton = new JRadioButton("Affiché", option_boutons.equals("on"));
		this.cacheButton = new JRadioButton("Caché", option_boutons.equals("off"));
		this.groupButton.add(this.autoButton);
		this.groupButton.add(this.afficheButton);
		this.groupButton.add(this.cacheButton);

		this.panelLabel.add(this.labelConv);
		this.panelConfig.add(this.autoConv);
		this.panelConfig.add(this.afficheConv);
		this.panelConfig.add(this.cacheConv);

		this.panelLabel.add(this.labelSaisie);
		this.panelConfig.add(this.autoSaisie);
		this.panelConfig.add(this.afficheSaisie);
		this.panelConfig.add(this.cacheSaisie);

		this.panelLabel.add(this.labelDiapo);
		this.panelConfig.add(this.autoDiapo);
		this.panelConfig.add(this.afficheDiapo);
		this.panelConfig.add(this.cacheDiapo);

		this.panelLabel.add(this.labelButton);
		this.panelConfig.add(this.autoButton);
		this.panelConfig.add(this.afficheButton);
		this.panelConfig.add(this.cacheButton);

		this.valider = new JButton("Valider");
		this.valider.addActionListener(event -> {
			if (autoConv.isSelected())
				Application.modifierConfig(utilisateur, "conversion", "auto");
			if (afficheConv.isSelected())
				Application.modifierConfig(utilisateur, "conversion", "on");
			if (cacheConv.isSelected())
				Application.modifierConfig(utilisateur, "conversion", "off");
			if (autoSaisie.isSelected())
				Application.modifierConfig(utilisateur, "saisie", "auto");
			if (afficheSaisie.isSelected())
				Application.modifierConfig(utilisateur, "saisie", "on");
			if (cacheSaisie.isSelected())
				Application.modifierConfig(utilisateur, "saisie", "off");
			if (autoDiapo.isSelected())
				Application.modifierConfig(utilisateur, "diaporama", "auto");
			if (afficheDiapo.isSelected())
				Application.modifierConfig(utilisateur, "diaporama", "on");
			if (cacheDiapo.isSelected())
				Application.modifierConfig(utilisateur, "diaporama", "off");
			if (autoButton.isSelected())
				Application.modifierConfig(utilisateur, "boutons", "auto");
			if (afficheButton.isSelected())
				Application.modifierConfig(utilisateur, "boutons", "on");
			if (cacheButton.isSelected())
				Application.modifierConfig(utilisateur, "boutons", "off");

			option_conversion = Application.lireConfig(utilisateur, "conversion");
			option_saisie = Application.lireConfig(utilisateur, "saisie");
			option_diaporama = Application.lireConfig(utilisateur, "diaporama");
			option_boutons = Application.lireConfig(utilisateur, "boutons");
		});
		this.annuler = new JButton("Annuler");
		this.annuler.addActionListener(event -> {
			if (option_conversion.equals("auto"))
				autoConv.setSelected(true);
			if (option_conversion.equals("on"))
				afficheConv.setSelected(true);
			if (option_conversion.equals("off"))
				cacheConv.setSelected(true);
			if (option_saisie.equals("auto"))
				autoSaisie.setSelected(true);
			if (option_saisie.equals("on"))
				afficheSaisie.setSelected(true);
			if (option_saisie.equals("off"))
				cacheSaisie.setSelected(true);
			if (option_diaporama.equals("auto"))
				autoDiapo.setSelected(true);
			if (option_diaporama.equals("on"))
				afficheDiapo.setSelected(true);
			if (option_diaporama.equals("off"))
				cacheDiapo.setSelected(true);
			if (option_boutons.equals("auto"))
				autoButton.setSelected(true);
			if (option_boutons.equals("on"))
				afficheButton.setSelected(true);
			if (option_boutons.equals("off"))
				cacheButton.setSelected(true);
		});
		this.panelButtons.add(this.valider);
		this.panelButtons.add(this.annuler);

		this.add(this.panelLabel);
		this.add(this.panelConfig, BorderLayout.EAST);
		this.add(this.panelButtons, BorderLayout.SOUTH);

		pack();
	}

}
