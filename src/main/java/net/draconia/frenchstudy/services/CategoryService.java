package net.draconia.frenchstudy.services;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.List;

import net.draconia.frenchstudy.model.Category;

public interface CategoryService extends Serializable
{
	public Category getById(final int iId) throws SQLException;
	public Category getByCategory(final String sCategory) throws SQLException;
	public List<Category> list() throws SQLException;
	public void remove(final Category objCategory) throws SQLException;
	public Category save(final Category objCategory) throws SQLException;
}