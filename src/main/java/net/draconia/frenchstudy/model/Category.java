package net.draconia.frenchstudy.model;

import java.io.Serializable;

import java.util.Observable;

public class Category extends Observable implements Serializable
{
	private static final long serialVersionUID = 5281400324476454101L;
	
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
		if(sCategory == null)
			msCategory = "";
		else
			msCategory = sCategory;
		
		setChanged();
		notifyObservers();
	}
	
	public void setId(final Integer iId)
	{
		if((iId == null) || (iId < 0))
			miId = 0;
		else
			miId = iId;
		
		setChanged();
		notifyObservers();
	}
	
	public String toString()
	{
		return(getCategory());
	}
}