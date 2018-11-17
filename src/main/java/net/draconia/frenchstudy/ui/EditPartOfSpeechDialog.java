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
import net.draconia.frenchstudy.ui.controllers.EditPartOfSpeechDialogController;
import net.draconia.frenchstudy.ui.listeners.EditPartOfSpeechWindowListener;
import net.draconia.frenchstudy.ui.model.EditPartOfSpeechDialogModel;

public class EditPartOfSpeechDialog extends JDialog
{
	private static final long serialVersionUID = 8363705195488168006L;
	
	private EditPartOfSpeechDialogController mObjController;
	private JPanel mPnlButtons;
	
	public EditPartOfSpeechDialog(final Window wndOwner, final EditPartOfSpeechDialogController objController)
	{
		super(wndOwner, "", ModalityType.DOCUMENT_MODAL);
		
		setController(objController);
		setFont((Font)(getBean(Font.class)));
		setTitle(((getDialogModel().getOriginalModel().getId() > 0) ? "Edit " : "Add ") + "Part of Speech");
		
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
			mPnlButtons = new EditPartOfSpeechDialogButtonPanel(this, getController());
		
		return(mPnlButtons);
	}
	
	protected EditPartOfSpeechDialogController getController()
	{
		return(mObjController);
	}
	
	protected JPanel getControlPaneL()
	{
		return((JPanel)(getBean(EditPartOfSpeechDialogControlPanel.class)));
	}
	
	public EditPartOfSpeechDialogModel getDialogModel()
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
		
		addWindowListener(new EditPartOfSpeechWindowListener(getController()));
		
		pack();
		
		setLocation(new Point((szScreen.width - getWidth()) / 2, (szScreen.height - getHeight()) / 2));
		
		getDialogModel().setEditing(true);
	}
	
	protected void setController(final EditPartOfSpeechDialogController objController)
	{
		mObjController = objController;
	}
}