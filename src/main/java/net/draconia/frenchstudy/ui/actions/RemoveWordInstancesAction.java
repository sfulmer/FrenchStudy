package net.draconia.frenchstudy.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.springframework.stereotype.Component;

@Component
public class RemoveWordInstancesAction extends AbstractAction
{
	private static final long serialVersionUID = -8662049414286993761L;
	
	public RemoveWordInstancesAction()
	{
		super("Remove");
		
		putValue(LONG_DESCRIPTION, "Removes selected Word Instance");
		putValue(SHORT_DESCRIPTION, "Removes selected Word Instance");
		
		setEnabled(false);
	}
	
	public void actionPerformed(ActionEvent objActionEvent)
	{ }
}