/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.amos.project4.views;

import java.beans.*;
import java.util.*;

import javax.swing.table.AbstractTableModel;

/**
 * A table model where each row represents one instance of a Java bean. When the
 * user edits a cell the model is updated.
 * 
 * @author Lennart Schedin
 * 
 * @param <M>
 *            The type of model
 */
@SuppressWarnings("serial")
public class BeanTableModel<M> extends AbstractTableModel {
	private List<M> rows = new ArrayList<M>();
	private List<BeanColumn> columns = new ArrayList<BeanColumn>();
	private Class<?> beanClass;

	public BeanTableModel(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public void addColumn(String columnGUIName, String beanAttribute,
			EditMode editable) {
		try {
			PropertyDescriptor descriptor = new PropertyDescriptor(
					beanAttribute, beanClass);
			columns.add(new BeanColumn(columnGUIName, editable, descriptor));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addColumn(String columnGUIName, String beanAttribute) {
		addColumn(columnGUIName, beanAttribute, EditMode.NON_EDITABLE);
	}

	public void addRow(M row) {
		rows.add(row);
	}

	public void addRows(List<M> rows) {
		for (M row : rows) {
			addRow(row);
		}
	}

	public int getColumnCount() {
		return columns.size();
	}

	public int getRowCount() {
		return rows.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		BeanColumn column = columns.get(columnIndex);
		M row = rows.get(rowIndex);

		Object result = null;
		try {
			result = column.descriptor.getReadMethod().invoke(row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		M row = rows.get(rowIndex);
		BeanColumn column = columns.get(columnIndex);

		try {
			column.descriptor.getWriteMethod().invoke(row, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Class<?> getColumnClass(int columnIndex) {
		BeanColumn column = columns.get(columnIndex);
		Class<?> returnType = column.descriptor.getReadMethod().getReturnType();
		return returnType;
	}

	public String getColumnName(int column) {
		return columns.get(column).columnGUIName;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columns.get(columnIndex).editable == EditMode.EDITABLE;
	}

	public List<M> getRows() {
		return rows;
	}

	public enum EditMode {
		NON_EDITABLE, EDITABLE;
	}

	/** One column in the table */
	private static class BeanColumn {
		private String columnGUIName;
		private EditMode editable;
		private PropertyDescriptor descriptor;

		public BeanColumn(String columnGUIName, EditMode editable,
				PropertyDescriptor descriptor) {
			this.columnGUIName = columnGUIName;
			this.editable = editable;
			this.descriptor = descriptor;
		}
	}
}