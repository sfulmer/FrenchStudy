package net.draconia.frenchstudy.ui.model;

import java.io.Serializable;

import net.draconia.frenchstudy.model.Word;

import net.draconia.utilities.PropertyChangeable;

import org.springframework.stereotype.Component;

@Component
public class DetailsPanelModel extends PropertyChangeable implements Serializable
{
	private static final long serialVersionUID = -6453878199637497766L;
	
	private Boolean mbEditing;
	private Word mObjEditingWord, mObjViewingWord;
	
	public Word getEditingWord()
	{
		return(mObjEditingWord);
	}
	
	public Word getViewingWord()
	{
		return(mObjViewingWord);
	}
	
	public boolean isEditing()
	{
		if(mbEditing == null)
			mbEditing = false;
		
		return(mbEditing);
	}
	
	public void setEditing(final Boolean bEditing)
	{
		Boolean bOldEditing = isEditing();
		
		if(bEditing == null)
			mbEditing = false;
		else
			mbEditing = bEditing;
		
		firePropertyChangeListeners("Editing", bOldEditing, isEditing());
	}
	
	public void setEditingWord(final Word objEditingWord)
	{
		Word objOldEditingWord = getEditingWord();
		
		mObjEditingWord = objEditingWord;
		
		firePropertyChangeListeners("EditingWord", objOldEditingWord, getEditingWord());
	}
	
	public void setViewingWord(final Word objViewingWord)
	{
		Word objOldViewingWord = getViewingWord();
		
		mObjViewingWord = objViewingWord;
		
		firePropertyChangeListeners("ViewingWord", objOldViewingWord, getViewingWord());
	}
}