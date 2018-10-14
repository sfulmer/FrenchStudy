package net.draconia.frenchstudy.ui.controllers;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.model.Word;

import net.draconia.frenchstudy.services.WordService;

@Component
public class ListPanelController implements Serializable
{
	private static final long serialVersionUID = 6496522109797619358L;
	
	@Autowired
	private WordService mObjWordService;
	
	public List<Word> list(final PartOfSpeech objPartOfSpeech, final Category objCategory, final Boolean bSlang) throws SQLException
	{
		if(objPartOfSpeech != null)
			if(objCategory != null)
				if(bSlang != null)
					return(getWordService().listByPartOfSpeechCategoryAndSlang(objPartOfSpeech, objCategory, bSlang));
				else
					return(getWordService().listByCategoryAndPartOfSpeech(objCategory, objPartOfSpeech));
			else if(bSlang != null)
				return(getWordService().listByPartOfSpeechAndSlang(objPartOfSpeech, bSlang));
			else
				return(getWordService().listByPartOfSpeech(objPartOfSpeech));
		else if(objCategory != null)
			if(bSlang != null)
				return(getWordService().listByCategoryAndSlang(objCategory, bSlang));
			else
				return(getWordService().listByCategory(objCategory));
		else
			return(getWordService().list());
	}
	
	protected WordService getWordService()
	{
		return(mObjWordService);
	}
}