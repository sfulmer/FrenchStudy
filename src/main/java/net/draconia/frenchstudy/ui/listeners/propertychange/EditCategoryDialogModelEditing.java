package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.Serializable;

import javax.swing.JTextField;

import net.draconia.FrenchStudy;

import net.draconia.frenchstudy.exceptions.NotEditingException;

import net.draconia.frenchstudy.ui.model.EditCategoryDialogModel;

import net.draconia.utilities.TextUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;

public class EditCategoryDialogModelEditing implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = -5119556260980847779L;
	private static Logger msObjLogger = LoggerFactory.getLogger(EditCategoryDialogModelEditing.class);
	
	private EditCategoryDialogModel mObjDialogModel;
	
	public EditCategoryDialogModelEditing(final EditCategoryDialogModel objDialogModel)
	{
		setDialogModel(objDialogModel);
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
	
	protected EditCategoryDialogModel getDialogModel()
	{
		return(mObjDialogModel);
	}
	
	protected JTextField getCategoryField()
	{
		return((JTextField)(getBean("txtEditCategoryDialogCategory")));
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		if(objPropertyChangeEvent.getPropertyName().equalsIgnoreCase("Editing"))
			{
			TextUtilities objTextUtilities = new TextUtilities();
			
			if(objPropertyChangeEvent.getNewValue().equals(true))
				try
					{
					objTextUtilities.setupUnRedoableBiDirectionalBoundTextComponent(getCategoryField(), getDialogModel().getEditingModel(), "Category", String.class);
					}
				catch(NotEditingException objException)
					{
					msObjLogger.error("There was a problem setting up the Part of Speech field for use...", objException);
					}
			else
				objTextUtilities.resetTextComponent(getCategoryField());
			}
	}
	
	protected void setDialogModel(final EditCategoryDialogModel objDialogModel)
	{
		mObjDialogModel = objDialogModel;
	}
}