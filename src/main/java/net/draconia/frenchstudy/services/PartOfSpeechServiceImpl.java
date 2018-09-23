package net.draconia.frenchstudy.services;

import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import net.draconia.frenchstudy.dao.PartOfSpeechDAO;

import net.draconia.frenchstudy.model.PartOfSpeech;

@Service("PartOfSpeechService")
public class PartOfSpeechServiceImpl implements PartOfSpeechService
{
	private static final long serialVersionUID = -4710455528043072107L;
	
	@Autowired
	private PartOfSpeechDAO mObjPartOfSpeechDAO;
	
	public PartOfSpeech getById(final int iId) throws SQLException
	{
		return(getPartOfSpeechDAO().getById(iId));
	}
	
	public PartOfSpeech getByPartOfSpeech(final String sPartOfSpeech) throws SQLException
	{
		return(getPartOfSpeechDAO().getByPartOfSpeech(sPartOfSpeech));
	}
	
	protected PartOfSpeechDAO getPartOfSpeechDAO()
	{
		return(mObjPartOfSpeechDAO);
	}

	public List<PartOfSpeech> list() throws SQLException
	{
		return(getPartOfSpeechDAO().list());
	}

	public void remove(final PartOfSpeech objPartOfSpeech) throws SQLException
	{
		getPartOfSpeechDAO().remove(objPartOfSpeech);
	}

	public PartOfSpeech save(final PartOfSpeech objPartOfSpeech) throws SQLException
	{
		return(getPartOfSpeechDAO().save(objPartOfSpeech));
	}
}