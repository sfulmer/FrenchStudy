package net.draconia.frenchstudy.ui.model;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import javax.swing.table.TableModel;

import net.draconia.ApplicationContextProvider;
import net.draconia.frenchstudy.exceptions.NoPartOfSpeechBoundException;
import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.model.Word;
import net.draconia.frenchstudy.model.WordInstance;
import net.draconia.frenchstudy.services.WordInstanceService;
import net.draconia.frenchstudy.ui.listeners.propertychange.WordInstancesTableModelSelectedRowsPropertyChangeListener;
import net.draconia.utilities.PropertyChangeable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DetailsPanelWordInstanceTableModel extends PropertyChangeable implements Serializable, TableModel
{
	private static final long serialVersionUID = -5146089514033897639L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(DetailsPanelWordInstanceTableModel.class);
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	private List<TableModelListener> mLstTableModelListeners;
	private List<WordInstance> mLstModel;
	private Map<WordInstance, Boolean> mMapSelected;
	private Word mObjWordInEffect;
	
	@Autowired
	private WordInstanceService mObjWordInstanceService;
	
	public void addTableModelListener(final TableModelListener objTableModelListener)
	{
		getTableModelListeners().add(objTableModelListener);
	}
	
	public int countSelected()
	{
		int iCount = 0;
		
		for(WordInstance objWordInstance : getSelectedMap().keySet())
			iCount += (getSelectedMap().get(objWordInstance) ? 1 : 0);
		
		return(iCount);
	}
	
	protected void fireTableChanged()
	{
		for(TableModelListener objTableModelListener : getTableModelListeners())
			objTableModelListener.tableChanged(new TableModelEvent(this));
	}
	
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
	
	public Class<?> getColumnClass(final int iColumnIndex)
	{
		switch(iColumnIndex)
			{
			case 0:
				return(PartOfSpeech.class);
			case 1:
				return(Category.class);
			case 2:
			case 3:
				return(Boolean.class);
			default:
				return(null);
			}
	}
	
	public int getColumnCount()
	{
		return(4);
	}
	
	public String getColumnName(final int iColumnIndex)
	{
		switch(iColumnIndex)
			{
			case 0:
				return("Part of Speech");
			case 1:
				return("Category");
			case 2:
				return("Slang");
			case 3:
				return("Select All");
			default:
				return(null);
			}
	}
	
	public List<WordInstance> getModel()
	{
		if(mLstModel == null)
			if(getWordInEffect() != null)
				try
					{
					mLstModel = getWordInstanceService().listByWord(getWordInEffect());
					}
				catch(SQLException objException)
					{
					msObjLogger.error("There was a problem getting the WordInstances for this word(" + getWordInEffect() + ")...", objException);
					
					mLstModel = new ArrayList<WordInstance>();
					}
			else
				mLstModel = new ArrayList<WordInstance>();
		
		return(mLstModel);
	}
	
	public int getRowCount()
	{
		return(getModel().size());
	}
	
	protected Map<WordInstance, Boolean> getSelectedMap()
	{
		if(mMapSelected == null)
			mMapSelected = new HashMap<WordInstance, Boolean>();
		
		return(mMapSelected);
	}
	
	protected PropertyChangeListener getSelectedRowsPropertyChangeListener()
	{
		return((WordInstancesTableModelSelectedRowsPropertyChangeListener)(getBean(WordInstancesTableModelSelectedRowsPropertyChangeListener.class)));
	}
	
	protected List<TableModelListener> getTableModelListeners()
	{
		if(mLstTableModelListeners == null)
			mLstTableModelListeners = new ArrayList<TableModelListener>();
		
		return(mLstTableModelListeners);
	}
	
	public Object getValueAt(final int iRowIndex, final int iColumnIndex)
	{
		WordInstance objWordInstance = getModel().get(iRowIndex);
		
		switch(iColumnIndex)
			{
			case 0:
				try
					{
					return(objWordInstance.getPartOfSpeech());
					}
				catch(NoPartOfSpeechBoundException objException)
					{
					return(new PartOfSpeech());
					}
			case 1:
				return(objWordInstance.getCategory());
			case 2:
				return(objWordInstance.isSlang());
			case 3:
				return(getSelectedMap().get(objWordInstance));
			default:
				return(null);
			}
	}
	
	public Word getWordInEffect()
	{
		return(mObjWordInEffect);
	}
	
	protected WordInstanceService getWordInstanceService()
	{
		return(mObjWordInstanceService);
	}
	
	@PostConstruct
	protected void init()
	{
		addPropertyChangeListener(getSelectedRowsPropertyChangeListener());
	}
	
	public boolean isCellEditable(final int iRowIndex, final int iColumnIndex)
	{
		return(iColumnIndex == 3);
	}
	
	public void removeTableModelListener(final TableModelListener objTableListener)
	{
		getTableModelListeners().remove(objTableListener);
	}
	
	public void setModel(final List<WordInstance> lstModel)
	{
		if(lstModel == null)
			mLstModel = new ArrayList<WordInstance>();
		else
			mLstModel = lstModel;
		
		fireTableChanged();
	}
	
	public void setValueAt(final Object objValue, final int iRowIndex, final int iColumnIndex)
	{
		if(iColumnIndex == 3)
			{
			getSelectedMap().put(getModel().get(iRowIndex), ((Boolean)(objValue)));
			
			firePropertyChangeListeners("SelectedRows", null, getSelectedMap());
			}
	}
	
	public void setWordInEffect(final Word objWordInEffect)
	{
		mObjWordInEffect = objWordInEffect;
		
		mLstModel = null;
		
		fireTableChanged();
	}
}