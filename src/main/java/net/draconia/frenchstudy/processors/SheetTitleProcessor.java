package net.draconia.frenchstudy.processors;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.services.CategoryService;
import net.draconia.frenchstudy.services.PartOfSpeechService;

@Component
public class SheetTitleProcessor implements Serializable
{
	private static final long serialVersionUID = 8895945693205677438L;
	
	private Category mObjCategory;
	
	@Autowired
	private CategoryService mObjCategoryService;
	
	private PartOfSpeech mObjPartOfSpeech;
	
	@Autowired
	private PartOfSpeechService mObjPartOfSpeechService;
	
	private String msSheetTitle;
	
	public Category getCategory()
	{
		return(mObjCategory);
	}
	
	protected CategoryService getCategoryService()
	{
		return(mObjCategoryService);
	}
	
	public PartOfSpeech getPartOfSpeech()
	{
		return(mObjPartOfSpeech);
	}
	
	protected PartOfSpeechService getPartOfSpeechService()
	{
		return(mObjPartOfSpeechService);
	}
	
	public String getSheetTitle()
	{
		return(msSheetTitle);
	}
	
	public void process() throws SQLException
	{
		String[] sArrTitleParts = StringUtils.split(getSheetTitle(), "(");
		
		if(sArrTitleParts.length > 1)
			sArrTitleParts[1] = StringUtils.split(sArrTitleParts[1], ")")[0];
		
		setPartOfSpeech(sArrTitleParts[0]);
		
		if(sArrTitleParts.length > 1)
			setCategory(sArrTitleParts[1]);
		else
			setCategory((Category)(null));
	}
	
	protected void setCategory(final Category objCategory) throws SQLException
	{
		mObjCategory = getCategoryService().save(objCategory);
	}
	
	protected void setCategory(final String sCategory) throws SQLException
	{
		mObjCategory = getCategoryService().getByCategory(sCategory);
		
		if(mObjCategory == null)
			setCategory(new Category(sCategory));
	}
	
	protected void setPartOfSpeech(final String sPartOfSpeech) throws SQLException
	{
		mObjPartOfSpeech = getPartOfSpeechService().getByPartOfSpeech(sPartOfSpeech);
		
		if(mObjPartOfSpeech == null)
			mObjPartOfSpeech = getPartOfSpeechService().save(new PartOfSpeech(sPartOfSpeech));
	}
	
	public void setSheetTitle(final String sSheetTitle)
	{
		msSheetTitle = sSheetTitle;
	}
}