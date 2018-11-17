package net.draconia.frenchstudy.ui.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import javax.swing.table.TableModel;

import net.draconia.frenchstudy.exceptions.NoPartOfSpeechBoundException;
import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.model.WordInstance;

import org.springframework.stereotype.Component;

@Component
public class DetailsPanelWordInstanceTableModel implements Serializable, TableModel
{
	private static final long serialVersionUID = -5146089514033897639L;
	
	private List<TableModelListener> mLstTableModelListeners;
	private List<WordInstance> mLstModel;
	private Map<WordInstance, Boolean> mMapSelected;
	
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
			getSelectedMap().put(getModel().get(iRowIndex), ((Boolean)(objValue)));
	}
}