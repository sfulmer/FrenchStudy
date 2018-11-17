package net.draconia.frenchstudy.ui.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;

import net.draconia.FrenchStudy;

import net.draconia.frenchstudy.exceptions.NotEditingException;

import net.draconia.frenchstudy.ui.controllers.EditPartOfSpeechDialogController;

import net.draconia.frenchstudy.ui.model.EditPartOfSpeechDialogModel;

public class EditPartOfSpeechWindowListener extends WindowAdapter
{
	private EditPartOfSpeechDialogController mObjController;
	
	public EditPartOfSpeechWindowListener(final EditPartOfSpeechDialogController objController)
	{
		setController(objController);
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
	
	protected EditPartOfSpeechDialogModel getDialogModel()
	{
		return(getController().getDialogModel());
	}
	
	protected JTextField getPartOfSpeechField()
	{
		return((JTextField)(getBean("txtEditPartOfSpeechDialogPartOfSpeech")));
	}
	
	protected void setController(final EditPartOfSpeechDialogController objController)
	{
		mObjController = objController;
	}
	
	public void windowActivated(final WindowEvent objWindowEvent)
	{
		try
			{
			getPartOfSpeechField().setText(getDialogModel().getEditingModel().getPartOfSpeech());
			}
		catch(NotEditingException objException)
			{
			getPartOfSpeechField().setText(getDialogModel().getOriginalModel().getPartOfSpeech());
			
			getPartOfSpeechField().setEditable(false);
			}
		
		getPartOfSpeechField().requestFocusInWindow();
	}
	
	public void windowClosing(final WindowEvent objWindowEvent)
	{
		(new JButton(getController().getCancelAction())).doClick(20);
	}
}