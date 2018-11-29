package net.draconia.frenchstudy.ui.listeners;

import java.io.Serializable;

import javax.swing.Action;
import javax.swing.JList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.model.Word;

import net.draconia.frenchstudy.ui.actions.EditAction;
import net.draconia.frenchstudy.ui.actions.RemoveAction;

import net.draconia.frenchstudy.ui.model.DetailsPanelModel;

@Component
public class WordListSelectionListener implements ListSelectionListener, Serializable
{
	private static final long serialVersionUID = -9217723520405990580L;
	
	@Autowired
	private DetailsPanelModel mObjDetailsPanelModel;
	@Autowired
	private EditAction mActEdit;
	@Autowired
	private RemoveAction mActRemove;
	
	protected DetailsPanelModel getDetailsPanelModel()
	{
		return(mObjDetailsPanelModel);
	}
	
	protected Action getEditAction()
	{
		return(mActEdit);
	}
	
	protected Action getRemoveAction()
	{
		return(mActRemove);
	}
	
	@SuppressWarnings("unchecked")
	public void valueChanged(final ListSelectionEvent objListSelectionEvent)
	{
		JList<Word> lstWords = ((JList<Word>)(objListSelectionEvent.getSource()));
		
		if(lstWords.getSelectedIndices().length == 1)
			getDetailsPanelModel().setViewingWord(lstWords.getSelectedValue());
		else
			{
			getDetailsPanelModel().setViewingWord(null);
			}
		
		getEditAction().setEnabled(lstWords.getSelectedIndices().length == 1);
		getRemoveAction().setEnabled(lstWords.getSelectedIndices().length >= 1);
	}
}