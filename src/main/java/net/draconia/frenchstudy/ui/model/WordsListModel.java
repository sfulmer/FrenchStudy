package net.draconia.frenchstudy.ui.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;

import javax.swing.event.ListDataListener;

import net.draconia.frenchstudy.model.Word;

import org.springframework.stereotype.Component;

@Component
public class WordsListModel implements ListModel<Word>, Serializable
{
	private static final long serialVersionUID = -2861642589412312389L;
	
	private List<ListDataListener> mLstListeners;
	private List<Word> mLstModel;
	
	public void addListDataListener(final ListDataListener objListener)
	{
		getListeners().add(objListener);
	}
	
	public Word getElementAt(final int iIndex)
	{
		return(getModel().get(iIndex));
	}
	
	protected List<Word> getModel()
	{
		if(mLstModel == null)
			mLstModel = new ArrayList<Word>();
		
		return(mLstModel);
	}
	
	protected List<ListDataListener> getListeners()
	{
		if(mLstListeners == null)
			mLstListeners = new ArrayList<ListDataListener>();
		
		return(mLstListeners);
	}
	
	public int getSize()
	{
		return(getModel().size());
	}
	
	public void removeListDataListener(final ListDataListener objListener)
	{
		getListeners().remove(objListener);
	}
}