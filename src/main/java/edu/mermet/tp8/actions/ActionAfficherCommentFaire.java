package edu.mermet.tp8.actions;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import org.w3c.dom.views.AbstractView;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.dialogue.DialogueHowTo;

public class ActionAfficherCommentFaire extends AbstractAction {
	Application app;
	DialogueHowTo dht;
	

	public ActionAfficherCommentFaire(Application application) {
		super("Comment faire ?");
		app = application;
		putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
		putValue(Action.MNEMONIC_KEY, KeyEvent.VK_U);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		dht = new DialogueHowTo(app);
//		commentFaire.setVisible(true);
		app.enableCommentFaire(false);
	}


}
