package net.draconia.frenchstudy.consumers;

import java.io.Serializable;

import java.util.function.Consumer;

import javax.swing.JButton;

import net.draconia.frenchstudy.ui.AbstractButtonPanel;

public class AbstractButtonPanelConsumer implements Consumer<JButton>, Serializable
{
	private static final long serialVersionUID = 3189065218627861626L;
	
	private AbstractButtonPanel mPnlButtons;
	
	public AbstractButtonPanelConsumer(final AbstractButtonPanel pnlButtons)
	{
		setButtonPanel(pnlButtons);
	}
	
	public void accept(final JButton btnThis)
	{
		getButtonPanel().add(btnThis);
	}
	
	protected AbstractButtonPanel getButtonPanel()
	{
		return(mPnlButtons);
	}
	
	protected void setButtonPanel(final AbstractButtonPanel pnlButtons)
	{
		mPnlButtons = pnlButtons;
	}
}