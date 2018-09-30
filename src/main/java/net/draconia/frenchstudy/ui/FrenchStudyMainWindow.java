package net.draconia.frenchstudy.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import net.draconia.ApplicationContextProvider;
import net.draconia.frenchstudy.ui.listeners.MainWindowListener;

@Component
public class FrenchStudyMainWindow extends JFrame
{
	private static final long serialVersionUID = 1700751983682358045L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContext;
	@Autowired
	private JSplitPane mPnlSplit;
	
	public FrenchStudyMainWindow()
	{
		super("French Study!");
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContext);
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	protected JSplitPane getSplitPane()
	{
		return(mPnlSplit);
	}
	
	protected void initControls()
	{
		add(getSplitPane(), BorderLayout.CENTER);
	}
	
	@PostConstruct
	protected void initFrame()
	{
		Dimension szScreen = getToolkit().getScreenSize();
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
		
		addWindowListener((MainWindowListener)(getBean(MainWindowListener.class)));
		
		initControls();
		
		pack();
		
		setLocation(new Point((int)((szScreen.getWidth() - getWidth())/2), (int)((szScreen.getHeight() - getHeight()) / 2)));
	}
	
	@Autowired
	public void setFont(final Font font)
	{
		super.setFont(font);
	}
	
	@Autowired
	public void setJMenuBar(final JMenuBar menubar)
	{
		super.setJMenuBar(menubar);
	}
}