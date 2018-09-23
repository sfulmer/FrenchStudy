package net.draconia.frenchstudy.dao;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.List;

import net.draconia.frenchstudy.model.PartOfSpeech;

public interface PartOfSpeechDAO extends Serializable
{
	public PartOfSpeech getById(final int iId) throws SQLException;
	public PartOfSpeech getByPartOfSpeech(final String sPartOfSpeech) throws SQLException;
	public List<PartOfSpeech> list() throws SQLException;
	public void remove(final PartOfSpeech objPartOfSpeech) throws SQLException;
	public PartOfSpeech save(final PartOfSpeech objPartOfSpeech) throws SQLException;
}