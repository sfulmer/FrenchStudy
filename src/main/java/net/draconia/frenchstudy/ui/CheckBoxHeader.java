package net.draconia.frenchstudy.ui;

import java.awt.Component;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class CheckBoxHeader extends JCheckBox implements MouseListener, TableCellRenderer
{
	private static final long serialVersionUID = 3339823104531938776L;
	
	private Boolean mbMousePressed;
	private int miColumn;
	
	public CheckBoxHeader(final JTable tblView)
	{
		addItemListener(new ItemListener()
		{	
			public void itemStateChanged(final ItemEvent objItemEvent)
			{
				boolean bChecked = false;
				Object objSource = objItemEvent.getSource();
				
				if(objSource instanceof AbstractButton == false)
					return;
				
				bChecked = objItemEvent.getStateChange() == ItemEvent.SELECTED;
				
				for(int iLength = tblView.getRowCount(), iLoop = 0; iLoop < iLength; iLoop++)
					tblView.setValueAt(bChecked, iLoop, 3);
			}
		});
	}
	
	public int getColumn()
	{
		return(miColumn);
	}
	
	public Component getTableCellRendererComponent(final JTable objTable, final Object objValue, final boolean bIsSelected, final boolean bHasFocus, final int iRow, final int iColumn)
	{
		if(objTable != null)
			{
			JTableHeader objTableHeader = objTable.getTableHeader();
			
			if(objTableHeader != null)
				objTableHeader.addMouseListener(this);
			}
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		setColumn(iColumn);
		setHorizontalAlignment(JLabel.CENTER);
		setText("Select All");
		
		return(this);
	}
	
	protected void handleClickEvent(final MouseEvent objMouseEvent)
	{
		if(isMousePressed())
			{
			int iColumn, iViewColumn; 
			JTable tblView;
			JTableHeader objTableHeader;
			TableColumnModel objColumnModel;
			
			setMousePressed(false);
			
			objTableHeader = ((JTableHeader)(objMouseEvent.getSource()));
			tblView = objTableHeader.getTable();
			objColumnModel = tblView.getColumnModel();
			iViewColumn = objColumnModel.getColumnIndexAtX(objMouseEvent.getX());
			iColumn = tblView.convertColumnIndexToModel(iViewColumn);
			
			if((iViewColumn == getColumn()) && (objMouseEvent.getClickCount() == 1) && (iColumn != -1))
				doClick();
			}
	}
	
	protected boolean isMousePressed()
	{
		if(mbMousePressed == null)
			mbMousePressed = false;
		
		return(mbMousePressed);
	}
	
	public void mouseClicked(final MouseEvent objMouseEvent)
	{
		handleClickEvent(objMouseEvent);
		
		((JTableHeader)(objMouseEvent.getSource())).repaint();
	}
	
	public void mouseEntered(final MouseEvent objMouseEvent)
	{
		
	}
	
	public void mouseExited(final MouseEvent objMouseEvent)
	{
		
	}
	
	public void mousePressed(final MouseEvent objMouseEvent)
	{
		setMousePressed(true);
	}
	
	public void mouseReleased(final MouseEvent objMouseEvent)
	{
		
	}
	
	protected void setColumn(final int iColumn)
	{
		miColumn = iColumn;
	}
	
	protected void setMousePressed(final Boolean bMousePressed)
	{
		if(bMousePressed == null)
			mbMousePressed = false;
		else
			mbMousePressed = bMousePressed;
	}
}