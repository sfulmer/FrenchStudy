package net.draconia.frenchstudy.ui;

import java.util.Arrays;

import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ListPanelButtonPanel extends AbstractButtonPanel
{
	private static final long serialVersionUID = 6787243291536779992L;
	
	public ListPanelButtonPanel(
		final @Qualifier("btnNew") JButton btnNew
	, 	final @Qualifier("btnEdit") JButton btnEdit
	,	final @Qualifier("btnRemove") JButton btnRemove)
	{
		super(Arrays.asList(new JButton[] {btnNew, btnEdit, btnRemove}));
	}
}