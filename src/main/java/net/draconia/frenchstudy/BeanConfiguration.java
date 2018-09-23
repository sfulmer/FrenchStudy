package net.draconia.frenchstudy;

import java.io.Serializable;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import net.draconia.ApplicationContextProvider;

@Configuration
@ComponentScan("net.draconia")
public class BeanConfiguration implements Serializable
{
	private static final long serialVersionUID = 6385306743058077939L;
	
	private ApplicationContextProvider mObjApplicationContextProvider;
	private DataSource mObjDataSource;
	
	@Bean("applicationContextProvider")
	public ApplicationContextProvider getApplicationContextProvider()
	{
		if(mObjApplicationContextProvider == null)
			mObjApplicationContextProvider = new ApplicationContextProvider();
		
		return(mObjApplicationContextProvider);
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
}