/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.service;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import com.temp.component.ProductMapper;
import com.temp.entity.Product;
import com.temp.repository.ProductRepository;
import com.temp.util.Emailer;

public class ProductManager {
	private ProductMapper entityMapper = null ;
	private ProductRepository productRepository = null ;
	
	private final String fileName;
	private final String encoding ;

	/** Read the contents of the given file. 
	 * @throws Exception */
	void load() throws Exception {
		Product product = null ;
		int goodRecords = 0 ;
		int badRecords = 0 ;
		
		Date dateBegin = new Date() ;
		Scanner scanner = new Scanner(new FileInputStream(fileName), encoding) ;
		try {
			
			if (!scanner.hasNextLine())
				throw new Exception("Empty file!") ;
			else
				scanner.nextLine() ; // skip headers...

			while (scanner.hasNextLine()) {
				// parse and set...
				product = entityMapper.parse(scanner.nextLine(), new Product()) ;
				if (product == null)
					badRecords++ ;
				else {
					goodRecords++ ;
					
					// Persist to db
					product = productRepository.addRow(product) ;
					//System.out.print("\n --> " + product.toString() +"\n") ;
				}
			}
			
			long time = new Date().getTime() - dateBegin.getTime() ;
			
			// Email status report
			Properties prop = System.getProperties() ;
			
			Emailer emailer = new Emailer() ;
			emailer.send(prop.getProperty("send.to"), "Feed Status", 
					String.format("\n Total Feed : %d \n Failed Feed : %d \n Time in seconds : %.2f", goodRecords, badRecords, time/1000f)) ;
			
		} finally {
			scanner.close();
		}
	}

	/** Constructor. 
	 * @throws Exception */
	ProductManager(String aFileName, String theEncoding) throws Exception {
    	Properties prop = System.getProperties() ;    	        	
	    prop.load(new FileInputStream("./src/config.properties"));
		
		entityMapper = new ProductMapper() ;
		productRepository = new ProductRepository() ;
		
		fileName = aFileName ;
		encoding = theEncoding ;
	}

	/** Requires two arguments - the file name, and the encoding to use. 
	 * @throws Exception */
	public static void main(String... aArgs) throws Exception {
		String fileName = aArgs[0] ;
		String encoding = aArgs[1] ;
		ProductManager test = new ProductManager(fileName, encoding) ;
		test.load() ;
	}
}
