package net.draconia.frenchstudy.ui.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.actions.ExitAction;

@Component
public class MainWindowListener extends WindowAdapter
{
	@Autowired
	private ExitAction mActExit;
	
	protected ExitAction getExitAction()
	{
		return(mActExit);
	}
	
	public void windowClosing(final WindowEvent objWindowEvent)
	{
		new JButton(getExitAction()).doClick(20);
	}
}