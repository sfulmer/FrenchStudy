package net.draconia.frenchstudy.ui;

import java.awt.FlowLayout;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ListPanelButtonPanel extends JPanel
{
	private static final long serialVersionUID = 6787243291536779992L;
	
	@Autowired
	@Qualifier("btnEdit")
	private JButton mBtnEdit;
	@Autowired
	@Qualifier("btnNew")
	private JButton mBtnNew;
	@Autowired
	@Qualifier("btnRemove")
	private JButton mBtnRemove;
	
	public ListPanelButtonPanel()
	{
		super(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
	
	protected JButton getEditButton()
	{
		return(mBtnEdit);
	}
	
	protected JButton getNewButton()
	{
		return(mBtnNew);
	}
	
	protected JButton getRemoveButton()
	{
		return(mBtnRemove);
	}
	
	@PostConstruct
	protected void initPanel()
	{
		add(getNewButton());
		add(getEditButton());
		add(getRemoveButton());
	}
}