package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.controllers.EditWordInstanceDialogController;
import net.draconia.frenchstudy.ui.model.EditWordInstanceDialogModel;

@Component
public class ApplyWordInstanceAction extends AbstractAction
{
	private static final long serialVersionUID = 5084839965781666910L;
	private Logger msObjLogger = LoggerFactory.getLogger(ApplyWordInstanceAction.class);
	
	@Autowired
	private EditWordInstanceDialogController mObjController;
	
	@Autowired
	private EditWordInstanceDialogModel mObjModel;
	
	public ApplyWordInstanceAction()
	{
		super("Apply...");
		
		putValue(LONG_DESCRIPTION, "Persists the Word Instance...");
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		putValue(SHORT_DESCRIPTION, "Persists the Word Instance...");
		
		setEnabled(false);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		try
			{
			getController().save(getModel().getEditingModel());
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error Saving/Persisting Word Instance to the database...", objException);
			}
	}
	
	protected EditWordInstanceDialogController getController()
	{
		return(mObjController);
	}
	
	protected EditWordInstanceDialogModel getModel()
	{
		return(mObjModel);
	}
}