package net.draconia.frenchstudy.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import net.draconia.frenchstudy.exceptions.TableAlreadyExistsException;
import net.draconia.frenchstudy.model.PartOfSpeech;

@Repository("PartOfSpeechDAO")
public class PartOfSpeechDAOImpl extends AbstractDAO<PartOfSpeech> implements PartOfSpeechDAO
{
	private static final long serialVersionUID = -7801915324485975217L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(PartOfSpeechDAOImpl.class);
	public static final String TableName = "PartsOfSpeech";
	
	@Autowired
	private TableUtils mObjTableUtils;
	
	public PartOfSpeechDAOImpl(final ConnectionUtils objConnectionUtils)
	{
		super(objConnectionUtils);
	}
	
	protected PartOfSpeech createObjectFromResults(final ResultSet objResults) throws SQLException
	{
		int iId;
		String sPartOfSpeech;
		
		iId = objResults.getInt("Id");
		sPartOfSpeech = objResults.getString("PartOfSpeech");
		
		return(new PartOfSpeech(iId, sPartOfSpeech)); 
	}
	
	protected List<PartOfSpeech> createObjectListFromResults(final ResultSet objResults) throws SQLException
	{
		List<PartOfSpeech> lstPartsOfSpeech = new ArrayList<PartOfSpeech>();
		
		while(objResults.next())
			lstPartsOfSpeech.add(createObjectFromResults(objResults));
		
		return(lstPartsOfSpeech);
	}
	
	protected boolean createTable() throws SQLException
	{
		try
			{
			if(!isTableExists())
				{
				PreparedStatement objStatement = getConnection().prepareStatement("create table " + TableName + " (Id int auto_increment not null primary key, PartOfSpeech varchar(64) not null);");
				
				return(objStatement.executeUpdate() == 1);
				}
			else
				throw new TableAlreadyExistsException(TableUtils.SchemaName, TableName);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public PartOfSpeech getById(final int iId) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from " + TableName + " where Id = ?");
			ResultSet objResults;
			
			objStatement.setInt(1, iId);
			
			objResults = objStatement.executeQuery();
			
			if(objResults.next())
				return(createObjectFromResults(objResults));
			else
				return(null);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error Retrieving Part of Speech by its id(" + iId + ")", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public PartOfSpeech getByPartOfSpeech(final String sPartOfSpeech) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from " + TableName + " where PartOfSpeech = ?");
			ResultSet objResults;
			
			objStatement.setString(1, sPartOfSpeech);
			
			objResults = objStatement.executeQuery();
			
			if(objResults.next())
				return(createObjectFromResults(objResults));
			else
				return(null);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error Retrieving Part of Speech by its Part of Speech Text(" + sPartOfSpeech + ")", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected String getQueriedColumnsForSelect()
	{
		return("Id, PartOfSpeech");
	}
	
	protected TableUtils getTableUtils()
	{
		return(mObjTableUtils);
	}
	
	protected PartOfSpeech insert(final PartOfSpeech objToSave) throws SQLException
	{
		PreparedStatement objStatement = getConnection().prepareStatement("insert into " + TableName + " (PartOfSpeech) values (?);", PreparedStatement.RETURN_GENERATED_KEYS);
		
		objStatement.setString(1, objToSave.getPartOfSpeech());
		
		if(objStatement.executeUpdate() == 1)
			{
			ResultSet objKeys = objStatement.getGeneratedKeys();
			
			if(objKeys.next())
				objToSave.setId(objKeys.getInt(1));
			}
		
		return(objToSave);
	}
	
	protected boolean isTableExists() throws SQLException
	{
		return(getTableUtils().isTableExists(TableName));
	}
	
	public List<PartOfSpeech> list() throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from " + TableName + ";");
			
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error loading the list of all parts of speech...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public void remove(final PartOfSpeech objPartOfSpeech) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("delete from " + TableName + " where Id = ?;");
			
			objStatement.setInt(1, objPartOfSpeech.getId());
			
			objStatement.executeUpdate();
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error removing Part of Speech with Id (" + objPartOfSpeech.getId()+ ")", objException);
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected void removeTable() throws SQLException
	{
		getTableUtils().removeTable(TableName);
	}
	
	public PartOfSpeech save(final PartOfSpeech objPartOfSpeech) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			if(objPartOfSpeech != null)
				if(objPartOfSpeech.getId() <= 0)
					return(insert(objPartOfSpeech));
				else
					return(update(objPartOfSpeech));
			else
				return(null);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error Saving the Part of Speech to the Database...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected PartOfSpeech update(final PartOfSpeech objToSave) throws SQLException
	{
		PreparedStatement objStatement = getConnection().prepareStatement("insert " + TableName + " set PartOfSpeech = ? where Id = ?;");
		
		objStatement.setString(1, objToSave.getPartOfSpeech());
		objStatement.setInt(2, objToSave.getId());
		
		if(objStatement.executeUpdate() == 1)
			return(objToSave);
		else
			return(null);
	}
}