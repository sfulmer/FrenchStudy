package net.draconia.frenchstudy.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Timer;

import net.draconia.FrenchStudy;

import net.draconia.frenchstudy.ui.controllers.EditCategoryDialogController;

import net.draconia.frenchstudy.ui.listeners.EditCategoryWindowListener;

import net.draconia.frenchstudy.ui.model.EditCategoryDialogModel;

public class EditCategoryDialog extends JDialog
{
	private static final long serialVersionUID = 8363705195488168006L;
	
	private EditCategoryDialogController mObjController;
	private JPanel mPnlButtons;
	
	public EditCategoryDialog(final Window wndOwner, final EditCategoryDialogController objController)
	{
		super(wndOwner, "", ModalityType.DOCUMENT_MODAL);
		
		setController(objController);
		setFont((Font)(getBean(Font.class)));
		setTitle(((getDialogModel().getOriginalModel().getId() > 0) ? "Edit " : "Add ") + "Category");
		
		new Timer(10, new ActionListener()
		{	
			public void actionPerformed(final ActionEvent objActionEvent)
			{
				((Timer)(objActionEvent.getSource())).stop();
				
				initDialog();
			}
		}).start();
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(FrenchStudy.getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(FrenchStudy.getApplicationContext().getBean(sBeanName));
	}
	
	protected JPanel getButtonPanel()
	{
		if(mPnlButtons == null)
			mPnlButtons = new EditCategoryDialogButtonPanel(this, getController());
		
		return(mPnlButtons);
	}
	
	protected EditCategoryDialogController getController()
	{
		return(mObjController);
	}
	
	protected JPanel getControlPaneL()
	{
		return((JPanel)(getBean(EditCategoryDialogControlPanel.class)));
	}
	
	public EditCategoryDialogModel getDialogModel()
	{
		return(getController().getDialogModel());
	}
	
	protected void initControls()
	{
		add(getControlPaneL(), BorderLayout.CENTER);
		add(getButtonPanel(), BorderLayout.SOUTH);
	}
	
	protected void initDialog()
	{
		Dimension szScreen = getToolkit().getScreenSize();
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
		
		initControls();
		
		addWindowListener(new EditCategoryWindowListener(getController()));
		
		pack();
		
		setLocation(new Point((szScreen.width - getWidth()) / 2, (szScreen.height - getHeight()) / 2));
		
		getDialogModel().setEditing(true);
	}
	
	protected void setController(final EditCategoryDialogController objController)
	{
		mObjController = objController;
	}
}