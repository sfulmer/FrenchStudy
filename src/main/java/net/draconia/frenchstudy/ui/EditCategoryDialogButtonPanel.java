package net.draconia.frenchstudy.ui;

import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import net.draconia.FrenchStudy;

import net.draconia.frenchstudy.ui.controllers.EditCategoryDialogController;

public class EditCategoryDialogButtonPanel extends JPanel
{
	private static final long serialVersionUID = 600563898061416849L;
	
	private EditCategoryDialog mDlgEditCategory;
	private EditCategoryDialogController mObjController;
	private JButton mBtnApply, mBtnCancel, mBtnSave;
	
	public EditCategoryDialogButtonPanel(final EditCategoryDialog dlgEditCategory, final EditCategoryDialogController objController)
	{
		super(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		setDialog(dlgEditCategory);
		setController(objController);
		
		initPanel();
	}
	
	protected Action getApplyAction()
	{
		return(getController().getApplyAction());
	}
	
	protected JButton getApplyButton()
	{
		if(mBtnApply == null)
			{
			mBtnApply = new JButton(getApplyAction());
			
			mBtnApply.setFont(getFont());
			}
		
		return(mBtnApply);
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(FrenchStudy.getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(FrenchStudy.getApplicationContext().getBean(sBeanName));
	}
	
	protected Action getCancelAction()
	{
		return(getController().getCancelAction());
	}
	
	protected JButton getCancelButton()
	{
		if(mBtnCancel == null)
			{
			mBtnCancel = new JButton(getCancelAction());
			
			mBtnCancel.setFont(getFont());
			}
		
		return(mBtnCancel);
	}
	
	protected EditCategoryDialogController getController()
	{
		return(mObjController);
	}
	
	protected EditCategoryDialog getDialog()
	{
		return(mDlgEditCategory);
	}
	
	protected Action getSaveAction()
	{
		return(getController().getSaveAction());
	}
	
	protected JButton getSaveButton()
	{
		if(mBtnSave == null)
			{
			mBtnSave = new JButton(getSaveAction());
			
			mBtnSave.setFont(getFont());
			}
		
		return(mBtnSave);
	}
	
	protected void initPanel()
	{
		setFont((Font)(getBean(Font.class)));
		
		add(getApplyButton());
		add(getSaveButton());
		add(getCancelButton());
		
		getDialog().getRootPane().getActionMap().put("CANCEL", getCancelAction());
		getDialog().getRootPane().getInputMap(JRootPane.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "CANCEL");
	}
	
	protected void setController(final EditCategoryDialogController objController)
	{
		mObjController = objController;
	}
	
	protected void setDialog(final EditCategoryDialog dlgEditCategory)
	{
		mDlgEditCategory = dlgEditCategory;
	}
}