package edu.mermet.tp8.actions;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.dialogue.DialogueConfig;

public class ActionAfficherConfig extends AbstractAction {
	Application app;
	DialogueConfig dc;
	String user;
	Action afconf;
	public ActionAfficherConfig(Application application,Action af,String utilisateur) {
		super("Configurer les menus");
		app = application;
		user = utilisateur;
		afconf = af;
        putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        putValue(Action.MNEMONIC_KEY,KeyEvent.VK_G);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		dc = new DialogueConfig(app,afconf,user);
		app.enableConfig(false);
//		commentFaire.setVisible(true);
//		enableCommentFaire(false);
	}
}
