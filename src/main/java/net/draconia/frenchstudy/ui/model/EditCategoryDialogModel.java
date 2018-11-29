package net.draconia.frenchstudy.ui.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.List;

import net.draconia.FrenchStudy;

import net.draconia.frenchstudy.exceptions.NotEditingException;

import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.ui.controllers.EditCategoryDialogController;
import net.draconia.frenchstudy.ui.listeners.propertychange.EditCategoryDialogModelDirty;
import net.draconia.frenchstudy.ui.listeners.propertychange.EditCategoryDialogModelEditing;
import net.draconia.utilities.PropertyChangeable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;

public class EditCategoryDialogModel extends PropertyChangeable implements PropertyChangeListener
{
	private static final long serialVersionUID = 780565785496277624L;
	private static Logger msObjLogger = LoggerFactory.getLogger(EditCategoryDialogModel.class);
	
	private Boolean mbEditing;
	private EditCategoryDialogController mObjController;
	private Category mObjEditing, mObjOriginal;
	
	public EditCategoryDialogModel(final EditCategoryDialogController objController)
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
	
	protected EditCategoryDialogController getController()
	{
		return(mObjController);
	}
	
	public Category getEditingModel() throws NotEditingException
	{
		if(isEditing())
			{
			if(mObjEditing == null)
				{
				mObjEditing = ((Category)(getOriginalModel().clone()));
				
				mObjEditing.addPropertyChangeListener(this);
				}
			}
		else
			throw new NotEditingException();
		
		return(mObjEditing);
	}
	
	public Category getOriginalModel()
	{
		if(mObjOriginal == null)
			mObjOriginal = new Category();
		
		return(mObjOriginal);
	}
	
	protected void init()
	{
		addPropertyChangeListener(new EditCategoryDialogModelEditing(this));		
		addPropertyChangeListener(new EditCategoryDialogModelDirty(getController()));
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
			getOriginalModel().setCategory(getEditingModel().getCategory());
			}
		else
			throw new NotEditingException();
	}
	
	protected void setController(final EditCategoryDialogController objController)
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
	public void setEditingModel(final Category objEditingModel) throws NotEditingException
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
			
			Category objOldEditingModel = getEditingModel();
			
			for(PropertyChangeListener objPropertyChangeListener : lstPropertyChangeListeners)
				getEditingModel().removePropertyChangeListener(objPropertyChangeListener);
			
			if(objEditingModel == null)
				mObjEditing = ((Category)(getOriginalModel().clone()));
			else
				mObjEditing = objEditingModel;
			
			getEditingModel().addPropertyChangeListener(this);
			
			firePropertyChangeListeners("EditingModel", objOldEditingModel, getEditingModel());
			}
	}
	
	public void setOriginalModel(final Category objOriginalModel)
	{
		Category objOldOriginalModel = getOriginalModel();
		
		if(objOriginalModel == null)
			mObjOriginal = new Category();
		else
			mObjOriginal = objOriginalModel;
		
		firePropertyChangeListeners("OriginalModel", objOldOriginalModel, getOriginalModel());
	}
}