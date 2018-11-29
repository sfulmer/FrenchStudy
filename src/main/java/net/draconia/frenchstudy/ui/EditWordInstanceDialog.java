package net.draconia.frenchstudy.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;

import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;

import javax.annotation.PostConstruct;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import net.draconia.ApplicationContextProvider;

import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;

import net.draconia.frenchstudy.ui.actions.CancelWordInstanceAction;

import net.draconia.frenchstudy.ui.listeners.EditWordInstanceWindowListener;

import net.draconia.frenchstudy.ui.model.EditWordInstanceDialogModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class EditWordInstanceDialog extends JDialog
{
	private static final long serialVersionUID = 4572352756334593560L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	@Autowired
	private CancelWordInstanceAction mActCancel;
	@Autowired
	private EditWordInstanceButtonPanel mPnlButtons;
	@Autowired
	private EditWordInstanceDialogModel mObjModel;
	@Autowired
	@Qualifier("btnEditWordInstanceApply")
	private JButton mBtnApply;
	@Autowired
	@Qualifier("chkWordInstanceSlang")
	private JCheckBox mChkSlang;
	@Autowired
	@Qualifier("cboWordInstanceCategory")
	private JComboBox<Category> mCboCategory;
	@Autowired
	@Qualifier("cboWordInstancePartOfSpeech")
	private JComboBox<PartOfSpeech> mCboPartOfSpeech;
	@Autowired
	@Qualifier("lblWordInstanceCategory")
	private JLabel mLblCategory;
	@Autowired
	@Qualifier("lblWordInstanceDefinition")
	private JLabel mLblDefinition;
	@Autowired
	@Qualifier("lblWordInstancePartOfSpeech")
	private JLabel mLblPartOfSpeech;
	@Autowired
	@Qualifier("lblWordInstanceWord")
	private JLabel mLblWord;
	@Autowired
	@Qualifier("scrWordInstanceDefinition")
	private JScrollPane mScrDefinition;
	@Autowired
	@Qualifier("txtWordInstanceWord")
	private JTextField mTxtWord;
	
	public EditWordInstanceDialog(final FrenchStudyMainWindow wndMain)
	{
		super(wndMain, "Edit Word Instance...", ModalityType.DOCUMENT_MODAL);
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
	}
	
	protected JButton getApplyButton()
	{
		return(mBtnApply);
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	protected JPanel getButtonPanel()
	{
		return(mPnlButtons);
	}
	
	protected Action getCancelAction()
	{
		return(mActCancel);
	}
	
	protected JComboBox<Category> getCategoryField()
	{
		return(mCboCategory);
	}
	
	protected JLabel getCategoryLabel()
	{
		return(mLblCategory);
	}
	
	protected JScrollPane getDefinitionScrollPane()
	{
		return(mScrDefinition);
	}
	
	protected JLabel getDefinitionLabel()
	{
		return(mLblDefinition);
	}
	
	protected EditWordInstanceDialogModel getModel()
	{
		return(mObjModel);
	}
	
	protected JComboBox<PartOfSpeech> getPartOfSpeechField()
	{
		return(mCboPartOfSpeech);
	}
	
	protected JLabel getPartOfSpeechLabel()
	{
		return(mLblPartOfSpeech);
	}
	
	protected JCheckBox getSlangField()
	{
		return(mChkSlang);
	}
	
	protected JTextField getWordField()
	{
		return(mTxtWord);
	}
	
	protected JLabel getWordLabel()
	{
		return(mLblWord);
	}
	
	protected void initControls()
	{
		GridBagConstraints objConstraints = new GridBagConstraints();
		
		objConstraints.gridx = objConstraints.gridy = 0;
		objConstraints.gridheight = objConstraints.gridwidth = 1;
		objConstraints.fill = GridBagConstraints.HORIZONTAL;
		objConstraints.anchor = GridBagConstraints.NORTHWEST;
		objConstraints.insets = new Insets(5, 5, 5, 5);
		add(getWordLabel(), objConstraints);
		
		objConstraints.gridx++;
		add(getWordField(), objConstraints);
		
		objConstraints.gridy++;
		objConstraints.gridx = 0;
		add(getPartOfSpeechLabel(), objConstraints);
		
		objConstraints.gridx++;
		add(getPartOfSpeechField(), objConstraints);
		
		objConstraints.gridy++;
		objConstraints.gridx = 0;
		add(getCategoryLabel(), objConstraints);
		
		objConstraints.gridx++;
		add(getCategoryField(), objConstraints);
		
		objConstraints.gridy++;
		objConstraints.gridx = 0;
		objConstraints.gridwidth = 2;
		add(getSlangField(), objConstraints);
		
		objConstraints.gridy++;
		objConstraints.gridx = 0;
		objConstraints.insets = new Insets(5, 5, 2, 5);
		add(getDefinitionLabel(), objConstraints);
		
		objConstraints.gridy++;
		objConstraints.insets = new Insets(2, 5, 5, 5);
		objConstraints.ipady = 25;
		add(getDefinitionScrollPane(), objConstraints);
		
		objConstraints.gridy++;
		objConstraints.insets = new Insets(5, 5, 5, 5);
		objConstraints.ipady = 0;
		add(getButtonPanel(), objConstraints);
	}
	
	@PostConstruct	
	protected void initDialog()
	{
		Dimension szScreen = getToolkit().getScreenSize();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridBagLayout());
		
		addWindowListener((WindowListener)(getBean(EditWordInstanceWindowListener.class)));
		
		initControls();
		
		pack();
		
		setLocation(new Point((szScreen.width - getWidth()) / 2, (szScreen.height - getHeight()) / 2));
		
		getRootPane().setDefaultButton(getApplyButton());
		
		getRootPane()
			.getActionMap()
				.put("CANCEL", getCancelAction());
		getRootPane()
			.getInputMap(JRootPane.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true), "CANCEL");
		
		if(getModel().getEditingModel().getId() <= 0)
			getTitle().replace("Edit", "Add");
	}
}