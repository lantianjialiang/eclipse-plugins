/*
 * Copyright (C) 2004 by Friederich Kupzog Elektronik & Software
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
Authors: 
Friederich Kupzog,  fkmk@kupzog.de, www.kupzog.de/fkmk
Lorenz Maierhofer, lorenz.maierhofer@logicmindguide.com

*/
package de.kupzog.ktable.t4eclipse.editors;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

import de.kupzog.ktable.t4eclipse.KTable;
import de.kupzog.ktable.t4eclipse.KTableCellEditor;

/**
 * A cell editor that expects a Boolean cell value
 * and simply switches this value. It has no control, it
 * just changes the value in the model and forces a cell
 * redraw.<p>
 * NOTE: This implementation makes the whole cell area sensible. 
 * It is activated by a RETURN, a SPACE or a single mouse click.
 * <p>
 * Note: If you need this behavior, but wish to have only a part
 * of the cell area that is sensible (like che checkbox that must
 * be clicked, independently of how big the cell area is), look at
 * KTableCellEditorCheckbox2.
 * 
 * @see de.kupzog.ktable.t4eclipse.editors.KTableCellEditorCheckbox2
 * @see de.kupzog.ktable.cellrenderers.CheckableCellRenderer
 * 
 * @author Lorenz Maierhofer <lorenz.maierhofer@logicmindguide.com>
 */
public class KTableCellEditorCheckbox extends KTableCellEditor {

    /**
	 * Activates the editor at the given position.
	 * Instantly closes the editor and switch the boolean content value.
	 * @param row
	 * @param col
	 * @param rect
	 */
	@Override
	public void open(KTable table, int col, int row, Rectangle rect) {
		m_Table = table;
		m_Model = table.getModel();
		m_Rect = rect;
		m_Row = row;
		m_Col = col;
		
		close(true);
		
		GC gc = new GC(m_Table);
		m_Table.updateCell(m_Col, m_Row);
		gc.dispose();
	}
	
	
	/**
	 * Simply switches the boolean value in the model!
	 */
	@Override
	public void close(boolean save) {
	    if (save) {
	        Object o = m_Model.getContentAt(m_Col, m_Row);
	        if (!(o instanceof Boolean))
	            throw new ClassCastException("CheckboxCellEditor needs a Boolean content!");
	        
	        boolean newVal = !((Boolean)o).booleanValue();
	        
	        m_Model.setContentAt(m_Col, m_Row, new Boolean(newVal));
	    }
	    super.close(save);
	}
    
    /**
     * This editor does not have a control, it only switches 
     * the boolean value on activation!
     * @see de.kupzog.ktable.t4eclipse.KTableCellEditor#createControl()
     */
    @Override
	protected Control createControl() {
		return null;
	}

    /**
     * This implementation does nothing!
     * @see de.kupzog.ktable.t4eclipse.KTableCellEditor#setContent(java.lang.Object)
     */
    @Override
	public void setContent(Object content) {
    }

    /**
	 * @return Returns a value indicating on which actions 
	 * this editor should be activated.
	 */
	@Override
	public int getActivationSignals() {
	    return SINGLECLICK | KEY_RETURN_AND_SPACE;
	}
}
