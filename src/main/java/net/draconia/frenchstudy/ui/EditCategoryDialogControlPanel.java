package net.draconia.frenchstudy.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.annotation.PostConstruct;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;

@Component
public class EditCategoryDialogControlPanel extends JPanel
{
	private static final long serialVersionUID = -2389309614449214686L;

	@Autowired
	@Qualifier("lblEditCategoryDialogId")
	private JLabel mLblId;
	
	@Autowired
	@Qualifier("lblEditCategoryDialogCategory")
	private JLabel mLblCategory;
	
	@Autowired
	@Qualifier("txtEditCategoryDialogId")
	private JTextField mTxtId;
	
	@Autowired
	@Qualifier("txtEditCategoryDialogCategory")
	private JTextField mTxtCategory;
	
	public EditCategoryDialogControlPanel()
	{
		super(new GridBagLayout());
	}
	
	protected JTextField getIdField()
	{
		return(mTxtId);
	}
	
	protected JLabel getIdLabel()
	{
		return(mLblId);
	}
	
	protected JTextField getCategoryField()
	{
		return(mTxtCategory);
	}
	
	protected JLabel getCategoryLabel()
	{
		return(mLblCategory);
	}
	
	@PostConstruct
	protected void initPanel()
	{
		GridBagConstraints objConstraints = new GridBagConstraints();
		
		objConstraints.gridx = objConstraints.gridy = 0;
		objConstraints.gridwidth = objConstraints.gridheight = 1;
		objConstraints.fill = GridBagConstraints.BOTH;
		objConstraints.anchor = GridBagConstraints.NORTHWEST;
		objConstraints.insets = new Insets(5, 5, 5, 5);
		add(getIdLabel(), objConstraints);
		
		objConstraints.gridx++;
		add(getIdField(), objConstraints);
		
		objConstraints.gridy++;
		objConstraints.gridx = 0;
		add(getCategoryLabel(), objConstraints);
		
		objConstraints.gridx++;
		add(getCategoryField(), objConstraints);
	}
}