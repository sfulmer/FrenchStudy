package net.draconia.frenchstudy.model;

import java.io.Serializable;

import net.draconia.utilities.PropertyChangeable;

public class Category extends PropertyChangeable implements Cloneable, Serializable
{
	private static final long serialVersionUID = 5281400324476454101L;
	
	public static final Category EMPTY_CATEGORY = new Category(); 
	
	private Integer miId;
	private String msCategory;
	
	public Category()
	{ }
	
	public Category(final int iId)
	{
		setId(iId);
	}
	
	public Category(final String sCategory)
	{
		setCategory(sCategory);
	}
	
	public Category(final int iId, final String sCategory)
	{
		setId(iId);
		setCategory(sCategory);
	}
	
	public Object clone()
	{
		return(new Category(Integer.valueOf(getId()), new String(getCategory())));
	}
	
	public String getCategory()
	{
		if(msCategory == null)
			msCategory = "";
		
		return(msCategory);
	}
	
	public int getId()
	{
		if((miId == null) || (miId < 0))
			miId = 0;
		
		return(miId);
	}
	
	public void setCategory(final String sCategory)
	{
		String sOldCategory = getCategory();
		
		if(sCategory == null)
			msCategory = "";
		else
			msCategory = sCategory;
		
		firePropertyChangeListeners("Category", sOldCategory, getCategory());
	}
	
	public void setId(final Integer iId)
	{
		Integer iOldId = getId();
		
		if((iId == null) || (iId < 0))
			miId = 0;
		else
			miId = iId;
		
		firePropertyChangeListeners("Id", iOldId, getId());
	}
	
	public String toString()
	{
		return(getCategory());
	}
}