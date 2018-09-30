package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;

import javax.swing.AbstractAction;

import org.springframework.stereotype.Component;

@Component
public class EditAction extends AbstractAction
{
	private static final long serialVersionUID = 3099384366640740397L;
	
	public EditAction()
	{
		super("Edit");
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_E);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		
	}
	
	@PostConstruct
	protected void init()
	{
		setEnabled(false);
	}
}