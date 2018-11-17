package net.draconia.frenchstudy.ui.controllers;

import java.awt.Window;

import java.io.Serializable;
import java.sql.SQLException;

import javax.swing.Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import net.draconia.FrenchStudy;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.services.PartOfSpeechService;
import net.draconia.frenchstudy.ui.EditPartOfSpeechDialog;

import net.draconia.frenchstudy.ui.actions.EditPartOfSpeechDialogApply;
import net.draconia.frenchstudy.ui.actions.EditPartOfSpeechDialogCancel;
import net.draconia.frenchstudy.ui.actions.EditPartOfSpeechDialogSave;

import net.draconia.frenchstudy.ui.model.EditPartOfSpeechDialogModel;

public class EditPartOfSpeechDialogController implements Serializable
{
	private static final long serialVersionUID = 3389636250851023917L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(EditPartOfSpeechDialogController.class);
	
	private Action mActApply, mActCancel, mActSave;
	private EditPartOfSpeechDialog mDlgEditPartOfSpeech;
	private EditPartOfSpeechDialogModel mObjDialogModel;
	private Window mWndOwner;
	
	public EditPartOfSpeechDialogController(final Window wndOwner)
	{
		setOwnerWindow(wndOwner);
	}
		
	protected EditPartOfSpeechDialog createDialog(final Window wndOwner)
	{
		EditPartOfSpeechDialog dlgEditPartOfSpeech = new EditPartOfSpeechDialog(wndOwner, this);
		
		return(dlgEditPartOfSpeech);
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
	
	public EditPartOfSpeechDialog getDialog()
	{
		if(mDlgEditPartOfSpeech == null)
			mDlgEditPartOfSpeech = createDialog(getOwnerWindow());
		
		return(mDlgEditPartOfSpeech);
	}
	
	public EditPartOfSpeechDialogModel getDialogModel()
	{
		if(mObjDialogModel == null)
			mObjDialogModel = new EditPartOfSpeechDialogModel(this);
		
		return(mObjDialogModel);
	}
	
	public Action getApplyAction()
	{
		if(mActApply == null)
			mActApply = new EditPartOfSpeechDialogApply(getDialogModel());
		
		return(mActApply);
	}
	
	public Action getCancelAction()
	{
		if(mActCancel == null)
			mActCancel = new EditPartOfSpeechDialogCancel(getDialog());
		
		return(mActCancel);
	}
	
	protected Window getOwnerWindow()
	{
		return(mWndOwner);
	}
	
	protected PartOfSpeechService getPartOfSpeechService()
	{
		return((PartOfSpeechService)(getBean(PartOfSpeechService.class)));
	}
	
	public Action getSaveAction()
	{
		if(mActSave == null)
			mActSave = new EditPartOfSpeechDialogSave(this);
		
		return(mActSave);
	}
	
	public void save(final PartOfSpeech objPartOfSpeech)
	{
		try
			{
			getPartOfSpeechService().save(objPartOfSpeech);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("There was a problem saving the Part of Speech...", objException);
			}
	}
	
	protected void setEditPartOfSpeechDialog(final EditPartOfSpeechDialog dlgEditPartOfSpeech)
	{
		mDlgEditPartOfSpeech = dlgEditPartOfSpeech;
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