package net.draconia.frenchstudy.dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class ConnectionUtils
{
	private List<Connection> mLstConnections;
	
	@Autowired
	private DataSource mObjDataSource;
	
	public void closeConnection(final Connection objConnection) throws SQLException
	{
		if(objConnection != null)
			{
			int iIndex = getConnections().indexOf(objConnection);
			
			if(iIndex >= 0)
				{
				getConnections().get(iIndex).close();
				getConnections().set(iIndex, null);
				}
			else
				objConnection.close();
			}
	}
	
	protected void finalize() throws Throwable
	{
		for(Connection objConnection : getConnections())
			closeConnection(objConnection);
	}
	
	public Connection getConnection() throws SQLException
	{
		return(getConnection(TableUtils.SchemaName));
	}
	
	@SuppressWarnings("resource")
	public Connection getConnection(final String sSchemaName) throws SQLException
	{
		Connection objConnection = null;
		
		for(int iConnectionLength = getConnections().size(), iConnectionLoop = 0; iConnectionLoop < iConnectionLength; iConnectionLoop++)
			{
			objConnection = getConnections().get(iConnectionLoop);
			
			if((objConnection == null) || ((objConnection != null) && (objConnection.isClosed())))
				{
				getConnections().set(iConnectionLoop, objConnection = getDataSource().getConnection());
				
				return(SchemaUtils.setDefaultSchema(objConnection, sSchemaName));
				}
			}
		
		objConnection = getDataSource().getConnection();
		
		getConnections().add(objConnection);
		
		return(SchemaUtils.setDefaultSchema(objConnection, sSchemaName));
	}
	
	protected List<Connection> getConnections()
	{
		if(mLstConnections == null)
			mLstConnections = new ArrayList<Connection>();
		
		return(mLstConnections);
	}
	
	protected DataSource getDataSource()
	{
		return(mObjDataSource);
	}
}