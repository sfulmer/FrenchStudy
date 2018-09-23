package net.draconia.frenchstudy.services;

import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import net.draconia.frenchstudy.dao.WordDAO;

import net.draconia.frenchstudy.model.Word;

@Service("WordService")
public class WordServiceImpl implements WordService
{
	private static final long serialVersionUID = -2364800813324544790L;
	
	@Autowired
	private WordDAO mObjWordDAO;
	
	public Word getByEnglishWord(final String sEnglish) throws SQLException
	{
		return(getWordDAO().getByEnglishWord(sEnglish));
	}
	
	public Word getById(int iId) throws SQLException
	{
		return(getWordDAO().getById(iId));
	}
	
	protected WordDAO getWordDAO()
	{
		return(mObjWordDAO);
	}
	
	public List<Word> list() throws SQLException
	{
		return(getWordDAO().list());
	}

	public void remove(final Word objWord) throws SQLException
	{
		getWordDAO().remove(objWord);
	}
	
	public Word save(final Word objWord) throws SQLException
	{
		return(getWordDAO().save(objWord));
	}
}