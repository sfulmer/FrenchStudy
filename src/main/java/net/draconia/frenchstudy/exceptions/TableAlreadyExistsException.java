package net.draconia.frenchstudy.exceptions;

import java.io.Serializable;
 
import java.sql.SQLException;

public class TableAlreadyExistsException extends SQLException implements Serializable
{
	private static final long serialVersionUID = -6532033064210424955L;
	
	public TableAlreadyExistsException(final String sSchemaName)
	{
		super("This table already exists in '" + sSchemaName + "'");
	}
	
	public TableAlreadyExistsException(final String sSchemaName, final String sTableName)
	{
		super(sTableName + " already exists in '" + sSchemaName + "'");
	}
	
	public TableAlreadyExistsException(final String sSchemaName, final Throwable objCause)
	{
		super("This table already exists in '" + sSchemaName + "'", objCause);
	}
	
	public TableAlreadyExistsException(final String sSchemaName, final String sTableName, final Throwable objCause)
	{
		super(sTableName + " already exists in '" + sSchemaName + "'", objCause);
	}
}
