package net.draconia.frenchstudy.dao;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.List;

import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.model.Word;

public interface WordDAO extends Serializable
{
	public Word getByEnglishWord(final String sEnglish) throws SQLException;
	public Word getById(final int iId) throws SQLException;
	public List<Word> list() throws SQLException;
	public List<Word> listByCategory(final Category objCategory) throws SQLException;
	public List<Word> listByPartOfSpeech(final PartOfSpeech objPartOfSpeech) throws SQLException;
	public List<Word> listBySlang(final boolean bSlang) throws SQLException;
	public List<Word> listByCategoryAndPartOfSpeech(final Category objCategory, PartOfSpeech objPartOfSpeech) throws SQLException;
	public List<Word> listByCategoryAndSlang(final Category objCategory, final boolean bSlang) throws SQLException;
	public List<Word> listByPartOfSpeechAndSlang(final PartOfSpeech objPartOfSpeech, final boolean bSlang) throws SQLException;
	public List<Word> listByPartOfSpeechCategoryAndSlang(final PartOfSpeech objPartOfSpeech, final Category objCategory, final boolean bSlang) throws SQLException;
	public void remove(final Word objWord) throws SQLException;
	public Word save(final Word objWord) throws SQLException;
}