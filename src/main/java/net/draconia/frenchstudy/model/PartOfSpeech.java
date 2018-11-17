package net.draconia.frenchstudy.model;

import java.io.Serializable;

import net.draconia.utilities.PropertyChangeable;

public class PartOfSpeech extends PropertyChangeable implements Cloneable, Serializable
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
	
	public Object clone()
	{
		return(new PartOfSpeech(Integer.valueOf(getId()), new String(getPartOfSpeech())));
	}
	
	public boolean equals(final Object objOther)
	{
		if(objOther instanceof PartOfSpeech)
			{
			boolean bPOSEqual;
			PartOfSpeech objPOS = ((PartOfSpeech)(objOther));
			
			bPOSEqual = getPartOfSpeech().equals(objPOS.getPartOfSpeech());
			
			if(bPOSEqual)
				return(Integer.valueOf(getId()).equals(objPOS.getId()));
			else
				return(false);
			}
		else
			return(false);
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
	
	public String toString()
	{
		return(getPartOfSpeech());
	}
}