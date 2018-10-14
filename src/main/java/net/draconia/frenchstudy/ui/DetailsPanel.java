package net.draconia.frenchstudy.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.annotation.PostConstruct;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;

@Component
public class DetailsPanel extends JPanel
{
	private static final long serialVersionUID = -5634692433905903319L;
	
	@Autowired
	@Qualifier("pnlEnglishWord")
	private JPanel mPnlEnglishWord;
	
	DetailsPanel()
	{
		super(new BorderLayout(5, 5));
	}
	
	protected JPanel getEnglishWordPanel()
	{
		return(mPnlEnglishWord);
	}
	
	@PostConstruct
	protected void initPanel()
	{
		add(getEnglishWordPanel(), BorderLayout.NORTH);
	}
	
	@Autowired
	public void setFont(final Font font)
	{
		super.setFont(font);
	}
}