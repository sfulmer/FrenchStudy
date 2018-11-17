package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.Serializable;

import java.util.Arrays;

import java.util.function.Consumer;

import javax.swing.JComponent;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.DetailsPanelEnglishWordPanel;

@Component
public class EnglishWordPanelPropertyChangeListener implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = -1357412598170800407L;
	
	@Autowired
	private DetailsPanelEnglishWordPanel mPnlEnglishWord;
	@Autowired
	@Qualifier("txtEnglishWord")
	private JTextField mTxtEnglishWord;
	
	protected JTextField getEnglishWordField()
	{
		return(mTxtEnglishWord);
	}
	
	protected DetailsPanelEnglishWordPanel getEnglishWordPanel()
	{
		return(mPnlEnglishWord);
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		if(objPropertyChangeEvent.getPropertyName().equalsIgnoreCase("Enabled"))
			{
			boolean bEnabled = ((Boolean)(objPropertyChangeEvent.getNewValue()));
			
			Arrays.asList(getEnglishWordPanel().getComponents())
				.stream()
					.forEach(new Consumer<Object>()
					{
						public void accept(final Object objComponent)
						{
							if(objComponent instanceof JComponent)
								((JComponent)(objComponent)).setEnabled(bEnabled);
						}
					});
			
			getEnglishWordField().requestFocusInWindow();
			}
	}
}