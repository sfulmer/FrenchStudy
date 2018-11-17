package net.draconia.frenchstudy;

import java.awt.Font;
import java.awt.SystemColor;

import java.awt.event.KeyEvent;

import java.io.Serializable;

import java.util.Properties;

import javax.sql.DataSource;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.table.TableModel;

import net.draconia.ApplicationContextProvider;

import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.model.Word;

import net.draconia.frenchstudy.ui.DetailsPanel;
import net.draconia.frenchstudy.ui.DetailsPanelEnglishWordPanel;
import net.draconia.frenchstudy.ui.ListPanel;

import net.draconia.frenchstudy.ui.actions.AddWordInstanceAction;
import net.draconia.frenchstudy.ui.actions.ApplyWordInstanceAction;
import net.draconia.frenchstudy.ui.actions.CancelWordInstanceAction;
import net.draconia.frenchstudy.ui.actions.EditAction;
import net.draconia.frenchstudy.ui.actions.EditWordInstancesAction;
import net.draconia.frenchstudy.ui.actions.ExitAction;
import net.draconia.frenchstudy.ui.actions.NewAction;
import net.draconia.frenchstudy.ui.actions.RemoveAction;
import net.draconia.frenchstudy.ui.actions.RemoveWordInstancesAction;
import net.draconia.frenchstudy.ui.actions.SaveWordInstanceAction;

import net.draconia.frenchstudy.ui.listeners.WordListSelectionListener;

import net.draconia.frenchstudy.ui.model.DetailsPanelWordInstanceTableModel;
import net.draconia.frenchstudy.ui.model.WordInstanceCategoryComboBoxModel;
import net.draconia.frenchstudy.ui.model.WordInstancePartOfSpeechComboBoxModel;
import net.draconia.frenchstudy.ui.model.WordsListModel;

import net.draconia.utilities.UtilityBeanConfiguration;

import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("net.draconia")
@Import(UtilityBeanConfiguration.class)
public class BeanConfiguration implements Serializable
{
	private static final long serialVersionUID = 6385306743058077939L;
	
	private ApplicationContextProvider mObjApplicationContextProvider;
	private DataSource mObjDataSource;
	private Font mFntDefault;
	private JButton mBtnEdit, mBtnEditWordInstanceApply, mBtnEditWordInstanceCancel; 
	private JButton mBtnEditWordInstanceSave, mBtnNew, mBtnRemove, mBtnWordInstancesAdd;
	private JButton mBtnWordInstancesEdit, mBtnWordInstancesRemove;
	private JCheckBox mChkWordInstnaceSlang;
	private JComboBox<Category> mCboWordInstanceCategory;
	private JComboBox<PartOfSpeech> mCboWordInstancePartOfSpeech;
	private JLabel mLblEditPartOfSpeechDialogId, mLblEditPartOfSpeechDialogPartOfSpeech;
	private JLabel mLblWordInstanceCategory, mLblWordInstanceDefinition, mLblEnglishWord;
	private JLabel mLblWordInstancePartOfSpeech, mLblWordInstanceWord, mLblWords;
	private JList<Word> mLstWords;
	private JMenu mMnuFile;
	private JMenuBar mMnuBar;
	private JScrollPane mScrWords, mScrWordInstances;
	private JSplitPane mPnlSplit;
	private JTable mTblWordInstances;
	private JTextArea mTxtWordInstanceDefinition;
	private JTextField mTxtEditPartOfSpeechDialogId, mTxtEditPartOfSpeechDialogPartOfSpeech;
	private JTextField mTxtEnglishWord, mTxtWordInstanceWord;
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	@Bean("applicationContextProvider")
	public ApplicationContextProvider getApplicationContextProvider()
	{
		if(mObjApplicationContextProvider == null)
			mObjApplicationContextProvider = new ApplicationContextProvider();
		
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
	
	@Bean("dataSource")
	public DataSource getDataSource()
	{
		if(mObjDataSource == null)
			{
			Properties objProperties = new Properties();
			
			mObjDataSource = new DriverManagerDataSource();
		
			((DriverManagerDataSource)(mObjDataSource)).setDriverClassName("com.mysql.cj.jdbc.Driver");
			((DriverManagerDataSource)(mObjDataSource)).setPassword("R3g1n@ 1$ my Qu3En!");
			((DriverManagerDataSource)(mObjDataSource)).setUrl("jdbc:mysql://localhost:3306");
			((DriverManagerDataSource)(mObjDataSource)).setUsername("root");
			
			objProperties.setProperty("useSSL", "false");
			objProperties.setProperty("autoReconnect", "true");
			
			((DriverManagerDataSource)(mObjDataSource)).setConnectionProperties(objProperties);
			}
		
		return(mObjDataSource);
	}
	
	@Bean("fntDefault")
	public Font getDefaultFont()
	{
		if(mFntDefault == null)
			mFntDefault = new Font("Tahana", Font.BOLD, 10);
		
		return(mFntDefault);
	}
	
	protected DetailsPanel getDetailsPanel()
	{
		return((DetailsPanel)(getBean(DetailsPanel.class)));
	}
	
	protected Action getEditAction()
	{
		return((EditAction)(getBean(EditAction.class)));
	}
	
	@Bean("btnEdit")
	public JButton getEditButton()
	{
		if(mBtnEdit == null)
			{
			mBtnEdit = new JButton(getEditAction());
			
			mBtnEdit.setFont(getDefaultFont());
			}
		
		return(mBtnEdit);
	}
	
	@Bean("txtEditPartOfSpeechDialogId")
	public JTextField getEditPartOfSpeechDialogIdField()
	{
		if(mTxtEditPartOfSpeechDialogId == null)
			{
			mTxtEditPartOfSpeechDialogId = new JTextField(30);
			
			mTxtEditPartOfSpeechDialogId.setBackground(SystemColor.control);
			mTxtEditPartOfSpeechDialogId.setBorder(BorderFactory.createEmptyBorder());
			mTxtEditPartOfSpeechDialogId.setEditable(false);
			mTxtEditPartOfSpeechDialogId.setFont(getDefaultFont());
			}
	
	return(mTxtEditPartOfSpeechDialogId);
	}
	
	@Bean("lblEditPartOfSpeechDialogId")
	public JLabel getEditPartOfSpeechDialogIdLabel()
	{
		if(mLblEditPartOfSpeechDialogId == null)
			{
			mLblEditPartOfSpeechDialogId = new JLabel("Id:");
			
			mLblEditPartOfSpeechDialogId.setDisplayedMnemonic(KeyEvent.VK_I);
			mLblEditPartOfSpeechDialogId.setFont(getDefaultFont());
			mLblEditPartOfSpeechDialogId.setLabelFor(getEditPartOfSpeechDialogIdField());
			mLblEditPartOfSpeechDialogId.setOpaque(false);
			}
		
		return(mLblEditPartOfSpeechDialogId);
	}
	
	@Bean("txtEditPartOfSpeechDialogPartOfSpeech")
	public JTextField getEditPartOfSpeechDialogPartOfSpeechField()
	{
		if(mTxtEditPartOfSpeechDialogPartOfSpeech == null)
			{
			mTxtEditPartOfSpeechDialogPartOfSpeech = new JTextField(30);
			
			mTxtEditPartOfSpeechDialogPartOfSpeech.setBorder(BorderFactory.createLoweredBevelBorder());
			mTxtEditPartOfSpeechDialogPartOfSpeech.setFont(getDefaultFont());
			}
	
		return(mTxtEditPartOfSpeechDialogPartOfSpeech);
	}
	
	@Bean("lblEditPartOfSpeechDialogPartOfSpeech")
	public JLabel getEditPartOfSpeechDialogPartOfSpeechLabel()
	{
		if(mLblEditPartOfSpeechDialogPartOfSpeech == null)
			{
			mLblEditPartOfSpeechDialogPartOfSpeech = new JLabel("Part of Speech:");
			
			mLblEditPartOfSpeechDialogPartOfSpeech.setDisplayedMnemonic(KeyEvent.VK_P);
			mLblEditPartOfSpeechDialogPartOfSpeech.setFont(getDefaultFont());
			mLblEditPartOfSpeechDialogPartOfSpeech.setLabelFor(getEditPartOfSpeechDialogPartOfSpeechField());
			mLblEditPartOfSpeechDialogPartOfSpeech.setOpaque(false);
			}
	
		return(mLblEditPartOfSpeechDialogPartOfSpeech);
	}
	
	protected Action getEditWordInstanceApplyAction()
	{
		return((Action)(getBean(ApplyWordInstanceAction.class)));
	}
	
	@Bean("btnEditWordInstanceApply")
	public JButton getEditWordInstanceApplyButton()
	{
		if(mBtnEditWordInstanceApply == null)
			mBtnEditWordInstanceApply = new JButton(getEditWordInstanceApplyAction());
		
		return(mBtnEditWordInstanceApply);
	}
	
	protected Action getEditWordInstanceCancelAction()
	{
		return((Action)(getBean(CancelWordInstanceAction.class)));
	}
	
	@Bean("btnEditWordInstanceCancel")
	public JButton getEditWordInstanceCancelButton()
	{
		if(mBtnEditWordInstanceCancel == null)
			mBtnEditWordInstanceCancel = new JButton(getEditWordInstanceCancelAction());
		
		return(mBtnEditWordInstanceCancel);
	}
	
	protected Action getEditWordInstanceSaveAction()
	{
		return((Action)(getBean(SaveWordInstanceAction.class)));
	}
	
	@Bean("btnEditWordInstanceSave")
	public JButton getEditWordInstanceSaveButton()
	{
		if(mBtnEditWordInstanceSave == null)
			mBtnEditWordInstanceSave = new JButton(getEditWordInstanceSaveAction());
		
		return(mBtnEditWordInstanceSave);
	}
	
	@Bean("txtEnglishWord")
	public JTextField getEnglishWordField()
	{
		if(mTxtEnglishWord == null)
			{
			mTxtEnglishWord = new JTextField(30);
			
			mTxtEnglishWord.setBorder(BorderFactory.createLoweredBevelBorder());
			mTxtEnglishWord.setEnabled(false);
			mTxtEnglishWord.setFont(getDefaultFont());
			}
		
		return(mTxtEnglishWord);
	}
	
	@Bean("lblEnglishWord")
	public JLabel getEnglishWordLabel()
	{
		if(mLblEnglishWord == null)
			{
			mLblEnglishWord = new JLabel("English:");
			
			mLblEnglishWord.setDisplayedMnemonic(KeyEvent.VK_E);
			mLblEnglishWord.setEnabled(false);
			mLblEnglishWord.setLabelFor(getEnglishWordField());
			mLblEnglishWord.setOpaque(false);
			}
		
		return(mLblEnglishWord);
	}
	
	@Bean("pnlEnglishWord")
	public JPanel getEnglishWordPanel()
	{
		return((DetailsPanelEnglishWordPanel)(getBean(DetailsPanelEnglishWordPanel.class)));
	}
	
	protected Action getExitAction()
	{
		return((Action)(getBean(ExitAction.class)));
	}
	
	protected JMenu getFileMenu()
	{
		if(mMnuFile == null)
			{
			mMnuFile = new JMenu("File");
			
			mMnuFile.setMnemonic(KeyEvent.VK_F);
			mMnuFile.setFont(getDefaultFont());
			
			mMnuFile.add(getExitAction());
			}
		
		return(mMnuFile);
	}
	
	protected ListPanel getListPanel()
	{
		return((ListPanel)(getBean(ListPanel.class)));
	}
	
	@Bean("mnuBar")
	public JMenuBar getMainMenuBar()
	{
		if(mMnuBar == null)
			{
			mMnuBar = new JMenuBar();
			
			mMnuBar.add(getFileMenu());
			}
		
		return(mMnuBar);
	}
	
	protected Action getNewAction()
	{
		return((NewAction)(getBean(NewAction.class)));
	}
	
	@Bean("btnNew")
	public JButton getNewButton()
	{
		if(mBtnNew == null)
			{
			mBtnNew = new JButton(getNewAction());
			
			mBtnNew.setFont(getDefaultFont());
			}
		
		return(mBtnNew);
	}
	
	protected Action getRemoveAction()
	{
		return((RemoveAction)(getBean(RemoveAction.class)));
	}
	
	@Bean("btnRemove")
	public JButton getRemoveButton()
	{
		if(mBtnRemove == null)
			{
			mBtnRemove = new JButton(getRemoveAction());
			
			mBtnRemove.setFont(getDefaultFont());
			}
		
		return(mBtnRemove);
	}
	
	@Bean("pnlSplit")
	public JSplitPane getSplitPane()
	{
		if(mPnlSplit == null)
			mPnlSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getListPanel(), getDetailsPanel());
		
		return(mPnlSplit);
	}
	
	@Bean("cboWordInstanceCategory")
	public JComboBox<Category> getWordInstanceCategoryField()
	{
		if(mCboWordInstanceCategory == null)
			{
			mCboWordInstanceCategory = new JComboBox<Category>((WordInstanceCategoryComboBoxModel)(getBean(WordInstanceCategoryComboBoxModel.class)));
			
			mCboWordInstanceCategory.setBorder(BorderFactory.createLoweredBevelBorder());
			mCboWordInstanceCategory.setFont(getDefaultFont());
			}
		
		return(mCboWordInstanceCategory);
	}
	
	@Bean("lblWordInstanceCategory")
	public JLabel getWordInstanceCategoryLabel()
	{
		if(mLblWordInstanceCategory == null)
			{
			mLblWordInstanceCategory = new JLabel("Category:");
			
			mLblWordInstanceCategory.setDisplayedMnemonic(KeyEvent.VK_T);
			mLblWordInstanceCategory.setFont(getDefaultFont());
			mLblWordInstanceCategory.setLabelFor(getWordInstanceCategoryField());
			mLblWordInstanceCategory.setOpaque(false);
			}
		
		return(mLblWordInstanceCategory);
	}
	
	@Bean("txtWordInstanceDefinition")
	public JTextArea getWordInstanceDefinitionField()
	{
		if(mTxtWordInstanceDefinition == null)
			{
			mTxtWordInstanceDefinition = new JTextArea();
			
			mTxtWordInstanceDefinition.setBorder(BorderFactory.createLoweredBevelBorder());
			mTxtWordInstanceDefinition.setFont(getDefaultFont());
			mTxtWordInstanceDefinition.setLineWrap(true);
			}
		
		return(mTxtWordInstanceDefinition);
	}
	
	@Bean("lblWordInstanceDefinition")
	public JLabel getWordInstanceDefinitionLabel()
	{
		if(mLblWordInstanceDefinition == null)
			{
			mLblWordInstanceDefinition = new JLabel("Definition:");
			
			mLblWordInstanceDefinition.setDisplayedMnemonic(KeyEvent.VK_D);
			mLblWordInstanceDefinition.setFont(getDefaultFont());
			mLblWordInstanceDefinition.setLabelFor(getWordInstanceDefinitionField());
			mLblWordInstanceDefinition.setOpaque(false);
			}
		
		return(mLblWordInstanceDefinition);
	}
	
	protected WordInstancePartOfSpeechComboBoxModel getWordInstancePartOfSpeechComboBoxModel()
	{
		return((WordInstancePartOfSpeechComboBoxModel)(getBean(WordInstancePartOfSpeechComboBoxModel.class)));
	}
	
	@Bean("cboWordInstancePartOfSpeech")
	public JComboBox<PartOfSpeech> getWordInstancePartOfSpeechField()
	{
		if(mCboWordInstancePartOfSpeech == null)
			{
			mCboWordInstancePartOfSpeech = new JComboBox<PartOfSpeech>(getWordInstancePartOfSpeechComboBoxModel());
			
			mCboWordInstancePartOfSpeech.setBorder(BorderFactory.createLoweredBevelBorder());
			mCboWordInstancePartOfSpeech.setFont(getDefaultFont());
			}
		
		return(mCboWordInstancePartOfSpeech);
	}
	
	@Bean("lblWordInstancePartOfSpeech")
	public JLabel getWordInstancePartOfSpeechLabel()
	{
		if(mLblWordInstancePartOfSpeech == null)
			{
			mLblWordInstancePartOfSpeech = new JLabel("Part of Speech:");
			
			mLblWordInstancePartOfSpeech.setDisplayedMnemonic(KeyEvent.VK_P);
			mLblWordInstancePartOfSpeech.setFont(getDefaultFont());
			mLblWordInstancePartOfSpeech.setLabelFor(getWordInstancePartOfSpeechField());
			mLblWordInstancePartOfSpeech.setOpaque(false);
			}
		
		return(mLblWordInstancePartOfSpeech);
	}
	
	protected Action getWordInstancesAddAction()
	{
		return((Action)(getBean(AddWordInstanceAction.class)));
	}
	
	@Bean("btnWordInstancesAdd")
	public JButton getWordInstancesAddButton()
	{
		if(mBtnWordInstancesAdd == null)
			mBtnWordInstancesAdd = new JButton(getWordInstancesAddAction());
		
		return(mBtnWordInstancesAdd);
	}
	
	protected Action getWordInstancesEditAction()
	{
		return((Action)(getBean(EditWordInstancesAction.class)));
	}
	
	@Bean("btnWordInstancesEdit")
	public JButton getWordInstancesEditButton()
	{
		if(mBtnWordInstancesEdit == null)
			mBtnWordInstancesEdit = new JButton(getWordInstancesEditAction());
		
		return(mBtnWordInstancesEdit);
	}
	
	protected Action getWordInstancesRemoveAction()
	{
		return((Action)(getBean(RemoveWordInstancesAction.class)));
	}
	
	@Bean("btnWordInstancesRemove")
	public JButton getWordInstancesRemoveButton()
	{
		if(mBtnWordInstancesRemove == null)
			mBtnWordInstancesRemove = new JButton(getWordInstancesRemoveAction());
		
		return(mBtnWordInstancesRemove);
	}
	
	@Bean("scrWordInstances")
	public JScrollPane getWordInstancesScrollPane()
	{
		if(mScrWordInstances == null)
			{
			mScrWordInstances = new JScrollPane(getWordInstancesTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			mScrWordInstances.setBorder(BorderFactory.createLoweredBevelBorder());
			mScrWordInstances.setEnabled(false);
			mScrWordInstances.setFont(getDefaultFont());
			}
		
		return(mScrWordInstances);
	}
	
	@Bean("chkWordInstanceSlang")
	public JCheckBox getWordInstanceSlangField()
	{
		if(mChkWordInstnaceSlang == null)
			{
			mChkWordInstnaceSlang = new JCheckBox("Slang");
			
			mChkWordInstnaceSlang.setHorizontalAlignment(JCheckBox.RIGHT);
			mChkWordInstnaceSlang.setFont(getDefaultFont());
			mChkWordInstnaceSlang.setMnemonic(KeyEvent.VK_S);
			mChkWordInstnaceSlang.setOpaque(false);
			}
		
		return(mChkWordInstnaceSlang);
	}
	
	@Bean("tblWordInstances")
	public JTable getWordInstancesTable()
	{
		if(mTblWordInstances == null)
			{
			mTblWordInstances = new JTable(getWordInstancesTableModel());
			
			mTblWordInstances.setBorder(BorderFactory.createLoweredBevelBorder());
			mTblWordInstances.setFont(getDefaultFont());
			}
		
		return(mTblWordInstances);
	}
	
	protected TableModel getWordInstancesTableModel()
	{
		return((DetailsPanelWordInstanceTableModel)(getBean(DetailsPanelWordInstanceTableModel.class)));
	}
	
	@Bean("txtWordInstanceWord")
	public JTextField getWordInstanceWordField()
	{
		if(mTxtWordInstanceWord == null)
			{
			mTxtWordInstanceWord = new JTextField(30);
			
			mTxtWordInstanceWord.setHorizontalAlignment(JTextField.LEFT);
			mTxtWordInstanceWord.setBackground(SystemColor.control);
			mTxtWordInstanceWord.setBorder(BorderFactory.createEmptyBorder());
			mTxtWordInstanceWord.setEditable(false);
			mTxtWordInstanceWord.setFont(getDefaultFont());
			}
		
		return(mTxtWordInstanceWord);
	}
	
	@Bean("lblWordInstanceWord")
	public JLabel getWordInstanceWordLabel()
	{
		if(mLblWordInstanceWord == null)
			{
			mLblWordInstanceWord = new JLabel("Word:");
			
			mLblWordInstanceWord.setFont(getDefaultFont());
			mLblWordInstanceWord.setOpaque(false);
			}
		
		return(mLblWordInstanceWord);
	}
	
	@Bean("lblWords")
	public JLabel getWordsLabel()
	{
		if(mLblWords == null)
			{
			mLblWords = new JLabel("Words:");
			
			mLblWords.setFont(getDefaultFont());
			mLblWords.setDisplayedMnemonic(KeyEvent.VK_W);
			mLblWords.setLabelFor(getWordsList());
			mLblWords.setOpaque(false);
			}
		
		return(mLblWords);
	}
	
	@Bean("lstWords")
	public JList<Word> getWordsList()
	{
		if(mLstWords == null)
			{
			mLstWords = new JList<Word>(getWordsListModel());
			
			mLstWords.setBorder(BorderFactory.createLoweredBevelBorder());
			mLstWords.setFont(getDefaultFont());
			
			mLstWords.addListSelectionListener((WordListSelectionListener)(getBean(WordListSelectionListener.class)));
			}
		
		return(mLstWords);
	}
	
	protected WordsListModel getWordsListModel()
	{
		return((WordsListModel)(getBean(WordsListModel.class)));
	}
	
	@Bean("scrWords")
	public JScrollPane getWordsScrollPane()
	{
		if(mScrWords == null)
			{
			mScrWords = new JScrollPane(getWordsList(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			mScrWords.setBorder(BorderFactory.createLoweredBevelBorder());
			mScrWords.setFont(getDefaultFont());
			}
		
		return(mScrWords);
	}
}