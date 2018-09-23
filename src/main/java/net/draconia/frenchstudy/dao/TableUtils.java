package net.draconia.frenchstudy.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class TableUtils
{
	public static final String SchemaName = "FrenchStudy";
	
	private Connection mObjConnection;
	
	@Autowired
	private ConnectionUtils mObjConnectionUtils;
	
	@Autowired
	private SchemaUtils mObjSchemaUtils;
	
	protected void closeConnection() throws SQLException
	{
		getConnectionUtils().closeConnection(getConnection());
		
		mObjConnection = null;
	}
	
	protected void createSchema() throws SQLException
	{
		getSchemaUtils().createSchema(SchemaName);
		
		SchemaUtils.setDefaultSchema(getConnection(), SchemaName);
	}
	
	protected Connection getConnection() throws SQLException
	{
		if(mObjConnection == null)
			mObjConnection = getConnectionUtils().getConnection();
		
		return(mObjConnection);
	}
	
	protected ConnectionUtils getConnectionUtils()
	{
		return(mObjConnectionUtils);
	}
	
	protected SchemaUtils getSchemaUtils()
	{
		return(mObjSchemaUtils);
	}
	
	protected boolean isSchemaExists() throws SQLException
	{
		return(getSchemaUtils().isSchemaExists(SchemaName));
	}
	
	public boolean isTableExists(final String sTableName) throws SQLException
	{
		try
			{
			if(!isSchemaExists())
				createSchema();
			
			DatabaseMetaData objMetaData = getConnection().getMetaData();
			
			return(objMetaData.getTables(null, null, sTableName, new String[] {"TABLE"}).next());
			}
		catch(SQLException objException)
			{
			objException.printStackTrace(System.err);
			
			return(false);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public void removeTable(final String sTableName) throws SQLException
	{
		PreparedStatement objStatement = getConnection().prepareStatement("drop table " + sTableName);
		
		if(!isSchemaExists())
			createSchema();
		
		objStatement.executeUpdate();
	}
}