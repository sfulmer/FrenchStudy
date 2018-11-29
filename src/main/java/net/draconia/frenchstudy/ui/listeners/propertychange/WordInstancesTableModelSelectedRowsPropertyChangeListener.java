package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.Serializable;

import java.util.Map;

import javax.swing.JTable;

import javax.swing.table.JTableHeader;

import net.draconia.frenchstudy.model.WordInstance;

import net.draconia.frenchstudy.ui.CheckBoxHeader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;

@Component
public class WordInstancesTableModelSelectedRowsPropertyChangeListener implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = -9006858693816674976L;
	
	@Autowired
	@Qualifier("tblWordInstances")
	private JTable mTblWordInstances;
	
	protected JTable getWordInstancesTable()
	{
		return(mTblWordInstances);
	}
	
	protected JTableHeader getWordInstancesTableHeader()
	{
		return(getWordInstancesTable().getTableHeader());
	}
	
	@SuppressWarnings("unchecked")
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		if(objPropertyChangeEvent.getPropertyName().equalsIgnoreCase("SelectedRows"))
			{
			boolean bAllSelected = true;
			Map<WordInstance, Boolean> mapSelectedRows = ((Map<WordInstance, Boolean>)(objPropertyChangeEvent.getNewValue()));
			
			for(WordInstance objWordInstance : mapSelectedRows.keySet())
				if(!mapSelectedRows.get(objWordInstance))
					{
					bAllSelected = false;
					
					break;
					}
			
			((CheckBoxHeader)(getWordInstancesTableHeader().getColumnModel().getColumn(3).getHeaderRenderer())).setSelected(bAllSelected);
			getWordInstancesTableHeader().repaint();
			}
	}
}