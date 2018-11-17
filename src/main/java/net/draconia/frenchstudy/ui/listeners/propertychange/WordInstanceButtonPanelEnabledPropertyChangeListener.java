package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.Serializable;

import javax.swing.Action;

import net.draconia.frenchstudy.ui.actions.AddWordInstanceAction;
import net.draconia.frenchstudy.ui.actions.EditWordInstancesAction;
import net.draconia.frenchstudy.ui.actions.RemoveWordInstancesAction;

import net.draconia.frenchstudy.ui.model.DetailsPanelModel;
import net.draconia.frenchstudy.ui.model.DetailsPanelWordInstanceTableModel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class WordInstanceButtonPanelEnabledPropertyChangeListener implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = 129445942932683337L;
	
	@Autowired
	private AddWordInstanceAction mActAdd;
	@Autowired
	private DetailsPanelModel mObjDetailsPanelModel;
	@Autowired
	private DetailsPanelWordInstanceTableModel mObjTableModel;
	@Autowired
	private EditWordInstancesAction mActEdit;
	@Autowired
	private RemoveWordInstancesAction mActRemove;
	
	
	protected Action getAddAction()
	{
		return(mActAdd);
	}
	
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
	
	protected DetailsPanelWordInstanceTableModel getTableModel()
	{
		return(mObjTableModel);
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		if(objPropertyChangeEvent.getPropertyName().equalsIgnoreCase("Enabled"))
			{
			boolean bValue = ((Boolean)(objPropertyChangeEvent.getNewValue()));
			
			if(!bValue)
				{
				getAddAction().setEnabled(false);
				getEditAction().setEnabled(false);
				getRemoveAction().setEnabled(false);
				}
			else
				{
				getAddAction().setEnabled(getDetailsPanelModel().getEditingWord().getEnglish().length() > 0);
				getEditAction().setEnabled(getTableModel().countSelected() == 1);
				getRemoveAction().setEnabled(getTableModel().countSelected() >= 1);
				}
			}
	}
}