package net.draconia.frenchstudy.services;

import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.draconia.frenchstudy.dao.WordInstanceDAO;
import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;

import net.draconia.frenchstudy.model.Word;
import net.draconia.frenchstudy.model.WordInstance;

@Service("WordInstanceService")
public class WordInstanceServiceImpl implements WordInstanceService
{
	private static final long serialVersionUID = 6570552535872692570L;
	
	@Autowired
	private WordInstanceDAO mObjWordInstanceDAO;
	
	public WordInstance getById(final int iId) throws SQLException
	{
		return(getWordInstanceDAO().getById(iId));
	}
	
	protected WordInstanceDAO getWordInstanceDAO()
	{
		return(mObjWordInstanceDAO);
	}
	
	public List<WordInstance> list() throws SQLException
	{
		return(getWordInstanceDAO().list());
	}
	
	public List<WordInstance> listByWordAndPartOfSpeech(final Word objWord, final PartOfSpeech objPartOfSpeech) throws SQLException
	{
		return(getWordInstanceDAO().listByWordAndPartOfSpeech(objWord, objPartOfSpeech));
	}
	
	public List<WordInstance> listByWordPartOfSpeechAndCategory(final Word objWord, final PartOfSpeech objPartOfSpeech, final Category objCategory) throws SQLException
	{
		return(getWordInstanceDAO().listByWordPartOfSpeechAndCategory(objWord, objPartOfSpeech, objCategory));
	}
	
	public List<WordInstance> listSlang() throws SQLException
	{
		return(getWordInstanceDAO().listSlang());
	}
	
	public void remove(final WordInstance objWordInstance) throws SQLException
	{
		getWordInstanceDAO().remove(objWordInstance);

	}
	
	public WordInstance save(final WordInstance objWordInstance) throws SQLException
	{
		return(getWordInstanceDAO().save(objWordInstance));
	}
}