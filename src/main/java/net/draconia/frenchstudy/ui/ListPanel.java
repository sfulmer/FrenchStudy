package net.draconia.frenchstudy.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ListPanel extends JPanel
{
	private static final long serialVersionUID = -5634692433905903319L;
	
	@Autowired
	@Qualifier("lblWords")
	private JLabel mLblWords;
	@Autowired
	@Qualifier("scrWords")
	private JScrollPane mScrWords;
	@Autowired
	private ListPanelButtonPanel mPnlButtons;
	
	public ListPanel()
	{
		super(new BorderLayout(5, 5));
	}
	
	protected ListPanelButtonPanel getButtonsPanel()
	{
		return(mPnlButtons);
	}
	
	protected JLabel getWordsLabel()
	{
		return(mLblWords);
	}
	
	protected JScrollPane getWordsScrollPane()
	{
		return(mScrWords);
	}
	
	@PostConstruct
	protected void initPanel()
	{
		add(getWordsLabel(), BorderLayout.NORTH);
		add(getWordsScrollPane(), BorderLayout.CENTER);
		add(getButtonsPanel(), BorderLayout.SOUTH);
	}
	
	@Autowired
	public void setFont(final Font font)
	{
		super.setFont(font);
	}
}