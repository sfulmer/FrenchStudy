package net.draconia.frenchstudy.ui.listeners;

import java.awt.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.Serializable;
	
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JComboBox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.draconia.frenchstudy.model.WordInstance;

import net.draconia.frenchstudy.ui.model.WordInstancePartOfSpeechComboBoxModel;

import net.draconia.utilities.AncestryUtilities;

public class EditWordInstanceComboBoxListener<T> implements ActionListener, Serializable
{
	private static final long serialVersionUID = -4373491126262289915L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(EditWordInstanceComboBoxListener.class);
	
	private Class<T> mClsDataType;
	private String mSField;
	private WordInstance mObjModel;
	
	public EditWordInstanceComboBoxListener(final WordInstance objModel, final String sField, final Class<T> clsDataType)
	{
		setModel(objModel);
		setField(sField);
		setDataType(clsDataType);
	}
	
	@SuppressWarnings("unchecked")
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		JComboBox<T> cboSource = ((JComboBox<T>)(objActionEvent.getSource()));
		Method funcSetter = null;
		T objSelected = ((T)(cboSource.getSelectedItem()));
		String sFieldName = getField().substring(0, 1).toUpperCase() + getField().substring(1);
		Window wndAncestor = new AncestryUtilities().findWindowAncestorOfComponent(cboSource);
		
		if(objSelected != null)
			try
				{
				if(!(objSelected.getClass().getMethod("get" + sFieldName, new Class<?>[0])).invoke(objSelected, new Object[0]).toString().toUpperCase().startsWith(("Add".toUpperCase())))
					try
						{
						funcSetter = getModel().getClass().getMethod("set" + sFieldName, new Class<?>[] {getDataType()});
						
						if(!funcSetter.canAccess(getModel()))
							funcSetter.setAccessible(true);
						
						funcSetter.invoke(getModel(), new Object[] {objSelected});
						}
					catch(NoSuchMethodException objException)
						{
						msObjLogger.error("Error persisting Selected item to field(" + getField() + ") in model...", objException);
						}
				else
					try
						{
						Object objDialogController = Class.forName("net.draconia.frenchstudy.ui.controllers.Edit" + getField() + "DialogController").getConstructor(new Class<?>[] {Window.class}).newInstance(new Object[] {wndAncestor});
						objDialogController.getClass().getMethod("showDialog", new Class<?>[0]).invoke(objDialogController, new Object[0]);
											
						((WordInstancePartOfSpeechComboBoxModel)(cboSource.getModel())).reloadList();
						}
					catch(ClassNotFoundException | InstantiationException objException)
						{
						msObjLogger.error("There was a problem displaying the Edit Detail Dialog...", objException);
						}
				}
			catch(IllegalAccessException | InvocationTargetException | NoSuchMethodException objException)
				{
				msObjLogger.error("There was a problem getting the selected drop down value for " + sFieldName, objException);
				}
	}
	
	protected Class<T> getDataType()
	{
		return(mClsDataType);
	}
	
	protected String getField()
	{
		return(mSField);
	}
	
	protected WordInstance getModel()
	{
		return(mObjModel);
	}
	
	protected void setDataType(final Class<T> clsDataType)
	{
		mClsDataType = clsDataType;
	}
	
	protected void setField(final String sField)
	{
		mSField = sField;
	}
	
	protected void setModel(final WordInstance objModel)
	{
		mObjModel = objModel;
	}
}