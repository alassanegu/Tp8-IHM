package edu.mermet.tp8.fenetres;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import edu.mermet.tp8.Application;

/**
 *
 * @author brunomermet
 */
public class FenetreConversion extends AbstractFenetreInterne {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField champCelsius;
    private JTextField champFarenheit;
    private JButton boutonConvertir;
    private Action actionConvertir;
    private boolean celsiusAFocus;
    
    private JButton aideCelsius;
	private JButton aideFarenheit;
    
    public FenetreConversion(Application appli, Action action) {
        super(appli, action, "Conversion celsius/Farenheit");
        this.setSize(new Dimension(100, 50));
		this.setLayout(new GridLayout(3, 1));

		// Création de l'icône
		java.net.URL imgURL = getClass().getResource("question_icon.png");
		Image image = new ImageIcon(imgURL).getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);

		////// Ligne Celsius
		JPanel ligneCelsius = new JPanel();
		ligneCelsius.setLayout(new FlowLayout(FlowLayout.TRAILING));
		JLabel labCelsius = new JLabel("Celsius :");
		champCelsius = new JTextField(15);
		champCelsius.addFocusListener(new EcouteurFocus(true));
		champCelsius.setToolTipText("Valeur en degré Celsius");
		labCelsius.setLabelFor(champCelsius);

		aideCelsius = new JButton(); // Bouton "?" (Celsius)
		aideCelsius.setIcon(icon);
		aideCelsius.setPreferredSize(new Dimension(20, 20));
		aideCelsius.addActionListener(event -> JOptionPane.showMessageDialog(this, "Valeur en degré Celsius", "Aide",
				JOptionPane.INFORMATION_MESSAGE));

		ligneCelsius.add(labCelsius);
		ligneCelsius.add(champCelsius);
		ligneCelsius.add(aideCelsius);
		this.add(ligneCelsius);
		celsiusAFocus = true;

		////// Ligne Farenheit
		JPanel ligneFarenheit = new JPanel();
		ligneFarenheit.setLayout(new FlowLayout(FlowLayout.TRAILING));
		JLabel labFarenheit = new JLabel("Farenheit :");
		champFarenheit = new JTextField(15);
		champFarenheit.addFocusListener(new EcouteurFocus(false));
		champFarenheit.setToolTipText("Valeur en degré Farenheit");
		labFarenheit.setLabelFor(champFarenheit);

		aideFarenheit = new JButton(); // Bouton "?" (Farenheit)
		aideFarenheit.setIcon(icon);
		aideFarenheit.setPreferredSize(new Dimension(20, 20));
		aideFarenheit.addActionListener(event -> JOptionPane.showMessageDialog(this, "Valeur en degré Farenheit",
				"Aide", JOptionPane.INFORMATION_MESSAGE));

		ligneFarenheit.add(labFarenheit);
		ligneFarenheit.add(champFarenheit);
		ligneFarenheit.add(aideFarenheit);
		this.add(ligneFarenheit);

		JPanel ligneValider = new JPanel();
		ligneValider.setLayout(new FlowLayout(FlowLayout.CENTER));
		actionConvertir = new ActionConvertir();
		boutonConvertir = new JButton(actionConvertir);
		ligneValider.add(boutonConvertir);
		this.add(ligneValider);

		pack();
		getRootPane().setDefaultButton(boutonConvertir);
	}

	private class EcouteurFocus implements FocusListener {
		private boolean aStocker;

		public EcouteurFocus(boolean b) {
			aStocker = b;
		}

		@Override
		public void focusGained(FocusEvent fe) {
			celsiusAFocus = aStocker;
		}

		@Override
		public void focusLost(FocusEvent fe) {
			return;
		}
	}

	private class ActionConvertir extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ActionConvertir() {
			super("Convertir");
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			double tempCelsius = 0;
			double tempFarenheit = 0;
			if (celsiusAFocus) {
				try {
					tempCelsius = Double.parseDouble(champCelsius.getText());
					tempFarenheit = 9. / 5 * tempCelsius + 32;
					champFarenheit.setText("" + tempFarenheit);
				} catch (NumberFormatException nfe) {
					champFarenheit.setText("Format incorrect");
				}
			} else {
				try {
					tempFarenheit = Double.parseDouble(champFarenheit.getText());
					tempCelsius = (tempFarenheit - 32) * 5. / 9;
					champCelsius.setText("" + tempCelsius);
				} catch (NumberFormatException nfe) {
					champCelsius.setText("Format incorrect");
				}

			}
		}
	}

}
