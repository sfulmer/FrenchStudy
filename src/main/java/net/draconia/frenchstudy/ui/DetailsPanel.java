package net.draconia.frenchstudy.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.annotation.PostConstruct;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class DetailsPanel extends JPanel
{
	private static final long serialVersionUID = -5634692433905903319L;
	
	DetailsPanel()
	{
		super(new BorderLayout(5, 5));
	}
	
	@PostConstruct
	protected void initPanel()
	{ }
	
	@Autowired
	public void setFont(final Font font)
	{
		super.setFont(font);
	}
}