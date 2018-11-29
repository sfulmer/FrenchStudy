package net.draconia.frenchstudy.ui.controllers;

import java.awt.Window;

import java.io.Serializable;

import java.sql.SQLException;

import javax.swing.Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.draconia.FrenchStudy;

import net.draconia.frenchstudy.model.Category;

import net.draconia.frenchstudy.services.CategoryService;

import net.draconia.frenchstudy.ui.EditCategoryDialog;

import net.draconia.frenchstudy.ui.actions.DialogCancel;
import net.draconia.frenchstudy.ui.actions.EditCategoryDialogApply;
import net.draconia.frenchstudy.ui.actions.EditCategoryDialogSave;

import net.draconia.frenchstudy.ui.model.EditCategoryDialogModel;

import org.springframework.context.ApplicationContext;

public class EditCategoryDialogController implements Serializable
{
	private static final long serialVersionUID = 3389636250851023917L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(EditCategoryDialogController.class);
	
	private Action mActApply, mActCancel, mActSave;
	private EditCategoryDialog mDlgEditCategory;
	private EditCategoryDialogModel mObjDialogModel;
	private Window mWndOwner;
	
	public EditCategoryDialogController(final Window wndOwner)
	{
		setOwnerWindow(wndOwner);
	}
		
	protected EditCategoryDialog createDialog(final Window wndOwner)
	{
		EditCategoryDialog dlgEditCategory = new EditCategoryDialog(wndOwner, this);
		
		return(dlgEditCategory);
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(FrenchStudy.getApplicationContext());
	}
	
	public Action getApplyAction()
	{
		if(mActApply == null)
			mActApply = new EditCategoryDialogApply(getDialogModel());
		
		return(mActApply);
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	public Action getCancelAction()
	{
		if(mActCancel == null)
			mActCancel = new DialogCancel(getDialog());
		
		return(mActCancel);
	}
	
	protected CategoryService getCategoryService()
	{
		return((CategoryService)(getBean(CategoryService.class)));
	}
	
	public EditCategoryDialog getDialog()
	{
		if(mDlgEditCategory == null)
			mDlgEditCategory = createDialog(getOwnerWindow());
		
		return(mDlgEditCategory);
	}
	
	public EditCategoryDialogModel getDialogModel()
	{
		if(mObjDialogModel == null)
			mObjDialogModel = new EditCategoryDialogModel(this);
		
		return(mObjDialogModel);
	}
	
	protected Window getOwnerWindow()
	{
		return(mWndOwner);
	}
	
	public Action getSaveAction()
	{
		if(mActSave == null)
			mActSave = new EditCategoryDialogSave(this);
		
		return(mActSave);
	}
	
	public void save(final Category objCategory)
	{
		try
			{
			getCategoryService().save(objCategory);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("There was a problem saving the Part of Speech...", objException);
			}
	}
	
	protected void setEditCategoryDialog(final EditCategoryDialog dlgEditCategory)
	{
		mDlgEditCategory = dlgEditCategory;
	}
	
	protected void setOwnerWindow(final Window wndOwner)
	{
		mWndOwner = wndOwner;
	}
	
	public void showDialog()
	{
		getDialog().setVisible(true);
	}
}