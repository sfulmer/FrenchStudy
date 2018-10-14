package net.draconia.frenchstudy.model;

import java.io.Serializable;

import net.draconia.utilities.PropertyChangeable;

public class PartOfSpeech extends PropertyChangeable implements Serializable
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
		Integer iOldId = getId();
		
		if((iId == null) || (iId < 0))
			miId = 0;
		else
			miId = iId;
		
		firePropertyChangeListeners("Id", iOldId, getId());
	}
	
	public void setPartOfSpeech(final String sPartOfSpeech)
	{
		String sOldPartOfSpeech = getPartOfSpeech();
		
		if(sPartOfSpeech == null)
			msPartOfSpeech = "";
		else
			msPartOfSpeech = sPartOfSpeech;
		
		firePropertyChangeListeners("PartOfSpeech", sOldPartOfSpeech, getPartOfSpeech());
	}
}