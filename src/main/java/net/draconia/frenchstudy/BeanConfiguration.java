package net.draconia.frenchstudy;

import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.KeyEvent;

import java.io.Serializable;

import java.util.Properties;

import javax.sql.DataSource;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import net.draconia.ApplicationContextProvider;

import net.draconia.frenchstudy.model.Word;

import net.draconia.frenchstudy.ui.DetailsPanel;
import net.draconia.frenchstudy.ui.ListPanel;

import net.draconia.frenchstudy.ui.actions.EditAction;
import net.draconia.frenchstudy.ui.actions.ExitAction;
import net.draconia.frenchstudy.ui.actions.NewAction;
import net.draconia.frenchstudy.ui.actions.RemoveAction;

import net.draconia.frenchstudy.ui.listeners.WordListSelectionListener;

import net.draconia.frenchstudy.ui.model.WordsListModel;

@Configuration
@ComponentScan("net.draconia")
public class BeanConfiguration implements Serializable
{
	private static final long serialVersionUID = 6385306743058077939L;
	
	private ApplicationContextProvider mObjApplicationContextProvider;
	private DataSource mObjDataSource;
	private Font mFntDefault;
	private JButton mBtnEdit, mBtnNew, mBtnRemove;
	private JLabel mLblEnglishWord, mLblWords;
	private JList<Word> mLstWords;
	private JMenu mMnuFile;
	private JMenuBar mMnuBar;
	private JPanel mPnlEnglishWord;
	private JScrollPane mScrWords;
	private JSplitPane mPnlSplit;
	private JTextField mTxtEnglishWord;
	
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
			mLblEnglishWord.setLabelFor(getEnglishWordField());
			mLblEnglishWord.setOpaque(false);
			}
		
		return(mLblEnglishWord);
	}
	
	@Bean("pnlEnglishWord")
	public JPanel getEnglishWordPanel()
	{
		if(mPnlEnglishWord == null)
			{
			mPnlEnglishWord = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			mPnlEnglishWord.add(getEnglishWordLabel());
			mPnlEnglishWord.add(getEnglishWordField());
			}
		
		return(mPnlEnglishWord);
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