package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.Serializable;

import javax.swing.Action;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.actions.ApplyWordInstanceAction;
import net.draconia.frenchstudy.ui.actions.SaveWordInstanceAction;

@Component
public class EditWordInstanceDialogSaveablePropertyChangeListener implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = 9103419663723543369L;
	
	@Autowired
	private ApplyWordInstanceAction mActApply;
	@Autowired
	private SaveWordInstanceAction mActSave;
	
	protected Action getApplyAction()
	{
		return(mActApply);
	}
	
	protected Action getSaveAction()
	{
		return(mActSave);
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		if(objPropertyChangeEvent.getPropertyName().equalsIgnoreCase("Saveable"))
			{
			getApplyAction().setEnabled((Boolean)(objPropertyChangeEvent.getNewValue()));
			getSaveAction().setEnabled((Boolean)(objPropertyChangeEvent.getNewValue()));
			}
	}
}