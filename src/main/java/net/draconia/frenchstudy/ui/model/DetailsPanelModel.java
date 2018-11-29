package net.draconia.frenchstudy.ui.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import net.draconia.ApplicationContextProvider;

import net.draconia.frenchstudy.model.Word;
import net.draconia.frenchstudy.model.WordInstance;

import net.draconia.frenchstudy.ui.controllers.DetailsPanelController;

import net.draconia.frenchstudy.ui.listeners.propertychange.DetailsPanelModelEditingPropertyChangeListener;
import net.draconia.frenchstudy.ui.listeners.propertychange.DetailsPanelModelEditingWordPropertyChangeListener;
import net.draconia.frenchstudy.ui.listeners.propertychange.DetailsPanelModelViewingWordPropertyChangeListener;
import net.draconia.frenchstudy.ui.listeners.propertychange.WordEnglishPositiveLengthPropertyChangeListener;
import net.draconia.utilities.PropertyChangeable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class DetailsPanelModel extends PropertyChangeable
{
	private static final long serialVersionUID = -6453878199637497766L;
	private static Logger msObjLogger = LoggerFactory.getLogger(DetailsPanelModel.class);
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	private Boolean mbEditing;
	
	@Autowired
	private DetailsPanelController mObjDetailsPanelController;
	
	private List<WordInstance> mLstWordInstances;
	private Word mObjEditingWord, mObjViewingWord;
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
	}
	
	protected Object getBean(final Class<?> clsBeanName)
	{
		return(getApplicationContext().getBean(clsBeanName));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	protected DetailsPanelController getDetailsPanelController()
	{
		return(mObjDetailsPanelController);
	}
	
	public Word getEditingWord()
	{
		return(mObjEditingWord);
	}
	
	public Word getViewingWord()
	{
		return(mObjViewingWord);
	}
	
	public List<WordInstance> getWordInstances()
	{
		if(mLstWordInstances == null)
			mLstWordInstances = getDetailsPanelController().getWordInstancesForWord(getViewingWord());
		
		return(mLstWordInstances);
	}
	
	@PostConstruct
	protected void init()
	{
		addPropertyChangeListener((DetailsPanelModelEditingPropertyChangeListener)(getBean(DetailsPanelModelEditingPropertyChangeListener.class)));
		addPropertyChangeListener((DetailsPanelModelEditingWordPropertyChangeListener)(getBean(DetailsPanelModelEditingWordPropertyChangeListener.class)));
		addPropertyChangeListener((DetailsPanelModelViewingWordPropertyChangeListener)(getBean(DetailsPanelModelViewingWordPropertyChangeListener.class)));
	}
	
	public boolean isDirty()
	{
		return(!getEditingWord().equals(getViewingWord()));
	}
	
	public boolean isEditing()
	{
		if(mbEditing == null)
			mbEditing = false;
		
		return(mbEditing);
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeListener)
	{
		firePropertyChangeListeners("Dirty", null, isDirty());
	}
	
	public boolean removeWordInstance(final WordInstance objWordInstance)
	{
		boolean bReturn = getWordInstances().remove(objWordInstance);
		
		if(bReturn)
			firePropertyChangeListeners("removeWordInstance", objWordInstance, null);
		
		return(bReturn);
	}
	
	public boolean removeWordInstances(final Collection<WordInstance> collWordInstances)
	{
		boolean bReturn = getWordInstances().removeAll(collWordInstances);
		
		if(bReturn)
			firePropertyChangeListeners("removeWordInstances", collWordInstances, null);
		
		return(bReturn);
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
	
	@SuppressWarnings("unchecked")
	public void setEditingWord(final Word objEditingWord)
	{
		Word objOldEditingWord = getEditingWord();
		
		if(objOldEditingWord != null)
			{
			List<PropertyChangeListener> lstPropertyChangeListeners = null;
			
			try
				{
   				lstPropertyChangeListeners = ((List<PropertyChangeListener>)(objOldEditingWord.getClass().getMethod("getPropertyChangeListeners",  new Class<?>[0]).invoke(objOldEditingWord, new Object[0])));
				}
			catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException objException)
				{
				msObjLogger.error("There was an issue acquiring the old word's list of Property Change Listeners to remove them...", objException);
				lstPropertyChangeListeners = new ArrayList<PropertyChangeListener>();
				}
			
			for(PropertyChangeListener objPropertyChangeListener : lstPropertyChangeListeners)
				objOldEditingWord.removePropertyChangeListener(objPropertyChangeListener);
			}
		
		if((objEditingWord != null) && (getViewingWord() != null) && (!getViewingWord().equals(objEditingWord)))
			setViewingWord(objEditingWord);
		
		mObjEditingWord = ((Word)(getViewingWord().clone()));
		
		mObjEditingWord.addPropertyChangeListener((WordEnglishPositiveLengthPropertyChangeListener)(getBean(WordEnglishPositiveLengthPropertyChangeListener.class)));
		
		firePropertyChangeListeners("EditingWord", objOldEditingWord, getEditingWord());
	}
	
	public void setViewingWord(final Word objViewingWord)
	{
		Word objOldViewingWord = getViewingWord();
		
		mObjViewingWord = objViewingWord;
		
		if(mObjViewingWord == null)
			setEditing(false);
		
		firePropertyChangeListeners("ViewingWord", objOldViewingWord, getViewingWord());
	}
}