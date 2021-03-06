package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;

import javax.swing.AbstractAction;
import javax.swing.JList;

import net.draconia.ApplicationContextProvider;

import net.draconia.frenchstudy.model.Word;

import net.draconia.frenchstudy.ui.controllers.DetailsPanelController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class EditAction extends AbstractAction
{
	private static final long serialVersionUID = 3099384366640740397L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	@Autowired
	private DetailsPanelController mObjDetailsPanelController;
	
	public EditAction()
	{
		super("Edit");
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_E);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		getDetailsPanelController().edit(getWordList().getSelectedValue());
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
	
	protected DetailsPanelController getDetailsPanelController()
	{
		return(mObjDetailsPanelController);
	}
	
	@SuppressWarnings("unchecked")
	protected JList<Word> getWordList()
	{
		return((JList<Word>)(getBean("lstWords")));
	}
	
	@PostConstruct
	protected void init()
	{
		setEnabled(false);
	}
}