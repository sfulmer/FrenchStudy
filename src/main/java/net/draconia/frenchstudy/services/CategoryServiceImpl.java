package net.draconia.frenchstudy.services;

import java.sql.SQLException;

import java.util.List;

import net.draconia.frenchstudy.dao.CategoryDAO;

import net.draconia.frenchstudy.model.Category;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService
{
	private static final long serialVersionUID = -2315192354584454522L;
	
	@Autowired
	private CategoryDAO mObjCategoryDAO;
	
	public Category getByCategory(final String sCategory) throws SQLException
	{
		return(getCategoryDAO().getByCategory(sCategory));
	}
	
	public Category getById(final int iId) throws SQLException
	{
		return(getCategoryDAO().getById(iId));
	}
	
	protected CategoryDAO getCategoryDAO()
	{
		return(mObjCategoryDAO);
	}
	
	public List<Category> list() throws SQLException
	{
		return(getCategoryDAO().list());
	}
	
	public void remove(final Category objCategory) throws SQLException
	{
		getCategoryDAO().remove(objCategory);
	}
	
	public Category save(final Category objCategory) throws SQLException
	{
		return(getCategoryDAO().save(objCategory));
	}
}