package net.draconia.frenchstudy.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.draconia.frenchstudy.model.Category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository("CategoryDAO")
public class CategoryDAOImpl extends AbstractDAO<Category> implements CategoryDAO {

	private static final long serialVersionUID = 7276997927550132848L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(CategoryDAOImpl.class);
	public static final String TableName = "Categories";
	
	@Autowired
	private TableUtils mObjTableUtils;
	
	public CategoryDAOImpl(final ConnectionUtils objConnectionUtils)
	{
		super(objConnectionUtils);
	}
	
	protected Category createObjectFromResults(final ResultSet objResults) throws SQLException
	{
		int iId;
		String sCategory;
		
		iId = objResults.getInt("Id");
		sCategory = objResults.getString("Category");
		
		return(new Category(iId, sCategory));
	}
	
	protected List<Category> createObjectListFromResults(final ResultSet objResults) throws SQLException
	{
		List<Category> lstCategories = new ArrayList<Category>();
		
		while(objResults.next())
			lstCategories.add(createObjectFromResults(objResults));
		
		return(lstCategories);
	}
	
	protected boolean createTable() throws SQLException
	{
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("create table " + TableName + " (Id int auto_increment not null primary key, Category varchar(64) not null);");
			
			return(objStatement.executeUpdate() == 1);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public Category getByCategory(final String sCategory) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from " + TableName + " where Category = ?;");
			ResultSet objResults;
			
			objStatement.setString(1, sCategory);
			
			objResults = objStatement.executeQuery();
			
			if(objResults.next())
				return(createObjectFromResults(objResults));
			else
				return(null);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error Loading Category by Category Name(" + sCategory + ")...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public Category getById(final int iId) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from " + TableName + " where Id = ?;");
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
			msObjLogger.error("Error Loading Category by Id(" + iId + ")...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected String getQueriedColumnsForSelect()
	{
		return("Id, Category");
	}
	
	protected TableUtils getTableUtils()
	{
		return(mObjTableUtils);
	}
	
	protected Category insert(final Category objToSave) throws SQLException
	{
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("insert into " + TableName + "(Category) values (?);", PreparedStatement.RETURN_GENERATED_KEYS);
			
			objStatement.setString(1, objToSave.getCategory());
			
			if(objStatement.executeUpdate() == 1)
				{
				ResultSet objKeys = objStatement.getGeneratedKeys();
				
				if(objKeys.next())
					objToSave.setId(objKeys.getInt(1));
				}
			
			return(objToSave);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("There was a problem inserting Category (" + objToSave.getCategory() + ") to the database...", objException);
			
			return(objToSave);
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected boolean isTableExists() throws SQLException
	{
		return(getTableUtils().isTableExists(TableName));
	}
	
	public List<Category> list() throws SQLException
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
			msObjLogger.error("There was a problem loading a list of all the Categories...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public void remove(final Category objCategory) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("delete from " + TableName + " where Id = ?;");
			
			objStatement.setInt(1, objCategory.getId());
			
			objStatement.executeUpdate();
			}
		catch(SQLException objException)
			{
			msObjLogger.error("There was a problem removing the Category with Id " + objCategory.getId() + "...", objException);
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
	
	public Category save(final Category objCategory) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			if(objCategory != null)
				if(objCategory.getId() <= 0)
					return(insert(objCategory));
				else
					return(update(objCategory));
			else
				return(null);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("There was a problem saving the Category to the database...", objException);
			
			return(null);
			}
	}
	
	protected Category update(final Category objToSave) throws SQLException
	{
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("update " + TableName + " set Category = ? where Id = ?;");
			
			objStatement.setString(1, objToSave.getCategory());
			objStatement.setInt(2, objToSave.getId());
			
			objStatement.executeUpdate();
			
			return(objToSave);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("There was a problem updating Category Id(" + objToSave.getId() + ") in the database...", objException);
			
			return(objToSave);
			}
		finally
			{
			closeConnection();
			}
	}
}