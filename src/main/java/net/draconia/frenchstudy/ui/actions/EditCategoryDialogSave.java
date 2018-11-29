package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

import net.draconia.frenchstudy.ui.EditCategoryDialog;

import net.draconia.frenchstudy.ui.controllers.EditCategoryDialogController;

import net.draconia.frenchstudy.ui.model.EditCategoryDialogModel;

public class EditCategoryDialogSave extends AbstractAction
{
	private static final long serialVersionUID = 2981455556841742150L;
	
	private EditCategoryDialogController mObjController;
	
	public EditCategoryDialogSave(final EditCategoryDialogController objController)
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
	
	protected EditCategoryDialogController getController()
	{
		return(mObjController);
	}
	
	protected EditCategoryDialog getDialog()
	{
		return(getController().getDialog());
	}
	
	protected EditCategoryDialogModel getDialogModel()
	{
		return(getController().getDialogModel());
	}
	
	@PostConstruct
	protected void init()
	{
		setEnabled(getDialogModel().isDirty());
	}
	
	protected void setController(final EditCategoryDialogController objController)
	{
		mObjController = objController;
	}
}