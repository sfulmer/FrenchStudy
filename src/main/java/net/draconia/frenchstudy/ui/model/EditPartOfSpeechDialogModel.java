package net.draconia.frenchstudy.ui.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.List;

import net.draconia.FrenchStudy;

import net.draconia.frenchstudy.exceptions.NotEditingException;

import net.draconia.frenchstudy.model.PartOfSpeech;

import net.draconia.frenchstudy.ui.controllers.EditPartOfSpeechDialogController;

import net.draconia.frenchstudy.ui.listeners.propertychange.EditPartOfSpeechDialogModelDirty;
import net.draconia.frenchstudy.ui.listeners.propertychange.EditPartOfSpeechDialogModelEditing;

import net.draconia.utilities.PropertyChangeable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;

public class EditPartOfSpeechDialogModel extends PropertyChangeable implements PropertyChangeListener
{
	private static final long serialVersionUID = 780565785496277624L;
	private static Logger msObjLogger = LoggerFactory.getLogger(EditPartOfSpeechDialogModel.class);
	
	private Boolean mbEditing;
	private EditPartOfSpeechDialogController mObjController;
	private PartOfSpeech mObjEditing, mObjOriginal;
	
	public EditPartOfSpeechDialogModel(final EditPartOfSpeechDialogController objController)
	{
		setController(objController);
		
		init();
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(FrenchStudy.getApplicationContext());
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	protected EditPartOfSpeechDialogController getController()
	{
		return(mObjController);
	}
	
	public PartOfSpeech getEditingModel() throws NotEditingException
	{
		if(isEditing())
			{
			if(mObjEditing == null)
				{
				mObjEditing = ((PartOfSpeech)(getOriginalModel().clone()));
				
				mObjEditing.addPropertyChangeListener(this);
				}
			}
		else
			throw new NotEditingException();
		
		return(mObjEditing);
	}
	
	public PartOfSpeech getOriginalModel()
	{
		if(mObjOriginal == null)
			mObjOriginal = new PartOfSpeech();
		
		return(mObjOriginal);
	}
	
	protected void init()
	{
		addPropertyChangeListener(new EditPartOfSpeechDialogModelEditing(this));		
		addPropertyChangeListener(new EditPartOfSpeechDialogModelDirty(getController()));
	}
	
	public boolean isDirty()
	{
		try
			{
			return(!getEditingModel().equals(getOriginalModel()));
			}
		catch(NotEditingException objException)
			{
			return(false);
			}
	}
	
	public boolean isEditing()
	{
		if(mbEditing == null)
			mbEditing = false;
		
		return(mbEditing);
	}
	
	public void propertyChange(PropertyChangeEvent evt)
	{
		firePropertyChangeListeners("Dirty", null, isDirty());
	}
	
	public void saveEditingModel() throws NotEditingException
	{
		if(isEditing())
			{
			getController().save(getEditingModel());
			
			getOriginalModel().setId(getEditingModel().getId());
			getOriginalModel().setPartOfSpeech(getEditingModel().getPartOfSpeech());
			}
		else
			throw new NotEditingException();
	}
	
	protected void setController(final EditPartOfSpeechDialogController objController)
	{
		mObjController = objController;
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
	public void setEditingModel(final PartOfSpeech objEditingModel) throws NotEditingException
	{
		if(!isEditing())
			throw new NotEditingException();
		else
			{
			List<PropertyChangeListener> lstPropertyChangeListeners;
			
			try
				{
				lstPropertyChangeListeners = (List<PropertyChangeListener>)(getEditingModel().getClass().getMethod("getPropertyChangeListeners", new Class<?>[0]).invoke(getEditingModel(),  new Object[0]));
				}
			catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException objException)
				{
				msObjLogger.error("Failed to retrieve Propeorty Change Listeners for Editing Model...", objException);
				
				lstPropertyChangeListeners = new ArrayList<PropertyChangeListener>();
				}
			
			PartOfSpeech objOldEditingModel = getEditingModel();
			
			for(PropertyChangeListener objPropertyChangeListener : lstPropertyChangeListeners)
				getEditingModel().removePropertyChangeListener(objPropertyChangeListener);
			
			if(objEditingModel == null)
				mObjEditing = ((PartOfSpeech)(getOriginalModel().clone()));
			else
				mObjEditing = objEditingModel;
			
			getEditingModel().addPropertyChangeListener(this);
			
			firePropertyChangeListeners("EditingModel", objOldEditingModel, getEditingModel());
			}
	}
	
	public void setOriginalModel(final PartOfSpeech objOriginalModel)
	{
		PartOfSpeech objOldOriginalModel = getOriginalModel();
		
		if(objOriginalModel == null)
			mObjOriginal = new PartOfSpeech();
		else
			mObjOriginal = objOriginalModel;
		
		firePropertyChangeListeners("OriginalModel", objOldOriginalModel, getOriginalModel());
	}
}