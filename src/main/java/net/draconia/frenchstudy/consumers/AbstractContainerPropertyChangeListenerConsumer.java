package net.draconia.frenchstudy.consumers;

import java.awt.Component;

import java.io.Serializable;

import java.util.function.Consumer;

import javax.swing.JComponent;

public class AbstractContainerPropertyChangeListenerConsumer implements Consumer<Component>, Serializable
{
	private static final long serialVersionUID = 8887517182854457538L;
	
	private Boolean mbEnabled;
	
	public AbstractContainerPropertyChangeListenerConsumer(final Boolean bEnabled)
	{
		setEnabled(bEnabled);
	}
	
	public void accept(Component objComponent)
	{
		if(objComponent instanceof JComponent)
			((JComponent)(objComponent)).setEnabled(isEnabled());
	}
	
	protected boolean isEnabled()
	{
		if(mbEnabled == null)
			mbEnabled = false;
		
		return(mbEnabled);
	}
	
	protected void setEnabled(final Boolean bEnabled)
	{
		if(bEnabled == null)
			mbEnabled = false;
		else
			mbEnabled = bEnabled;
	}
}