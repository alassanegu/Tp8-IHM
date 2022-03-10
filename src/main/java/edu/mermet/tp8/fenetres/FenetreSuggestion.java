package edu.mermet.tp8.fenetres;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.mermet.tp8.Application;

public class FenetreSuggestion extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JEditorPane zoneTxt;
    private JButton fermer;
    private JButton arreter;

    public FenetreSuggestion(String txt, String utilisateur) {
        super("Suggestion du jour");
        this.setUndecorated(true);

        String suggestion = "Texte par défaut";
        switch (txt) {
            case "key_conversion":
                suggestion = "Appuyez sur CTRL + C pour ouvrir la fenêtre de conversion.";
                break;
            case "key_saisie":
                suggestion = "Appuyez sur CTRL + T pour ouvrir la fenêtre de saisie de texte.";
                break;
            case "key_diaporama":
                suggestion = "Appuyez sur CTRL + D pour ouvrir la fenêtre diaporama.";
                break;
            case "key_boutons":
                suggestion = "Appuyez sur CTRL + B pour ouvrir la fenêtre des boutons.";
                break;
            case "info_conversion":
                suggestion = "L'option 'Conversion Celsius/Farenheit' permet de convertir des degrés de températures d'une unité de mesure à une autre.";
                break;
            case "info_saisie":
                suggestion = "L'option 'Saisie de texte' permet d'écrire et de modifier la forme d'un texte.";
                break;
            case "info_diaporama":
                suggestion = "L'option 'Diaporama' permet d'afficher un diaporama du Cotentin.";
                break;
            case "info_boutons":
                suggestion = "L'option 'Boutons' permet d'ouvrir toutes les autres fenêtres plus facilement.";
                break;
        }
        this.zoneTxt = new JEditorPane("text/html", suggestion);
        this.zoneTxt.setEditable(false);

        this.fermer = new JButton("Fermer");
        this.fermer.addActionListener(event -> this.dispose());
        this.arreter = new JButton("Ne plus afficher");
        this.arreter.addActionListener(event -> {
            Application.modifierConfig(utilisateur, txt, "off");
            this.dispose();
        });

        JPanel panelSud = new JPanel();
        panelSud.add(this.fermer);
        panelSud.add(this.arreter);

        this.add(this.zoneTxt, BorderLayout.CENTER);
        this.add(panelSud, BorderLayout.SOUTH);
        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
