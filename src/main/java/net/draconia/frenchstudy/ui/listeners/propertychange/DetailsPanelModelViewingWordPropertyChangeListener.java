package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.Serializable;

import javax.swing.JTextField;

import net.draconia.frenchstudy.model.Word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;

@Component
public class DetailsPanelModelViewingWordPropertyChangeListener implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = -2336742468684508237L;
	
	@Autowired
	@Qualifier("txtEnglishWord")
	private JTextField mTxtEnglishWord;
	
	protected JTextField getEnglishWordField()
	{
		return(mTxtEnglishWord);
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		if(objPropertyChangeEvent.getPropertyName().equalsIgnoreCase("ViewingWord"))
			{
			Word objWord = ((Word)(objPropertyChangeEvent.getNewValue()));
			
			if(objWord != null)
				{
				if(!getEnglishWordField().getText().equals(objWord.getEnglish()))
					getEnglishWordField().setText(objWord.getEnglish());
				}
			else
				getEnglishWordField().setText("");
			}
	}
}