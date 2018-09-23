package net.draconia.frenchstudy.model;

import java.io.Serializable;

import java.util.Observable;

public class PartOfSpeech extends Observable implements Serializable
{
	private static final long serialVersionUID = -6078393935088298959L;
	
	private Integer miId;
	private String msPartOfSpeech;
	
	public PartOfSpeech()
	{ }
	
	public PartOfSpeech(final int iId)
	{
		setId(iId);
	}
	
	public PartOfSpeech(final String sPartOfSpeech)
	{
		setPartOfSpeech(sPartOfSpeech);
	}
	
	public PartOfSpeech(final int iId, final String sPartOfSpeech)
	{
		setId(iId);
		setPartOfSpeech(sPartOfSpeech);
	}
	
	public int getId()
	{
		if((miId == null) || (miId < 0))
			miId = 0;
		
		return(miId);
	}
	
	public String getPartOfSpeech()
	{
		if(msPartOfSpeech == null)
			msPartOfSpeech = "";
		
		return(msPartOfSpeech);
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
	
	public void setPartOfSpeech(final String sPartOfSpeech)
	{
		if(sPartOfSpeech == null)
			msPartOfSpeech = "";
		else
			msPartOfSpeech = sPartOfSpeech;
		
		setChanged();
		notifyObservers();
	}
}