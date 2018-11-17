package net.draconia.frenchstudy.ui;

import java.util.Arrays;

import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import net.draconia.ApplicationContextProvider;
import net.draconia.frenchstudy.ui.listeners.propertychange.WordInstanceButtonPanelEnabledPropertyChangeListener;

@Component
public class DetailsPanelWordInstancesButtonPanel extends AbstractButtonPanel
{
	private static final long serialVersionUID = -6350342829629677709L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	public DetailsPanelWordInstancesButtonPanel
			(	final @Qualifier("btnWordInstancesAdd") JButton btnAdd
			,	final @Qualifier("btnWordInstancesEdit") JButton btnEdit
			,	final @Qualifier("btnWordInstancesRemove") JButton btnRemove)
	{
		super(Arrays.asList(new JButton[] {btnAdd, btnEdit, btnRemove}));
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
	
	protected WordInstanceButtonPanelEnabledPropertyChangeListener getPropertyChangeListener()
	{
		return((WordInstanceButtonPanelEnabledPropertyChangeListener)(getBean(WordInstanceButtonPanelEnabledPropertyChangeListener.class)));
	}
	
	protected void initPanel()
	{
		super.initPanel();
		
		addPropertyChangeListener(getPropertyChangeListener());
	}
}