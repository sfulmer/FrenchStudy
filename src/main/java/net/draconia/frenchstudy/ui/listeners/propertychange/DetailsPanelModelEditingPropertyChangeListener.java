package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.DetailsPanel;
import net.draconia.frenchstudy.ui.model.DetailsPanelModel;
import net.draconia.utilities.TextUtilities;

@Component
public class DetailsPanelModelEditingPropertyChangeListener implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = -5906246129285696946L;
	
	@Autowired
	private DetailsPanel mPnlDetails;
	
	@Autowired
	@Qualifier("txtEnglishWord")
	private JTextField mTxtEnglishWord;
	
	@Autowired
	private TextUtilities mObjTextUtilities;
	
	protected DetailsPanel getDetailsPanel()
	{
		return(mPnlDetails);
	}
	
	protected JTextField getEnglishWordField()
	{
		return(mTxtEnglishWord);
	}
	
	protected TextUtilities getTextUtilities()
	{
		return(mObjTextUtilities);
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		if(objPropertyChangeEvent.getPropertyName().equalsIgnoreCase("Editing"))
			{
			boolean bEditing = ((Boolean)(objPropertyChangeEvent.getNewValue()));
			DetailsPanelModel objModel = ((DetailsPanelModel)(objPropertyChangeEvent.getSource()));
			
			getDetailsPanel().setEnabled(bEditing);
			
			if(bEditing)
				getTextUtilities().setupUnRedoableBiDirectionalBoundTextComponent(getEnglishWordField(), objModel.getEditingWord(), "English", String.class);
			else
				getTextUtilities().resetTextComponent(getEnglishWordField());
			}
	}
}