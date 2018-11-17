package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.awt.Container;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.Serializable;

import java.util.Arrays;

import net.draconia.frenchstudy.consumers.AbstractContainerPropertyChangeListenerConsumer;

class AbstractContainerPropertyChangeListener implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = -4914993279607348729L;
	
	private Container mObjContainer;
	
	public AbstractContainerPropertyChangeListener(final Container objContainer)
	{
		setContainer(objContainer);
	}
	
	protected Container getContainer()
	{
		return(mObjContainer);
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		if(objPropertyChangeEvent.getPropertyName().equalsIgnoreCase("Enabled"))
			{
			final boolean bEnabled = ((Boolean)(objPropertyChangeEvent.getNewValue()));
			
			Arrays
				.asList(getContainer().getComponents())
					.stream()
						.forEach(new AbstractContainerPropertyChangeListenerConsumer(bEnabled));
			}
	}
	
	protected void setContainer(final Container objContainer)
	{
		mObjContainer = objContainer;
	}
}