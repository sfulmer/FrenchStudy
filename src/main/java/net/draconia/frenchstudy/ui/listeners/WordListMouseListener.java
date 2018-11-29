package net.draconia.frenchstudy.ui.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import net.draconia.frenchstudy.ui.actions.EditAction;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class WordListMouseListener extends MouseAdapter
{
	@Autowired
	private EditAction mActEdit;
	
	protected EditAction getEditAction()
	{
		return(mActEdit);
	}
	
	public void mouseClicked(final MouseEvent objMouseEvent)
	{
		if(objMouseEvent.getClickCount() == 2)
			(new JButton(getEditAction())).doClick(20);
	}
}