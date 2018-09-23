package net.draconia.frenchstudy.processors;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import java.sql.SQLException;

import net.draconia.frenchstudy.NoPartOfSpeechBoundException;
import net.draconia.frenchstudy.NoWordBoundException;

import net.draconia.frenchstudy.services.CategoryService;
import net.draconia.frenchstudy.services.PartOfSpeechService;
import net.draconia.frenchstudy.services.WordInstanceService;
import net.draconia.frenchstudy.services.WordService;

import org.jopendocument.dom.spreadsheet.SpreadSheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Loader implements Runnable, Serializable
{
	private static final long serialVersionUID = 3528282104707482766L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(Loader.class);
	
	@Autowired
	private CategoryService mObjCategoryService;
	
	@Autowired
	private PartOfSpeechService mObjPartOfSpeechService;
	
	@Autowired
	private SheetProcessor mObjSheetProcessor;
	
	@Autowired
	private WordService mObjWordService;
	
	@Autowired
	private WordInstanceService mObjWordInstanceService;
	
	private String msFileName;
	
	protected CategoryService getCategoryService()
	{
		return(mObjCategoryService);
	}
	
	public String getFileName()
	{
		if(msFileName == null)
			msFileName = "";
		
		return(msFileName);
	}
	
	protected PartOfSpeechService getPartOfSpeechService()
	{
		return(mObjPartOfSpeechService);
	}
	
	protected SheetProcessor getSheetProcessor()
	{
		return(mObjSheetProcessor);
	}
	
	protected WordService getWordService()
	{
		return(mObjWordService);
	}
	
	protected WordInstanceService getWordInstanceService()
	{
		return(mObjWordInstanceService);
	}
	
	public void run()
	{
		try
			{
			SpreadSheet objODSFile = SpreadSheet.createFromFile(new File(getFileName()));
			
			for(int iSheetLength = objODSFile.getSheetCount(), iSheetLoop = 0; iSheetLoop < iSheetLength; iSheetLoop++)
				{
				getSheetProcessor().setSheet(objODSFile.getSheet(iSheetLoop));
				getSheetProcessor().process();
				}
			}
		catch(IOException | NoPartOfSpeechBoundException | NoWordBoundException | SQLException objException)
			{
			msObjLogger.error("There was a problem importing from the LibreOffice Calc File...", objException);
			}
	}
	
	public void setFileName(final String sFileName)
	{
		if(sFileName == null)
			msFileName = "";
		else
			msFileName = sFileName;
	}
}