package net.draconia.frenchstudy.ui;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import net.draconia.ApplicationContextProvider;

@Component
public class EditWordInstanceButtonPanel extends AbstractButtonPanel
{
	private static final long serialVersionUID = -2233812426427712350L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	public EditWordInstanceButtonPanel
	(	final @Qualifier("btnEditWordInstanceApply") JButton btnApply
	,	final @Qualifier("btnEditWordInstanceSave") JButton btnSave
	,	final @Qualifier("btnEditWordInstanceCancel") JButton btnCancel
	)
	{
		super(Arrays.asList(new JButton[] {btnApply, btnSave, btnCancel}));
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
	
	@PostConstruct
	protected void initPanel()
	{
		super.initPanel();
		
		
	}
}