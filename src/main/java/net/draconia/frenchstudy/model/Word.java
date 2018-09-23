package net.draconia.frenchstudy.model;

import java.util.Observable;

public class Word extends Observable
{
	private Integer miId;
	private String msEnglish;
	
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
		if(sEnglish == null)
			msEnglish = "";
		else
			msEnglish = sEnglish;
		
		setChanged();
		notifyObservers();
	}
	
	public void setId(final Integer iId)
	{
		if(iId == null)
			miId = 0;
		else
			miId = iId;
		
		setChanged();
		notifyObservers();
	}
}