package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.Serializable;

import net.draconia.frenchstudy.model.Word;

import net.draconia.frenchstudy.ui.model.DetailsPanelModel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class ListModelViewingWordPropertyChangeListener implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = -1810993066543316519L;
	
	@Autowired
	private DetailsPanelModel mObjDetailsPanelModel;
	
	protected DetailsPanelModel getDetailsPanelModel()
	{
		return(mObjDetailsPanelModel);
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		Word objViewingWord = ((Word)(objPropertyChangeEvent.getNewValue()));
		
		getDetailsPanelModel().setViewingWord(objViewingWord);
	}
}