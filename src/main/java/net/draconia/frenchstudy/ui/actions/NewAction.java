package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import org.springframework.stereotype.Component;

@Component
public class NewAction extends AbstractAction
{
	private static final long serialVersionUID = 4920059316121496285L;
	
	public NewAction()
	{
		super("New");
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_N);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		
	}
}