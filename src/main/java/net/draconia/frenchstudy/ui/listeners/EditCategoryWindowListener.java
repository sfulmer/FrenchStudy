package net.draconia.frenchstudy.ui.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

import net.draconia.FrenchStudy;

import net.draconia.frenchstudy.exceptions.NotEditingException;

import net.draconia.frenchstudy.ui.controllers.EditCategoryDialogController;
import net.draconia.frenchstudy.ui.model.EditCategoryDialogModel;

import org.springframework.context.ApplicationContext;

public class EditCategoryWindowListener extends WindowAdapter
{
	private EditCategoryDialogController mObjController;
	
	public EditCategoryWindowListener(final EditCategoryDialogController objController)
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
	
	protected EditCategoryDialogController getController()
	{
		return(mObjController);
	}
	
	protected EditCategoryDialogModel getDialogModel()
	{
		return(getController().getDialogModel());
	}
	
	protected JTextField getCategoryField()
	{
		return((JTextField)(getBean("txtEditCategoryDialogCategory")));
	}
	
	protected void setController(final EditCategoryDialogController objController)
	{
		mObjController = objController;
	}
	
	public void windowActivated(final WindowEvent objWindowEvent)
	{
		try
			{
			getCategoryField().setText(getDialogModel().getEditingModel().getCategory());
			}
		catch(NotEditingException objException)
			{
			getCategoryField().setText(getDialogModel().getOriginalModel().getCategory());
			
			getCategoryField().setEditable(false);
			}
		
		getCategoryField().requestFocusInWindow();
	}
	
	public void windowClosing(final WindowEvent objWindowEvent)
	{
		(new JButton(getController().getCancelAction())).doClick(20);
	}
}