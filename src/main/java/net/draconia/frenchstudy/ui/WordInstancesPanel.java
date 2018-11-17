package net.draconia.frenchstudy.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.annotation.PostConstruct;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.listeners.propertychange.WordInstancesEnabledPropertyChangeListener;

@Component
public class WordInstancesPanel extends JPanel
{
	private static final long serialVersionUID = 4395548220750539669L;
	
	@Autowired
	private DetailsPanelWordInstancesButtonPanel mPnlButtons;
	@Autowired
	@Qualifier("scrWordInstances")
	private JScrollPane mScrWordInstances;
	@Autowired
	private WordInstancesEnabledPropertyChangeListener mObjWordInstancesEnabledPropertyChangeListener;
	
	public WordInstancesPanel()
	{
		super(new BorderLayout(5, 5));
	}
	
	protected JPanel getButtonPanel()
	{
		return(mPnlButtons);
	}
	
	protected WordInstancesEnabledPropertyChangeListener getPropertyChangeListener()
	{
		return(mObjWordInstancesEnabledPropertyChangeListener);
	}
	
	protected JScrollPane getWordInstancesScrollPane()
	{
		return(mScrWordInstances);
	}
	
	@PostConstruct
	protected void initPanel()
	{
		setEnabled(false);
		
		addPropertyChangeListener(getPropertyChangeListener());
		
		add(getWordInstancesScrollPane(), BorderLayout.CENTER);
		add(getButtonPanel(), BorderLayout.SOUTH);
		add(new JPanel(), BorderLayout.EAST);
		add(new JPanel(), BorderLayout.WEST);
	}
	
	@Autowired
	public void setFont(final Font font)
	{
		super.setFont(font);
	}
}