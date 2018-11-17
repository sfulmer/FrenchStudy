package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

import net.draconia.frenchstudy.ui.EditPartOfSpeechDialog;
import net.draconia.frenchstudy.ui.controllers.EditPartOfSpeechDialogController;
import net.draconia.frenchstudy.ui.model.EditPartOfSpeechDialogModel;

public class EditPartOfSpeechDialogSave extends AbstractAction
{
	private static final long serialVersionUID = 2981455556841742150L;
	
	private EditPartOfSpeechDialogController mObjController;
	
	public EditPartOfSpeechDialogSave(final EditPartOfSpeechDialogController objController)
	{
		super("Save...");
		
		putValue(LONG_DESCRIPTION, "Saves the Part of Speech to the database and closes the window...");
		putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		putValue(SHORT_DESCRIPTION, "Saves the Part of Speech to the database and closes the window...");
		
		setController(objController);
		
		setEnabled(getApplyAction().isEnabled());
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		(new JButton(getApplyAction())).doClick(20);
		
		getDialog().dispose();
	}
	
	protected Action getApplyAction()
	{
		return(getController().getApplyAction());
	}
	
	protected EditPartOfSpeechDialogController getController()
	{
		return(mObjController);
	}
	
	protected EditPartOfSpeechDialog getDialog()
	{
		return(getController().getDialog());
	}
	
	protected EditPartOfSpeechDialogModel getDialogModel()
	{
		return(getController().getDialogModel());
	}
	
	@PostConstruct
	protected void init()
	{
		setEnabled(getDialogModel().isDirty());
	}
	
	protected void setController(final EditPartOfSpeechDialogController objController)
	{
		mObjController = objController;
	}
}