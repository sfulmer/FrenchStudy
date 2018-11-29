package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class DialogCancel extends AbstractAction
{
	private static final long serialVersionUID = 3368301149287168433L;
	
	private JDialog mDlgToClose;
	
	public DialogCancel(final JDialog dlgToClose)
	{
		super("Cancel");
		
		setDialog(dlgToClose);
		
		putValue(LONG_DESCRIPTION, "Closes the window without saving...");
		putValue(MNEMONIC_KEY, KeyEvent.VK_C);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		if(JOptionPane.showConfirmDialog(getDialog(), "Are you sure you want to cancel?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
			getDialog().dispose();
	}
	
	protected JDialog getDialog()
	{
		return(mDlgToClose);
	}
	
	protected void setDialog(final JDialog dlgToClose)
	{
		mDlgToClose = dlgToClose;
	}
}