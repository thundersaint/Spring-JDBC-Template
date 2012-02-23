/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.util;

import java.sql.Blob;
import java.sql.SQLException;

public class ExtractorUtil {
	static public String getFromBlob(Blob blob) {
		String result = null ;
		byte[] bdata;
		
		try {
			if (blob != null) {
				bdata = blob.getBytes(1, (int) blob.length());
				result = new String(bdata);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.out);
		}
		return result ;
	}
}
