package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import net.draconia.frenchstudy.ui.EditWordInstanceDialog;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class CancelWordInstanceAction extends AbstractAction
{
	private static final long serialVersionUID = 924031173817950885L;
	
	@Autowired
	private EditWordInstanceDialog mDlgEditWordInstance;
	
	public CancelWordInstanceAction()
	{
		super("Cancel");
		
		putValue(LONG_DESCRIPTION, "Cancels editing this Word Instance and closes the dialog...");
		putValue(MNEMONIC_KEY, KeyEvent.VK_C);
		putValue(SHORT_DESCRIPTION, "Cancels editing this Word Instance and closes the dialog...");
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		if(JOptionPane.showConfirmDialog(getEditWordInstanceDialog(), "Are you sure?", "Cancel Word Instance Editing?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION)
			getEditWordInstanceDialog().dispose();
	}
	
	protected EditWordInstanceDialog getEditWordInstanceDialog()
	{
		return(mDlgEditWordInstance);
	}
}