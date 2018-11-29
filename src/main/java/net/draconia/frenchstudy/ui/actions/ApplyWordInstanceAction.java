package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.sql.SQLException;

import javax.swing.AbstractAction;

import net.draconia.frenchstudy.exceptions.NoPartOfSpeechBoundException;
import net.draconia.frenchstudy.exceptions.NoWordBoundException;
import net.draconia.frenchstudy.model.WordInstance;

import net.draconia.frenchstudy.ui.controllers.EditWordInstanceDialogController;

import net.draconia.frenchstudy.ui.model.EditWordInstanceDialogModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

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
		WordInstance objEditingModel = getModel().getEditingModel(), objOriginalModel = getModel().getOriginalModel();
		
		try
			{
			getController().save(objEditingModel);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error Saving/Persisting Word Instance to the database...", objException);
			}
		
		try
			{
			objOriginalModel.setCategory(objEditingModel.getCategory());
			objOriginalModel.setDefinition(objEditingModel.getDefinition());
			objOriginalModel.setId(objEditingModel.getId());
			objOriginalModel.setPartOfSpeech(objEditingModel.getPartOfSpeech());
			objOriginalModel.setSlang(objEditingModel.isSlang());
			objOriginalModel.setWord(objEditingModel.getWord());
			}
		catch(NoPartOfSpeechBoundException | NoWordBoundException objException)
			{ } // No reason for any exception to be thrown here!
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