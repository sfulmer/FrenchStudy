package net.draconia.frenchstudy.model;

import java.io.Serializable;

import net.draconia.utilities.PropertyChangeable;

public class Word extends PropertyChangeable implements Serializable
{
	private static final long serialVersionUID = 191393811589856766L;
	
	private Integer miId;
	private String msEnglish;
	
	public Word()
	{ }
	
	public Word(final int iId)
	{
		setId(iId);
	}
	
	public Word(final String sEnglish)
	{
		setEnglish(sEnglish);
	}
	
	public Word(final int iId, final String sEnglish)
	{
		setId(iId);
		setEnglish(sEnglish);
	}
	
	public String getEnglish()
	{
		if(msEnglish == null)
			msEnglish = "";
		
		return(msEnglish);
	}
	
	public int getId()
	{
		if(miId == null)
			miId = 0;
		
		return(miId);
	}
	
	public void setEnglish(final String sEnglish)
	{
		String sOldEnglish = getEnglish();
		
		if(sEnglish == null)
			msEnglish = "";
		else
			msEnglish = sEnglish;
		
		firePropertyChangeListeners("English", sOldEnglish, getEnglish());
	}
	
	public void setId(final Integer iId)
	{
		Integer iOldId = getId();
		
		if(iId == null)
			miId = 0;
		else
			miId = iId;
		
		firePropertyChangeListeners("Id", iOldId, getId());
	}
}