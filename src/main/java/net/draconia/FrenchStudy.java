package net.draconia;

import net.draconia.frenchstudy.BeanConfiguration;

import net.draconia.frenchstudy.ui.FrenchStudyMainWindow;

import java.io.Serializable;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.core.config.Configurator;

import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;

import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class FrenchStudy implements Runnable, Serializable
{
	private static final long serialVersionUID = 4590995369435227775L;
	private static final Logger msObjLogger = LoggerFactory.getLogger(FrenchStudy.class);
	
	private static ApplicationContext mObjApplicationContext;
	
	@Autowired
	private FrenchStudyMainWindow mWndMain;
	
	public FrenchStudy()
	{ }
	
	protected static void configureLogger()
	{
		ConfigurationBuilder<BuiltConfiguration> objBuilder = ConfigurationBuilderFactory.newConfigurationBuilder();
		ComponentBuilder<?> objTriggeringPolicies = objBuilder.newComponent("Policies").addComponent(objBuilder.newComponent("CronTriggeringPolicy").addAttribute("schedule", "0 0 0 * * ?")).addComponent(objBuilder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "100M"));
		
		AppenderComponentBuilder objConsole = objBuilder.newAppender("stdout", "Console");
		objBuilder.add(objConsole);
		
		AppenderComponentBuilder objRollingFile = objBuilder.newAppender("rolling", "RollingFile");
		objRollingFile.addAttribute("fileName", "FrenchStudy.log");
		objRollingFile.addAttribute("filePattern", "FrenchStudy-%d{MM-dd-yy}.log.gz");
		objRollingFile.addComponent(objTriggeringPolicies);
		
		objBuilder.add(objRollingFile);
		
		Configurator.initialize(objBuilder.build());
	}
	
	public static ApplicationContext getApplicationContext()
	{
		if(mObjApplicationContext == null)
			{
			mObjApplicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
			
			((ConfigurableApplicationContext)(mObjApplicationContext)).registerShutdownHook();
			}
		
		return(mObjApplicationContext);
	}
	
	protected FrenchStudyMainWindow getMainWindow()
	{
		return(mWndMain);
	}
	
	public void run()
	{
		try
			{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			
			getMainWindow().setVisible(true);
			}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException objException)
			{
			msObjLogger.error("There was a problem displaying the main window...", objException);
			}
	}

	public static void main(final String[] sArrArgs)
	{
		configureLogger();
		
		new Thread(getApplicationContext().getBean(FrenchStudy.class)).start();
	}
}