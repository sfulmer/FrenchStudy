package net.draconia.frenchstudy.ui;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.draconia.frenchstudy.consumers.AbstractButtonPanelConsumer;

public abstract class AbstractButtonPanel extends JPanel
{
	private static final long serialVersionUID = -8003668733816280933L;
	
	private List<JButton> mLstButtons;
	
	public AbstractButtonPanel(final List<JButton> lstButtons)
	{
		super(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		setButtons(lstButtons);
	}
	
	public List<JButton> getButtons()
	{
		if(mLstButtons == null)
			mLstButtons = new ArrayList<JButton>();
		
		return(mLstButtons);
	}
	
	@PostConstruct
	protected void initPanel()
	{
		setEnabled(false);
		
		getButtons()
			.stream()
				.forEach(new AbstractButtonPanelConsumer(this));
	}
	
	public void setButtons(final List<JButton> lstButtons)
	{
		if(lstButtons == null)
			mLstButtons = new ArrayList<JButton>();
		else
			mLstButtons = lstButtons;
		
		invalidate();
	}
	
	
}