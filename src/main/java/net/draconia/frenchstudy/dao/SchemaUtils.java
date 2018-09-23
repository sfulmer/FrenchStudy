package net.draconia.frenchstudy.dao;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class SchemaUtils implements Serializable
{
	private static final long serialVersionUID = -7085880410926085446L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(SchemaUtils.class);
	
	private Connection mObjConnection;
	
	@Autowired
	private ConnectionUtils mObjConnectionUtils;
	
	protected void closeConnection() throws SQLException
	{
		getConnectionUtils().closeConnection(getConnection());
		
		mObjConnection = null;
	}
	
	public void createSchema(final String sSchemaName) throws SQLException
	{
		PreparedStatement objStatement = getConnection().prepareStatement("create schema " + sSchemaName);
		
		objStatement.executeUpdate();
	}
	
	protected void finalize() throws Throwable
	{
		closeConnection();
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
	
	protected boolean isSchemaExists(final String sSchemaName) throws SQLException
	{
		try
			{
			DatabaseMetaData objMetaData = getConnection().getMetaData();
			ResultSet objResults = objMetaData.getCatalogs();
			
			while(objResults.next())
				if(objResults.getString("TABLE_CAT").equalsIgnoreCase(sSchemaName))
					return(true);
			
			return(false);
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
	
	public static Connection setDefaultSchema(final Connection objConnection, final String sSchemaName)
	{
		try
			{
			objConnection.setCatalog(sSchemaName);
			objConnection.setSchema(sSchemaName);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("There was a problem setting the default schema...", objException);
			}
		
		return(objConnection);
	}
}