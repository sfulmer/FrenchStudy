package net.draconia.frenchstudy.ui;

import java.awt.FlowLayout;

import javax.annotation.PostConstruct;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.draconia.ApplicationContextProvider;
import net.draconia.frenchstudy.ui.listeners.propertychange.EnglishWordPanelPropertyChangeListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DetailsPanelEnglishWordPanel extends JPanel
{
	private static final long serialVersionUID = 3283852640107674891L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	@Autowired
	@Qualifier("lblEnglishWord")
	private JLabel mLblEnglish;
	@Autowired
	@Qualifier("txtEnglishWord")
	private JTextField mTxtEnglish;
	
	public DetailsPanelEnglishWordPanel()
	{
		super(new FlowLayout(FlowLayout.LEFT, 5, 5));
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected ApplicationContextProvider  getApplicationContextProvider()
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
	
	protected JTextField getEnglishWordField()
	{
		return(mTxtEnglish);
	}
	
	protected JLabel getEnglishWordLabel()
	{
		return(mLblEnglish);
	}
	
	protected EnglishWordPanelPropertyChangeListener getPropertyChangeListener()
	{
		return((EnglishWordPanelPropertyChangeListener)(getBean(EnglishWordPanelPropertyChangeListener.class)));
	}
	
	@PostConstruct
	protected void initPanel()
	{
		setEnabled(false);
		
		addPropertyChangeListener(getPropertyChangeListener());
		
		add(getEnglishWordLabel());
		add(getEnglishWordField());
	}
}