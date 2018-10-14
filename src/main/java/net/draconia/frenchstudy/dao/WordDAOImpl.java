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

import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.model.Word;

@Repository("WordDAO")
public class WordDAOImpl extends AbstractDAO<Word> implements WordDAO
{
	private static final long serialVersionUID = 5291812128492591276L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(WordDAOImpl.class);
	public static final String TableName = "Words";
	
	@Autowired
	private TableUtils mObjTableUtils;
	
	public WordDAOImpl(final ConnectionUtils objConnectionUtils)
	{
		super(objConnectionUtils);
	}
	
	protected Word createObjectFromResults(final ResultSet objResults) throws SQLException
	{
		int iId;
		String sEnglish;
		
		iId = objResults.getInt("Id");
		sEnglish = objResults.getString("English");
		
		return(new Word(iId, sEnglish));
	}
	
	protected List<Word> createObjectListFromResults(ResultSet objResults) throws SQLException
	{
		List<Word> lstWords = new ArrayList<Word>();
		
		while(objResults.next())
			lstWords.add(createObjectFromResults(objResults));
		
		return(lstWords);
	}
	
	protected boolean createTable() throws SQLException
	{
		try
			{
			if(!isTableExists())
				{
				PreparedStatement objStatement = getConnection().prepareStatement("create table Words (Id int auto_increment not null primary key, English varchar(50) not null);");
				
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
	
	public Word getByEnglishWord(final String sEnglish) throws SQLException, TableAlreadyExistsException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from Words where English = ?;");
			ResultSet objResults;
			
			objStatement.setString(1, sEnglish);
			
			objResults = objStatement.executeQuery();
			
			if(objResults.next())
				return(createObjectFromResults(objResults));
			else
				return(null);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error listing objects by English Word(" + sEnglish + ")...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public Word getById(final int iId) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from Words where Id = ?");
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
			msObjLogger.error("Error Getting Word By Id(" + iId + ")...", objException);
						
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected String getQueriedColumnsForSelect()
	{
		return("Id, English");
	}
	
	protected TableUtils getTableUtils()
	{
		return(mObjTableUtils);
	}
	
	protected Word insert(final Word objToSave) throws SQLException
	{
		PreparedStatement objStatement = getConnection().prepareStatement("insert into Words (English) values (?);", PreparedStatement.RETURN_GENERATED_KEYS);
		
		objStatement.setString(1, objToSave.getEnglish());
		
		if(objStatement.executeUpdate() == 1)
			{
			ResultSet objKeys = objStatement.getGeneratedKeys();
			
			if(objKeys.next())
				objToSave.setId(objKeys.getInt(1));
			
			return(objToSave);
			}
		else
			return(null);
	}
	
	protected boolean isTableExists() throws SQLException
	{
		return(getTableUtils().isTableExists(TableName));
	}

	public List<Word> list() throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from Words;");
			
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error creating list of all Words...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public List<Word> listByCategory(final Category objCategory) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement;
			String sSQL = "select " + getQueriedColumnsForSelect() + " from Words inner join WordInstances on WordInstances.Word = Words.Id amd WordInstances.Category ";

			if(objCategory == Category.EMPTY_CATEGORY)
				sSQL += " is null;";
			else
				sSQL += " = ?";
			
			objStatement = getConnection().prepareStatement(sSQL);
			
			if(objCategory != Category.EMPTY_CATEGORY)
				objStatement.setInt(1, objCategory.getId());
					
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error creating list of all Words...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public List<Word> listByPartOfSpeech(final PartOfSpeech objPartOfSpeech) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from Words inner join WordInstances on WordInstances.Word = Words.Id and WordInstances.PartOfSpeech = ?;");

			objStatement.setInt(1, objPartOfSpeech.getId());
			
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error creating list of all Words...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public List<Word> listBySlang(final boolean bSlang) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from Words inner join WordInstances on WordInstances.Word = Words.Id and WordInstances.Slang = ?;");

			objStatement.setBoolean(1, bSlang);
			
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error creating list of all Words...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public List<Word> listByCategoryAndPartOfSpeech(final Category objCategory, PartOfSpeech objPartOfSpeech) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement;
			String sSQL = "select " + getQueriedColumnsForSelect() + " from Words inner join WordInstances on WordInstances.Word = Words.Id and WordInstances.PartOfSpeech = ? and WordInstances.Category ";
			
			if(objCategory == Category.EMPTY_CATEGORY)
				sSQL += "is null;";
			else
				sSQL += "= ?;";
			
			objStatement = getConnection().prepareStatement(sSQL);
			
			objStatement.setInt(1, objPartOfSpeech.getId());
			
			if(objCategory != Category.EMPTY_CATEGORY)
				objStatement.setInt(2, objCategory.getId());
			
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error creating list of all Words...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public List<Word> listByCategoryAndSlang(final Category objCategory, final boolean bSlang) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement;
			String sSQL = "select " + getQueriedColumnsForSelect() + " from Words inner join WordInstances on WordInstances.Word = Words.Id and WordInstances.Slang = ? and WordInstances.Category ";
			
			if(objCategory == Category.EMPTY_CATEGORY)
				sSQL += "is null;";
			else
				sSQL += "= ?;";
			
			objStatement = getConnection().prepareStatement(sSQL);
			
			objStatement.setBoolean(1, bSlang);
			
			if(objCategory != Category.EMPTY_CATEGORY)
				objStatement.setInt(2, objCategory.getId());
			
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error creating list of all Words...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public List<Word> listByPartOfSpeechAndSlang(final PartOfSpeech objPartOfSpeech, final boolean bSlang) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from Words inner join WordInstances on WordInstances.Word = Words.Id and WordInstances.Slang = ? and WordInstances.PartOfSpeech = ?;");
			
			objStatement.setBoolean(1, bSlang);
			objStatement.setInt(2, objPartOfSpeech.getId());
			
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error creating list of all Words...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public List<Word> listByPartOfSpeechCategoryAndSlang(final PartOfSpeech objPartOfSpeech, final Category objCategory, final boolean bSlang) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement;
			String sSQL = "select " + getQueriedColumnsForSelect() + " from Words inner join WordInstances on WordInstances.Word = Words.Id and WordInstances.Slang = ? and WordInstances.PartOfSpeech = ? amd WordInstances.Category ";
			
			if(objCategory == Category.EMPTY_CATEGORY)
				sSQL += "is null;";
			else
				sSQL += "= ?;";
			
			objStatement = getConnection().prepareStatement(sSQL);
			
			objStatement.setBoolean(1, bSlang);
			objStatement.setInt(2, objPartOfSpeech.getId());
			
			if(objCategory != Category.EMPTY_CATEGORY)
				objStatement.setInt(3, objCategory.getId());
			
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error creating list of all Words...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public void remove(final Word objWord) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("delete from Words where Id = ?;");
			
			objStatement.setInt(1, objWord.getId());
			
			objStatement.executeUpdate();
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error removing Word with Id(" + objWord.getId() + ")", objException);
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected void removeTable() throws SQLException
	{
		getTableUtils().removeTable("Words");
	}
	
	public Word save(final Word objWord) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			if(objWord != null)
				{
				if(objWord.getId() <= 0)
					return(insert(objWord));
				else
					return(update(objWord));
				}
			else
				return(null);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error Saving Word with Id(" + objWord.getId() + ") to Database...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected Word update(final Word objToSave) throws SQLException
	{
		PreparedStatement objStatement = getConnection().prepareStatement("update Words set English = ? where Id = ?;");
		
		objStatement.setString(1, objToSave.getEnglish());
		objStatement.setInt(2, objToSave.getId());
		
		if(objStatement.executeUpdate() == 1)
			return(objToSave);
		else
			return(null);
	}
}