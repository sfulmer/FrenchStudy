package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.Serializable;

import javax.swing.Action;

import net.draconia.frenchstudy.ui.controllers.EditCategoryDialogController;

public class EditCategoryDialogModelDirty implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = 6523173632549078041L;

	private EditCategoryDialogController mObjController;
	
	public EditCategoryDialogModelDirty(final EditCategoryDialogController objController)
	{
		setController(objController);
	}
	
	protected Action getApplyAction()
	{
		return(getController().getApplyAction());
	}
	
	protected EditCategoryDialogController getController()
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
	
	protected void setController(final EditCategoryDialogController objController)
	{
		mObjController = objController;
	}
}