package net.draconia.frenchstudy.consumers;

import java.awt.event.ActionListener;

import java.util.function.Consumer;

import javax.swing.JComboBox;

public class EditWordInstanceDialogEditingModelPropertyActionListenerRemoverConsumer<T> implements Consumer<ActionListener>
{
	private JComboBox<T> mCboBox;
	
	public EditWordInstanceDialogEditingModelPropertyActionListenerRemoverConsumer(final JComboBox<T> cboBox)
	{
		setComboBox(cboBox);
	}
	
	public void accept(final ActionListener objActionListener)
	{
		getComboBox().removeActionListener(objActionListener);
	}
	
	protected JComboBox<T> getComboBox()
	{
		return(mCboBox);
	}
	
	protected void setComboBox(final JComboBox<T> cboBox)
	{
		mCboBox = cboBox;
	}
}