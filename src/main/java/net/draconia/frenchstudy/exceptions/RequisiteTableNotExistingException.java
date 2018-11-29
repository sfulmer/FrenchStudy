package net.draconia.frenchstudy.exceptions;

public class RequisiteTableNotExistingException extends Exception
{
	private static final long serialVersionUID = 8295516944636503627L;
	
	public RequisiteTableNotExistingException()
	{
		this(null, null);
	}
	
	public RequisiteTableNotExistingException(final String sTableName)
	{
		this(sTableName, null);
	}
	
	public RequisiteTableNotExistingException(final Throwable objThrowable)
	{
		this(null, objThrowable);
	}
	
	public RequisiteTableNotExistingException(final String sTableName, final Throwable objThrowable)
	{
		super("Requisite Table" + ((sTableName != null) ? "(" + sTableName + ")" : "") + " does not exist!", objThrowable);
	}
}