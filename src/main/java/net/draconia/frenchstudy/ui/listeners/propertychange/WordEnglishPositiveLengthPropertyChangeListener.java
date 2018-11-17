package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

import javax.swing.Action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.actions.AddWordInstanceAction;

@Component
public class WordEnglishPositiveLengthPropertyChangeListener implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = -8716998622047352044L;
	
	@Autowired
	private AddWordInstanceAction mActAdd;
	
	protected Action getAddAction()
	{
		return(mActAdd);
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		if(objPropertyChangeEvent.getPropertyName().equalsIgnoreCase("English"))
			getAddAction().setEnabled(((String)(objPropertyChangeEvent.getNewValue())).length() > 0);
	}
}