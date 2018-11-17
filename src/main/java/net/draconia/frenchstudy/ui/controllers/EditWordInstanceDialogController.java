package net.draconia.frenchstudy.ui.controllers;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.List;

import net.draconia.ApplicationContextProvider;
import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.model.WordInstance;

import net.draconia.frenchstudy.services.CategoryService;
import net.draconia.frenchstudy.services.PartOfSpeechService;
import net.draconia.frenchstudy.services.WordInstanceService;

import net.draconia.frenchstudy.ui.EditWordInstanceDialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EditWordInstanceDialogController implements Serializable
{
	private static final long serialVersionUID = 3318033215373040444L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	@Autowired
	@Qualifier("categoryService")
	private CategoryService mObjCategoryService;
	
	@Autowired
	private EditWordInstanceDialog mDlgEditWordInstance;
	
	@Autowired
	@Qualifier("partOfSpeechService")
	private PartOfSpeechService mObjPartOfSpeechService;
	
	@Autowired
	private WordInstanceService mObjWordInstanceService;
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	protected CategoryService getCategoryService()
	{
		return(mObjCategoryService);
	}
	
	public List<Category> listCategories() throws SQLException
	{
		return(getCategoryService().list());
	}
	
	protected EditWordInstanceDialog getEditWordInstanceDialog()
	{
		return(mDlgEditWordInstance);
	}
	
	protected PartOfSpeechService getPartOfSpeechService()
	{
		if(mObjPartOfSpeechService == null)
			mObjPartOfSpeechService = ((PartOfSpeechService)(getBean(PartOfSpeechService.class)));
		
		return(mObjPartOfSpeechService);
	}
	
	public List<PartOfSpeech> listPartOfSpeeches() throws SQLException
	{
		return(getPartOfSpeechService().list());
	}
	
	protected WordInstanceService getWordInstanceService()
	{
		return(mObjWordInstanceService);
	}
	
	public void save(final WordInstance objWordInstance) throws SQLException
	{
		getWordInstanceService().save(objWordInstance);
	}
	
	public void showDialog()
	{
		getEditWordInstanceDialog().setVisible(true);
	}
}