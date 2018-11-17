package net.draconia.frenchstudy.exceptions;

public class NotEditingException extends Exception
{
	private static final long serialVersionUID = 4167081450964528880L;
	
	public NotEditingException()
	{
		this(null);
	}
	
	public NotEditingException(final Throwable objThrowable)
	{
		super("This is not being edited currently...", objThrowable);
	}
}