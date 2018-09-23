package net.draconia.frenchstudy.model;

import java.io.Serializable;

import java.util.Observable;

import net.draconia.frenchstudy.NoPartOfSpeechBoundException;
import net.draconia.frenchstudy.NoWordBoundException;

public class WordInstance extends Observable implements Serializable
{
	private static final long serialVersionUID = 508352573636136087L;
	
	private Boolean mbSlang;
	private Category mObjCategory;
	private Integer miId;
	private PartOfSpeech mObjPartOfSpeech;
	private String msDefinition;
	private Word mObjWord;
	
	public WordInstance()
	{ }
	
	public WordInstance(final int iId)
	{
		setId(iId);
	}
	
	public WordInstance(final Word objWord, final PartOfSpeech objPartOfSpeech) throws NoPartOfSpeechBoundException, NoWordBoundException 
	{
		setWord(objWord);
		setPartOfSpeech(objPartOfSpeech);
	}
	
	public WordInstance(final Word objWord, final PartOfSpeech objPartOfSpeech, final Category objCategory) throws NoPartOfSpeechBoundException, NoWordBoundException
	{
		this(objWord, objPartOfSpeech);
		
		setCategory(objCategory);
	}
	
	public WordInstance(final Word objWord, final PartOfSpeech objPartOfSpeech, final Category objCategory, final boolean bSlang) throws NoPartOfSpeechBoundException, NoWordBoundException
	{
		this(objWord, objPartOfSpeech, objCategory);
		
		setSlang(bSlang);
	}
	
	public WordInstance(final Word objWord, final PartOfSpeech objPartOfSpeech, final Category objCategory, final String sDefinition) throws NoPartOfSpeechBoundException, NoWordBoundException
	{
		this(objWord, objPartOfSpeech, objCategory);
		
		setDefinition(sDefinition);
	}
	
	public WordInstance(final Word objWord, final PartOfSpeech objPartOfSpeech, final Category objCategory, final String sDefinition, final Boolean bSlang) throws NoPartOfSpeechBoundException, NoWordBoundException
	{
		this(objWord, objPartOfSpeech, objCategory, sDefinition);
		
		setSlang(bSlang);
	}
	
	public WordInstance(int iId, final Word objWord, final PartOfSpeech objPartOfSpeech, final Category objCategory, final Boolean bSlang) throws NoPartOfSpeechBoundException, NoWordBoundException
	{
		this(objWord, objPartOfSpeech, objCategory, bSlang);
		
		setId(iId);
	}
	
	public WordInstance(int iId, final Word objWord, final PartOfSpeech objPartOfSpeech, final Category objCategory, final String sDefinition, final Boolean bSlang) throws NoPartOfSpeechBoundException, NoWordBoundException
	{
		this(objWord, objPartOfSpeech, objCategory, sDefinition, bSlang);
		
		setId(iId);
	}
	
	public Category getCategory()
	{
		return(mObjCategory);
	}
	
	public String getDefinition()
	{
		if(msDefinition == null)
			msDefinition = "";
		
		return(msDefinition);
	}
	
	public int getId()
	{
		if((miId == null) || (miId < 0))
			miId = 0;
		
		return(miId);
	}
	
	public PartOfSpeech getPartOfSpeech() throws NoPartOfSpeechBoundException
	{
		if(mObjPartOfSpeech == null)
			throw new NoPartOfSpeechBoundException();
		
		return(mObjPartOfSpeech);
	}
	
	public Word getWord() throws NoWordBoundException
	{
		if(mObjWord == null)
			throw new NoWordBoundException();
		
		return(mObjWord);
	}
	
	public boolean isSlang()
	{
		if(mbSlang == null)
			mbSlang = false;
		
		return(mbSlang);
	}
	
	public void setCategory(final Category objCategory)
	{
		mObjCategory = objCategory;
		
		setChanged();
		notifyObservers();
	}
	
	public void setDefinition(final String sDefinition)
	{
		if(sDefinition == null)
			msDefinition = "";
		else
			msDefinition = sDefinition;
		
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
	
	public void setPartOfSpeech(final PartOfSpeech objPartOfSpeech) throws NoPartOfSpeechBoundException
	{
		if(objPartOfSpeech == null)
			throw new NoPartOfSpeechBoundException();
		else
			mObjPartOfSpeech = objPartOfSpeech;
		
		setChanged();
		notifyObservers();
	}
	
	public void setSlang(final Boolean bSlang)
	{
		if(bSlang == null)
			mbSlang = false;
		else
			mbSlang = bSlang;
		
		setChanged();
		notifyObservers();
	}
	
	public void setWord(final Word objWord) throws NoWordBoundException
	{
		if(objWord == null)
			throw new NoWordBoundException();
		else
			mObjWord = objWord;
		
		setChanged();
		notifyObservers();
	}
}