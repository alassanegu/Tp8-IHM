package edu.mermet.tp8.dialogue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.mermet.tp8.Application;

public class DialogueHowTo extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final int LARGEUR = 500;
	private static final int HAUTEUR = 400;
	
	private JEditorPane zoneTxt;
	private JList<String> liste;
	private ArrayList<String> alInstructions;
	private DefaultListModel<String> listModel;
	
	public DialogueHowTo(Application appli) {
		super(appli, "Comment Faire ?",false);
		init();
		setSize(LARGEUR, HAUTEUR);
//		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	

	public void init() {

		BorderLayout layout = new BorderLayout();
		layout.setHgap(10);
		this.setLayout(layout);

		this.listModel = new DefaultListModel<String>();
		this.alInstructions = new ArrayList<String>();
		this.listModel.addElement("Conversion Celsius → Farenheit");
		this.alInstructions.add("Pour convertir une température du Celsius au Farenheit,"
				+ " sélectionnez l'item <FONT COLOR=red>Conversion Celsius/Farenheit</FONT>"
				+ " dans le menu <FONT COLOR=blue>Applications</FONT>."
				+ "<br><br>Entrez ensuite une valeur dans le champs <FONT COLOR=purple>Celsius</FONT>,"
				+ " et appuyez sur la touche <FONT COLOR=green>ENTER</FONT> du clavier"
				+ " ou sur le bouton <FONT COLOR=green>Convertir</FONT>.");
		this.listModel.addElement("Conversion Farenheit → Celsius");
		this.alInstructions.add("Pour convertir une température du Farenheit au Celsius,"
				+ " sélectionnez l'item <FONT COLOR=red>Conversion Celsius/Farenheit</FONT>"
				+ " dans le menu <FONT COLOR=blue>Applications</FONT>."
				+ "<br><br>Entrez ensuite une valeur dans le champs <FONT COLOR=purple>Farenheit</FONT>,"
				+ " et appuyez sur la touche <FONT COLOR=green>ENTER</FONT> du clavier"
				+ " ou sur le bouton <FONT COLOR=green>Convertir</FONT>.");
		this.listModel.addElement("Mettre du texte en gras");
		this.alInstructions.add("Pour mettre du texte en gras,"
				+ " sélectionnez l'item <FONT COLOR=red>Saisie de texte</FONT>"
				+ " dans le menu <FONT COLOR=blue>Applications</FONT>."
				+ "<br><br>Entrez ensuite le texte désiré dans la <FONT COLOR=purple>zone de texte </FONT>"
				+ " qui vous est présentée."
				+ " Au choix, vous pouvez cliquer sur la case <FONT COLOR=green>gras</FONT>,"
				+ " ou bien	cliquer sur l'item correspondant dans le menu <FONT COLOR=blue>Style</FONT>.");
		this.listModel.addElement("Changer la couleur du texte");
		this.alInstructions.add("Pour changer la couleur du texte,"
				+ " sélectionnez l'item <FONT COLOR=red>Saisie de texte</FONT>"
				+ " dans le menu <FONT COLOR=blue>Applications</FONT>."
				+ "<br><br>Entrez ensuite le texte désiré dans la <FONT COLOR=purple>zone de texte </FONT>"
				+ " qui vous est présentée."
				+ " Au choix, vous pouvez cliquer sur la case <FONT COLOR=green>rouge</FONT>,"
				+ " ou bien	cliquer sur l'item correspondant dans le menu <FONT COLOR=blue>Style</FONT>.");

		this.liste = new JList<String>();

		liste.setModel(this.listModel);
		liste.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				jList1ValueChanged(evt);
			}
		});

		this.zoneTxt = new JEditorPane("text/html", this.alInstructions.get(0));
		this.zoneTxt.setEditable(false);

		this.liste.setSelectedIndex(0);

		this.add(this.liste, BorderLayout.WEST);
		this.add(this.zoneTxt, BorderLayout.CENTER);

		this.setSize(500, 200);
	}

	private void jList1ValueChanged(ListSelectionEvent evt) {
		if (this.liste.getSelectedIndex() < this.alInstructions.size()) {
			this.zoneTxt.setText(this.alInstructions.get(this.liste.getSelectedIndex()));
		}
	}

}
