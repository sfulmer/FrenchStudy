package net.draconia.frenchstudy;

import java.io.Serializable;

public class NoWordBoundException extends Exception implements Serializable
{	
	private static final long serialVersionUID = 7034733063963080563L;
	
	public NoWordBoundException()
	{
		super("No word is bound to this Word Instance like it should");
	}
	
	public NoWordBoundException(final Throwable objCause)
	{
		super("No word is bound to this Word Instance like it should", objCause);
	}
}