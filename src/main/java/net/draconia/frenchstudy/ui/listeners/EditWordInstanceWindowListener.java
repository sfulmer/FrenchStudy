package net.draconia.frenchstudy.ui.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;

import net.draconia.frenchstudy.exceptions.NoPartOfSpeechBoundException;
import net.draconia.frenchstudy.exceptions.NoWordBoundException;

import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.model.WordInstance;

import net.draconia.frenchstudy.ui.actions.CancelWordInstanceAction;
import net.draconia.frenchstudy.ui.model.EditWordInstanceDialogModel;
import net.draconia.utilities.TextUtilities;

@Component
public class EditWordInstanceWindowListener extends WindowAdapter
{
	@Autowired
	CancelWordInstanceAction mActCancel;
	@Autowired
	private EditWordInstanceDialogModel mObjDialogModel;
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
	private JTextArea mTxtDefinition;
	@Autowired
	@Qualifier("txtWordInstanceWord")
	private JTextField mTxtWord;
	private TextUtilities mObjTextUtilities;
	
	protected Action getCancelAction()
	{
		return(mActCancel);
	}
	
	protected JComboBox<Category> getCategoryField()
	{
		return(mCboCategory);
	}
	
	protected JTextArea getDefinitionField()
	{
		return(mTxtDefinition);
	}
	
	protected EditWordInstanceDialogModel getDialogModel()
	{
		return(mObjDialogModel);
	}
	
	protected JComboBox<PartOfSpeech> getPartOfSpeechField()
	{
		return(mCboPartOfSpeech);
	}
	
	protected JCheckBox getSlangField()
	{
		return(mChkSlang);
	}
	
	protected TextUtilities getTextUtilities()
	{
		if(mObjTextUtilities == null)
			mObjTextUtilities = new TextUtilities();
		
		return(mObjTextUtilities);
	}
	
	protected JTextField getWordField()
	{
		return(mTxtWord);
	}
	
	public void windowActivated(final WindowEvent objWindowEvent)
	{
		WordInstance objEditingModel = getDialogModel().getEditingModel();
		
		try
			{
			getPartOfSpeechField().getModel().setSelectedItem(objEditingModel.getPartOfSpeech());
			}
		catch(NoPartOfSpeechBoundException objException)
			{
			getPartOfSpeechField().setSelectedItem(null);
			}
			
		getCategoryField().setSelectedItem(objEditingModel.getCategory());
		getSlangField().setSelected(objEditingModel.isSlang());
		
		getDefinitionField().setText(objEditingModel.getDefinition());
		getTextUtilities().setupUnRedoableBiDirectionalBoundTextComponent(getDefinitionField(), objEditingModel, "Definition", String.class);
		
		try
			{
			getWordField().setText(objEditingModel.getWord().getEnglish());
			getTextUtilities().setupUnRedoableMenuTextComponent(getWordField());
			}
		catch(NoWordBoundException objException)
			{ }
		
		getPartOfSpeechField().requestFocusInWindow();
	}
	
	public void windowClosing(final WindowEvent objWindowEvent)
	{
		(new JButton(getCancelAction())).doClick(20);
	}
}