package net.draconia.frenchstudy.services;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.List;

import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.model.Word;
import net.draconia.frenchstudy.model.WordInstance;

public interface WordInstanceService extends Serializable
{
	public WordInstance getById(final int iId) throws SQLException;
	public List<WordInstance> list() throws SQLException;
	public List<WordInstance> listByWord(final Word objWord) throws SQLException;
	public List<WordInstance> listByWordAndPartOfSpeech(final Word objWord, final PartOfSpeech objPartOfSpeech) throws SQLException;
	public List<WordInstance> listByWordPartOfSpeechAndCategory(final Word objWord, final PartOfSpeech objPartOfSpeech, final Category objCategory) throws SQLException;
	public List<WordInstance> listSlang() throws SQLException;
	public void remove(final WordInstance objWordInstance) throws SQLException;
	public WordInstance save(final WordInstance objWordInstance) throws SQLException;
}