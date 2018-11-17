package net.draconia.frenchstudy.ui.model;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import net.draconia.ApplicationContextProvider;

import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.model.Word;

import net.draconia.frenchstudy.ui.controllers.ListPanelController;
import net.draconia.frenchstudy.ui.listeners.propertychange.ListModelViewingWordPropertyChangeListener;
import net.draconia.utilities.PropertyChangeable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class ListPanelModel extends PropertyChangeable implements Serializable
{
	private static final long serialVersionUID = -3529354778483360200L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(ListPanelModel.class);
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	private Boolean mbCurrentSlang;
	private Category mObjCurrentCategory;
	private List<Word> mLstModel;
	private PartOfSpeech mObjCurrentPartOfSpeech;
	private Word mObjViewingWord;
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	public Category getCurrentCategory()
	{
		return(mObjCurrentCategory);
	}
	
	public PartOfSpeech getCurrentPartOfSpeech()
	{
		return(mObjCurrentPartOfSpeech);
	}
	
	protected ListPanelController getListPanelController()
	{
		return((ListPanelController)(getBean(ListPanelController.class)));
	}
	
	public List<Word> getModel()
	{
		if(mLstModel == null)
			try
				{
				mLstModel = getListPanelController().list(getCurrentPartOfSpeech(), getCurrentCategory(), isCurrentlySlang());
				}
			catch(SQLException objException)
				{
				msObjLogger.error("There was a problem producing the list of Words by PartOfSpeech, Category, and Slang...", objException);
				
				mLstModel = new ArrayList<Word>();
				}
		
		return(mLstModel);
	}
	
	public Word getViewingWord()
	{
		return(mObjViewingWord);
	}
	
	@PostConstruct
	protected void init()
	{
		addPropertyChangeListener((ListModelViewingWordPropertyChangeListener)(getBean(ListModelViewingWordPropertyChangeListener.class)));
	}
		
	public Boolean isCurrentlySlang()
	{
		return(mbCurrentSlang);
	}
	
	public Word newWord()
	{
		return(new Word());
	}
	
	public void setCurrentCategory(final Category objCategory)
	{
		Category objOldCategory = getCurrentCategory();
		
		mObjCurrentCategory = objCategory;
		
		firePropertyChangeListeners("CurrentCategory", objOldCategory, getCurrentCategory());
	}
	
	public void setCurrentPartOfSpeech(final PartOfSpeech objPartOfSpeech)
	{
		PartOfSpeech objOldPartOfSpeech = getCurrentPartOfSpeech();
		
		mObjCurrentPartOfSpeech = objPartOfSpeech;
		
		firePropertyChangeListeners("CurrentPartOfSpeech", objOldPartOfSpeech, getCurrentPartOfSpeech());
	}
	
	public void setCurrentSlang(final Boolean bSlang)
	{
		Boolean bOldSlang = isCurrentlySlang();
		
		mbCurrentSlang = bSlang;
		
		firePropertyChangeListeners("CurrentSlang", bOldSlang, isCurrentlySlang());
	}
	
	protected void setModel(final List<Word> lstModel)
	{
		List<Word> lstOldModel = getModel();
		
		mLstModel = lstModel;
		
		firePropertyChangeListeners("Model", lstOldModel, getModel());
	}
	
	public void setViewingWord(final Word objViewingWord)
	{
		Word objOldViewingWord = getViewingWord();
		
		mObjViewingWord = objViewingWord;
		
		firePropertyChangeListeners("ViewingWord", objOldViewingWord, getViewingWord());
	}
}