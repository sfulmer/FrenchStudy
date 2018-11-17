package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.Serializable;

import javax.swing.Action;

import net.draconia.frenchstudy.ui.controllers.EditPartOfSpeechDialogController;

public class EditPartOfSpeechDialogModelDirty implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = 6523173632549078041L;

	private EditPartOfSpeechDialogController mObjController;
	
	public EditPartOfSpeechDialogModelDirty(final EditPartOfSpeechDialogController objController)
	{
		setController(objController);
	}
	
	protected Action getApplyAction()
	{
		return(getController().getApplyAction());
	}
	
	protected EditPartOfSpeechDialogController getController()
	{
		return(mObjController);
	}
	
	protected Action getSaveAction()
	{
		return(getController().getSaveAction());
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		if(objPropertyChangeEvent.getPropertyName().equalsIgnoreCase("Dirty"))
			{
			getApplyAction().setEnabled((Boolean)(objPropertyChangeEvent.getNewValue()));
			getSaveAction().setEnabled((Boolean)(objPropertyChangeEvent.getNewValue()));
			}
	}
	
	protected void setController(final EditPartOfSpeechDialogController objController)
	{
		mObjController = objController;
	}
}