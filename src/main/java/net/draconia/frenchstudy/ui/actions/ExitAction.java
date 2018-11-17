package net.draconia.frenchstudy.ui.actions;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.FrenchStudyMainWindow;

@Component
public class ExitAction extends AbstractAction
{
	private static final long serialVersionUID = 3566821316000424261L;
	
	@Autowired
	private FrenchStudyMainWindow mWndMain;
	
	public ExitAction()
	{
		super("Exit");
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		putValue(LONG_DESCRIPTION, "Exits the Application");
		putValue(MNEMONIC_KEY, KeyEvent.VK_X);
		putValue(SHORT_DESCRIPTION, "Exits the Application");
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		if(JOptionPane.showConfirmDialog(getMainWindow(), "Are you sure you wish to exit?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
			{
			getMainWindow().dispose();
			System.exit(0);
			}
	}
	
	protected Window getMainWindow()
	{
		return(mWndMain);
	}
}