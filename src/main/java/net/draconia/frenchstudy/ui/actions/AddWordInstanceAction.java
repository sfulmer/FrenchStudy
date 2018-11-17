package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import net.draconia.frenchstudy.exceptions.NoPartOfSpeechBoundException;
import net.draconia.frenchstudy.exceptions.NoWordBoundException;
import net.draconia.frenchstudy.model.WordInstance;
import net.draconia.frenchstudy.ui.controllers.EditWordInstanceDialogController;
import net.draconia.frenchstudy.ui.model.DetailsPanelModel;
import net.draconia.frenchstudy.ui.model.EditWordInstanceDialogModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddWordInstanceAction extends AbstractAction
{
	private static final long serialVersionUID = 3800422935189972576L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(AddWordInstanceAction.class);
	
	@Autowired
	private DetailsPanelModel mOBjDetailsPanelModel;
	@Autowired
	private EditWordInstanceDialogController mObjEditWordInstanceDialogController;
	@Autowired
	private EditWordInstanceDialogModel mObjEditWordInstanceDialogModel;
	
	public AddWordInstanceAction()
	{
		super("Add");
		
		putValue(LONG_DESCRIPTION, "Adds a new Word Instance...");
		putValue(SHORT_DESCRIPTION, "Adds a new Word Instance...");
		
		setEnabled(false);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		try {
			getEditWordInstanceDialogModel().setEditingModel(new WordInstance(getDetailsPanelModel().getEditingWord()));
			
			getEditWordInstanceDialogController().showDialog();
			}
		catch(NoPartOfSpeechBoundException | NoWordBoundException objException)
			{
			msObjLogger.error("There was a problem setting the new Editing Dialog Model for Word Instances...", objException);
			}
	}
	
	protected DetailsPanelModel getDetailsPanelModel()
	{
		return(mOBjDetailsPanelModel);
	}
	
	protected EditWordInstanceDialogController getEditWordInstanceDialogController()
	{
		return(mObjEditWordInstanceDialogController);
	}
	
	protected EditWordInstanceDialogModel getEditWordInstanceDialogModel()
	{
		return(mObjEditWordInstanceDialogModel);
	}
}