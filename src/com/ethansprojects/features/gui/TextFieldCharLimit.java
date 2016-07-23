package com.ethansprojects.features.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Sets a character limit for GUI text fields. (c'mon Oracle, add this functionality yourself!)
 * @author ethansprojects
 */

public class TextFieldCharLimit extends PlainDocument {
	private static final long serialVersionUID = 1L;
	
	private int limit;
	
	public TextFieldCharLimit(int limitation) {
		this.limit = limitation;
	}
	
	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
		
		if (str == null) {
			return;
		} else if ((getLength() + str.length()) <= limit) {
			//str = str.toLowerCase();
			super.insertString(offset, str, set);
		}
		
	}

}
