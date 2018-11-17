package net.draconia.frenchstudy.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.annotation.PostConstruct;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import net.draconia.ApplicationContextProvider;
import net.draconia.frenchstudy.ui.listeners.propertychange.DetailsPanelEnabledPropertyChangeListener;

@Component
public class DetailsPanel extends JPanel
{
	private static final long serialVersionUID = -5634692433905903319L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	@Autowired
	@Qualifier("pnlEnglishWord")
	private JPanel mPnlEnglishWord;
	
	@Autowired
	private WordInstancesPanel mPnlWordInstances;
	
	DetailsPanel()
	{
		super(new BorderLayout(5, 5));
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	protected DetailsPanelEnabledPropertyChangeListener getEnabledPropertyChangeListener()
	{
		return((DetailsPanelEnabledPropertyChangeListener)(getBean(DetailsPanelEnabledPropertyChangeListener.class)));
	}
	
	protected JPanel getEnglishWordPanel()
	{
		return(mPnlEnglishWord);
	}
	
	protected JPanel getWordInstancesPanel()
	{
		return(mPnlWordInstances);
	}
	
	@PostConstruct
	protected void initPanel()
	{
		setEnabled(false);
		
		addPropertyChangeListener(getEnabledPropertyChangeListener());
		
		add(getEnglishWordPanel(), BorderLayout.NORTH);
		add(getWordInstancesPanel(), BorderLayout.CENTER);
	}
	
	@Autowired
	public void setFont(final Font font)
	{
		super.setFont(font);
	}
}