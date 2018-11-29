package net.draconia.frenchstudy.dao;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import net.draconia.frenchstudy.exceptions.RequisiteTableNotExistingException;
import net.draconia.frenchstudy.exceptions.TableAlreadyExistsException;

public abstract class AbstractDAO<T> implements Serializable
{
	private static final long serialVersionUID = 1668823887267845327L;
	
	private Connection mObjConnection;
	private ConnectionUtils mObjConnectionUtils;
	
	public AbstractDAO(final ConnectionUtils objConnectionUtils)
	{
		setConnectionUtils(objConnectionUtils);
	}
	
	protected void closeConnection() throws SQLException
	{
		getConnectionUtils().closeConnection(getConnection());
		
		mObjConnection = null;
	}
	
	protected abstract T createObjectFromResults(final ResultSet objResults) throws SQLException;
	protected abstract List<T> createObjectListFromResults(final ResultSet objResults) throws SQLException;
	
	protected abstract boolean createTable() throws RequisiteTableNotExistingException, SQLException, TableAlreadyExistsException;
	
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
	
	protected abstract String getQueriedColumnsForSelect();
	
	protected abstract T insert(final T objToSave) throws SQLException;
	
	protected abstract boolean isTableExists() throws SQLException;
	
	protected abstract void removeTable() throws SQLException;
	
	protected void setConnectionUtils(final ConnectionUtils objConnectionUtils)
	{
		mObjConnectionUtils = objConnectionUtils;
	}
	
	protected abstract T update(final T objToSave) throws SQLException;
}