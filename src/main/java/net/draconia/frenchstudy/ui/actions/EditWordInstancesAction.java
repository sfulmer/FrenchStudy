package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.springframework.stereotype.Component;

@Component
public class EditWordInstancesAction extends AbstractAction
{
	private static final long serialVersionUID = -8662049414286993761L;
	
	public EditWordInstancesAction()
	{
		super("Edit");
		
		putValue(LONG_DESCRIPTION, "Edits selected Word Instance");
		putValue(SHORT_DESCRIPTION, "Edits selected Word Instance");
		
		setEnabled(false);
	}
	
	public void actionPerformed(ActionEvent objActionEvent)
	{ }
}