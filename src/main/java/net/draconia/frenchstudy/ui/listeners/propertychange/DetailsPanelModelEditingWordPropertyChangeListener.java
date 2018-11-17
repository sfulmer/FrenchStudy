package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.actions.AddWordInstanceAction;

import net.draconia.frenchstudy.ui.model.DetailsPanelModel;

@Component
public class DetailsPanelModelEditingWordPropertyChangeListener implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = 7043653723573648705L;
	
	@Autowired
	private AddWordInstanceAction mActAdd;
	
	protected AddWordInstanceAction getAddAction()
	{
		return(mActAdd);
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		if(objPropertyChangeEvent.getPropertyName().equals("EditingWord"))
			{
			DetailsPanelModel objModel = ((DetailsPanelModel)(objPropertyChangeEvent.getSource()));
			
			getAddAction().setEnabled(objModel.getEditingWord().getEnglish().length() > 0);
			}
	}	
}