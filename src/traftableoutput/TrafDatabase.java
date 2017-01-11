// CHECKSTYLE:FileLength:OFF
/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2016 by Pentaho : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package traftableoutput;

import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleDatabaseException;
import org.pentaho.di.core.logging.LoggingObjectInterface;
import org.pentaho.di.core.row.RowMetaInterface;

public class TrafDatabase extends Database {

	public TrafDatabase(TrafTableOutput trafTableOutput, DatabaseMeta databaseMeta) {
		super(trafTableOutput, databaseMeta);
	}

	public TrafDatabase(LoggingObjectInterface loggingobject, DatabaseMeta databaseMeta) {
		super(loggingobject, databaseMeta);
	}

	public String getSQLOutput(String schemaName, String tableName, RowMetaInterface fields, Object[] r,
			String dateFormat) throws KettleDatabaseException {
		String ins = super.getSQLOutput(schemaName, tableName, fields, r, dateFormat);
		return ins.replaceFirst("INSERT INTO", "UPSERT USING LOAD INTO");
	}

	public String getInsertStatement(String schemaName, String tableName, RowMetaInterface fields) {
		String ins = super.getInsertStatement(schemaName, tableName, fields);
		return ins.replaceFirst("INSERT INTO", "UPSERT USING LOAD INTO");
	}
}
