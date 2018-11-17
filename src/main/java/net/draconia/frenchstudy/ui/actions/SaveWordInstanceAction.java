package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.EditWordInstanceDialog;

@Component
public class SaveWordInstanceAction extends AbstractAction
{
	private static final long serialVersionUID = 5084839965781666910L;
	
	@Autowired
	private ApplyWordInstanceAction mActApply;
	@Autowired
	private EditWordInstanceDialog mDlgEditWordInstance;
	
	public SaveWordInstanceAction()
	{
		super("Save");
		
		putValue(LONG_DESCRIPTION, "Applies changes to this Word Instance and closes the dialog...");
		putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		putValue(SHORT_DESCRIPTION, "Applies changes to this Word Instance and closes the dialog...");
		
		setEnabled(false);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		new JButton(getApplyAction()).doClick(20);
		
		getEditWordInstanceDialog().dispose();
	}
	
	protected ApplyWordInstanceAction getApplyAction()
	{
		return(mActApply);
	}
	
	protected EditWordInstanceDialog getEditWordInstanceDialog()
	{
		return(mDlgEditWordInstance);
	}
}