package net.draconia.frenchstudy.processors;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.jopendocument.dom.spreadsheet.Sheet;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.NoPartOfSpeechBoundException;
import net.draconia.frenchstudy.NoWordBoundException;

import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.model.Word;
import net.draconia.frenchstudy.model.WordInstance;

import net.draconia.frenchstudy.services.WordInstanceService;
import net.draconia.frenchstudy.services.WordService;

@Component
public class SheetProcessor implements Serializable
{
	private static final long serialVersionUID = 8772759830156495804L;
	
	private Category mObjCategory;
	private PartOfSpeech mObjPartOfSpeech;
	private Sheet mObjSheet;
	private Word mObjWord;
	
	@Autowired
	private SheetTitleProcessor mObjSheetTitleProcessor;
	
	@Autowired
	private WordInstanceService mObjWordInstanceService;
	
	@Autowired
	private WordService mObjWordService;
	
	protected Category getCategory()
	{
		return(mObjCategory);
	}
	
	protected PartOfSpeech getPartOfSpeech()
	{
		return(mObjPartOfSpeech);
	}
	
	public Sheet getSheet()
	{
		return(mObjSheet);
	}
	
	protected String getSheetTitle()
	{
		return(getSheet().getName());
	}
	
	protected SheetTitleProcessor getSheetTitleProcessor()
	{
		return(mObjSheetTitleProcessor);
	}
	
	protected String getWordText(final String sCellCoordinate)
	{
		return(getSheet().getCellAt(sCellCoordinate).getTextValue());
	}
	
	protected Word getWord()
	{
		return(mObjWord);
	}
	
	protected WordInstanceService getWordInstanceService()
	{
		return(mObjWordInstanceService);
	}
	
	protected WordService getWordService()
	{
		return(mObjWordService);
	}
	
	public void process() throws NoPartOfSpeechBoundException, NoWordBoundException, SQLException
	{
		getSheetTitleProcessor().setSheetTitle(getSheetTitle());
		getSheetTitleProcessor().process();
		
		setCategory(getSheetTitleProcessor().getCategory());
		setPartOfSpeech(getSheetTitleProcessor().getPartOfSpeech());
		
		for(int iRowLength = getSheet().getRowCount(), iRowLoop = 2;iRowLoop <= iRowLength;iRowLoop++)
			{
			processWordObject("A" + iRowLoop);
			
			if(getWord() != null)
				processWordInstance();
			else
				break;
			}
	}
	
	protected void processWordObject(final String sCellCoordinate) throws SQLException
	{
		String sWord = getWordText(sCellCoordinate);
		
		if(!sWord.equals(""))
			{
			Word objWord = getWordService().getByEnglishWord(sWord);
			
			if(objWord == null)
				setWord(getWordService().save(new Word(sWord)));
			else
				setWord(objWord);
			}
		else
			setWord(null);
	}
	
	protected void processWordInstance() throws NoPartOfSpeechBoundException, NoWordBoundException, SQLException
	{
		List<WordInstance> lstWordInstances = getWordInstanceService().listByWordPartOfSpeechAndCategory(getWord(), getPartOfSpeech(), getCategory());
		
		if((lstWordInstances == null) || (lstWordInstances.size() <= 0))
			getWordInstanceService().save(new WordInstance(getWord(), getPartOfSpeech(), getCategory()));
	}
	
	protected void setCategory(final Category objCategory)
	{
		mObjCategory = objCategory;
	}
	
	protected  void setPartOfSpeech(final PartOfSpeech objPartOfSpeech)
	{
		mObjPartOfSpeech = objPartOfSpeech;
	}
	
	public void setSheet(final Sheet objSheet)
	{
		mObjSheet = objSheet;
	}
	
	protected void setWord(final Word objWord)
	{
		mObjWord = objWord;
	}
}