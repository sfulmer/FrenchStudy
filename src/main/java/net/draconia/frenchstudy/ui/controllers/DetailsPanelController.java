package net.draconia.frenchstudy.ui.controllers;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.model.Word;
import net.draconia.frenchstudy.model.WordInstance;
import net.draconia.frenchstudy.services.WordInstanceService;

@Component
public class DetailsPanelController implements Serializable
{
	private static final long serialVersionUID = 6496522109797619358L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(DetailsPanelController.class);
	
	@Autowired
	private WordInstanceService mObjWordInstanceService;
	
	protected WordInstanceService getWordInstanceService()
	{
		return(mObjWordInstanceService);
	}
	
	public List<WordInstance> getWordInstancesForWord(final Word objWord)
	{
		try
			{
			return(getWordInstanceService().listByWord(objWord));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Failed to retrieve Word Instances for Word (" + objWord.getId() + ")...", objException);
			
			return(new ArrayList<WordInstance>());
			}
	}
	
	public Word newWord()
	{
		return(new Word());
	}
}