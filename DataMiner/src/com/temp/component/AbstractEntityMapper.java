/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.component;

import java.text.NumberFormat;
import java.text.ParsePosition;

import com.temp.entity.Product;


public abstract class AbstractEntityMapper<T> {
	protected static String RGX_URL = "^[A-Za-z][A-Za-z0-9+.-]{1,120}:[A-Za-z0-9/](([A-Za-z0-9$_.+!*,;/?:@&~=-])|%[A-Fa-f0-9]{2}){1,333}(#([a-zA-Z0-9][a-zA-Z0-9$_.+!*,;/?:@&~=%-]{0,1000}))?$" ;
	protected final String delim = "\t";
	
	abstract boolean validateAndSet(int i, String line, T t) ;
	
	public T parse(String line, T t) {
		
		String data[] = null ;
				
		try {
			data = line.split(delim) ;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null ;
		}
		
		for (int i = 0; i < data.length; i++) {
			if (!validateAndSet(i, data[i].trim(), t))
				return null ;
		}
		
		return t ;
	}

	// Check null or empty
	protected boolean isNotEmpty(String str) {
		return str != null && str.length() > 0 ;
	}
	
	// Check url
	protected boolean isCorrectUrlFormat(String str) {
		return str.matches(RGX_URL) ;
	}
	
	// Check number
	protected boolean isNumber(String str)
	{
		//return str.matches("[+-]?\\d*(\\.\\d+)?") ;
		
		NumberFormat formatter = NumberFormat.getInstance() ;
		ParsePosition pos = new ParsePosition(0) ;
		formatter.parse(str, pos) ;
		return str.length() == pos.getIndex() ;
		
	}
	
	// Check length
	protected boolean isRightLength(String data, int len) {
		return data.length() <= len ;
	}
}
