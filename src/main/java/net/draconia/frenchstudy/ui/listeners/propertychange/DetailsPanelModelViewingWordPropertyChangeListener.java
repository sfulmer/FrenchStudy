package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.Serializable;

import javax.swing.JTextField;

import net.draconia.frenchstudy.model.Word;
import net.draconia.frenchstudy.ui.model.DetailsPanelWordInstanceTableModel;

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
	
	@Autowired
	private DetailsPanelWordInstanceTableModel mObjWordInstanceTableModel;
	
	protected JTextField getEnglishWordField()
	{
		return(mTxtEnglishWord);
	}
	
	protected DetailsPanelWordInstanceTableModel getWordInstanceTableModel()
	{
		return(mObjWordInstanceTableModel);
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
				
				if(!objWord.equals(getWordInstanceTableModel().getWordInEffect()))
					getWordInstanceTableModel().setWordInEffect(objWord);
				}
			else
				{
				getEnglishWordField().setText("");
				getWordInstanceTableModel().setWordInEffect(null);
				}
			}
	}
}