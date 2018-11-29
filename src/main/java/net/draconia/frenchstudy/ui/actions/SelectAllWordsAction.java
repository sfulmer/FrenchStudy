package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JList;

import net.draconia.ApplicationContextProvider;

import net.draconia.frenchstudy.model.Word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SelectAllWordsAction extends AbstractAction
{
	private static final long serialVersionUID = -7460169343716499317L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	public SelectAllWordsAction()
	{
		super("Select All");
		
		putValue(LONG_DESCRIPTION, "Selects all Words in the List");
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		putValue(SHORT_DESCRIPTION, "Selects all Words in the List");
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		getWordsList().clearSelection();
		getWordsList().addSelectionInterval(0, getWordsList().getModel().getSize() - 1);
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
	
	@SuppressWarnings("unchecked")
	protected JList<Word> getWordsList()
	{
		return((JList<Word>)(getBean("lstWords")));
	}
}