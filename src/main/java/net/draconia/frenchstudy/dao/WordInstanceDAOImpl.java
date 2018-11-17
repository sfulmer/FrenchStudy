package net.draconia.frenchstudy.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import net.draconia.frenchstudy.exceptions.NoPartOfSpeechBoundException;
import net.draconia.frenchstudy.exceptions.NoWordBoundException;
import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.model.Word;
import net.draconia.frenchstudy.model.WordInstance;

@Repository("WordInstanceDAO")
public class WordInstanceDAOImpl extends AbstractDAO<WordInstance> implements WordInstanceDAO
{
	private static final long serialVersionUID = -5948774576768447937L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(WordInstanceDAOImpl.class);
	public static final String TableName = "WordInstances";
	
	@Autowired
	private CategoryDAO mObjCategoryDAO;
	
	@Autowired
	private PartOfSpeechDAO mObjPartOfSpeechDAO;
	
	@Autowired
	private TableUtils mObjTableUtils;
	
	@Autowired
	private WordDAO mObjWordDAO;
	
	public WordInstanceDAOImpl(final ConnectionUtils objConnectionUtils)
	{
		super(objConnectionUtils);
	}
	
	protected WordInstance createObjectFromResults(final ResultSet objResults) throws SQLException
	{
		try
			{
			boolean bSlang;
			Category objCategory;
			int iCategoryId, iId, iPartOfSpeechId, iWordId;
			PartOfSpeech objPartOfSpeech;
			String sDefinition;
			Word objWord;
			
			bSlang = objResults.getBoolean("Slang");
			iCategoryId = objResults.getInt("Category");
			iId = objResults.getInt("Id");
			iPartOfSpeechId = objResults.getInt("PartOfSpeech");
			iWordId = objResults.getInt("Word");
			sDefinition = objResults.getString("Definition");
			
			objCategory = getCategoryDAO().getById(iCategoryId);
			objPartOfSpeech = getPartOfSpeechDAO().getById(iPartOfSpeechId);
			objWord = getWordDAO().getById(iWordId);
			
			return(new WordInstance(iId, objWord, objPartOfSpeech, objCategory, sDefinition, bSlang));
			}
		catch(NoPartOfSpeechBoundException | NoWordBoundException objException)
			{
			msObjLogger.error("Eror creating Word Instance from Query Results...", objException);
			
			return(null);
			}
	}
	
	protected List<WordInstance> createObjectListFromResults(final ResultSet objResults) throws SQLException
	{
		List<WordInstance> lstWordInstances = new ArrayList<WordInstance>();
		
		while(objResults.next())
			lstWordInstances.add(createObjectFromResults(objResults));
		
		return(lstWordInstances);
	}
	
	protected boolean createTable() throws SQLException
	{
		try
			{
			if(!isTableExists())
				{
				PreparedStatement objStatement = getConnection().prepareStatement("create table " + TableName + "(Id int auto_increment not null primary key, Word int not null, PartOfSpeech int not null, Category int, Definition varchar(5000) default ' ' not null, Slang boolean default 0 not null, foreign key (Word) references Words(Id), foreign key (PartOfSpeech) references PartsOfSpeech(Id), foreign key (Category) references Categories(Id), unique key(Word, PartOfSpeech, Category, Slang));");
				
				return(objStatement.executeUpdate() == 1);
				}
			else
				throw new TableAlreadyExistsException(TableName);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public WordInstance getById(final int iId) throws SQLException
	{
		try
			{
			PreparedStatement objStatement;
			ResultSet objResults;
			
			if(!isTableExists())
				createTable();
			
			objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from " + TableName + " where Id = ?;");
			
			objStatement.setInt(1, iId);
			
			objResults = objStatement.executeQuery();
			
			if(objResults.next())
				return(createObjectFromResults(objResults));
			else
				return(null);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error getting Word Instance by its Id(" + iId + ")...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected CategoryDAO getCategoryDAO()
	{
		return(mObjCategoryDAO);
	}
	
	protected PartOfSpeechDAO getPartOfSpeechDAO()
	{
		return(mObjPartOfSpeechDAO);
	}
	
	protected String getQueriedColumnsForSelect()
	{
		return("Id, Word, PartOfSpeech, Category, Definition, Slang");
	}
	
	protected TableUtils getTableUtils()
	{
		return(mObjTableUtils);
	}
	
	protected WordDAO getWordDAO()
	{
		return(mObjWordDAO);
	}
	
	protected WordInstance insert(WordInstance objToSave) throws SQLException
	{
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("insert into " + TableName + "(Word, PartOfSpeech, Category, Definition, Slang) values(?, ?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
			
			if(objToSave.getWord().getId() <= 0)
				objToSave.getWord().setId(getWordDAO().save(objToSave.getWord()).getId());
			
			objStatement.setInt(1, objToSave.getWord().getId());
			
			if(objToSave.getPartOfSpeech().getId() <= 0)
				objToSave.getPartOfSpeech().setId(getPartOfSpeechDAO().save(objToSave.getPartOfSpeech()).getId());
			
			objStatement.setInt(2, objToSave.getPartOfSpeech().getId());
			
			if(objToSave.getCategory() != null)
				{
				if(objToSave.getCategory().getId() <= 0)
					objToSave.getCategory().setId(getCategoryDAO().save(objToSave.getCategory()).getId());
				
				objStatement.setInt(3, objToSave.getCategory().getId());
				}
			else
				objStatement.setNull(3, Types.INTEGER);
			
			objStatement.setString(4, objToSave.getDefinition());
			objStatement.setBoolean(5, objToSave.isSlang());
			
			if(objStatement.executeUpdate() == 1)
				{
				ResultSet objResults = objStatement.getGeneratedKeys();
				
				if(objResults.next())
					objToSave.setId(objResults.getInt(1));
				}
			
			return(objToSave);
			}
		catch(NoPartOfSpeechBoundException | NoWordBoundException | SQLException objException)
			{
			msObjLogger.error("Error inserting record into the database...", objException);
			
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

	public List<WordInstance> list() throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from " + TableName + ";");
		
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		finally
			{
			closeConnection();
			}
	}
	
	public List<WordInstance> listByWord(final Word objWord) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from " + TableName + " where Word = ?");
			
			objStatement.setInt(1, objWord.getId());
			
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error getting Word Instances by Word(Id = " + objWord.getId() + ")...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}

	public List<WordInstance> listByWordAndPartOfSpeech(final Word objWord, final PartOfSpeech objPartOfSpeech) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from " + TableName + " where Word = ? and PartOfSpeech = ?;");
			
			objStatement.setInt(1, objWord.getId());
			objStatement.setInt(2, objPartOfSpeech.getId());
			
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error getting Word Instances by Word(Id = " + objWord.getId() + ") and Part of Speech(Id = " + objPartOfSpeech.getId() + ")...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public List<WordInstance> listByWordPartOfSpeechAndCategory(final Word objWord, final PartOfSpeech objPartOfSpeech, final Category objCategory) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement;
			String sSQL = "select " + getQueriedColumnsForSelect() + " from " + TableName + " where Word = ? and PartOfSpeech = ?";
			
			if(objCategory == null)
				sSQL += " and Category is null";
			else
				sSQL += " and Category = ?";
			
			sSQL += ";";
			
			objStatement = getConnection().prepareStatement(sSQL);
			
			objStatement.setInt(1, objWord.getId());
			objStatement.setInt(2, objPartOfSpeech.getId());
			
			if(objCategory != null)
				objStatement.setInt(3, objCategory.getId());
			
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("There was a problem retrieving the word instances that had the same word, part of speech, and category...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	public List<WordInstance> listSlang() throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("select " + getQueriedColumnsForSelect() + " from " + TableName + " where Slang = ?;");
			
			objStatement.setBoolean(1, true);
			
			return(createObjectListFromResults(objStatement.executeQuery()));
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error getting Word Instances which are slang...", objException);
			
			return(null);
			}
		finally
			{
			closeConnection();
			}
	}

	public void remove(final WordInstance objWordInstance) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("delete from " + TableName + " where Id = ?;");
			
			objStatement.setInt(1, objWordInstance.getId());
			
			objStatement.executeUpdate();
			}
		catch(SQLException objException)
			{
			msObjLogger.error("Error deleting Word Instance with Id (" + objWordInstance.getId()+ ")...", objException);
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

	public WordInstance save(final WordInstance objWordInstance) throws SQLException
	{
		if(!isTableExists())
			createTable();
		
		try
			{
			if(objWordInstance != null)
				if(objWordInstance.getId() <= 0)
					return(insert(objWordInstance));
				else
					return(update(objWordInstance));
			else
				return(null);
			}
		catch(SQLException objException)
			{
			msObjLogger.error("There was a problem saving the Word Instance to the database...", objException);
			
			return(null);
			}
	}
	
	protected WordInstance update(final WordInstance objToSave) throws SQLException
	{
		try
			{
			PreparedStatement objStatement = getConnection().prepareStatement("update " + TableName + " set Word = ?, PartOfSpeech = ?, Category = ?, Definition = ?, Slang = ? where Id = ?;");
			
			if(objToSave.getWord().getId() <= 0)
				objToSave.getWord().setId(getWordDAO().save(objToSave.getWord()).getId());
			
			objStatement.setInt(1, objToSave.getWord().getId());
			
			if(objToSave.getPartOfSpeech().getId() <= 0)
				objToSave.getPartOfSpeech().setId(getPartOfSpeechDAO().save(objToSave.getPartOfSpeech()).getId());
			
			objStatement.setInt(2, objToSave.getPartOfSpeech().getId());
			
			if(objToSave.getCategory().getId() <= 0)
				objToSave.getCategory().setId(getCategoryDAO().save(objToSave.getCategory()).getId());
			
			objStatement.setInt(3, objToSave.getCategory().getId());
			
			objStatement.setString(4, objToSave.getDefinition());
			objStatement.setBoolean(5, objToSave.isSlang());
			
			objStatement.setInt(6, objToSave.getId());
			
			objStatement.executeUpdate();
			
			return(objToSave);
			}
		catch(NoPartOfSpeechBoundException | NoWordBoundException | SQLException objException)
			{
			msObjLogger.error("Error inserting record into the database...", objException);
			
			return(objToSave);
			}
		finally
			{
			closeConnection();
			}
	}
}