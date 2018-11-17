package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.controllers.DetailsPanelController;
import net.draconia.frenchstudy.ui.model.DetailsPanelModel;

@Component
public class NewAction extends AbstractAction
{
	private static final long serialVersionUID = 4920059316121496285L;
	
	@Autowired
	private DetailsPanelController mObjDetailsPanelController;
	@Autowired
	private DetailsPanelModel mObjDetailsPanelModel;
	
	public NewAction()
	{
		super("New");
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_N);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		getDetailsPanelModel().setEditingWord(getDetailsPanelController().newWord());
		
		getDetailsPanelModel().setEditing(getDetailsPanelModel().getEditingWord() != null);
	}
	
	protected DetailsPanelController getDetailsPanelController()
	{
		return(mObjDetailsPanelController);
	}
	
	protected DetailsPanelModel getDetailsPanelModel()
	{
		return(mObjDetailsPanelModel);
	}
}