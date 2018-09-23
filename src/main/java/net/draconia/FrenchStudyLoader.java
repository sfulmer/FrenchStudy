package net.draconia;

import net.draconia.frenchstudy.BeanConfiguration;
import net.draconia.frenchstudy.processors.Loader;

import org.apache.commons.lang3.StringUtils;

import org.apache.logging.log4j.core.config.Configurator;

import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;

import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class FrenchStudyLoader
{
	private static ApplicationContext mObjApplicationContext;
	
	public FrenchStudyLoader()
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
	
	protected static ApplicationContext getApplicationContext()
	{
		if(mObjApplicationContext == null)
			{
			mObjApplicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
			
			((ConfigurableApplicationContext)(mObjApplicationContext)).registerShutdownHook();
			}
		
		return(mObjApplicationContext);
	}

	public static void main(final String[] sArrArgs)
	{
		Loader objLoader;
		
		configureLogger();
		
		objLoader = ((Loader)(getApplicationContext().getBean(Loader.class)));
		
		for(int iArgLength = sArrArgs.length, iArgLoop = 0; iArgLoop < iArgLength; iArgLoop++)
			{
			String sArg = sArrArgs[iArgLoop];
			
			if(sArg.toLowerCase().startsWith("file="))
				{
				String sFilename = StringUtils.replace(sArg.substring("file=".length()), "\"", "");
				
				objLoader.setFileName(sFilename);
				
				break;
				}
			}
		
		new Thread(objLoader).start();
	}
}