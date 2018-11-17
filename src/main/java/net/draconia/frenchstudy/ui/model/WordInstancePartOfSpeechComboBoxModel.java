package net.draconia.frenchstudy.ui.model;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import net.draconia.frenchstudy.model.PartOfSpeech;

import net.draconia.frenchstudy.ui.controllers.EditWordInstanceDialogController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class WordInstancePartOfSpeechComboBoxModel implements ComboBoxModel<PartOfSpeech>, Serializable
{
	private static final long serialVersionUID = 2509351721137099113L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(WordInstancePartOfSpeechComboBoxModel.class);

	private Integer miSelectedIndex;
	private List<ListDataListener> mLstListDataListeners;
	private List<PartOfSpeech> mLstModel;
	
	@Autowired
	private EditWordInstanceDialogController mObjDialogController;
	
	public void addListDataListener(final ListDataListener objListDataListener)
	{
		getListDataListeners().add(objListDataListener);
	}
	
	protected void fireContentsChanged()
	{
		for(ListDataListener objListDataListener : getListDataListeners())
			objListDataListener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, -1, -1));
	}
	
	protected EditWordInstanceDialogController getDialogController()
	{
		return(mObjDialogController);
	}
	
	public PartOfSpeech getElementAt(final int iIndex)
	{
		if(iIndex > 0)
			return(getModel().get(iIndex - 1));
		else if(iIndex == 0)
			return(new PartOfSpeech("Add..."));
		else
			return(null);
	}
	
	protected List<ListDataListener> getListDataListeners()
	{
		if(mLstListDataListeners == null)
			mLstListDataListeners = new ArrayList<ListDataListener>();
		
		return(mLstListDataListeners);
	}
	
	protected List<PartOfSpeech> getModel()
	{
		try
			{
			if(mLstModel == null)
				mLstModel = getDialogController().listPartOfSpeeches();
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error retrieving list of Parts of Speech...", objException);
			mLstModel = new ArrayList<PartOfSpeech>();
			}
		
		return(mLstModel);
	}
	
	protected int getSelectedIndex()
	{
		if(miSelectedIndex == null)
			miSelectedIndex = -1;
		
		return(miSelectedIndex);
	}
	
	public Object getSelectedItem()
	{
		return(getElementAt(getSelectedIndex()));
	}
	
	public int getSize()
	{
		return(getModel().size() + 1);
	}
	
	public void reloadList()
	{
		mLstModel = null;
		
		fireContentsChanged();
	}
	
	public void removeListDataListener(final ListDataListener objListDataListener)
	{
		getListDataListeners().remove(objListDataListener);
	}
	
	protected void setSelectedIndex(final Integer iSelectedIndex)
	{
		if(iSelectedIndex == null)
			miSelectedIndex = -1;
		else
			miSelectedIndex = iSelectedIndex;
		
		fireContentsChanged();
	}
	
	public void setSelectedItem(final Object objPartOfSpeech)
	{
		if(objPartOfSpeech == null)
			setSelectedIndex(null);
		else if(objPartOfSpeech instanceof PartOfSpeech)
			{
			int iIndex = getModel().indexOf(objPartOfSpeech);
			PartOfSpeech objPOS = ((PartOfSpeech)(objPartOfSpeech));
			
			if(iIndex == -1)
				if(objPOS.getPartOfSpeech().equals("Add..."))
					setSelectedIndex(0);
				else
					setSelectedIndex(-1);
			else
				setSelectedIndex(iIndex + 1);
			}
	}
}