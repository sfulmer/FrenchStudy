package net.draconia.frenchstudy.ui.listeners.propertychange;

import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.ui.WordInstancesPanel;

@Component
public class WordInstancesEnabledPropertyChangeListener extends AbstractContainerPropertyChangeListener
{
	private static final long serialVersionUID = 8545885739787247232L;
	
	public WordInstancesEnabledPropertyChangeListener(final WordInstancesPanel objWordInstancesPanel)
	{
		super(objWordInstancesPanel);
	}
}