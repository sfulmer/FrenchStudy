package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.DetailsPanelEnglishWordPanel;
import net.draconia.frenchstudy.ui.WordInstancesPanel;

@Component
public class DetailsPanelEnabledPropertyChangeListener implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = 4840528916085542182L;
	
	@Autowired
	private DetailsPanelEnglishWordPanel mPnlEnglishWord;
	@Autowired
	private WordInstancesPanel mPnlWordInstances;
	
	protected DetailsPanelEnglishWordPanel getEnglishWordPanel()
	{
		return(mPnlEnglishWord);
	}
	
	protected WordInstancesPanel getWOrdInstancesPanel()
	{
		return(mPnlWordInstances);
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		if(objPropertyChangeEvent.getPropertyName().equalsIgnoreCase("Enabled"))
			{
			boolean bEnabled = ((Boolean)(objPropertyChangeEvent.getNewValue()));
			
			getEnglishWordPanel().setEnabled(bEnabled);
			getWOrdInstancesPanel().setEnabled(bEnabled);
			}
	}
}