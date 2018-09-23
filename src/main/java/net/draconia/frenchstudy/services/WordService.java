package net.draconia.frenchstudy.services;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.List;

import net.draconia.frenchstudy.model.Word;

public interface WordService extends Serializable
{
	public Word getByEnglishWord(final String sEnglish) throws SQLException;
	public Word getById(final int iId) throws SQLException;
	public List<Word> list() throws SQLException;
	public void remove(final Word objWord) throws SQLException;
	public Word save(final Word objWord) throws SQLException;
}