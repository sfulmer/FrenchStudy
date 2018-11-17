package net.draconia.frenchstudy.ui.listeners.propertychange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.Serializable;
import java.util.Arrays;

import javax.swing.JComboBox;

import net.draconia.frenchstudy.consumers.EditWordInstanceDialogEditingModelPropertyActionListenerRemoverConsumer;
import net.draconia.frenchstudy.model.Category;
import net.draconia.frenchstudy.model.PartOfSpeech;
import net.draconia.frenchstudy.model.WordInstance;

import net.draconia.frenchstudy.ui.listeners.EditWordInstanceComboBoxListener;

import net.draconia.frenchstudy.ui.model.EditWordInstanceDialogModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;

@Component
public class EditWordInstanceDialogEditingModelPropertyChangeListener implements PropertyChangeListener, Serializable
{
	private static final long serialVersionUID = -546433775286185315L;
	
	@Autowired
	@Qualifier("cboWordInstancePartOfSpeech")
	private JComboBox<PartOfSpeech> mCboPartOfSpeech;
	@Autowired
	@Qualifier("cboWordInstanceCategory")
	private JComboBox<Category> mCboCategory;
	
	protected JComboBox<Category> getCategoryField()
	{
		return(mCboCategory);
	}
	
	protected JComboBox<PartOfSpeech> getPartOfSpeechField()
	{
		return(mCboPartOfSpeech);
	}
	
	public void propertyChange(final PropertyChangeEvent objPropertyChangeEvent)
	{
		if(objPropertyChangeEvent.getPropertyName().equalsIgnoreCase("EditingModel"))
			{
			EditWordInstanceDialogModel objModel = ((EditWordInstanceDialogModel)(objPropertyChangeEvent.getSource()));
			WordInstance objEditingModel = objModel.getEditingModel();
			
			getPartOfSpeechField().addActionListener(new EditWordInstanceComboBoxListener<PartOfSpeech>(objEditingModel, "PartOfSpeech", PartOfSpeech.class));
			getCategoryField().addActionListener(new EditWordInstanceComboBoxListener<Category>(objEditingModel, "Category", Category.class));
			}
		else
			{
			Arrays
				.asList(getPartOfSpeechField().getActionListeners())
				.stream()
				.forEach(new EditWordInstanceDialogEditingModelPropertyActionListenerRemoverConsumer<PartOfSpeech>(getPartOfSpeechField()));
			Arrays
				.asList(getCategoryField().getActionListeners())
				.stream()
				.forEach(new EditWordInstanceDialogEditingModelPropertyActionListenerRemoverConsumer<Category>(getCategoryField()));
			}
	}
}