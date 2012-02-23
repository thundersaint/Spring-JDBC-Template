/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.datasource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public abstract class AbstractSystemDatasource extends SimpleDriverDataSource{
	
	public AbstractSystemDatasource(Class<? extends Driver> driver) throws FileNotFoundException, IOException {
		init(driver) ;
	}

	private void init(Class<? extends Driver> driver) throws FileNotFoundException, IOException {
    	Properties prop = System.getProperties() ;
	    
	    System.out.println(prop.getProperty("url")) ;
	    System.out.println(prop.getProperty("dbuser")) ;
	    System.out.println(prop.getProperty("dbpassword")) ;

        setUrl(prop.getProperty("url"));
        setUsername(prop.getProperty("dbuser"));
        setPassword(prop.getProperty("dbpassword"));
        
		setDriverClass(driver) ;
	}
}
