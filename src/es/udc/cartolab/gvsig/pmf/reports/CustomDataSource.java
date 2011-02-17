package es.udc.cartolab.gvsig.pmf.reports;
/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRRuntimeException;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: CustomDataSource.java 3030 2009-08-27 11:12:48Z teodord $
 */
public class CustomDataSource implements JRDataSource
{



	/**
	 *
	 */
	private List<HashMap<String,Object>> records = new ArrayList<HashMap<String,Object>>();
	private Iterator<HashMap<String, Object>> iterator;
	private HashMap<String,Object> currentRecord;


	/**
	 *
	 */
	public CustomDataSource(List<HashMap<String, Object>> records)
	{
		this.records = records;

		/*HashMap<String,Object> map = new HashMap<String,Object>();

		map.put("name", "John McClane");
		map.put("street", "La jungla de cristal");
		map.put("the_city","New York");
		map.put("id", new Integer(1));

		records = new ArrayList<HashMap<String,Object>>();
		records.add(map);*/

		iterator = records.iterator();
	}


	/**
	 *
	 */
	public boolean next()
	{
		boolean hasNext = false;

		if (iterator != null)
		{
			hasNext = iterator.hasNext();

			if (hasNext)
			{
				currentRecord = iterator.next();
			}
		}

		return hasNext;
	}


	/**
	 *
	 */
	public Object getFieldValue(JRField jrField)
	{
		System.out.println("Buscamos " + jrField.getName());
		if (!currentRecord.containsKey(jrField.getName()))
		{
			throw new JRRuntimeException("Field \"" + jrField.getName() + "\" not found in data source.");
		}

		System.out.println("Y tiene el valor " + currentRecord.get(jrField.getName()));

		return currentRecord.get(jrField.getName());
	}


	/**
	 *
	 */
	public void moveFirst()
	{
		iterator = records.iterator();
	}


}
