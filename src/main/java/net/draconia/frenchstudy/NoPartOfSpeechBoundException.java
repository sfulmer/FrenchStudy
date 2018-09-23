package net.draconia.frenchstudy;

import java.io.Serializable;

public class NoPartOfSpeechBoundException extends Exception implements Serializable
{	
	private static final long serialVersionUID = 7034733063963080563L;
	
	public NoPartOfSpeechBoundException()
	{
		super("No Part of Speech is bound to this Word Instance like it should");
	}
	
	public NoPartOfSpeechBoundException(final Throwable objCause)
	{
		super("No Part of Speech is bound to this Word Instance like it should", objCause);
	}
}