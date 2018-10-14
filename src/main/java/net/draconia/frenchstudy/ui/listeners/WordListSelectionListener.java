package net.draconia.frenchstudy.ui.listeners;

import java.io.Serializable;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.model.DetailsPanelModel;

@Component
public class WordListSelectionListener implements ListSelectionListener, Serializable
{
	private static final long serialVersionUID = -9217723520405990580L;
	
	@Autowired
	private DetailsPanelModel mObjDetailsPanelModel;
	
	protected DetailsPanelModel getDetailsPanelModel()
	{
		return(mObjDetailsPanelModel);
	}
	
	public void valueChanged(final ListSelectionEvent objListSelectionEvent)
	{
		
	}
}