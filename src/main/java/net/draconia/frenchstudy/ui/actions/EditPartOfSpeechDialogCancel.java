package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import net.draconia.frenchstudy.ui.EditPartOfSpeechDialog;

public class EditPartOfSpeechDialogCancel extends AbstractAction
{
	private static final long serialVersionUID = 3368301149287168433L;
	
	private EditPartOfSpeechDialog mDlgEditPartOfSpeech;
	
	public EditPartOfSpeechDialogCancel(final EditPartOfSpeechDialog dlgEditPartOfSpeech)
	{
		super("Cancel");
		
		setDialog(dlgEditPartOfSpeech);
		
		putValue(LONG_DESCRIPTION, "Closes the window without saving...");
		putValue(MNEMONIC_KEY, KeyEvent.VK_C);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		if(JOptionPane.showConfirmDialog(getDialog(), "Are you sure you want to cancel?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
			getDialog().dispose();
	}
	
	protected EditPartOfSpeechDialog getDialog()
	{
		return(mDlgEditPartOfSpeech);
	}
	
	protected void setDialog(final EditPartOfSpeechDialog dlgEditPartOfSpeech)
	{
		mDlgEditPartOfSpeech = dlgEditPartOfSpeech;
	}
}