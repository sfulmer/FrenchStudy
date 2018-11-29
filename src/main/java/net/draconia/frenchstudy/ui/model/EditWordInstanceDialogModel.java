package net.draconia.frenchstudy.ui.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

import javax.annotation.PostConstruct;

import net.draconia.ApplicationContextProvider;
import net.draconia.frenchstudy.exceptions.NoPartOfSpeechBoundException;
import net.draconia.frenchstudy.exceptions.NoWordBoundException;
import net.draconia.frenchstudy.model.WordInstance;
import net.draconia.frenchstudy.ui.listeners.propertychange.EditWordInstanceDialogEditingModelDirtyPropertyChangeListener;
import net.draconia.frenchstudy.ui.listeners.propertychange.EditWordInstanceDialogEditingModelPropertyChangeListener;
import net.draconia.frenchstudy.ui.listeners.propertychange.EditWordInstanceDialogSaveablePropertyChangeListener;
import net.draconia.utilities.PropertyChangeable;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EditWordInstanceDialogModel extends PropertyChangeable implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = 5044135679551989534L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(EditWordInstanceDialogModel.class);
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	private WordInstance mObjEditingModel, mObjOriginalModel;
	
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
	
	public WordInstance getEditingModel()
	{
		if(mObjEditingModel == null)
			{
			mObjEditingModel = ((WordInstance)(getOriginalModel().clone()));
			
			mObjEditingModel.addPropertyChangeListener(this);
			}
		
		return(mObjEditingModel);
	} 
	
	protected EditWordInstanceDialogEditingModelDirtyPropertyChangeListener getEditingWordDirtyPropertyChangeListener()
	{
		return((EditWordInstanceDialogEditingModelDirtyPropertyChangeListener)(getBean(EditWordInstanceDialogEditingModelDirtyPropertyChangeListener.class)));
	}
	
	protected EditWordInstanceDialogEditingModelPropertyChangeListener getEditingWordPropertyChangeListener()
	{
		return((EditWordInstanceDialogEditingModelPropertyChangeListener)(getBean(EditWordInstanceDialogEditingModelPropertyChangeListener.class)));
	}
	
	protected EditWordInstanceDialogSaveablePropertyChangeListener getEditingWordSaveablePropertyChangeListener()
	{
		return((EditWordInstanceDialogSaveablePropertyChangeListener)(getBean(EditWordInstanceDialogSaveablePropertyChangeListener.class)));
	}
	
	public WordInstance getOriginalModel()
	{
		if(mObjOriginalModel == null)
			try
				{
				mObjOriginalModel = new WordInstance();
				}
			catch(NoPartOfSpeechBoundException | NoWordBoundException objException)
				{
				msObjLogger.error("There was a problem setting the default Original Model...", objException);
				}
		
		return(mObjOriginalModel);
	}
	
	@PostConstruct
	protected void init()
	{
		addPropertyChangeListener(getEditingWordPropertyChangeListener());
		addPropertyChangeListener(getEditingWordDirtyPropertyChangeListener());
		addPropertyChangeListener(getEditingWordSaveablePropertyChangeListener());
	}
	
	public boolean isDirty()
	{
		return(!getOriginalModel().equals(getEditingModel()));
	}
	
	public boolean isSaveable()
	{
		try
			{
			return(getEditingModel().getPartOfSpeech().getId() > 0);
			}
		catch(NoPartOfSpeechBoundException objException)
			{
			return(false);
			}
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		firePropertyChangeListeners("Dirty", null, isDirty());
		firePropertyChangeListeners("Saveable", null, isSaveable());
	}
	
	public void setEditingModel(final WordInstance objModel)
	{
		if(objModel != null)
			{
			WordInstance objOldValue = getEditingModel();
			
			mObjEditingModel = objModel;
			
			mObjEditingModel.addPropertyChangeListener(this);
			
			firePropertyChangeListeners("EditingModel", objOldValue, getEditingModel());
			}
		else
			{
			setOriginalModel(objModel);
			setEditingModel((WordInstance)(getOriginalModel().clone()));
			}
	}
	
	public void setOriginalModel(final WordInstance objModel)
	{
		if(objModel != null)
			{
			WordInstance objOldValue = getOriginalModel();
			
			mObjOriginalModel = objModel;
			
			firePropertyChangeListeners("OriginalModel", objOldValue, getOriginalModel());
			}
		else
			try
				{
				setOriginalModel(new WordInstance());
				}
			catch(NoPartOfSpeechBoundException | NoWordBoundException objException)
				{
				msObjLogger.error("There was a problem setting the original model...", objException);
				}
	}
}