package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.sql.SQLException;

import javax.swing.AbstractAction;

import net.draconia.FrenchStudy;

import net.draconia.frenchstudy.exceptions.NotEditingException;

import net.draconia.frenchstudy.model.Category;

import net.draconia.frenchstudy.services.CategoryService;

import net.draconia.frenchstudy.ui.model.EditCategoryDialogModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;

public class EditCategoryDialogApply extends AbstractAction
{
	private static final long serialVersionUID = -6876813136858256418L;
	private Logger msObjLogger = LoggerFactory.getLogger(EditCategoryDialogApply.class);
	
	private EditCategoryDialogModel mObjDialogModel;
	
	public EditCategoryDialogApply(final EditCategoryDialogModel objDialogModel)
	{
		super("Apply...");
		
		putValue(LONG_DESCRIPTION, "Saves the Part of Speech to the database without closing the window...");
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		putValue(SHORT_DESCRIPTION, "Saves the Part of Speech to the database without closing the window...");
		
		setDialogModel(objDialogModel);
		
		init();
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		try
			{
			getCategoryService().save(getModelToSave());
			}
		catch(SQLException objException)
			{
			msObjLogger.error("There was an issue saving the Part of Sepech...", objException);
			}
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
	
	protected CategoryService getCategoryService()
	{
		return((CategoryService)(getBean(CategoryService.class)));
	}
	
	protected EditCategoryDialogModel getDialogModel()
	{
		return(mObjDialogModel);
	}
	
	protected Category getModelToSave()
	{
		try
			{
			return(getDialogModel().getEditingModel());
			}
		catch(NotEditingException objException)
			{
			return(null);
			}
	}
	
	protected void init()
	{
		setEnabled(getDialogModel().isDirty());
	}
	
	protected void setDialogModel(final EditCategoryDialogModel objDialogModel)
	{
		mObjDialogModel = objDialogModel;
	}
}