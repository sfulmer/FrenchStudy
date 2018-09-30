package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;

import javax.swing.AbstractAction;

import org.springframework.stereotype.Component;

@Component
public class RemoveAction extends AbstractAction
{
	private static final long serialVersionUID = 7742972342861208091L;
	
	public RemoveAction()
	{
		super("Remove");
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_R);
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