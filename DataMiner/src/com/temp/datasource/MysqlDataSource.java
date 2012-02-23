/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.datasource;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MysqlDataSource extends AbstractSystemDatasource {
	public MysqlDataSource() throws FileNotFoundException, IOException {
		super(com.mysql.jdbc.Driver.class) ;
	}
}
